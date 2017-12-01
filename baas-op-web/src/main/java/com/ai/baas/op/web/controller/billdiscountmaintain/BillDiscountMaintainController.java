package com.ai.baas.op.web.controller.billdiscountmaintain;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ai.baas.bmc.api.baseInfo.params.BaseCode;
import com.ai.baas.bmc.api.marktableproduct.params.ProductInfo;
import com.ai.baas.bmc.api.marktableproduct.params.ServiceVO;
import com.ai.baas.dst.api.billsdiscount.interfaces.IBillDiscountMaintainSV;
import com.ai.baas.dst.api.billsdiscount.interfaces.IBillDiscountQuerySV;
import com.ai.baas.dst.api.billsdiscount.params.BillDiscountDeleteRequest;
import com.ai.baas.dst.api.billsdiscount.params.BillDiscountInfo;
import com.ai.baas.dst.api.billsdiscount.params.BillDiscountListQueryRequest;
import com.ai.baas.dst.api.billsdiscount.params.BillDiscountListQueryResponse;
import com.ai.baas.dst.api.billsdiscount.params.BillDiscountListResponse;
import com.ai.baas.dst.api.billsdiscount.params.BillDiscountMaintainResponse;
import com.ai.baas.dst.api.billsdiscount.params.BillDiscountQueryRequest;
import com.ai.baas.dst.api.billsdiscount.params.BillDiscountQueryResponse;
import com.ai.baas.dst.api.billsdiscount.params.BillDiscountUpdateVo;
import com.ai.baas.dst.api.billsdiscount.params.BillDiscountVo;
import com.ai.baas.dst.api.billsdiscount.params.EffectiveProductQueryRequest;
import com.ai.baas.dst.api.billsdiscount.params.EffectiveProductQueryResponse;
import com.ai.baas.dst.api.billsdiscount.params.ExtendInfo;
import com.ai.baas.dst.api.billsdiscount.params.GiftProduct;
import com.ai.baas.dst.api.coupon.interfaces.ICouponSV;
import com.ai.baas.dst.api.coupon.params.CouponInfo;
import com.ai.baas.dst.api.coupon.params.CouponListResponse;
import com.ai.baas.dst.api.coupon.params.CouponReq;
import com.ai.baas.dst.api.coupon.params.CouponResponse;
import com.ai.baas.dst.api.coupon.params.ExPortReq;
import com.ai.baas.dst.api.coupon.params.ExportCouponResponse;
import com.ai.baas.dst.api.coupon.params.SingleCouponReq;
import com.ai.baas.op.web.constants.BaaSOPConstants;
import com.ai.baas.op.web.constants.BaaSOPConstants.BDAttrName;
import com.ai.baas.op.web.constants.BaaSOPConstants.DiscountType;
import com.ai.baas.op.web.controller.common.ParamController;
import com.ai.baas.op.web.model.BillDiscountInfoVo;
import com.ai.baas.op.web.model.BillDiscountQueryVo;
import com.ai.baas.op.web.model.BillDiscountResp;
import com.ai.baas.op.web.model.BillDiscountSaveVo;
import com.ai.baas.op.web.model.BillProductQueryVo;
import com.ai.baas.op.web.util.DateUtil;
import com.ai.baas.op.web.util.TradeSeqUtil;
import com.ai.baas.prd.api.element.interfaces.IPriceElementSV;
import com.ai.baas.prd.api.element.params.ElementDetailRequireResult;
import com.ai.baas.prd.api.element.params.ElementDetailRequireVO;
import com.ai.baas.prd.api.element.params.ElementRequireResult;
import com.ai.baas.prd.api.element.params.ElementRequireVO;
import com.ai.baas.prd.api.element.params.Product;
import com.ai.net.xss.util.CollectionUtil;
import com.ai.opt.base.vo.BaseInfo;
import com.ai.opt.base.vo.BaseResponse;
import com.ai.opt.base.vo.PageInfo;
import com.ai.opt.sdk.dubbo.util.DubboConsumerFactory;
import com.ai.opt.sdk.web.model.ResponseData;
import com.ai.opt.sso.client.filter.SSOClientConstants;
import com.ai.opt.sso.client.filter.SSOClientUser;
import com.ai.paas.ipaas.util.StringUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 账单优惠
 */
@RestController
@RequestMapping("/billDiscountMaintain")
public class BillDiscountMaintainController {

	private static final Logger LOG = Logger.getLogger(BillDiscountMaintainController.class);
	
	@RequestMapping("/list")
    public ModelAndView list(HttpServletRequest request) {
	    SSOClientUser user = (SSOClientUser) request.getSession().getAttribute(SSOClientConstants.USER_SESSION_KEY);
	    String phone = user.getPhone();
	    request.setAttribute("phoneNum", phone);
        return new ModelAndView("jsp/billdiscountmaintain/billDiscountList");
    }
	
	@RequestMapping("/toAdd")
    public ModelAndView toAdd(HttpServletRequest request) {
        return new ModelAndView("jsp/billdiscountmaintain/addBillDiscount");
    }
	
	@RequestMapping("/toUpdate")
	public ModelAndView toUpdate(HttpServletRequest request) {
	    request.setAttribute("dt", request.getParameter("dt"));//计费类型
	    request.setAttribute("flag", request.getParameter("flag"));//查看、更新标记
	    request.setAttribute("productId", request.getParameter("productId"));
	    return new ModelAndView("jsp/billdiscountmaintain/addBillDiscount");
	}
	
