package com.frank;

import java.util.ArrayList;
import java.util.List;

/**
 * {@link  }
 *
 * @Date 2021/5/16
 * @Author frank
 * @Description:
 */
public class Generic<T> {

    // 编译错误泛型类中今天太方法和静态变量不可以
    // 使用泛型类所申明的泛型类型参数
    // 泛型类中的泛型参数的实例化是在定义泛型对象的时候指定的。
    // 静态变量不需要使用对象来调用。对象没有创建，如何确定泛型参数是何种类型
 /*   static T item;

    static T show(T one){
        return null;
    }*/


    // 泛型方法，在泛型方法中使用的T是自己再方法中定义的T ,而不是泛型类中定义的T
    public static<T> T show(T one){
        return null;
    }

    public static void fillNumberList(List<? extends Number> list) {
//        list.add(new Integer(0));//编译错误
//        list.add(new Integer(3));//编译错误

         }
}
