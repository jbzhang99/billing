/**
 * Copyright 2005 Jasper Systems, Inc. All rights reserved.
 *
 * This software code is the confidential and proprietary information of
 * Jasper Systems, Inc. ("Confidential Information"). Any unauthorized
 * review, use, copy, disclosure or distribution of such Confidential
 * Information is strictly prohibited.
 */
package com.ai.runner.center.ctp.rtm.core.service;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.xml.soap.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import com.ai.runner.center.ctp.rtm.core.service.model.GetTerminalUsageDataDetail;
import com.ai.runner.center.ctp.rtm.core.service.model.GetTerminalUsageDataDetailsResponse;
import com.ai.runner.center.ctp.rtm.core.util.ApiClientConstant;
import com.ai.runner.utils.util.StringUtil;
import com.sun.xml.wss.ProcessingContext;
import com.sun.xml.wss.XWSSProcessor;
import com.sun.xml.wss.XWSSProcessorFactory;
import com.sun.xml.wss.XWSSecurityException;
import com.sun.xml.wss.impl.callback.PasswordCallback;
import com.sun.xml.wss.impl.callback.UsernameCallback;

/**
 * @author Sunil Sheshadri
 * @version $Id: //depot/jasper_release/module/ProvisionApp/web/secure/apidoc/java/com/jasperwireless/ws/client/sample/GetTerminalDetailsClient.java#3 $
 */
public class GetTerminalUsageDataDetailsClient implements ApiClientConstant {
	
	private static Logger logger = LoggerFactory.getLogger(GetTerminalUsageDataDetailsClient.class);
    private SOAPConnectionFactory connectionFactory;
    private MessageFactory messageFactory;
    private URL url;
    private String licenseKey;
    private XWSSProcessorFactory processorFactory;
    
    private String messageId;
    private String version;
    private String iccid;
    private String cycleStartDate;
    private String pageNumber;

    /**
     * Constructor which initializes Soap Connection, messagefactory and ProcessorFactory
     *
     * @param url
     * @throws SOAPException
     * @throws MalformedURLException
     * @throws XWSSecurityException
     */
    public GetTerminalUsageDataDetailsClient(String url, String licenseKey)
            throws SOAPException, MalformedURLException, XWSSecurityException {
        connectionFactory = SOAPConnectionFactory.newInstance();
        messageFactory = MessageFactory.newInstance();
        processorFactory = XWSSProcessorFactory.newInstance();
        this.url = new URL(url);
        this.licenseKey = licenseKey;
    }
    
    

    public GetTerminalUsageDataDetailsClient(String url, String licenseKey, String messageId,
			String version, String iccid, String cycleStartDate,
			String pageNumber)throws SOAPException, MalformedURLException, XWSSecurityException  {
		super();
		connectionFactory = SOAPConnectionFactory.newInstance();
        messageFactory = MessageFactory.newInstance();
        processorFactory = XWSSProcessorFactory.newInstance();
		this.licenseKey = licenseKey;
		this.messageId = messageId;
		this.version = version;
		this.iccid = iccid;
		this.cycleStartDate = cycleStartDate;
		this.pageNumber = pageNumber;
        this.url = new URL(url);
        this.licenseKey = licenseKey;
	}
    
    public GetTerminalUsageDataDetailsClient(String url, String licenseKey, String messageId,
			String version, String iccid, String cycleStartDate)throws SOAPException, MalformedURLException, XWSSecurityException  {
		super();
		connectionFactory = SOAPConnectionFactory.newInstance();
        messageFactory = MessageFactory.newInstance();
        processorFactory = XWSSProcessorFactory.newInstance();
		this.licenseKey = licenseKey;
		this.messageId = messageId;
		this.version = version;
		this.iccid = iccid;
		this.cycleStartDate = cycleStartDate;
        this.url = new URL(url);
        this.licenseKey = licenseKey;
	}
    
    public GetTerminalUsageDataDetailsClient(String url, String licenseKey, String messageId,
			String version)throws SOAPException, MalformedURLException, XWSSecurityException  {
		super();
		connectionFactory = SOAPConnectionFactory.newInstance();
        messageFactory = MessageFactory.newInstance();
        processorFactory = XWSSProcessorFactory.newInstance();
		this.licenseKey = licenseKey;
		this.messageId = messageId;
		this.version = version;
        this.url = new URL(url);
        this.licenseKey = licenseKey;
	}
    
    



