package payday;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author myeongju.jung
 */
@SpringBootApplication
@EnableSpringConfigured
@EnableTransactionManagement(mode = AdviceMode.ASPECTJ)
public class PaydayApplication {
    public static void main(String[] args) {
        SpringApplication.run(PaydayApplication.class);
    }
}
