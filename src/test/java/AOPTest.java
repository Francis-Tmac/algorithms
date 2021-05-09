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
