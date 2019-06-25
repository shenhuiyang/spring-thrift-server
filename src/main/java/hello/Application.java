package hello;

import Server.FtpThriftServer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
public class Application {
    private static FtpThriftServer ftpThriftServer;

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(Application.class, args);
        try {
            ftpThriftServer = context.getBean(ftpThriftServer.getClass());
            ftpThriftServer.startServer();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {
            System.out.println("inspect the beans provided by Spring Boot");
            String[] beanNames = ctx.getBeanDefinitionNames();
            Arrays.sort(beanNames);
            for (String beanName : beanNames) {
                System.out.println(beanName);
            }
        };
    }
}
