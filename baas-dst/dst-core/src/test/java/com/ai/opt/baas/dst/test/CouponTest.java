package com.ai.opt.baas.dst.test;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ai.baas.dst.api.coupon.interfaces.ICouponSV;
import com.ai.baas.dst.api.coupon.params.ActiveReq;
import com.ai.baas.dst.api.coupon.params.ApplyCouponReq;
import com.ai.baas.dst.api.coupon.params.AuditCouponsReq;
import com.ai.baas.dst.api.coupon.params.BaseReq;
import com.ai.baas.dst.api.coupon.params.ChannelCodeReq;
import com.ai.baas.dst.api.coupon.params.ConditionDetail;
import com.ai.baas.dst.api.coupon.params.CouponChannelReq;
import com.ai.baas.dst.api.coupon.params.CouponCodeReq;
import com.ai.baas.dst.api.coupon.params.CouponConditionReq;
import com.ai.baas.dst.api.coupon.params.CouponListResponse;
import com.ai.baas.dst.api.coupon.params.CouponReq;
import com.ai.baas.dst.api.coupon.params.CouponsAuditInfoReq;
import com.ai.baas.dst.api.coupon.params.ExistisCouponReq;
import com.ai.baas.dst.api.coupon.params.ExportCodeReq;
import com.ai.baas.dst.api.coupon.params.ExportExistisCouponReq;
import com.ai.baas.dst.api.coupon.params.InvalidReq;
import com.ai.baas.dst.api.coupon.params.OPCouponInfoReq;
import com.ai.baas.dst.api.coupon.params.OPCouponQueryReq;
import com.ai.baas.dst.api.coupon.params.UpQDCouponInfoReq;
import com.ai.baas.dst.api.coupon.params.UseReq;
import com.ai.baas.dst.constants.DstConstants;
import com.ai.baas.dst.constants.DstConstants.CouponStatus;
import com.ai.baas.dst.service.business.interfaces.ICouponBusiSV;
import com.ai.opt.base.vo.BaseResponse;
import com.ai.opt.sdk.util.DateUtil;
import com.alibaba.fastjson.JSON;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "/context/core-context.xml" })
public class CouponTest {
	@Autowired
	ICouponSV icouponSV;
	@Autowired
	ICouponBusiSV bs;
	
	@Test
	public void testActiveCoupon(){
		ActiveReq req=new ActiveReq();
		req.setCouponId("00000000000000000011");
		req.setTenantId("VIV-BYD");
		BaseResponse res=icouponSV.activeCoupon(req);
		System.out.println(JSON.toJSONString(res));
	}
	@Test
	public void testUsedCoupon(){
		UseReq req=new UseReq();
		req.setCouponId("00000000000000000011");
		req.setTenantId("VIV-BYD");
		req.setCouponCode("GFUKH7H0");
		BaseResponse res=icouponSV.useCoupon(req);
		System.out.println(JSON.toJSONString(res));
	}
	@Test
	public void testBill(){
		
	//	CouponListResponse getGiftCoupons(CouponReq req)
		CouponReq req=new CouponReq();
		//req.setCouponId("00000000000000000011");
		req.setTenantId("7BAF6267AE2F421FA8D1E305EE35C4BA");
		req.setCouponConType("IMREDUCE");
		req.setPageNO(1);
		req.setPageSize(10);
		CouponListResponse res=icouponSV.getGiftCoupons(req);
		System.out.println(JSON.toJSONString(res));
	}
	@Test
	public void activeTest(){
		
		//	CouponListResponse getGiftCoupons(CouponReq req)
		    ActiveReq req=new ActiveReq();
			//req.setCouponId("00000000000000000011");
			req.setTenantId("7BAF6267AE2F421FA8D1E305EE35C4BA");
		    req.setCouponId("00000000000000000099");
		    System.out.println(JSON.toJSONString(req));
			BaseResponse res=icouponSV.activeCoupon(req);
			System.out.println(JSON.toJSONString(res));
		}
	
	
	   @Test
	   public void getCouponListTest(){
		
		//	CouponListResponse getGiftCoupons(CouponReq req)
		   CouponReq req=new CouponReq();
		  
			//req.setCouponId("00000000000000000011");
			req.setTenantId("ECITIC");
		    req.setStatus(DstConstants.CouponStatus.INVALID);
		    req.setPageNO(1);
		    req.setPageSize(5);
		    System.out.println(JSON.toJSONString(req));
		    CouponListResponse res=icouponSV.getCouponList(req);
			System.out.println(JSON.toJSONString(res));
		}
	   @Test
	   public void getinvalidTest(){
		
		//	CouponListResponse getGiftCoupons(CouponReq req)
		   InvalidReq req=new InvalidReq();
		  
			req.setCouponId("00000000000000000251");
			req.setTenantId("ECITIC");
			Timestamp ti=Timestamp.valueOf("2017-03-25 13:23:23");
			req.setTime(ti);
			System.out.println(JSON.toJSONString(req));
			BaseResponse res=icouponSV.invalidCoupon(req);
			System.out.println(JSON.toJSONString(res));
		}
	   
	   
	   