	/**
     * 账单优惠产品列表查询
     */
    @RequestMapping("/getList")
    public ResponseData<PageInfo<BillDiscountInfoVo>> getList(BillDiscountQueryVo queryVo, HttpServletRequest request){
        
        ResponseData<PageInfo<BillDiscountInfoVo>> responseData = null;
        
        try {
            SSOClientUser user = (SSOClientUser) request.getSession().getAttribute(SSOClientConstants.USER_SESSION_KEY);
            String tenantId = user.getTenantId();
            
            BillDiscountListQueryRequest reqParam = new BillDiscountListQueryRequest();
            reqParam.setTenantId(tenantId);
            if(!StringUtil.isBlank(queryVo.getProductId())){
                reqParam.setDiscountId(queryVo.getProductId());//账单优惠产品ID
            }
            if(!StringUtil.isBlank(queryVo.getDiscountType())){
                reqParam.setDiscountType(queryVo.getDiscountType());//账单优惠类型 mz:满赠 mj:满减 dz:限时折扣 bd:保底 fd:封顶
            }
            if(!StringUtil.isBlank(queryVo.getProductName())){
                reqParam.setDiscountName(queryVo.getProductName());//优惠活动名称
            }
            if(!StringUtil.isBlank(queryVo.getEffectDate())){
                reqParam.setEffectDate(queryVo.getEffectDate()+" 00:00:00");//生效日期
            }
            if(!StringUtil.isBlank(queryVo.getExpireDate())){
                reqParam.setExpireDate(queryVo.getExpireDate()+" 23:59:59");//失效日期
            }
            
            String strPageNo=(null==request.getParameter(BaaSOPConstants.PAGENO))?"1":request.getParameter(BaaSOPConstants.PAGENO);
            String strPageSize=(null==request.getParameter(BaaSOPConstants.PAGESIZE))?"10":request.getParameter(BaaSOPConstants.PAGESIZE);
            PageInfo<BillDiscountInfo> pageInfo = new PageInfo<BillDiscountInfo>();
            pageInfo.setPageNo(Integer.parseInt(strPageNo));
            pageInfo.setPageSize(Integer.parseInt(strPageSize));
            reqParam.setPageInfo(pageInfo);
            
            IBillDiscountQuerySV service = DubboConsumerFactory.getService(IBillDiscountQuerySV.class);
            LOG.debug("账单优惠列表查询入参:" + JSONArray.fromObject(reqParam));
            BillDiscountListQueryResponse queryList = service.queryBillDiscountProductList(reqParam);
            LOG.debug("账单优惠列表查询出参:" + JSONArray.fromObject(queryList));
            
            PageInfo<BillDiscountInfoVo> result = new PageInfo<BillDiscountInfoVo>();
            pageInfo = queryList!=null ? queryList.getPageInfo() : null;
            if(pageInfo != null){
                result.setPageCount(pageInfo.getPageCount());
                result.setCount(pageInfo.getCount());
                result.setPageNo(pageInfo.getPageNo());
                result.setPageSize(pageInfo.getPageSize()); 
                List<BillDiscountInfoVo> productList = this.transalteBillDiscountEntity(tenantId, pageInfo.getResult());//翻译优惠类型
                result.setResult(productList);
            }
            
            responseData = new ResponseData<PageInfo<BillDiscountInfoVo>>(ResponseData.AJAX_STATUS_SUCCESS, "查询成功", result);
        } catch (Exception e) {
            responseData = new ResponseData<PageInfo<BillDiscountInfoVo>>(ResponseData.AJAX_STATUS_FAILURE, "账单优惠列表查询异常");
            LOG.error("账单优惠列表查询出错：", e);
        }
        return responseData;
    }
    
