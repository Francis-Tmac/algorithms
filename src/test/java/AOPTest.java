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

    @Test
    public void test_2(){
        String a = "23412hsfhsgjyekeasdfasdfjkghjkgdhfgshrthrthfghdfghsewtwecwe";
        String b = "23412hsfhsgjyekeasdfasdfjkghjkgdhfgshrthrthfghdfghsewtwecwe";
        String c = new String("23412hsfhsgjyekeasdfasdfjkghjkgdhfgshrthrthfghdfghsewtwecwe");
        String d = new String("23412hsfhsgjyekeasdfasdfjkghjkgdhfgshrthrthfghdfghsewtwecwe");
        System.out.println(System.identityHashCode(a));
        System.out.println(System.identityHashCode(b));
        System.out.println(System.identityHashCode(c));
        System.out.println(System.identityHashCode(d));
    }

    @Test
    public void test_3(){
//        String field = "test";
//        String methodName = "get" + ;
//        System.out.println(methodName);
    }

    public String upperCase(String str) {
        char[] ch = str.toCharArray();
        if (ch[0] >= 'a' && ch[0] <= 'z') {
            ch[0] = (char) (ch[0] - 32);
        }
        return new String(ch);
    }
}
