package client;

import client.service.FtpThriftClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class ClientApplication {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(ClientApplication.class, args);

        try {
            System.out.println("start client!!!");
            FtpThriftClient client = context.getBean(FtpThriftClient.class);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
