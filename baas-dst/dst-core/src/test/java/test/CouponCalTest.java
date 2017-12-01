package test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ai.baas.dst.api.couponcal.interfaces.ICouponCalSV;
import com.ai.baas.dst.api.couponcal.params.CouponCalRequest;
import com.ai.baas.dst.api.couponcal.params.CouponCalResponse;
import com.ai.baas.dst.api.couponcal.params.ProductReq;;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "/context/core-context.xml" })
public class CouponCalTest {
	@Autowired
	private ICouponCalSV iCouponCalSV;

	@Test
	public void testCal() {
		List<ProductReq> products = new ArrayList<ProductReq>();
		
		ProductReq one = new ProductReq();
		one.setProductID("401");
		one.setTimeDuration("1");
		one.setUnitPrice("50");
		products.add(one);
		
		ProductReq two = new ProductReq();
		two.setProductID("402");
		two.setTimeDuration("2");
		two.setUnitPrice("10");
		products.add(two);
		
		CouponCalRequest request = new CouponCalRequest();
		request.setProducts(products);
		request.setTenantId("ECITIC");
		request.setCouponCode("17051520AQ45");
		
		CouponCalResponse response = iCouponCalSV.getResultByCouponCal(request);
		
		List<ProductReq> pros = response.getProductInfo();
		
		for(ProductReq cur : pros){
			System.out.println("response.getProductID:" + cur.getProductID());
			System.out.println("response.getUnitPrice:" + cur.getUnitPrice());
			System.out.println("response.getQuantity:" + cur.getQuantity());
			System.out.println("response.getCurAmount:" + cur.getCurAmount());
			System.out.println("response.getTimeDuration:" + cur.getTimeDuration());
			System.out.println();
		}
		System.out.println("response.getIsUsed:" + response.getIsUsed());
		System.out.println("response.code:" + response.getResponseHeader().getResultCode());
		System.out.println("response.msg:" + response.getResponseHeader().getResultMessage());
	}
	
}


