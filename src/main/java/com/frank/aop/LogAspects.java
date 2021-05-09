package com.frank.aop;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/**
 * {@link  }
 *
 * @Date 2021/5/9
 * @Author frank
 * @Description:
 * 界面类
 */
@Aspect
public class LogAspects {

    @Pointcut("execution(public int com.frank.aop.MathCalculator.*(..))")
    public void pointCut(){}

    @Before("pointCut()")
    public void logStart(){
        System.out.println("目标方法开始运行。。。参数列表：");
    }

    // 表示MathCalculator 类下的所有方法
    @After("pointCut()")
    public void logEnd(){
        System.out.println("目标方法运行结束");
    }

    public void logReturn(){

    }
}
