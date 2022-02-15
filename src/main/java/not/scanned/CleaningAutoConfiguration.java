package not.scanned;

import com.fasterxml.jackson.databind.ObjectMapper;
import not.scanned.deeper.MyClass;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

@Configuration
@ComponentScan(basePackageClasses = MyClass.class)
@ConditionalOnBean(ObjectMapper.class)
@AutoConfigureOrder(Ordered.LOWEST_PRECEDENCE)
public class CleaningAutoConfiguration {

    @Bean
    public Fake fake(){
        return new Fake();
    }

    private static class Fake{

    }

}
