package test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ai.baas.pkgfee.api.pkgunsubscribe.interfaces.IPkgUnsubscribeSV;
import com.ai.baas.pkgfee.api.pkgunsubscribe.params.PkgUnsubscribeRequest;
import com.ai.opt.base.vo.BaseResponse;
import com.alibaba.fastjson.JSON;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "/context/core-context.xml" })
public class PkgUnsubscribeTest {
	@Autowired
	IPkgUnsubscribeSV iPkgUnsubscribeSV;

	@Test
    public void testPkgFeeQue(){
		PkgUnsubscribeRequest request=new PkgUnsubscribeRequest();

		request.setTenantId("WOCLOUD");
		request.setExtCustId("69");
        request.setPriceCode("20019");
        request.setBusiSerialNo(String.valueOf(System.currentTimeMillis()));
        request.setInstanceId("b18a588c-b134-464f-940c-f37a581811e3");
        request.setUnsubTime("2017-05-24 10:11:12");
		
		BaseResponse responseMessage = iPkgUnsubscribeSV.unsubscribe(request);
		
		System.out.println("param="+com.alibaba.fastjson.JSON.toJSONString(request));
        System.out.println("responseMessage="+com.alibaba.fastjson.JSON.toJSONString(responseMessage));
        System.out.println();
    }
}
