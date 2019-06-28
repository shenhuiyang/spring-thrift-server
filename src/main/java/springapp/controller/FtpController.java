package springapp.controller;

import org.apache.logging.log4j.LogManager;
import org.springframework.stereotype.Component;
import thriftpath.thrift.CatMode;
import thriftpath.thrift.FTPServer;
import thriftpath.thrift.FileStruct;

import java.nio.ByteBuffer;
import java.util.List;

import org.apache.logging.log4j.Logger;

@Component
public class FtpController implements FTPServer.Iface {
    protected final Logger logger = LogManager.getLogger(FtpController.class.getName());

    @Override
    public List<FileStruct> ls(String path) throws org.apache.thrift.TException {
        return null;
    }

    @Override
    public String cat(FileStruct file, CatMode mode) throws org.apache.thrift.TException {
        return "cat func";
    }

    @Override
    public boolean upload(String name, ByteBuffer data) throws org.apache.thrift.TException {
        return false;
    }

    @Override
    public ByteBuffer downlaod(String path) throws org.apache.thrift.TException {
        return null;
    }
}