	public MessageFactory getMessageFactory() {
		return messageFactory;
	}



	public void setMessageFactory(MessageFactory messageFactory) {
		this.messageFactory = messageFactory;
	}



	public URL getUrl() {
		return url;
	}



	public void setUrl(URL url) {
		this.url = url;
	}



	public String getLicenseKey() {
		return licenseKey;
	}



	public void setLicenseKey(String licenseKey) {
		this.licenseKey = licenseKey;
	}



	public String getMessageId() {
		return messageId;
	}



	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}



	public String getVersion() {
		return version;
	}



	public void setVersion(String version) {
		this.version = version;
	}



	public String getIccid() {
		return iccid;
	}



	public void setIccid(String iccid) {
		this.iccid = iccid;
	}



	public String getCycleStartDate() {
		return cycleStartDate;
	}



	public void setCycleStartDate(String cycleStartDate) {
		this.cycleStartDate = cycleStartDate;
	}



	public String getPageNumber() {
		return pageNumber;
	}



	public void setPageNumber(String pageNumber) {
		this.pageNumber = pageNumber;
	}



	/**
     * <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:sch="http://api.jasperwireless.com/ws/schema">
		  <soapenv:header>
		    <wsse:Security xmlns:wsse="http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd" soapenv:mustUnderstand="1">
		      <wsse:UsernameToken xmlns:wsu="http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd" wsu:Id="UsernameToken-16847597">
		        <wsse:Username>yangly</wsse:Username>
		        <wsse:Password Type="http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-username-token-profile-1.0#PasswordText">nihao@567634</wsse:Password>
		      </wsse:UsernameToken>
		    </wsse:Security>
		  </soapenv:header>
   <soapenv:Body>
      <sch:EditTerminalRequest>
         <sch:messageId>123</sch:messageId>
         <sch:version>1</sch:version>
         <sch:licenseKey>8a83105a-c980-4add-a2fd-735d90e18361</sch:licenseKey>
         <sch:iccid>89860616090000080786</sch:iccid>
         <!--Optional:-->
           <sch:effectiveDate>2016-05-27</sch:effectiveDate>
         <!--Optional:-->
         <sch:targetValue>ACTIVATED_NAME</sch:targetValue>
         <sch:changeType>3</sch:changeType>
      </sch:EditTerminalRequest>
   </soapenv:Body>
</soapenv:Envelope>
     *
     * @return
     * @throws SOAPException
     */
    private SOAPMessage createEchoRequest() throws SOAPException {
        SOAPMessage message = messageFactory.createMessage();
        message.getMimeHeaders().addHeader("SOAPAction", "http://api.jasperwireless.com/ws/service/billing/GetTerminalUsageDataDetails");
        SOAPEnvelope envelope = message.getSOAPPart().getEnvelope();
        Name editTerminalRequestName = envelope.createName("GetTerminalUsageDataDetailsRequest", BODY_PREFIX, NAMESPACE_URI);
        SOAPBodyElement getTerminalUsageDataDetailsNameElement = message.getSOAPBody()
                .addBodyElement(editTerminalRequestName);
        Name msgId = envelope.createName("messageId", BODY_PREFIX, NAMESPACE_URI);
        SOAPElement msgElement = getTerminalUsageDataDetailsNameElement.addChildElement(msgId);
        if (!StringUtil.isBlank(messageId)) {
        	msgElement.setValue(messageId);
		}
        Name versionName = envelope.createName("version", BODY_PREFIX, NAMESPACE_URI);
        SOAPElement versionElement = getTerminalUsageDataDetailsNameElement.addChildElement(versionName);
        if (!StringUtil.isBlank(version)) {
        	versionElement.setValue(version);
		}
        Name license = envelope.createName("licenseKey", BODY_PREFIX, NAMESPACE_URI);
        SOAPElement licenseElement = getTerminalUsageDataDetailsNameElement.addChildElement(license);
        if (!StringUtil.isBlank(licenseKey)) {
        	licenseElement.setValue(licenseKey);
		}
        Name iccidName = envelope.createName("iccid", BODY_PREFIX, NAMESPACE_URI);
        SOAPElement iccidElement = getTerminalUsageDataDetailsNameElement.addChildElement(iccidName);
        if (!StringUtil.isBlank(iccid)) {
        	iccidElement.setValue(iccid);
		}
        Name cycleStartDateName = envelope.createName("cycleStartDate", BODY_PREFIX, NAMESPACE_URI);
        SOAPElement cycleStartDateElement = getTerminalUsageDataDetailsNameElement.addChildElement(cycleStartDateName);
        if (!StringUtil.isBlank(cycleStartDate)) {
        	cycleStartDateElement.setValue(cycleStartDate.trim());
//        	cycleStartDateElement.setValue("2016-04-23+00:00");//TODO 开发测试用，切换环境时需注释删除
		}
        Name pageNumberName = envelope.createName("pageNumber", BODY_PREFIX, NAMESPACE_URI);
        SOAPElement pageNumberElement = getTerminalUsageDataDetailsNameElement.addChildElement(pageNumberName);
        if (!StringUtil.isBlank(pageNumber)) {
        	pageNumberElement.setValue(pageNumber);
		}
        return message;
    }

    public GetTerminalUsageDataDetailsResponse callWebService(String username, String password) throws SOAPException, IOException, XWSSecurityException, Exception {
        SOAPMessage request = createEchoRequest();
        request = secureMessage(request, username, password);
        SOAPConnection connection = connectionFactory.createConnection();
        SOAPMessage response = connection.call(request, url);
        GetTerminalUsageDataDetailsResponse returnResponse = null;
        if (!response.getSOAPBody().hasFault()) {
        	returnResponse = readTerminalResponse(response);
        } else {
            SOAPFault fault = response.getSOAPBody().getFault();
            logger.error("Received SOAP Fault");
            logger.error("SOAP Fault Code :" + fault.getFaultCode());
            logger.error("SOAP Fault String :" + fault.getFaultString());
        }
       return returnResponse ;
    }

    /**
     * Gets the terminal response.
     *
     * @param message
     * @throws SOAPException
     */
    private GetTerminalUsageDataDetailsResponse readTerminalResponse(SOAPMessage message) throws SOAPException {
        SOAPEnvelope envelope = message.getSOAPPart().getEnvelope();
        Name terminalResponseName = envelope.createName("GetTerminalUsageDataDetailsResponse", PREFIX, NAMESPACE_URI);
        SOAPBodyElement terminalResponseElement = (SOAPBodyElement) message
                .getSOAPBody().getChildElements(terminalResponseName).next();
        Name terminals = envelope.createName("usageDetails", PREFIX, NAMESPACE_URI);
        SOAPBodyElement terminalsElement = (SOAPBodyElement) terminalResponseElement.getChildElements(terminals).next();
        Name terminal = envelope.createName("usageDetail", PREFIX, NAMESPACE_URI);
        GetTerminalUsageDataDetailsResponse response = new GetTerminalUsageDataDetailsResponse();
    	NodeList list = terminalResponseElement.getChildNodes();
        for (int i = 0; i < list.getLength(); i ++) {
        	Node n = list.item(i);
        	setResponseValue(terminalsElement, terminal, response, list, n);
        }
        return response;
    }



	private void setResponseValue(SOAPBodyElement terminalsElement,
			Name terminal, GetTerminalUsageDataDetailsResponse response,
			NodeList list, Node n) {
		if (!StringUtil.isBlank(n.getNodeName())) {
			String value = n.getTextContent();
			switch (n.getNodeName().trim()) {
			case "ns2:correlationId":
				response.setCorrelationId(value);
				break;
			case "ns2:version":
				response.setVersion(value);
				break;
			case "ns2:build":
				response.setBuild(value);
				break;
			case "ns2:timestamp":
				response.setTimestamp(value);
				break;
			case "ns2:usageDetails":
				NodeList detailslist = n.getChildNodes();
				int detailsLen = detailslist.getLength();
				List<GetTerminalUsageDataDetail> details = new ArrayList<GetTerminalUsageDataDetail>(detailsLen);
				for (int k = 0; k < detailsLen; k++) {
		            NodeList detaillist = detailslist.item(k).getChildNodes();
		            GetTerminalUsageDataDetail detail =  new GetTerminalUsageDataDetail();
		            for (int j = 0; j < detaillist.getLength(); j ++) {
		            	Node detailNode = detaillist.item(j);
		            	setDetailValue(detail, detailNode);
		            }
		            details.add(detail);
				}
		        response.setGetTerminalUsageDataDetails(details);
				break;
			case "ns2:totalPages":
				response.setTotalPages(Integer.parseInt(value));
				break;
			default:
				logger.error("请求联通返回字段未能正确解析【{}:{}】",n.getNodeName().trim(),value);
				break;
			}
		}
	}



	private void setDetailValue(GetTerminalUsageDataDetail detail, Node n) {
		if (!StringUtil.isBlank(n.getNodeName())) {
			String value = n.getTextContent();
			switch (n.getNodeName().trim()) {
			case "ns2:iccid":
				detail.setIccid(value);
				break;
			case "ns2:cycleStartDate":
				detail.setCycleStartDate(value);
				break;
			case "ns2:billable":
				detail.setBillable(value);
				break;
			case "ns2:zone":
				detail.setZone(value);
				break;
			case "ns2:sessionStartTime":
				detail.setSessionStartTime(value);
				break;
			case "ns2:duration":
				detail.setDuration(value);
				break;
			case "ns2:dataVolume":
				detail.setDataVolume(value);
				break;
			case "ns2:countryCode":
				detail.setCountryCode(value);				
				break;
			case "ns2:serviceType":
				detail.setServiceType(value);
				break;
			default:
				logger.info("请求联通返回字段未能正确解析【{}:{}】",n.getNodeName().trim(),value);
				break;
			}
		}
	}

    /**
     * This method is used to add the security. This uses xwss:UsernameToken configuration and expects
     * Username and Password to be passes. SecurityPolicy.xml file should be in classpath.
     *
     * @param message
     * @param username
     * @param password
     * @return
     * @throws IOException
     * @throws XWSSecurityException
     */
    @SuppressWarnings("resource")
	private SOAPMessage secureMessage(SOAPMessage message, final String username, final String password)
            throws IOException, XWSSecurityException {
        CallbackHandler callbackHandler = new CallbackHandler() {
            public void handle(Callback[] callbacks) throws UnsupportedCallbackException {
                for (int i = 0; i < callbacks.length; i++) {
                    if (callbacks[i] instanceof UsernameCallback) {
                        UsernameCallback callback = (UsernameCallback) callbacks[i];
                        callback.setUsername(username);
                    } else if (callbacks[i] instanceof PasswordCallback) {
                        PasswordCallback callback = (PasswordCallback) callbacks[i];
                        callback.setPassword(password);
                    } else {
                        throw new UnsupportedCallbackException(callbacks[i]);
                    }
                }
            }
        };
        InputStream policyStream = null;
        XWSSProcessor processor = null;
        try {
//        	String path =  this.getClass().getResource("/").getPath()+ "securityPolicy.xml";
//        	path = path.substring(1, path.length());
//        	File file = new File(path);
//        	policyStream = new FileInputStream(file);
        	ApplicationContext context = new ClassPathXmlApplicationContext();
        	policyStream = context.getResource("securityPolicy.xml").getInputStream();
            processor = processorFactory.createProcessorForSecurityConfiguration(policyStream, callbackHandler);
        }
        finally {
            if (policyStream != null) {
                policyStream.close();
            }
        }
        ProcessingContext context = processor.createProcessingContext(message);
        return processor.secureOutboundMessage(context);
    }

    @Override
    public String toString() {
    	return "【url:"+url.toString()+"】【licenseKey:"+licenseKey+"】【messageId:"+messageId+"】【version:"+version+"】【iccid:"+iccid+"】【cycleStartDate:"+cycleStartDate+"】【pageNumber:"+pageNumber+"】";
    }
    
    /**
     * Main program. Usage : TerminalClient <username> <password>
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        // Apitest URL. See "Get WSDL Files" in the API documentation for Production URL.
        String url = "https://api.10646.cn/ws/service/terminal";
        GetTerminalUsageDataDetailsClient terminalClient = new GetTerminalUsageDataDetailsClient(url, "8a83105a-c980-4add-a2fd-735d90e18361");
        terminalClient.callWebService("yangly", "nihao@567634");
    }
}