	   @Test
	   public void testGetCode(){
		   CouponCodeReq req=new CouponCodeReq();
		   req.setTenantId("7BAF6267AE2F421FA8D1E305EE35C4BA");
		   req.setCouponId("00000000000000000172");
		   req.setPageNO(1);
		   req.setPageSize(5);
		  System.out.println(JSON.toJSONString(icouponSV.getOPCouponCode(req)));  
		   
	   }
	 @Test
	 public void testAddOp(){
	//BaseResponse addQDCoupon(QDCouponInfoReq couponInfo) throws BusinessException,SystemException;
   
		 
		 //	BaseResponse addOPCoupon(OPCouponInfoReq couponInfo) throws BusinessException,SystemException;

		 OPCouponInfoReq req=new OPCouponInfoReq();
		 req.setCouponName("满200减10元优惠券测试");
		 req.setActiveTime(DateUtil.getTimestamp("2017-05-02 11:11:11"));
		 req.setInactiveTime(DateUtil.getTimestamp("2017-05-31 11:11:11"));
		 req.setCreateTime(DateUtil.getSysDate());
		 req.setCouponConType("FULLREDUCE");
		 req.setCouponAmount("10");
		 req.setCouponType("ALL");
		 req.setCreatorId("0001");
		 req.setCreatorName("管理员");
		 req.setCouponValue("10元");
		 req.setConditionValue("满100元,减10元");
		 req.setTenantId("ECITIC");
		 req.setCouponRule("ALL");
		 req.setTopMoney("20");
		 req.setCanApplyCount("100");
		 ConditionDetail con=new ConditionDetail();
		 con.setDstTypeUnit("REDUCE");
		 con.setDstUnit("YUAN");
		 con.setDstValue("10");
		 con.setReachAmount("200");
		 con.setReachUnit("YUAN");
		 req.setConditonDetail(con);
		 System.out.println(JSON.toJSONString(icouponSV.addOPCoupon(req)));  
	}
	 @Test
	public void teestGetopCoupons(){
		
		//OPCouponListRes getOPCoupons(OPCouponQueryReq req) throws BusinessException,SystemException;
		OPCouponQueryReq req=new OPCouponQueryReq();
		req.setTenantId("ECITIC");
		req.setPageNO(1);
		req.setPageSize(10);
		req.setCouponId("00000000000000000312");
		System.out.println(JSON.toJSONString(icouponSV.getOPCoupons(req)));
	}
	
