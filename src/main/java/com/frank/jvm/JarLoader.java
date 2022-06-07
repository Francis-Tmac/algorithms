package com.frank.jvm;


import org.springframework.util.CollectionUtils;

import java.io.*;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * @author chiangtaol
 * @date 2021-10-19
 * @describe
 */

/**
 * 提供Jar隔离的加载机制，会把传入的路径、及其子路径、以及路径中的jar文件加入到class path。
 * 破坏双亲委派机制，改为逆向
 */
public class JarLoader extends URLClassLoader {

    private static final ThreadLocal<URL[]> threadLocal = new ThreadLocal<>();

    private final URL[] allUrl;

    public JarLoader(String[] paths) {
        this(paths, JarLoader.class.getClassLoader());
    }

    public JarLoader(String[] paths, ClassLoader parent) {
        super(getURLs(paths), parent);
        //暂时先这样
        allUrl = threadLocal.get();
        threadLocal.remove();
    }

    /**
     * 破坏双亲委派模型,采用逆向双亲委派
     */
    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        if (allUrl != null) {
            String classPath = name.replace(".", "/");
            classPath = classPath.concat(".class");
            for (URL url : allUrl) {

                byte[] data = null;
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                InputStream is = null;
                try {
                    File file = new File(url.toURI());
                    if (file.exists()) {
                        JarFile jarFile = new JarFile(file);
                        JarEntry jarEntry = jarFile.getJarEntry(classPath);
                        if (jarEntry != null) {
                            is = jarFile.getInputStream(jarEntry);
                            int c = 0;
                            while (-1 != (c = is.read())) {
                                baos.write(c);
                            }
                            data = baos.toByteArray();
                            return this.defineClass(name, data, 0, data.length);

                        }

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (is != null) {
                            is.close();
                        }
                        baos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        }
        return super.loadClass(name);
    }

    /**
     * 获取所有路径下所有jar 包
     *
     * @param paths
     * @return
     */
    private static URL[] getURLs(String[] paths) {
        if (null == paths || 0 == paths.length) {
            throw new RuntimeException("jar包路径不能为空.");
        }

        List<String> dirs = new ArrayList<String>();
        for (String path : paths) {
            dirs.add(path);
            // 将包路径下的子路径添加到 dirs 中，
            JarLoader.collectDirs(path, dirs);
        }

        List<URL> urls = new ArrayList<URL>();
        for (String path : dirs) {
            urls.addAll(doGetURLs(path));
        }
        if(CollectionUtils.isEmpty(urls)){
            throw new RuntimeException("未找到任何jar 包");
        }
        URL[] urls1 = urls.toArray(new URL[0]);
        threadLocal.set(urls1);
        return urls1;
    }

    /**
     * 获取所有文件路径，包括子路径
     *
     * @param path
     * @param collector
     */
    private static void collectDirs(String path, List<String> collector) {
        if (null == path || "".equalsIgnoreCase(path)) {
            return;
        }

        File current = new File(path);
        if (!current.exists() || !current.isDirectory()) {
            return;
        }
        File[] tempFilesList = current.listFiles();

        if (tempFilesList != null) {
            for (File child : tempFilesList) {
                if (!child.isDirectory()) {
                    continue;
                }

                collector.add(child.getAbsolutePath());
                collectDirs(child.getAbsolutePath(), collector);
            }
        }
    }

    /**
     * 获取所有jar 包的URL
     *
     * @param path
     * @return
     */
    private static List<URL> doGetURLs(final String path) {
        if (null == path || "".equalsIgnoreCase(path)) {
            throw new RuntimeException("jar包路径不能为空.");
        }
        File jarPath = new File(path);

        if (!jarPath.exists() || !jarPath.isDirectory()) {
            throw new RuntimeException("jar包路径必须存在且为目录.");
        }

        /* set filter */
        // FileFilter jarFilter = pathname -> pathname.getName().endsWith(".jar");
        FileFilter jarFilter = new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.getName().endsWith(".jar");
            }
        };

        /* iterate all jar */
        File[] allJars = new File(path).listFiles(jarFilter);
        if(allJars == null){
            return Collections.emptyList();
        }
        List<URL> jarUrls = new ArrayList<>(allJars.length);

        for (File allJar : allJars) {
            try {
                jarUrls.add(allJar.toURI().toURL());
            } catch (Exception e) {
                throw new RuntimeException("系统加载jar包出错", e);
            }
        }
        return jarUrls;
    }

}

