package client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FtpThriftClientConfiguration {
    @Value("${thrift-client.port:8090}")
    

}
