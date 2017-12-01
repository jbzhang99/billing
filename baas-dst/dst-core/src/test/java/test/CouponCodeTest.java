package test;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ai.baas.dst.api.coupon.interfaces.ICouponSV;
import com.ai.baas.dst.api.coupon.params.BoundCouponCodeReq;
import com.ai.baas.dst.api.coupon.params.ChannelCodeDetailList;
import com.ai.baas.dst.api.coupon.params.ChannelCodeReq;
import com.ai.baas.dst.api.coupon.params.ExportOPCouponCodeRes;
import com.ai.baas.dst.api.coupon.params.MyOwnCodeReq;
import com.ai.baas.dst.api.coupon.params.MyOwnCouponCodeRes;
import com.ai.baas.dst.api.coupon.params.OPCheckCodeStatusReq;
import com.ai.baas.dst.api.coupon.params.OPCheckCodeStatusRes;
import com.ai.baas.dst.api.coupon.params.OPCouponCodeListRes;
import com.ai.baas.dst.api.coupon.params.OPCouponCodeReq;
import com.ai.baas.dst.api.coupon.params.OPCouponUsedReq;
import com.ai.opt.base.vo.BaseResponse;
import com.ai.opt.sdk.util.DateUtil;
import com.alibaba.fastjson.JSON;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "/context/core-context.xml" })
public class CouponCodeTest {
	@Autowired
	private ICouponSV couponSV;
	@Test
	public void testCodeInfo(){
		OPCouponCodeReq code = new OPCouponCodeReq();
		//code.setCouponCode("QDHKZ4U995");
		code.setCouponStatus("EFFECTIVE");
		//code.setConditionValue("满100元,折8折");
		//code.setServiceId("11");
		//code.setCouponId("00000000000000000281");
		//code.setActiveTime(DateUtil.getTimestamp("2017-05-08 00:00:00"));
		//code.setInactiveTime(DateUtil.getTimestamp("2017-05-31 23:59:59"));
		code.setTenantId("ECITIC");
		//code.setChannelId("234323423432");
		//code.setOrderId("18628483364");
		code.setPageNo(1);
		code.setPageSize(10);
		System.out.println(JSON.toJSON(code));
		OPCouponCodeListRes info = couponSV.getOPCouponInfoCode(code);
		System.out.println(JSON.toJSONString(info));
	}
	@Test
	public void testChangeCouponCodeToUsed(){
		OPCouponUsedReq req = new OPCouponUsedReq();
		req.setTenantId("ECITIC");
		req.setCouponCode("17051520M106");
		req.setServiceId("xiaomin");
		req.setOrderId("18628483364");
		System.out.println(JSON.toJSON(req));
		BaseResponse res = couponSV.changeCouponCodeToUsed(req);
		System.out.println(JSON.toJSON(res));
	}
	
	@Test
	public void testCheckCodeUsed(){
		OPCheckCodeStatusReq req = new OPCheckCodeStatusReq();
		req.setTenantId("ECITIC");
		req.setCouponCode("QDZMEHZ4QM");
		req.setServiceId("xiaohong");
		System.out.println(JSON.toJSON(req));
		OPCheckCodeStatusRes res = couponSV.checkOPCouponCodeForUse(req);
		System.out.println(JSON.toJSON(res));
	}
	
	@Test
	public void testMyOwnCodeInfo(){
		MyOwnCodeReq req = new MyOwnCodeReq();
		req.setTenantId("ECITIC");
		req.setServiceId("xiaohong");
		System.out.println(JSON.toJSON(req));
		MyOwnCouponCodeRes res = couponSV.getMyOwnCouponCode(req);
		System.out.println(JSON.toJSONString(res));
	}
	
	@Test
	public void testBoundCouponCode(){
		BoundCouponCodeReq req = new BoundCouponCodeReq();
		req.setTenantId("ECITIC");
		req.setServiceId("xiaoming");
		req.setCouponCode("170515208NS8");
		System.out.println(JSON.toJSON(req));
		BaseResponse res = couponSV.boundCouponCode(req);
		System.out.println(JSON.toJSON(res));
		
	}
	
	@Test
	public void testExportCouponCode(){
		OPCouponCodeReq code = new OPCouponCodeReq();
		//code.setCouponCode("QDHKZ4U995");
		code.setCouponStatus("INVALID");
		//code.setConditionValue("满100元,折8折");
		//code.setServiceId("11");
		//code.setCouponId("00000000000000000281");
		//code.setActiveTime(DateUtil.getTimestamp("2017-05-08 00:00:00"));
		//code.setInactiveTime(DateUtil.getTimestamp("2017-05-31 23:59:59"));
		code.setTenantId("ECITIC");
		//code.setChannelId("234323423432");
		//code.setOrderId("18628483364");
		//code.setPageNo(1);
		//code.setPageSize(10);
		System.out.println(JSON.toJSON(code));
		ExportOPCouponCodeRes info = couponSV.exportOPCouponCode(code);
		System.out.println(JSON.toJSONString(info));
	}
	
	@Test
	public void testExportCouponDetails(){
		ChannelCodeReq req = new ChannelCodeReq();
		req.setTenantId("WOCLOUD");
		req.setChannelId("234323423432");
		req.setCouponId("00000000000000000286");
		//req.setCouponCode("QDWzSnoGch");
		req.setStatus("INVALID");
		System.out.println(JSON.toJSON(req));
		ChannelCodeDetailList info = couponSV.getCodeDetailList(req);
		System.out.println(JSON.toJSON(info));
	}
}