    /**
     * 新增、修改［账单优惠产品］保存方法
     */
    @RequestMapping("/save")
    public ResponseData<String> addOrUpdateBillDiscountProduct(BillDiscountSaveVo paramVo, HttpServletRequest request){
        
        ResponseData<String> responseData = null;
        
        try {
            SSOClientUser user = (SSOClientUser) request.getSession().getAttribute(SSOClientConstants.USER_SESSION_KEY);
            IBillDiscountMaintainSV service = DubboConsumerFactory.getService(IBillDiscountMaintainSV.class);
            
            String resultMessage = null;
            String data = null;
            
            String productId = paramVo.getDiscountId();
            
            IBillDiscountQuerySV billDiscountQuerySV = DubboConsumerFactory.getService(IBillDiscountQuerySV.class);
    		BillDiscountListQueryRequest billDiscountListQueryRequest = new BillDiscountListQueryRequest();
    		billDiscountListQueryRequest.setTenantId(user.getTenantId());
    		billDiscountListQueryRequest.setAllPrdDiscount(BaaSOPConstants.PrdDiscountType.ALL);
    		BillDiscountListResponse listQueryResponse = billDiscountQuerySV.queryDiscountList(billDiscountListQueryRequest);
    		
    		EffectiveProductQueryRequest productQueryRequest = new EffectiveProductQueryRequest();
			productQueryRequest.setTenantId(user.getTenantId());
			if(StringUtils.isNotBlank(productId)){
				List<String> discountIds = new ArrayList<String>();
  				discountIds.add(productId);
  				productQueryRequest.setDiscountIds(discountIds);
			}
			productQueryRequest.setEffectDate(paramVo.getEffectDate()+" 00:00:00");
			productQueryRequest.setExpireDate(paramVo.getExpireDate()+" 23:59:59");
			EffectiveProductQueryResponse productQueryResponse = billDiscountQuerySV.queryEffectiveScopeProduct(productQueryRequest);
			boolean flag = true;
			
			if(productQueryResponse!=null && !CollectionUtil.isEmpty(productQueryResponse.getProductIds())){
				if(StringUtils.equals(BaaSOPConstants.PrdDiscountType.ALL, paramVo.getAllPrdDiscount())){
					responseData = new ResponseData<String>(ResponseData.AJAX_STATUS_FAILURE, "选择的产品在该有效期内已有参与优惠活动");
					flag = false;
				}else{
					for(String discountProductId: paramVo.getDiscountProductList()){
						if(productQueryResponse.getProductIds().contains(discountProductId)){
							responseData = new ResponseData<String>(ResponseData.AJAX_STATUS_FAILURE, "选择的产品在该有效期内已有参与优惠活动");
							flag = false;
							break;
						}
					}
				}
			}
			
			if(flag && listQueryResponse!=null && listQueryResponse.getDiscounts()!=null){
				for(BillDiscountInfo billDiscountInfo : listQueryResponse.getDiscounts()){
    				if(!StringUtils.equals(productId, billDiscountInfo.getDiscountId())	&&
    					!(DateUtil.getTimestamp(paramVo.getEffectDate()+" 00:00:00", DateUtil.DATETIME_FORMAT).getTime()>billDiscountInfo.getExpireDate().getTime() ||
    						DateUtil.getTimestamp(paramVo.getExpireDate()+" 23:59:59", DateUtil.DATETIME_FORMAT).getTime()<billDiscountInfo.getEffectDate().getTime())){
    					responseData = new ResponseData<String>(ResponseData.AJAX_STATUS_FAILURE, "选择的产品在该有效期内已有参与优惠活动");
    					flag = false;
    					break; 
    				}
    			}
			}
            if(flag){
            	if(StringUtil.isBlank(productId)){//新增
                    BillDiscountVo reqParam = new BillDiscountVo();
                    BeanUtils.copyProperties(paramVo, reqParam);
                    reqParam.setTenantId(user.getTenantId());//租户Id，必填
                    reqParam.setTradeSeq(TradeSeqUtil.newTradeSeq(user.getTenantId()));//流水号，必填
                    
                    if(!StringUtil.isBlank(paramVo.getEffectDate())){
                        reqParam.setEffectDate(DateUtil.getTimestamp(paramVo.getEffectDate()+" 00:00:00", DateUtil.DATETIME_FORMAT).toString());//生效日期
                    }
                    if(!StringUtil.isBlank(paramVo.getExpireDate())){
                        reqParam.setExpireDate(DateUtil.getTimestamp(paramVo.getExpireDate()+" 23:59:59", DateUtil.DATETIME_FORMAT).toString());//失效日期
                    }
                    
                    //处理赠品
                    if(StringUtils.equals(DiscountType.MZ, paramVo.getDiscountType())){
                    	GiftProduct giftProduct = new GiftProduct();
                    	if(!CollectionUtil.isEmpty(paramVo.getGiftProductIdList())){
                    		giftProduct.setProductIdList(paramVo.getGiftProductIdList());
                    	}
                        giftProduct.setActiveMode(paramVo.getGiftActiveMode());
                        giftProduct.setActivePeriod(paramVo.getGiftActivePeriod());
                        if(!StringUtil.isBlank(paramVo.getGiftEffectDate())){
                            giftProduct.setEffectDate(DateUtil.getTimestamp(paramVo.getGiftEffectDate()+" 00:00:00", DateUtil.DATETIME_FORMAT).toString());
                        }
                        giftProduct.setGiftType(paramVo.getGiftType());
                        if(StringUtils.isNotBlank(paramVo.getCashAmount())){
                        	giftProduct.setCashAmount(Double.valueOf(paramVo.getCashAmount()));
                        }
                        if(StringUtils.isNotBlank(paramVo.getVirtualCoinsNum())){
                        	giftProduct.setVirtualCoinsNum(Long.valueOf(paramVo.getVirtualCoinsNum()));
                        }
                        reqParam.setGiftProduct(giftProduct);
                    }
                    
                    LOG.error("账单优惠新增入参:" + JSONArray.fromObject(reqParam));
                    BillDiscountMaintainResponse resp = service.addBillDiscountProduct(reqParam);
                    LOG.error("账单优惠新增出参:" + JSONArray.fromObject(resp));
                    
                    resultMessage = "新增"+resp.getResponseHeader().getResultMessage();
                    data = resp.getResponseHeader().getResultCode();
                    
                }else{//修改
                    BillDiscountUpdateVo reqParam = new BillDiscountUpdateVo();
                    BeanUtils.copyProperties(paramVo, reqParam);
                    reqParam.setTenantId(user.getTenantId());//租户Id，必填
                    
                    if(!StringUtil.isBlank(paramVo.getEffectDate())){
                        reqParam.setEffectDate(DateUtil.getTimestamp(paramVo.getEffectDate()+" 00:00:00", DateUtil.DATETIME_FORMAT).toString());//生效日期
                    }else{
                        reqParam.setEffectDate(null);
                    }
                    if(!StringUtil.isBlank(paramVo.getExpireDate())){
                        reqParam.setExpireDate(DateUtil.getTimestamp(paramVo.getExpireDate()+" 23:59:59", DateUtil.DATETIME_FORMAT).toString());//失效日期
                    }else{
                        reqParam.setExpireDate(null);
                    }
                    
                    //处理账单科目
                    if(CollectionUtil.isEmpty(paramVo.getDiscountProductList())){
                        reqParam.setDiscountProductList(new ArrayList<String>());
                    }
                    
                    //处理赠品
                    if(StringUtils.equals(DiscountType.MZ, paramVo.getDiscountType())){
                    	GiftProduct giftProduct = new GiftProduct();
                    	if(!CollectionUtil.isEmpty(paramVo.getGiftProductIdList())){
                    		giftProduct.setProductIdList(paramVo.getGiftProductIdList());
                    	}
                        giftProduct.setActiveMode(paramVo.getGiftActiveMode());
                        giftProduct.setActivePeriod(paramVo.getGiftActivePeriod());
                        if(!StringUtil.isBlank(paramVo.getGiftEffectDate())){
                            giftProduct.setEffectDate(DateUtil.getTimestamp(paramVo.getGiftEffectDate()+" 00:00:00", DateUtil.DATETIME_FORMAT).toString());
                        }
                        giftProduct.setGiftType(paramVo.getGiftType());
                        if(StringUtils.isNotBlank(paramVo.getCashAmount())){
                        	giftProduct.setCashAmount(Double.valueOf(paramVo.getCashAmount()));
                        }
                        if(StringUtils.isNotBlank(paramVo.getVirtualCoinsNum())){
                        	giftProduct.setVirtualCoinsNum(Long.valueOf(paramVo.getVirtualCoinsNum()));
                        }
                        reqParam.setGiftProduct(giftProduct);
                    }
                    
                    LOG.error("账单优惠更新入参:" + JSONArray.fromObject(reqParam));
                    BaseResponse resp = service.updateBillDiscountProduct(reqParam);
                    LOG.error("账单优惠更新出参:" + JSONArray.fromObject(resp));
                    
                    resultMessage = "修改"+resp.getResponseHeader().getResultMessage();
                    data = resp.getResponseHeader().getResultCode();
                }
            	responseData = new ResponseData<String>(ResponseData.AJAX_STATUS_SUCCESS, resultMessage, data);
            }
        } catch (Exception e) {
            responseData = new ResponseData<String>(ResponseData.AJAX_STATUS_FAILURE, "账单优惠保存异常");
            LOG.error("账单优惠保存异常：", e);
        }
        return responseData;
    }
    
