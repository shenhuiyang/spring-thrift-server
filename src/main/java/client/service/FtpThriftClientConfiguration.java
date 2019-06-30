package client.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FtpThriftClientConfiguration {
    @Value("${thrift-client.host}")
    private String host;
    @Value("${thrift-client.port:8090}")
    private int port;

    @Bean(initMethod = "init")
    public FtpThriftClient getFtpThriftClient() {
        FtpThriftClient ftpThriftClient = new FtpThriftClient();
        ftpThriftClient.setHost(host);
        ftpThriftClient.setPort(port);
        return ftpThriftClient;
    }

}
