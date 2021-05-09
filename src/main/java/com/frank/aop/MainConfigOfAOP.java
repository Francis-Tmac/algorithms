package com.frank.aop;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * {@link  }
 *
 * @Date 2021/5/9
 * @Author frank
 * @Description:
 * 1. 定义一个日志切面类（LogAspects），切面类里面的方法需要动态感知MathCalculator.div 运行到哪里
 *  通知方法：
 *  前置通知(@Before): logStart
 *  后置通知(@After): logEnd(无论方法正常或异常结束)
 *  返回通知(@AfterReturning): logReturn
 *  异常通知(@AfterThrowing): logException
 *  环绕通知(@Around): 动态代理，手动推进目标方法运行(joinPoint.proceed())
 *  2. 给切面类的目标方法标注何时何地运行，通知注解。
 *  3. 将切面和业务逻辑类（切点所在类）都加入到容器中。
 *  4. 必须告诉spring 那个是切面类（给切面类加一个注解 @Aspect）
 *  5. 给配置类中加 @EnableAspectJAutoProxy 开启基于注解的aop 代理
 */

@EnableAspectJAutoProxy
@Configuration
public class MainConfigOfAOP {

    @Bean
    public MathCalculator calculator(){
        return new MathCalculator();
    }

    @Bean
    public LogAspects logAspects(){
        return new LogAspects();
    }

}