    /**
     * 删除账单优惠产品
     */
    @RequestMapping("/delete")
    public ResponseData<String> deleteBillDiscountProduct(HttpServletRequest request){
        ResponseData<String> responseData = null;
        SSOClientUser user = (SSOClientUser) request.getSession().getAttribute(SSOClientConstants.USER_SESSION_KEY);
        
        try {
            String productId = request.getParameter("productId");
            if(StringUtil.isBlank(productId)){
                return new ResponseData<String>(ResponseData.AJAX_STATUS_FAILURE, "产品ID不能为空！");
            }
            
            BillDiscountDeleteRequest paramReq = new BillDiscountDeleteRequest();
            paramReq.setTenantId(user.getTenantId());//租户Id
            paramReq.setDiscountId(productId);//产品Id
            
            IBillDiscountMaintainSV service = DubboConsumerFactory.getService(IBillDiscountMaintainSV.class);
            LOG.error("删除账单优惠产品入参:" + JSONArray.fromObject(paramReq));
            BaseResponse resp = service.deleteBillDiscountProduct(paramReq);
            LOG.error("删除账单优惠产品出参:" + JSONArray.fromObject(resp));
            
            responseData = new ResponseData<String>(ResponseData.AJAX_STATUS_SUCCESS, resp.getResponseHeader().getResultMessage(), null);
            LOG.debug("产品删除成功！");
        } catch (Exception e) {
            responseData = new ResponseData<String>(ResponseData.AJAX_STATUS_FAILURE, "产品删除失败!");
            LOG.debug("产品删除失败！",e);
        }
        
        return responseData;
    }
    
    /**
     * 更新状态（生效、待生效）
     */
    @RequestMapping("/updateStatus")
    public ResponseData<String> updateStatus(HttpServletRequest request){
        ResponseData<String> responseData = null;
        SSOClientUser user = (SSOClientUser) request.getSession().getAttribute(SSOClientConstants.USER_SESSION_KEY);
        
        try {
            String productId = request.getParameter("productId");
            String status = request.getParameter("feeStatus");
            if(StringUtil.isBlank(productId) || StringUtil.isBlank(status)){
                return new ResponseData<String>(ResponseData.AJAX_STATUS_FAILURE, "产品ID和状态不能为空！");
            }
            
            BillDiscountUpdateVo paramReq = new BillDiscountUpdateVo();
            paramReq.setTenantId(user.getTenantId());//租户Id
            paramReq.setDiscountId(productId);//产品Id
            paramReq.setStatus(status);//状态（0:失效 1:生效 2:待生效）
            
            IBillDiscountMaintainSV service = DubboConsumerFactory.getService(IBillDiscountMaintainSV.class);
            LOG.error("更新账单优惠产品状态入参:" + JSONArray.fromObject(paramReq));
            BaseResponse resp = service.updateBillDiscountProduct(paramReq);
            LOG.error("更新账单优惠产品状态出参:" + JSONArray.fromObject(resp));
            
            responseData = new ResponseData<String>(ResponseData.AJAX_STATUS_SUCCESS, resp.getResponseHeader().getResultMessage(), null);
            LOG.debug("状态修改成功！");
        } catch (Exception e) {
            responseData = new ResponseData<String>(ResponseData.AJAX_STATUS_FAILURE, "状态修改失败!");
            LOG.debug("状态修改失败！",e);
        }
        
        return responseData;
    }
    
    /**
     * 账单优惠产品查询（根据id查询单条记录）
     */
    @RequestMapping("/getByProductId")
    public ResponseData<BillDiscountResp> getByProductId(HttpServletRequest request) {
        
        ResponseData<BillDiscountResp> responseData = null;
        try {
            SSOClientUser user = (SSOClientUser) request.getSession().getAttribute(SSOClientConstants.USER_SESSION_KEY);
            String tenantId = user.getTenantId();
            
            BillDiscountQueryRequest reqParam = new BillDiscountQueryRequest();
            reqParam.setTenantId(tenantId);
            reqParam.setDiscountId(request.getParameter("productId"));
            
            IBillDiscountQuerySV service = DubboConsumerFactory.getService(IBillDiscountQuerySV.class);
            LOG.error("账单优惠查询入参:" + JSONArray.fromObject(reqParam));
            BillDiscountQueryResponse resp = service.queryBillDiscountProduct(reqParam);
            LOG.error("账单优惠查询出参:" + JSONArray.fromObject(resp));
            
            BillDiscountResp result = new BillDiscountResp();
            if(resp != null){
                BillDiscountInfo billDiscountProduct = resp.getInfo();
                BeanUtils.copyProperties(billDiscountProduct, result);
                
                result = this.setAttValueToResp(tenantId, result, billDiscountProduct);
            }
            
            responseData = new ResponseData<BillDiscountResp>(ResponseData.AJAX_STATUS_SUCCESS, "查询成功", result);
        } catch (Exception e) {
            responseData = new ResponseData<BillDiscountResp>(ResponseData.AJAX_STATUS_FAILURE, "账单优惠查询异常");
            LOG.error("账单优惠查询出错：", e);
        }
        return responseData;
    }
    
