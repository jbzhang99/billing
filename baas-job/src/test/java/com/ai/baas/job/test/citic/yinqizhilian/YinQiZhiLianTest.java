package com.ai.baas.job.test.citic.yinqizhilian;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ai.opt.sdk.dubbo.util.HttpClientUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "/context/job-context.xml" })
public class YinQiZhiLianTest {
    private static final Logger LOGGER = LogManager.getLogger(YinQiZhiLianTest.class);

    /**
     * 3.15.3 支付联行信息查询
     * 
     * @author mayt
     * @throws Exception
     * @throws
     * @ApiDocMethod
     */
    @Test
    public void DLBNKCOD() throws Exception {
        // eg: http://IP:PORT/DLink/DLServlet
        // eg: <?xml version="1.0"
        // encoding="GBK"?><stream><action>DLBNKCOD</action><userName>lfhzl</userName><tgfi>102374500343</tgfi></stream>
        String url = "http://10.248.4.24:6799/DLink/DLServlet";
        Document document = DocumentHelper.createDocument();
        document.setXMLEncoding("GBK");
        Element streamElement = document.addElement("stream");
        Element actionElement = streamElement.addElement("action");
        actionElement.addText("DLBNKCOD");
        Element userNameElement = streamElement.addElement("userName");
        userNameElement.addText("zhilianceshi1");
        Element tgfiElement = streamElement.addElement("tgfi");
        tgfiElement.addText("102374500343");

        String body = document.asXML();
        LOGGER.info(body);
        Map<String, String> header = new HashMap<String, String>();
        header.put("Content-Type", "text/xml");
        header.put("charset", "GBK");

        String result = HttpClientUtil.sendPost(url, body, header);
        LOGGER.info(result);
    }

    /**
     * 3.5.7 附属账户交易明细查询
     * 
     * @author mayt
     * @throws Exception
     * @throws
     * @ApiDocMethod
     */
    @Test
    public void DLSUBDTL() throws Exception {
        String url = "http://127.0.0.1:6789/DLink/DLServlet";
        Document document = DocumentHelper.createDocument();
        document.setXMLEncoding("GBK");
        Element streamElement = document.addElement("stream");
        Element actionElement = streamElement.addElement("action");
        actionElement.addText("DLSUBDTL");
        Element userNameElement = streamElement.addElement("userName");
        userNameElement.addText("zhilianceshi1");
        Element subAccNoElement = streamElement.addElement("subAccNo");
        subAccNoElement.addText("3110710008921019591");
        Element startDateElement = streamElement.addElement("startDate");
        startDateElement.addText("20160901");
        Element endDateElement = streamElement.addElement("endDate");
        endDateElement.addText("20160914");

        String body = document.asXML();
        LOGGER.info(body);
        Map<String, String> header = new HashMap<String, String>();
        header.put("Content-Type", "text/xml");
        header.put("charset", "GBK");

        String result = HttpClientUtil.sendPost(url, body, header);
        LOGGER.info(result);
    }

    /**
     * 3.5.6 附属账户余额查询
     * 
     * @author mayt
     * @throws Exception
     * @throws
     * @ApiDocMethod
     */
    @Test
    public void DLSUBBAL() throws Exception {
        String url = "http://10.248.4.24:6799/DLink/DLServlet";
        Document document = DocumentHelper.createDocument();
        document.setXMLEncoding("GBK");
        Element streamElement = document.addElement("stream");
        Element actionElement = streamElement.addElement("action");
        actionElement.addText("DLSUBBAL");
        Element userNameElement = streamElement.addElement("userName");
        userNameElement.addText("zhilianceshi1");
        Element subAccNoElement = streamElement.addElement("subAccNo");
        subAccNoElement.addText("3110710008921019589");

        String body = document.asXML();
        LOGGER.info(body);
        Map<String, String> header = new HashMap<String, String>();
        header.put("Content-Type", "text/xml");
        header.put("charset", "GBK");

        HttpClientUtil.sendPost(url, body, header);
    }
    /**
     * 3.5.2 附属账户内部转账
     * @author mayt
     * @throws Exception 
     * @throws  
     * @ApiDocMethod
     */
    @Test
    public void DLSINSUB() throws Exception{
        String url = "http://10.248.4.24:6799/DLink/DLServlet";
        Document document = DocumentHelper.createDocument();
        document.setXMLEncoding("GBK");
        Element streamElement = document.addElement("stream");
        Element actionElement = streamElement.addElement("action");
        actionElement.addText("DLSINSUB");
        Element userNameElement = streamElement.addElement("userName");
        userNameElement.addText("zhilianceshi1");
        Element clientIDElement = streamElement.addElement("clientID");
        clientIDElement.addText("201609141");
        Element mainAccNoElement = streamElement.addElement("mainAccNo");
        mainAccNoElement.addText("8110701013700014275");
        Element payAccNoElement = streamElement.addElement("payAccNo");
        payAccNoElement.addText("3110710008921019589");
        Element recvAccNoElement = streamElement.addElement("recvAccNo");
        recvAccNoElement.addText("3110710008921019591");
        Element recvAccNmElement = streamElement.addElement("recvAccNm");
        recvAccNmElement.addText("投融资1");
        Element tranAmtElement = streamElement.addElement("tranAmt");
        tranAmtElement.addText("12.34");
        Element preFlgElement = streamElement.addElement("preFlg");
        preFlgElement.addText("0");
        Element abstractElement = streamElement.addElement("abstract");
        abstractElement.addText("摘要");

        String body = document.asXML();
        LOGGER.info(body);
        Map<String, String> header = new HashMap<String, String>();
        header.put("Content-Type", "text/xml");
        header.put("charset", "GBK");

        HttpClientUtil.sendPost(url, body, header);
    }
}
