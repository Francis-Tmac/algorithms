package com.frank.multihread.unsafe;

import java.lang.reflect.Field;
import sun.misc.Unsafe;
import sun.rmi.runtime.Log;

/**
 * {@link  }
 *
 * @Date 2021/5/2
 * @Author frank
 * @Description:
 */
public class UnSafeDemo {

    public static void main(String[] args) throws Exception{
        Field field = Unsafe.class.getDeclaredField("theUnsafe");
        field.setAccessible(true);
        Unsafe unsafe = (Unsafe)field.get(null);
        System.out.println(unsafe);

        User user = (User)unsafe.allocateInstance(User.class);
        Class userClass = user.getClass();
        Field name = userClass.getDeclaredField("name");
        Field age = userClass.getDeclaredField("age");
        Field id = userClass.getDeclaredField("id");

        unsafe.putInt(user,unsafe.objectFieldOffset(age),18);
        unsafe.putObject(user, unsafe.objectFieldOffset(name),"android TV");

        Object staticBase = unsafe.staticFieldBase(id);
        System.out.println("staticBase:" + staticBase);

        long staticOffset = unsafe.staticFieldOffset(userClass.getDeclaredField("id"));

        System.out.println("设置前的ID：" + unsafe.getObject(staticBase, staticOffset));


        unsafe.putObject(staticBase, staticOffset, "SSSSSSS");

        System.out.println("设置后的ID：" + unsafe.getObject(staticBase, staticOffset));

        System.out.println("输出USER: " + user.toString());

        long data = 1000;
        byte size = 1;

        long memoryAddress = unsafe.allocateMemory(size);

        unsafe.putAddress(memoryAddress, data);
        long addData = unsafe.getAddress(memoryAddress);

        System.out.println("addressData: " + addData);
    }
}