    private BillDiscountResp setAttValueToResp(String tenantId, BillDiscountResp result, 
                    BillDiscountInfo billDiscountProduct){
        try {
            //关联账单科目
            List<String> relatedSubjectList = billDiscountProduct.getRelatedSubjectList();
            if(!CollectionUtil.isEmpty(relatedSubjectList)){
                result.setRelatedSubjectId(relatedSubjectList.get(0));
            }
            
            List<String> discountProductIdListList = new ArrayList<String>();//优惠科目ID
            List<String> productIdList = new ArrayList<String>();//满赠赠品ID
            
            List<ExtendInfo> extendInfoList = billDiscountProduct.getExtendInfoList();
            for(ExtendInfo extendInfo : extendInfoList){
                if(BDAttrName.DISCOUNT_PRODUCT_ID.equals(extendInfo.getAttrName())){//优惠科目ID
                	discountProductIdListList.add(extendInfo.getAttrValue());
                }else if(BDAttrName.GIFT_PRODUCT_ID.equals(extendInfo.getAttrName())){//满赠赠品
            		if(DiscountType.MZ.equals(billDiscountProduct.getDiscountType())){
            			productIdList.add(extendInfo.getAttrValue());
                    }
                }else if(BDAttrName.FULL_COST_AMOUNT.equals(extendInfo.getAttrName())){//满赠（满减）到达金额
                    result.setFullCostAmount(extendInfo.getAttrValue());
                }else if(BDAttrName.DISCOUNT_UNIT_ID.equals(extendInfo.getAttrName())){
                    result.setDiscountUnitId(extendInfo.getAttrValue());
                }else if(BDAttrName.DISCOUNT_RULL_TYPE.equals(extendInfo.getAttrName())){
                    result.setDiscountRullType(extendInfo.getAttrValue());
                }else if(BDAttrName.FULL_COST_UNIT_ID.equals(extendInfo.getAttrName())){
                    result.setFullCostUnitId(extendInfo.getAttrValue());
                }else if(BDAttrName.DISCOUNT_AMOUNT.equals(extendInfo.getAttrName())){//扣减金额
                    result.setDiscountAmount(extendInfo.getAttrValue());
                }else if(BDAttrName.DISCOUNT_PERCENT.equals(extendInfo.getAttrName())){//折扣比例
                    result.setDiscountPercent(extendInfo.getAttrValue());
                }else if(BDAttrName.BD_AMOUNT.equals(extendInfo.getAttrName())){//保底金额
                    result.setBottomAmount(extendInfo.getAttrValue());
                }else if(BDAttrName.FD_AMOUNT.equals(extendInfo.getAttrName())){//封顶金额
                    result.setTopAmount(extendInfo.getAttrValue());
                }else if(BDAttrName.ACTIVE_MODE.equals(extendInfo.getAttrName())){//赠品：生效方式
                    result.setGiftActiveMode(extendInfo.getAttrValue());
                }else if(BDAttrName.ACTIVE_PERIOD.equals(extendInfo.getAttrName())){//赠品：赠送业务周期
                    result.setGiftActivePeriod(extendInfo.getAttrValue());
                }else if(BDAttrName.EFFECT_DATE.equals(extendInfo.getAttrName())){//赠品：生效时间
                    result.setGiftEffectDate(extendInfo.getAttrValue());
                }else if(BDAttrName.DISCOUNT_START_TIME.equals(extendInfo.getAttrName())){//折扣开始时间
                    result.setStartTime(extendInfo.getAttrValue());
                }else if(BDAttrName.DISCOUNT_END_TIME.equals(extendInfo.getAttrName())){//折扣结束时间
                    result.setEndTime(extendInfo.getAttrValue());
                }
            }
            
            //优惠科目查询
            List<ElementDetailRequireResult> subjectInfoList = new ArrayList<ElementDetailRequireResult>();
            if(!CollectionUtil.isEmpty(discountProductIdListList)){
                for(String categoryId : discountProductIdListList){
                	
                	IPriceElementSV priceElementSV = DubboConsumerFactory.getService(IPriceElementSV.class);
            		ElementDetailRequireVO requireVO = new ElementDetailRequireVO();
        			requireVO.setTenantId(tenantId);
        			requireVO.setCategoryId(categoryId);
        			ElementDetailRequireResult response = priceElementSV.searchElementDetail(requireVO);
                    subjectInfoList.add(response);
                }
            }
            if(!CollectionUtil.isEmpty(subjectInfoList)){
                result.setDiscountProductList(subjectInfoList);
            }
            
            //满赠赠品查询
            if(DiscountType.MZ.equals(billDiscountProduct.getDiscountType())){
            	List<Object> productInfoList = new ArrayList<Object>();
                if(!CollectionUtil.isEmpty(productIdList) && billDiscountProduct.getGiftProduct()!=null){
                	if(BaaSOPConstants.GiftType.GIFT_TYPE_YW.equals(billDiscountProduct.getGiftProduct().getGiftType())){
                		IPriceElementSV priceElementSV = DubboConsumerFactory.getService(IPriceElementSV.class);
                		for(String categoryId : productIdList){
                    		ElementDetailRequireVO requireVO = new ElementDetailRequireVO();
                			requireVO.setTenantId(tenantId);
                			requireVO.setCategoryId(categoryId);
                			ElementDetailRequireResult response = priceElementSV.searchElementDetail(requireVO);
                			productInfoList.add(response);
                        }
                	}else if(BaaSOPConstants.GiftType.GIFT_TYPE_YHQ.equals(billDiscountProduct.getGiftProduct().getGiftType())){
                		ICouponSV icouponSV = DubboConsumerFactory.getService(ICouponSV.class);
                		for(String couponId : productIdList){
                			SingleCouponReq portReq = new SingleCouponReq();
	                		portReq.setCouponId(couponId);
	                		portReq.setTenantId(tenantId);
	                		CouponResponse response = icouponSV.getSingleCoupon(portReq);
	                		productInfoList.add(response);
                		}
                	}
                }
                if(!CollectionUtil.isEmpty(productInfoList)){
                    result.setGiftProductList(productInfoList);
                }
                if(billDiscountProduct.getGiftProduct()!=null){
                	result.setGiftType(billDiscountProduct.getGiftProduct().getGiftType());
                	result.setGiftActiveMode(billDiscountProduct.getGiftProduct().getActiveMode());
                	result.setGiftActivePeriod(billDiscountProduct.getGiftProduct().getActivePeriod());
                	if(billDiscountProduct.getGiftProduct().getEffectDate()!=null){
                		result.setGiftEffectDate(DateUtil.getDateString(billDiscountProduct.getGiftProduct().getEffectDate(), DateUtil.DATETIME_FORMAT));
                	}
                	result.setCashAmount(billDiscountProduct.getGiftProduct().getCashAmount());
                	result.setVirtualCoinsNum(billDiscountProduct.getGiftProduct().getVirtualCoinsNum());
                }
            }
            
        } catch (Exception e) {
            LOG.error("账单优惠查询出错：", e);
            return result;
        }
        
        return result;
    }
        
