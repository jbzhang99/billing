package mds;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.ai.opt.sdk.appserver.DubboServiceStart;
import com.ai.opt.sdk.components.base.ComponentConfigLoader;
import com.ai.opt.sdk.components.mds.MDSClientFactory;
import com.ai.opt.sdk.components.mo.PaasConf;
import com.ai.paas.ipaas.mds.IMessageConsumer;
import com.ai.paas.ipaas.mds.IMessageProcessor;
import com.ai.paas.ipaas.mds.IMessageSender;
import com.ai.paas.ipaas.mds.IMsgProcessorHandler;
import com.ai.paas.ipaas.mds.MsgConsumerFactory;
import com.ai.paas.ipaas.mds.MsgSenderFactory;
import com.ai.paas.ipaas.uac.vo.AuthDescriptor;

import net.sf.json.JSONObject;

public class MdsTest {

	@Test
	public void sendMsgTest(){
		String mdsns = "baas-ResourceClient-topic";//
		IMessageSender msgSender = MDSClientFactory.getSenderClient(mdsns);
		for(int i=0;i<4;i++){
			int part=i%2;
			
			JSONObject userInfoObject = new JSONObject();
            userInfoObject.put("SUBS_ID", "603");
            userInfoObject.put("TENANT_ID", "VIV-BYD");
            userInfoObject.put("MSG_TYPE", "BATCH");
            userInfoObject.put("USER_STATE", "1");
			//{"SUBS_ID":"603","TENANT_ID":"VIV-BYD","MSG_TYPE":"BATCH","USER_STATE":"1"}
            msgSender.send(userInfoObject.toString(), part);
//			msgSender.send("[opt-sdk-msg:"+i+"]This is a test message……", part);//第二个参数为分区键，如果不分区，传入0	
		}

	}
	@Test
	public void recvMsgTest(){
		String mdsns = "baas-ResourceClient-topic";//
		IMsgProcessorHandler msgProcessorHandler=new IMsgProcessorHandler() {
			
			@Override
			public IMessageProcessor[] createInstances(int paramInt) {
				// TODO Auto-generated method stub
				
				List<IMessageProcessor> processors = new ArrayList<>();
				IMessageProcessor processor = null;
				for (int i = 0; i < paramInt; i++) {
					processor = new MessageProcessorImpl();
					processors.add(processor);
				}
				return processors.toArray(new IMessageProcessor[processors.size()]);

			}
		};
		IMessageConsumer msgConsumer= MDSClientFactory.getConsumerClient(mdsns, msgProcessorHandler);
		msgConsumer.start();
		synchronized (MdsTest.class) {
			while (true) {
				try {
					MdsTest.class.wait();

				} catch (Exception e) {
					System.out.println("MDS 消费错误，具体信息为：" + e.getMessage());
				}
			}
		}
		
	}
}
