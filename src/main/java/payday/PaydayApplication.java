package payday;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;
import payday.employee.add.AddSalariedEmployee;

/**
 * @author myeongju.jung
 */
@SpringBootApplication
@EnableSpringConfigured
public class PaydayApplication {
    public static void main(String[] args) {
        SpringApplication.run(PaydayApplication.class);
    }

    @Bean
    @Scope("PROTOTYPE")
    public AddSalariedEmployee addSalariedEmployee() {
        return new AddSalariedEmployee();
    }
}