    /**
	 * 
	 * @param queryVo
	 * @param request
	 * @return
	 */
    @RequestMapping("/getRelatedProductList")
    public ResponseData<PageInfo<Product>> getRelatedProductList(BillProductQueryVo queryVo, HttpServletRequest request){
    	
    	ResponseData<PageInfo<Product>> responseData = null;
    	try {
    		SSOClientUser user = (SSOClientUser) request.getSession().getAttribute(SSOClientConstants.USER_SESSION_KEY);

    		IPriceElementSV elementSV = DubboConsumerFactory.getService(IPriceElementSV.class);
    		ElementRequireVO queryParam = new ElementRequireVO();
    		queryParam.setTenantId(user.getTenantId());
    		queryParam.setMainProductId(queryVo.getMainProductId());
    		queryParam.setMainProductName(queryVo.getMainProductName());
    		queryParam.setBillingCycle(queryVo.getBillingCycle());
    		
	    	queryParam.setPageNo(queryVo.getPageNo());
	    	queryParam.setPageSize(queryVo.getPageSize());
    		
	    	LOG.info("优惠活动产品查询入參"+JSONObject.fromObject(queryParam).toString());
	    	ElementRequireResult response = elementSV.searchElement(queryParam);
	    	LOG.info("优惠活动产品查询出參"+JSONObject.fromObject(response).toString());
 	    	if(response.getResponseHeader().isSuccess()){
 	    		if(response.getProducts()!=null && !CollectionUtil.isEmpty(response.getProducts().getResult())){
 	    			List<Product> products = new ArrayList<Product>();
 	 	    		IBillDiscountQuerySV service = DubboConsumerFactory.getService(IBillDiscountQuerySV.class);
 	 	    		BillDiscountListQueryRequest billDiscountListQueryRequest = new BillDiscountListQueryRequest();
 	 	    		billDiscountListQueryRequest.setTenantId(user.getTenantId());
 	 	    		billDiscountListQueryRequest.setAllPrdDiscount(BaaSOPConstants.PrdDiscountType.ALL);
 	 	    		BillDiscountListResponse listQueryResponse = service.queryDiscountList(billDiscountListQueryRequest);
 	 	    		boolean flag = true;
 	 	    		if(listQueryResponse!=null && listQueryResponse.getDiscounts()!=null){
 	 	    			for(BillDiscountInfo billDiscountInfo : listQueryResponse.getDiscounts()){
 	 	    				if(!StringUtils.equals(billDiscountInfo.getDiscountId(), queryVo.getDiscountId()) &&
 	 	    						!(DateUtil.getTimestamp(queryVo.getEffectDate()+" 00:00:00", DateUtil.DATETIME_FORMAT).getTime()>billDiscountInfo.getExpireDate().getTime() ||
 	 	    						DateUtil.getTimestamp(queryVo.getExpireDate()+" 23:59:59", DateUtil.DATETIME_FORMAT).getTime()<billDiscountInfo.getEffectDate().getTime())){
 	 	    					flag = false;
 	 	    					break; 
 	 	    				}
 	 	    			}
 	 	    		}
 	 	    		if(flag){
 	 	    		   
// 	 	    			List<String> discountProductIds = new ArrayList<String>();
// 	 	    		    if(StringUtils.isNotBlank(queryVo.getDiscountId())){
// 	 	    			   BillDiscountQueryRequest reqParam = new BillDiscountQueryRequest();
// 	 	 	               reqParam.setTenantId(user.getTenantId());
// 	 	 	               reqParam.setDiscountId(queryVo.getDiscountId());
// 	 	 	               
// 	 	 	               BillDiscountQueryResponse resp = service.queryBillDiscountProduct(reqParam);
// 	 	 	               
// 	 	 	               if(resp.getInfo()!=null){
// 	 	 	            	 for(ExtendInfo extendInfo : resp.getInfo().getExtendInfoList()){
// 	 	 	 	                if(BDAttrName.DISCOUNT_PRODUCT_ID.equals(extendInfo.getAttrName())){
// 	 	 	 	                	discountProductIds.add(extendInfo.getAttrValue());
// 	 	 	 	                }
// 	 	 	 	               }
// 	 	 	               }
// 	 	    		    }
 	    				EffectiveProductQueryRequest productQueryRequest = new EffectiveProductQueryRequest();
 	    				productQueryRequest.setTenantId(user.getTenantId());
 	    				if(StringUtils.isNotBlank(queryVo.getDiscountId())){
 	    					List<String> discountIds = new ArrayList<String>();
 	 	    				discountIds.add(queryVo.getDiscountId());
 	 	    				productQueryRequest.setDiscountIds(discountIds);
 	    				}
 	    				
 	    				productQueryRequest.setEffectDate(queryVo.getEffectDate()+" 00:00:00");
 	    				productQueryRequest.setExpireDate(queryVo.getExpireDate()+" 23:59:59");
 	    				EffectiveProductQueryResponse productQueryResponse = service.queryEffectiveScopeProduct(productQueryRequest);
 	    				if(productQueryResponse!=null && !CollectionUtil.isEmpty(productQueryResponse.getProductIds())){
 	    					for(Product product: response.getProducts().getResult()){
 	    						if(!productQueryResponse.getProductIds().contains(product.getCategoryId())){
 	    							products.add(product);
 	    						}
 	    					}
 	    				}else{
 	    					products = response.getProducts().getResult();
 	    				}
 	    			}
 	 	    		response.getProducts().setResult(products);
 	    		}
 	    		responseData = new ResponseData<PageInfo<Product>>(ResponseData.AJAX_STATUS_SUCCESS, "查询成功", response.getProducts());
 	    	}else{
 	    		responseData = new ResponseData<PageInfo<Product>>(ResponseData.AJAX_STATUS_FAILURE, "查询失败");
 	    	}
    		
    	} catch (Exception e) {
			responseData = new ResponseData<PageInfo<Product>>(ResponseData.AJAX_STATUS_FAILURE,"获取信息异常");
			LOG.error("获取信息出错：", e);
		}
        return responseData;
    }
    
    /**
	 * 查询优惠券
	 * @param queryVo
	 * @param request
	 * @return
	 */
    @RequestMapping("/getRelatedCouponList")
    public ResponseData<PageInfo<CouponInfo>> getRelatedCouponList(CouponReq queryVo, HttpServletRequest request){
    	
    	ResponseData<PageInfo<CouponInfo>> responseData = null;
    	try {
    		SSOClientUser user = (SSOClientUser) request.getSession().getAttribute(SSOClientConstants.USER_SESSION_KEY);

    		ICouponSV icouponSV = DubboConsumerFactory.getService(ICouponSV.class);
    		queryVo.setTenantId(user.getTenantId());
    		
    		String strPageNo=(null==request.getParameter(BaaSOPConstants.PAGENO))?"1":request.getParameter(BaaSOPConstants.PAGENO);
            String strPageSize=(null==request.getParameter(BaaSOPConstants.PAGESIZE))?"10":request.getParameter(BaaSOPConstants.PAGESIZE);
            queryVo.setPageNO(Integer.parseInt(strPageNo));
            queryVo.setPageSize(Integer.parseInt(strPageSize));
            CouponListResponse response = icouponSV.getGiftCoupons(queryVo);
 	    	if(response.getResponseHeader().isSuccess()){
 	    		
 	    		responseData = new ResponseData<PageInfo<CouponInfo>>(ResponseData.AJAX_STATUS_SUCCESS, "查询成功", response.getPageInfo());
 	    	}else{
 	    		responseData = new ResponseData<PageInfo<CouponInfo>>(ResponseData.AJAX_STATUS_FAILURE, "查询失败");
 	    	}
    		
    	} catch (Exception e) {
			responseData = new ResponseData<PageInfo<CouponInfo>>(ResponseData.AJAX_STATUS_FAILURE,"获取信息异常");
			LOG.error("获取信息出错：", e);
		}
        return responseData;
    }
    
