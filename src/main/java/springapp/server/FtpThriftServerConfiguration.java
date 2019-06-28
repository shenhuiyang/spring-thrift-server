package springapp.server;

import springapp.controller.FtpController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FtpThriftServerConfiguration {

    @Value("${thrift-server.port:8090}")
    private int port;

    @Value("${thrift-server.minWorkerTheads}")
    private int minWorkerTheads;

    @Value("${thrift-server.maxWorkerTheads}")
    private int maxWorkerTheads;

    @Autowired
    FtpController ftpController;

    @Bean
    public FtpThriftServer getFtpThriftServer() {
        FtpThriftServer server = new FtpThriftServer(port, minWorkerTheads, maxWorkerTheads, ftpController);
        return server;
    }


}