	 @Test
	public void testGetOpSingle(){
	//OPSingleCouponRes getOPSingleCoupon(BaseReq req) throws BusinessException,SystemException;
		
		BaseReq req=new BaseReq();
		req.setCouponId("00000000000000000311");
		req.setTenantId("ECITIC");
		
		System.out.println(JSON.toJSONString(icouponSV.getOPSingleCoupon(req)));
		
		
	}
	 @Test
	 public void testGetChannelCodes(){
		//	ChannelCodeListRes getChannelCodes(ChannelCodeReq req)throws BusinessException,SystemException;
		 
		 ChannelCodeReq req=new ChannelCodeReq();
		 req.setCouponId("00000000000000000311");
		 req.setTenantId("ECITIC");
		 req.setPageNO(1);
		 req.setPageSize(10);
		 req.setChannelId("123");
		 System.out.println(JSON.toJSONString(req));
		 System.out.println(JSON.toJSONString(icouponSV.getChannelCodes(req)));

	 }
	 @Test
	 public void distributCoupon(){
		 
		 ApplyCouponReq req=new ApplyCouponReq();
		 req.setApplyCount(100);
		 req.setChannelAccount("gg@163.com");
		 req.setChannelId("234323423432");
		 req.setComments("双十一活动");
		 req.setCouponId("00000000000000000311");
		 req.setTenantId("ECITIC");
		 System.out.println(JSON.toJSONString(req));
		 System.out.println(JSON.toJSONString(icouponSV.applyCoupon(req)));
		 
	 }
	 @Test
	 public void testChannelExistsCodes(){
		 ExistisCouponReq req=new ExistisCouponReq();
		 req.setChannelId("234323423432");
		 req.setTenantId("ECITIC");
		 req.setPageNO(1);
		 req.setPageSize(10);
		 System.out.println(JSON.toJSONString(req));
		 System.out.println(JSON.toJSONString(icouponSV.getExistsCoupons(req)));
	 }
	 /**
	  * 查询申请优惠卷
	  * @throws ParseException
	  */
	 @Test
	 public void getCouponsInfo() throws ParseException{
		 
		 CouponConditionReq req=new CouponConditionReq();
		 req.setTenantId("ECITIC");
		 Date data = new SimpleDateFormat("yyyy-MM-dd").parse("2017-05-14");
		 req.setStartTime(new Timestamp(data.getTime()));
		 data = new SimpleDateFormat("yyyy-MM-dd").parse("2017-05-29");
		 req.setEndTime(new Timestamp(data.getTime()));
		 req.setPageNO(1);
		 req.setPageSize(10);
		 System.out.println(JSON.toJSONString(icouponSV.getCouponInfoList(req)));
		 
	 }
	 
	 
	 /**
	  * 优惠券详情下的渠道商信息查询（所有）
	  * @throws ParseException
	  */
	 @Test
	 public void getChannelOPCouponCodeList() throws ParseException{
		 
		 CouponChannelReq req=new CouponChannelReq();
		 req.setTenantId("7BAF6267AE2F421FA8D1E305EE35C4BA");
		 req.setCouponId("00000000000000000172");
		 req.setChannelId("1111111");
		 System.out.println(JSON.toJSONString(req));
		 System.out.println(JSON.toJSONString(icouponSV.getChannelOPCouponCodeList(req)));
		 
	 }
	 @Test
	 public void testGetExpChannelCodes(){
		// ChannelCodeExportRes getChannelCodesExport(ExportCodeReq req)throws BusinessException,SystemException;
		 ExportCodeReq req=new ExportCodeReq();
		 req.setChannelId("234323423432");
		// req.setCouponCode(couponCode);
		 req.setTenantId("ECITIC");
		 req.setCouponId("00000000000000000311");
		 System.out.println(JSON.toJSONString(req));
		 System.out.println(JSON.toJSONString(icouponSV.getChannelCodesExport(req)));
		 
	 }
	 
	 @Test
	 public void testgetExistsCouponsExport(){
		// ChannelCodeExportRes getChannelCodesExport(ExportCodeReq req)throws BusinessException,SystemException;
		 ExportExistisCouponReq req=new ExportExistisCouponReq();
		 req.setChannelId("234323423432");
		// req.setCouponCode(couponCode);
		 req.setTenantId("ECITIC");
//		 req.setCouponId("00000000000000000311");
		 System.out.println(JSON.toJSONString(req));
		 System.out.println(JSON.toJSONString(icouponSV.getExistsCouponsExport(req)));
		 
	 }
	 @Test
	 public void testgetCouponAuditPageList() throws ParseException{
		// ChannelCodeExportRes getChannelCodesExport(ExportCodeReq req)throws BusinessException,SystemException;
		 CouponsAuditInfoReq req=new CouponsAuditInfoReq();
		// req.setCouponCode(couponCode);
		 req.setTenantId("WOCLOUD");
//		 req.setCouponId("00000000000000000311");
		 Date data = new SimpleDateFormat("yyyy-MM-dd").parse("2017-05-8");
		 req.setStartTime(new Timestamp(data.getTime()));
		 data = new SimpleDateFormat("yyyy-MM-dd").parse("2017-05-29");
		 req.setEndTime(new Timestamp(data.getTime()));
		 req.setStatus("CREATE");
		 req.setPageNO(1);
		 req.setPageSize(1);
		 System.out.println(JSON.toJSONString(req));
		 System.out.println(JSON.toJSONString(icouponSV.getCouponAuditPageList(req)));
	 }
	 