    /**
	 * 查询已经参加优惠活动的优惠券id
	 * @param request
	 * @return
	 */
    @RequestMapping("/queryEffectiveCoupon")
    public ResponseData<List<String>> queryEffectiveCoupon(HttpServletRequest request){
    	
    	ResponseData<List<String>> responseData = null;
    	try {
    		SSOClientUser user = (SSOClientUser) request.getSession().getAttribute(SSOClientConstants.USER_SESSION_KEY);

    		IBillDiscountQuerySV billDiscountQuerySV = DubboConsumerFactory.getService(IBillDiscountQuerySV.class);
    		BaseInfo queryVo = new BaseInfo();
    		queryVo.setTenantId(user.getTenantId());
    		
    		EffectiveProductQueryResponse response = billDiscountQuerySV.queryEffectiveCoupon(queryVo);
 	    	if(response.getResponseHeader().isSuccess()){
 	    		
 	    		responseData = new ResponseData<List<String>>(ResponseData.AJAX_STATUS_SUCCESS, "查询成功", response.getProductIds());
 	    	}else{
 	    		responseData = new ResponseData<List<String>>(ResponseData.AJAX_STATUS_FAILURE, "查询失败");
 	    	}
    		
    	} catch (Exception e) {
			responseData = new ResponseData<List<String>>(ResponseData.AJAX_STATUS_FAILURE,"获取信息异常");
			LOG.error("获取信息出错：", e);
		}
        return responseData;
    }
    
    //翻译字段（优惠类型）
    private List<BillDiscountInfoVo> transalteBillDiscountEntity(String tenantId, List<BillDiscountInfo> productList){
        
        //优惠类型
        List<BaseCode> baseCodeList = ParamController.getSysParams(tenantId, "BILL_DISCOUNT_TYPE", TradeSeqUtil.newTradeSeq(tenantId));
        //优惠类型
        List<BaseCode> discountRullTypeList = ParamController.getSysParams(tenantId, "DISCOUNT_RULL_TYPE", TradeSeqUtil.newTradeSeq(tenantId));
        //优惠类型
        List<BaseCode> costUnitIdList = ParamController.getSysParams(tenantId, "COST_UNIT_ID", TradeSeqUtil.newTradeSeq(tenantId));
        
        List<BillDiscountInfoVo> resultList = new ArrayList<BillDiscountInfoVo>();
        if(!CollectionUtil.isEmpty(productList)){
            for(BillDiscountInfo productInfo : productList){
                BillDiscountInfoVo result = new BillDiscountInfoVo();
                BeanUtils.copyProperties(productInfo, result);
                
                if(!CollectionUtil.isEmpty(baseCodeList)){
                    for(BaseCode code : baseCodeList){
                        if(code.getParamCode().equalsIgnoreCase(productInfo.getDiscountType())){
                            result.setDiscountTypeDesc(code.getParamName());
                        }
                    }
                }
                if(StringUtils.equals(BaaSOPConstants.DiscountType.MJ, productInfo.getDiscountType())){
                	result.setDiscountRuleStr(this.getMjDiscountRuleStr(productInfo, discountRullTypeList, costUnitIdList));
                }else if(StringUtils.equals(BaaSOPConstants.DiscountType.MZ, productInfo.getDiscountType())){
                	if(productInfo.getGiftProduct()!=null){
                		String fullCostAmount = "",fullCostUnitId = "";
            			StringBuffer giftProduct = new StringBuffer("");
                		if(StringUtils.equals(BaaSOPConstants.GiftType.GIFT_TYPE_YW, productInfo.getGiftProduct().getGiftType())){
                			IPriceElementSV priceElementSV = DubboConsumerFactory.getService(IPriceElementSV.class);
                    		for(ExtendInfo extendInfo : productInfo.getExtendInfoList()){
                    			if(StringUtils.equals(BaaSOPConstants.BDAttrName.GIFT_PRODUCT_ID, extendInfo.getAttrName())){
                    				ElementDetailRequireVO requireVO = new ElementDetailRequireVO();
                        			requireVO.setTenantId(tenantId);
                        			requireVO.setCategoryId(extendInfo.getAttrValue());
                        			ElementDetailRequireResult response = priceElementSV.searchElementDetail(requireVO);
                        			giftProduct.append(response.getCategoryName()+",");
                    			}else if(StringUtils.equals(BaaSOPConstants.BDAttrName.FULL_COST_AMOUNT, extendInfo.getAttrName())){
                    				fullCostAmount = extendInfo.getAttrValue();
                    			}else if(StringUtils.equals(BaaSOPConstants.BDAttrName.FULL_COST_UNIT_ID, extendInfo.getAttrName())){
                    				for(BaseCode code : costUnitIdList){
                                        if(code.getParamCode().equalsIgnoreCase(extendInfo.getAttrValue())){
                                        	fullCostUnitId = code.getParamName();
                                        }
                                    }
                    			}
                            }
                    		if(!StringUtils.isBlank(giftProduct.toString())){
                    			result.setDiscountRuleStr("满"+fullCostAmount+fullCostUnitId+"赠送"+giftProduct.toString().substring(0, giftProduct.toString().length()-1));
                    		}else{
                    			result.setDiscountRuleStr("满"+fullCostAmount+fullCostUnitId+"赠送");
                    		}
                		}else if(StringUtils.equals(BaaSOPConstants.GiftType.GIFT_TYPE_YHQ, productInfo.getGiftProduct().getGiftType())){
                			ICouponSV icouponSV = DubboConsumerFactory.getService(ICouponSV.class);
                    		for(ExtendInfo extendInfo : productInfo.getExtendInfoList()){
                    			if(StringUtils.equals(BaaSOPConstants.BDAttrName.GIFT_PRODUCT_ID, extendInfo.getAttrName())){
                    				SingleCouponReq portReq = new SingleCouponReq();
                    				portReq.setTenantId(tenantId);
        	                		portReq.setCouponId(extendInfo.getAttrValue());
        	                		CouponResponse response = icouponSV.getSingleCoupon(portReq);
        	                		if(response!=null && response.getSingleCoupon()!=null){
        	                			giftProduct.append(response.getSingleCoupon().getCouponName()+",");
        	                		}
                    			}else if(StringUtils.equals(BaaSOPConstants.BDAttrName.FULL_COST_AMOUNT, extendInfo.getAttrName())){
                    				fullCostAmount = extendInfo.getAttrValue();
                    			}else if(StringUtils.equals(BaaSOPConstants.BDAttrName.FULL_COST_UNIT_ID, extendInfo.getAttrName())){
                    				for(BaseCode code : costUnitIdList){
                                        if(code.getParamCode().equalsIgnoreCase(extendInfo.getAttrValue())){
                                        	fullCostUnitId = code.getParamName();
                                        }
                                    }
                    			}
                    		}
                    		if(!StringUtils.isBlank(giftProduct.toString())){
                    			result.setDiscountRuleStr("满"+fullCostAmount+fullCostUnitId+"赠送"+giftProduct.toString().substring(0, giftProduct.toString().length()-1));
                    		}else{
                    			result.setDiscountRuleStr("满"+fullCostAmount+fullCostUnitId+"赠送");
                    		}
                		}else if(StringUtils.equals(BaaSOPConstants.GiftType.GIFT_TYPE_XJ, productInfo.getGiftProduct().getGiftType()) || 
                				StringUtils.equals(BaaSOPConstants.GiftType.GIFT_TYPE_XNB, productInfo.getGiftProduct().getGiftType())){
                    		for(ExtendInfo extendInfo : productInfo.getExtendInfoList()){
                    			if(StringUtils.equals(BaaSOPConstants.BDAttrName.FULL_COST_AMOUNT, extendInfo.getAttrName())){
                    				fullCostAmount = extendInfo.getAttrValue();
                    			}else if(StringUtils.equals(BaaSOPConstants.BDAttrName.FULL_COST_UNIT_ID, extendInfo.getAttrName())){
                    				for(BaseCode code : costUnitIdList){
                                        if(code.getParamCode().equalsIgnoreCase(extendInfo.getAttrValue())){
                                        	fullCostUnitId = code.getParamName();
                                        }
                                    }
                    			}
                    		}
                    		if(StringUtils.equals(BaaSOPConstants.GiftType.GIFT_TYPE_XJ, productInfo.getGiftProduct().getGiftType())){
                    			result.setDiscountRuleStr("满"+fullCostAmount+fullCostUnitId+"赠送现金"+productInfo.getGiftProduct().getCashAmount()+"元");
                    		}else{
                    			result.setDiscountRuleStr("满"+fullCostAmount+fullCostUnitId+"赠送虚拟币"+productInfo.getGiftProduct().getVirtualCoinsNum()+"个");
                    		}
                		}
                	}
                }else if(StringUtils.equals(BaaSOPConstants.DiscountType.DZ, productInfo.getDiscountType())){
                	String discountPercent = "";
                	for(ExtendInfo extendInfo : productInfo.getExtendInfoList()){
                		if(StringUtils.equals(BaaSOPConstants.BDAttrName.DISCOUNT_PERCENT, extendInfo.getAttrName())){
                			discountPercent = extendInfo.getAttrValue();
                		}
                	}
                	result.setDiscountRuleStr("打"+discountPercent+"折");
                }
                resultList.add(result);
            }
        }
        return resultList;
    }
    
