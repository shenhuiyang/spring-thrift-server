package server;

import controller.FtpController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
import org.apache.thrift.transport.TTransportFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import thriftpath.thrift.FTPServer;

@Component
public class FtpThriftServer {
    protected final Logger logger = LogManager.getLogger(FtpController.class.getName());

    @Value("${thrift.port}");
    private int port;
    @Value("${thrift.minWorkerThreads}");
    private int minWorkerTheads;
    @Value("${thrift.maxWorkerThreads}");
    private int maxWorkerTheads;

    private TBinaryProtocol.Factory protocolFactory;
    private TTransportFactory transportFactory;

    @Autowired
    private FtpController ftpController;

    public void init() {
        protocolFactory = new TBinaryProtocol.Factory();
        transportFactory = new TTransportFactory();
    }

    public void startServer() {
        FTPServer.Processor processor = new FTPServer.Processor<FTPServer.Iface>(ftpController);
        init();

        try {
            TServerTransport transport = new TServerSocket(port);
            TThreadPoolServer.Args args = new TThreadPoolServer.Args(transport);
            args.processor(processor);
            args.protocolFactory(protocolFactory);
            args.transportFactory(transportFactory);
            args.minWorkerThreads(minWorkerTheads);
            args.maxWorkerThreads(maxWorkerTheads);
            TServer server = new TThreadPoolServer(args);

            logger.info("thrift start succ, port = {}", port);
            server.serve();
        } catch (Exception e) {
            logger.error("thrift start fail, err: {}", e);
            System.exit(1);
        }
    }

}
