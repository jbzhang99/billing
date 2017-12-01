package test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ai.baas.dst.api.billsdiscount.params.BillDiscountInfo;
import com.ai.baas.dst.api.billsdiscount.params.BillDiscountListQueryRequest;
import com.ai.baas.dst.api.billsdiscount.params.BillDiscountQueryRequest;
import com.ai.baas.dst.api.billsdiscount.params.EffectiveProductQueryRequest;
import com.ai.baas.dst.service.business.interfaces.IBillDiscountQueryBusiSV;
import com.ai.opt.base.vo.PageInfo;
import com.ai.opt.sdk.util.DateUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "/context/core-context.xml" })
public class ReBillingTest {
	 @Autowired
	 IBillDiscountQueryBusiSV rebillingImpl;
	    
	    @Test
	    public void test(){
	       // PageInfo<StandardList> resultPage=new PageInfo<StandardList>();
//	    	 BillDiscountListQueryRequest reqParam = new BillDiscountListQueryRequest();
//	    	 PageInfo<BillDiscountInfo> pageInfo = new PageInfo<BillDiscountInfo>();
//             pageInfo.setPageNo(1);
//             pageInfo.setPageSize(10);
//             reqParam.setPageInfo(pageInfo);
//             reqParam.setTenantId("7BAF6267AE2F421FA8D1E305EE35C4BA");
//             
             EffectiveProductQueryRequest request = new EffectiveProductQueryRequest();
             List<String> discountIds = new ArrayList<String>();
				discountIds.add("0000000271");
				request.setDiscountIds(discountIds);
				request.setEffectDate("2017-03-01"+" 00:00:00");
				request.setExpireDate("2017-03-17"+" 23:59:59");
				request.setTenantId("7BAF6267AE2F421FA8D1E305EE35C4BA");
	    	 rebillingImpl.queryEffectiveScopeProduct(request);
	    	 System.out.println("---");
	       
	    }
	    
	    @Test
	    public void test2(){
	       // PageInfo<StandardList> resultPage=new PageInfo<StandardList>();
	    	 BillDiscountQueryRequest reqParam = new BillDiscountQueryRequest();
	         reqParam.setTenantId("7BAF6267AE2F421FA8D1E305EE35C4BA");
	         reqParam.setDiscountId("0000000304");
//	    	 rebillingImpl.queryBillDiscountProduct(reqParam);
	    	 
	    	 System.out.println(DateUtil.getSysDate());
	       
	    }
	    
	   
}
