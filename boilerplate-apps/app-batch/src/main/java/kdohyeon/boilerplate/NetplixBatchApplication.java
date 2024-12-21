package kdohyeon.boilerplate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class NetplixBatchApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(NetplixBatchApplication.class, args);
        SpringApplication.exit(run);
    }
}
