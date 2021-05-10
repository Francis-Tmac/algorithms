import com.frank.aop.MainConfigOfAOP;
import com.frank.aop.MathCalculator;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * {@link  }
 *
 * @Date 2021/5/9
 * @Author frank
 * @Description:
 *
 * 1. 传入配置类，创建IOC 容器
 * 2. 注册配置类，调用refresh() 刷新容器
 * 3. registerBeanPostProcessors(beanFactory); 注册bean 的后置处理器拦截bean的创建
 *
 */
public class AOPTest {
    @Test
    public void test_1(){
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(
                MainConfigOfAOP.class);
        MathCalculator mathCalculator = applicationContext.getBean(MathCalculator.class);
        mathCalculator.div(4,2);
    }
}
