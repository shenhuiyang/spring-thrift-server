package springapp.server;

import springapp.controller.FtpController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
import org.apache.thrift.transport.TTransportFactory;
import thriftpath.thrift.FTPServer;

import java.util.concurrent.Executors;

public class FtpThriftServer {
    protected final Logger logger = LogManager.getLogger(FtpController.class.getName());

    private int port;
    private int minWorkerTheads;
    private int maxWorkerTheads;
    private FtpController ftpController;

    public FtpThriftServer(int port, int minWorkerTheads, int maxWorkerTheads, FtpController ftpController) {
        this.port = port;
        this.minWorkerTheads = minWorkerTheads;
        this.maxWorkerTheads = maxWorkerTheads;
        this.ftpController = ftpController;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getMinWorkerTheads() {
        return minWorkerTheads;
    }

    public void setMinWorkerTheads(int minWorkerTheads) {
        this.minWorkerTheads = minWorkerTheads;
    }

    public int getMaxWorkerTheads() {
        return maxWorkerTheads;
    }

    public void setMaxWorkerTheads(int maxWorkerTheads) {
        this.maxWorkerTheads = maxWorkerTheads;
    }

    private TBinaryProtocol.Factory protocolFactory;
    private TTransportFactory transportFactory;

    public void init() {
        protocolFactory = new TBinaryProtocol.Factory();
        transportFactory = new TTransportFactory();
    }

    public void startServer() {
        FTPServer.Processor<FtpController> processor = new FTPServer.Processor<>(ftpController);
        init();

        try {
            TServerTransport transport = new TServerSocket(port);
            TThreadPoolServer.Args args = new TThreadPoolServer.Args(transport);
            args.processor(processor);
            args.protocolFactory(protocolFactory);
            args.transportFactory(transportFactory);
            args.minWorkerThreads(minWorkerTheads);
            args.maxWorkerThreads(maxWorkerTheads);
            args.executorService = Executors.newCachedThreadPool();
            TServer server = new TThreadPoolServer(args);

            logger.info("thriftpath.thrift start succ, port = {}", port);
            server.serve();
        } catch (Exception e) {
            logger.error("thriftpath.thrift start fail, err: {}", e);
            System.exit(1);
        }
    }

}
