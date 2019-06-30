package client.service;

import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransportException;
import thriftpath.thrift.FTPServer;

public class FtpThriftClient {
    private FTPServer.Client ftpclient;
    private TBinaryProtocol protocol;
    private TSocket transport;
    private String host;
    private int port;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public FTPServer.Client getFtpclient() {
        return ftpclient;
    }

    public void init() {
        transport = new TSocket(host, port);
        protocol = new TBinaryProtocol(transport);
        ftpclient = new FTPServer.Client(protocol);
    }

    public void open() throws TTransportException {
        transport.open();
    }

    public void close() {
        transport.close();
    }

}
