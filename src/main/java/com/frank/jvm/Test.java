package com.frank.jvm;

import java.lang.reflect.Method;

/**
 * @author fukangyang
 * @date 2022/6/7
 * @ desc
 */

public class Test {

    public static void main(String[] args) throws Exception {
        String jar1 = "/Users/frank/Notes/algorithms/src/main/resources/jar1.0"; //自己定义的测试jar包，不同版本打印内容不同
        String jar2 = "/Users/frank/Notes/algorithms/src/main/resources/jar2.0";

        JarLoader jarLoader = new JarLoader(new String[]{jar1});
        ClassLoaderSwapper classLoaderSwapper = ClassLoaderSwapper.newCurrentThreadClassLoaderSwapper();
        classLoaderSwapper.setCurrentThreadClassLoader(jarLoader);
        Class<?> aClass = Thread.currentThread().getContextClassLoader().loadClass("TestClass");
        classLoaderSwapper.restoreCurrentThreadClassLoader();
        Object o = aClass.newInstance();
        Method isEmptyMethod = aClass.getDeclaredMethod("hello");
        Object invoke = isEmptyMethod.invoke(o);
        System.out.println(invoke);


        JarLoader jarLoader2 = new JarLoader(new String[]{jar2});
        ClassLoaderSwapper classLoaderSwapper2 = ClassLoaderSwapper.newCurrentThreadClassLoaderSwapper();
        classLoaderSwapper2.setCurrentThreadClassLoader(jarLoader2);
        Class<?> aClass2 = Thread.currentThread().getContextClassLoader().loadClass("TestClass");
        classLoaderSwapper.restoreCurrentThreadClassLoader();
        Object o2 = aClass2.newInstance();
        Method isEmptyMethod2 = aClass2.getDeclaredMethod("hello");
        Object invoke2 = isEmptyMethod2.invoke(o2);
        System.out.println(invoke2);

    }
}