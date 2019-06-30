package client.controller;

import client.service.FtpThriftClient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.thrift.TException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import springapp.controller.FtpController;
import thriftpath.thrift.CatMode;
import thriftpath.thrift.FileStruct;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/spring-req")
public class FtpThriftClientController {
    protected final Logger logger = LogManager.getLogger(FtpController.class.getName());

    @Autowired
    private FtpThriftClient ftpThriftClient;

    @RequestMapping(value = "/cat", method = RequestMethod.GET)
    public String catContents(HttpServletRequest request, HttpServletResponse response) {
        try {
            logger.info("cat func");
            String path = request.getParameter("path");
            FileStruct fileStruct = new FileStruct();
            fileStruct.path = path;
            ftpThriftClient.open();
            String content = ftpThriftClient.getFtpclient().cat(fileStruct, CatMode.TXT);

            return content;
        } catch (TException e) {
            e.printStackTrace();
            return null;
        } finally {
            ftpThriftClient.close();
        }
    }
}
