import com.cy.aop.DoSomethingService;
import com.cy.aop.TimeProxy;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;

/**
 * Created by zxj on 2017/4/26.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("classpath:spring-application-aop.xml")
public class AopTest {

    @Autowired
    private DoSomethingService doSomethingService;

    @Test
    public void test() {
        doSomethingService.doSomething("a");
    }

}
