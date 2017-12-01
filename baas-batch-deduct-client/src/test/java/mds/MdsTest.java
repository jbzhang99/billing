package mds;

import com.ai.opt.sdk.appserver.LoadConfServiceStart;
import com.ai.opt.sdk.components.mds.MDSClientFactory;
import com.ai.paas.ipaas.mds.IMessageConsumer;
import com.ai.paas.ipaas.mds.IMessageProcessor;
import com.ai.paas.ipaas.mds.IMessageSender;
import com.ai.paas.ipaas.mds.IMsgProcessorHandler;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class MdsTest {

	@Test
	public void sendMsgTest(){
		String mdsns = "baas-bmc-topic";//
		IMessageSender msgSender = MDSClientFactory.getSenderClient(mdsns);
		for(int i=0;i<5;i++){
			int part=i%2;
			msgSender.send("[opt-sdk-msg:"+i+"]This is a test message……", part);//第二个参数为分区键，如果不分区，传入0	
		}

	}
	@Test
	public void recvMsgTest(){
		String mdsns = "baas-bmc-topic";//
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

	public static void main(String[] args) {
		LoadConfServiceStart.main(new String[]{"/Users/wangyongxin/ai_work/workspace/baas/baas-batch-deduct-client/src/main/resources/paas/sdkmode"});
	}
}