	 @Test
	 public void testgetCouponAuditList() throws ParseException{
		// ChannelCodeExportRes getChannelCodesExport(ExportCodeReq req)throws BusinessException,SystemException;
		 CouponsAuditInfoReq req=new CouponsAuditInfoReq();
		// req.setCouponCode(couponCode);
		 req.setTenantId("WOCLOUD");
//		 req.setCouponId("00000000000000000311");
		 Date data = new SimpleDateFormat("yyyy-MM-dd").parse("2017-05-8");
		 req.setStartTime(new Timestamp(data.getTime()));
		 data = new SimpleDateFormat("yyyy-MM-dd").parse("2017-05-29");
		 req.setEndTime(new Timestamp(data.getTime()));
		 req.setStatus("CREATE");
		 System.out.println(JSON.toJSONString(req));
		 System.out.println(JSON.toJSONString(icouponSV.getCouponAuditListExport(req)));
	 }
	 @Test
	 public void testauditCouponOP() throws ParseException{
		 // ChannelCodeExportRes getChannelCodesExport(ExportCodeReq req)throws BusinessException,SystemException;
		 AuditCouponsReq req=new AuditCouponsReq();
		 req.setTenantId("WOCLOUD");
		 req.setStatus(CouponStatus.EFFECTIVE);
		 req.setCouponId("00000000000000000284");
		 System.out.println(JSON.toJSONString(req));
		 System.out.println(JSON.toJSONString(icouponSV.auditCouponOP(req)));
	 }
	 
	 @Test
	 public void testgetCouponNotAudit() throws ParseException{
		 // ChannelCodeExportRes getChannelCodesExport(ExportCodeReq req)throws BusinessException,SystemException;
		 CouponConditionReq req=new CouponConditionReq();
		 req.setTenantId("ECITIC");
		 req.setCreatorId("0001");
		 req.setPageNO(1);
		 req.setPageSize(10);
		 System.out.println(JSON.toJSONString(req));
		 System.out.println(JSON.toJSONString(icouponSV.getCouponNotAudit(req)));
	 }
	 @Test
	 public void testgetCouponNotAuditExport() throws ParseException{
		 // ChannelCodeExportRes getChannelCodesExport(ExportCodeReq req)throws BusinessException,SystemException;
		 CouponConditionReq req=new CouponConditionReq();
		 req.setTenantId("ECITIC");
		 req.setCreatorId("0001");
		 System.out.println(JSON.toJSONString(req));
		 System.out.println(JSON.toJSONString(icouponSV.getCouponNotAuditExport(req)));
	 }
	 @Test
	 public void testdeleteCouponNotAudit() throws ParseException{
		 // ChannelCodeExportRes getChannelCodesExport(ExportCodeReq req)throws BusinessException,SystemException;
		 CouponConditionReq req=new CouponConditionReq();
		 req.setTenantId("ECITIC");
		 req.setCreatorId("0001");
		 req.setCouponId("00000000000000000351");
		 System.out.println(JSON.toJSONString(req));
		 System.out.println(JSON.toJSONString(icouponSV.deleteCouponNotAudit(req)));
	 }
	 @Test
	 public void testupdateCouponNotAudit() throws ParseException{
		 // ChannelCodeExportRes getChannelCodesExport(ExportCodeReq req)throws BusinessException,SystemException;
		 UpQDCouponInfoReq req=new UpQDCouponInfoReq();
		 req.setTenantId("ECITIC");
		 req.setCreatorId("0001");
		 req.setApplyCount("121");
		 req.setCouponAmount("12112");
		 req.setCouponName("121");
		 req.setTopMoney("1211");
		 req.setCouponId("00000000000000000351");
		 System.out.println(JSON.toJSONString(req));
		 System.out.println(JSON.toJSONString(icouponSV.updateCouponNotAudit(req)));
	 }
}
