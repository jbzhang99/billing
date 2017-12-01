package test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ai.baas.pkgfee.api.querypkgfee.interfaces.IQueryPkgFeeInfoSV;
import com.ai.baas.pkgfee.api.querypkgfee.params.PkgfeeQueryRequest;
import com.ai.opt.base.vo.BaseResponse;
import com.ai.opt.sdk.appserver.LoadConfServiceStart;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "/context/core-context.xml" })
public class QueryPkgFeeInfoTest {
	@Autowired
	IQueryPkgFeeInfoSV iPkgFeeInfoSV;
//	@Test
//    public void testQueryPkgFee(){
//		PkgfeeQueryRequest pkgFeeQueryRequest=new PkgfeeQueryRequest();
//
//		pkgFeeQueryRequest.setTenantId("aaaa");
//		pkgFeeQueryRequest.setPriceCode("1234");
//		PkgfeeResponse responseMessage=iPkgFeeInfoSV.getPkgFeeInfo(pkgFeeQueryRequest);
//		System.out.println("param="+com.alibaba.fastjson.JSON.toJSONString(pkgFeeQueryRequest));
//        System.out.println("responseMessage="+com.alibaba.fastjson.JSON.toJSONString(responseMessage));
//        System.out.println();
//	}

	@Test
    public void testPkgFeeQue(){
		PkgfeeQueryRequest pkgFeeQueryRequest=new PkgfeeQueryRequest();

		pkgFeeQueryRequest.setTenantId("ECITIC");
		BaseResponse responseMessage=iPkgFeeInfoSV.getPkgFeeQueInfo(pkgFeeQueryRequest);
		System.out.println("param="+com.alibaba.fastjson.JSON.toJSONString(pkgFeeQueryRequest));
        System.out.println("responseMessage="+com.alibaba.fastjson.JSON.toJSONString(responseMessage));
        System.out.println();
        //LoadConfServiceStart.main(new String[]{"D:\\Work\\AI-workspace\\YunJFSource\\AI-OPT\\baas-pkgfee\\pkgfee-core\\src\\main\\resources\\paas\\sdkmode"});
	}
}