    private String getMjDiscountRuleStr(BillDiscountInfo productInfo, List<BaseCode> discountRullTypeList, List<BaseCode> costUnitIdList){
    	String fullCostAmount = "", 
    		   fullCostUnitId = "",
    		   discountRullType = "",
			   discountAmount = "",
			   discountUnitId = "";
    	if(productInfo!=null){
    		List<ExtendInfo> extendInfos = productInfo.getExtendInfoList();
    		for(ExtendInfo info:extendInfos){
    			if(StringUtils.equals(BaaSOPConstants.BDAttrName.FULL_COST_AMOUNT, info.getAttrName())){
    				fullCostAmount = info.getAttrValue();
    			}
    			if(StringUtils.equals(BaaSOPConstants.BDAttrName.FULL_COST_UNIT_ID, info.getAttrName())){
    				for(BaseCode code : costUnitIdList){
                        if(code.getParamCode().equalsIgnoreCase(info.getAttrValue())){
                        	fullCostUnitId = code.getParamName();
                        }
                    }
    			}
    			if(StringUtils.equals(BaaSOPConstants.BDAttrName.DISCOUNT_RULL_TYPE, info.getAttrName())){
    				for(BaseCode code : discountRullTypeList){
                        if(code.getParamCode().equalsIgnoreCase(info.getAttrValue())){
                        	discountRullType = code.getParamName();
                        }
                    }
    			}
    			if(StringUtils.equals(BaaSOPConstants.BDAttrName.DISCOUNT_AMOUNT, info.getAttrName())){
    				discountAmount = info.getAttrValue();
    			}
    			if(StringUtils.equals(BaaSOPConstants.BDAttrName.DISCOUNT_UNIT_ID, info.getAttrName())){
    				for(BaseCode code : costUnitIdList){
                        if(code.getParamCode().equalsIgnoreCase(info.getAttrValue())){
                        	discountUnitId = code.getParamName();
                        }
                    }
    			}
    		}
    		
    	}
    	return "满"+fullCostAmount+fullCostUnitId+discountRullType+discountAmount+discountUnitId;
    }
    
    //翻译字段（计费类型、业务类型）
    private List<ProductInfo> transalteProductEntity(String tenantId, List<ProductInfo> productInfoList){
        
        //计费类型
        List<BaseCode> billingType = ParamController.getSysParams(tenantId, "GROUP_BILLING_TYPE", TradeSeqUtil.newTradeSeq(tenantId));
        //业务类型
        List<BaseCode> serviceDetail = ParamController.getSysParams(tenantId, "SERVICE_DETAIL", TradeSeqUtil.newTradeSeq(tenantId));
        
        if(!CollectionUtil.isEmpty(productInfoList)){
            for(ProductInfo productInfo : productInfoList){
                //1.翻译计费类型
                if(!CollectionUtil.isEmpty(billingType)){
                    for(BaseCode code : billingType){
                        if(code.getParamCode().equalsIgnoreCase(productInfo.getBillingType())){
                            productInfo.setTradeSeq(code.getParamName());
                        }
                    }
                }
                
                //2.翻译业务类型
                if(!CollectionUtil.isEmpty(serviceDetail)){
                    List<ServiceVO> usageList = productInfo.getUsageList();
                    for(ServiceVO serviceVO : usageList){
                        for(BaseCode code : serviceDetail){
                            if(code.getParamCode().equalsIgnoreCase(serviceVO.getServiceTypeDetail())){
                                serviceVO.setServiceTypeDetail(code.getParamName());
                            }
                        }
                    }
                    productInfo.setUsageList(usageList);
                }
            }
        }
        
        return productInfoList;
    }
    
}
