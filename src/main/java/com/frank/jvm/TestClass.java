package com.frank.jvm;
// 到本地生成class 文件把 package 去掉
// https://www.cnblogs.com/mq0036/p/8566427.html

/***
 * 1. javac TestClass.java 生成class 文件
 * 2. jar -cvfm test-1.0.jar MANIFEST.MF SoutUtil.class TestClass.class 生成jar 包
 * 3. java -jar test-1.0.jar 运行jar包
 *
 */


public class TestClass {

    public String hello(){
        return SoutUtil.sout();
    }

    public static void main(String[] args) {
        TestClass testClass = new TestClass();
        System.out.println(testClass.hello());
    }
}
