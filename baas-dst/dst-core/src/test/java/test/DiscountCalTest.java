package test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ai.baas.dst.api.discountcal.interfaces.IDiscountCalSV;
import com.ai.baas.dst.api.discountcal.params.DiscountCalRequest;
import com.ai.baas.dst.api.discountcal.params.DiscountCalResponse;
import com.ai.opt.sdk.components.ccs.CCSClientFactory;
import com.ai.opt.sdk.components.mcs.MCSClientFactory;
import com.ai.paas.ipaas.mcs.interfaces.ICacheClient;
import com.alibaba.fastjson.JSON;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "/context/core-context.xml" })
public class DiscountCalTest {
	@Autowired
	private IDiscountCalSV iDiscountCalSV;

	@Test
	public void testCal() {
//		ICacheClient cacheClient = MCSClientFactory.getCacheClient("DecimalMode");
//		
//		cacheClient.hset("DecimalMode", "123", "234");
		
//		Double discountPrice = 0.04;
//		BigDecimal bg = new BigDecimal(discountPrice);
//		System.out.println(discountPrice);
//		System.out.println("ROUND_CEILING:" + String.valueOf(bg.setScale(2, BigDecimal.ROUND_CEILING).doubleValue()));
//		System.out.println("ROUND_DOWN:" + String.valueOf(bg.setScale(2, BigDecimal.ROUND_DOWN).doubleValue()));
//		System.out.println("ROUND_FLOOR:" + String.valueOf(bg.setScale(2, BigDecimal.ROUND_FLOOR).doubleValue()));
//		System.out.println("ROUND_HALF_DOWN:" + String.valueOf(bg.setScale(2, BigDecimal.ROUND_HALF_DOWN).doubleValue()));
//		System.out.println("ROUND_HALF_EVEN:" + String.valueOf(bg.setScale(2, BigDecimal.ROUND_HALF_EVEN).doubleValue()));
//		System.out.println("ROUND_HALF_UP:" + String.valueOf(bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue()));
//		System.out.println("ROUND_UP:" + String.valueOf(bg.setScale(2, BigDecimal.ROUND_UP).doubleValue()));
//		
//		System.out.println("***************************");
//				
//		Double discountPrice1 = 0.05;
//		BigDecimal bg1 = new BigDecimal(discountPrice1);
//		System.out.println(discountPrice1);
//		System.out.println("ROUND_CEILING:" + String.valueOf(bg1.setScale(2, BigDecimal.ROUND_CEILING).doubleValue()));
//		System.out.println("ROUND_DOWN:" + String.valueOf(bg1.setScale(2, BigDecimal.ROUND_DOWN).doubleValue()));
//		System.out.println("ROUND_FLOOR:" + String.valueOf(bg1.setScale(2, BigDecimal.ROUND_FLOOR).doubleValue()));
//		System.out.println("ROUND_HALF_DOWN:" + String.valueOf(bg1.setScale(2, BigDecimal.ROUND_HALF_DOWN).doubleValue()));
//		System.out.println("ROUND_HALF_EVEN:" + String.valueOf(bg1.setScale(2, BigDecimal.ROUND_HALF_EVEN).doubleValue()));
//		System.out.println("ROUND_HALF_UP:" + String.valueOf(bg1.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue()));
//		System.out.println("ROUND_UP:" + String.valueOf(bg1.setScale(2, BigDecimal.ROUND_UP).doubleValue()));
//		
		List<String> productsID = new ArrayList<String>();
//		productsID.add("404");
//		productsID.add("405");
//		productsID.add("406");
//		productsID.add("111");
//		productsID.add("222");
		productsID.add("411");
		
		DiscountCalRequest request = new DiscountCalRequest();
		request.setTenantId("WOCLOUD");
		request.setPriceProductIDs(productsID);
		request.setUnitPrice("500.33");
		request.setQuantity("15");
		
		System.out.println(JSON.toJSONString(request));
		
		DiscountCalResponse response = iDiscountCalSV.getResultByDiscountCal(request);
		
		System.out.println("response.getCategoryID:" + response.getCategoryID());
		System.out.println("response.getDiscountDesc:" + response.getDiscountDesc());
		System.out.println("response.getDiscountID:" + response.getDiscountID());
		System.out.println("response.getDiscountPrice:" + response.getDiscountPrice());
		System.out.println("response.getOriginalPrice:" + response.getOriginalPrice());
		
		System.out.println("response.code:" + response.getResponseHeader().getResultCode());
		System.out.println("response.msg:" + response.getResponseHeader().getResultMessage());
	}
	
}


