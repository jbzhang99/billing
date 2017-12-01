package com.ai.baas.op.web.controller.billdiscount;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ai.baas.amc.api.billdiscount.interfaces.IBillDiscountProductMaintainSV;
import com.ai.baas.amc.api.billdiscount.param.BillDiscountProductDeleteRequest;
import com.ai.baas.amc.api.billdiscount.param.BillDiscountProductMaintainResponse;
import com.ai.baas.amc.api.billdiscount.param.BillDiscountProductUpdateVo;
import com.ai.baas.amc.api.billdiscount.param.BillDiscountProductVo;
import com.ai.baas.amc.api.billdiscount.param.GiftProduct;
import com.ai.baas.amc.api.billdiscountquery.interfaces.IBillDiscountProductQuerySV;
import com.ai.baas.amc.api.billdiscountquery.param.BillDiscountProductInfo;
import com.ai.baas.amc.api.billdiscountquery.param.BillDiscountProductListQueryRequest;
import com.ai.baas.amc.api.billdiscountquery.param.BillDiscountProductListQueryResponse;
import com.ai.baas.amc.api.billdiscountquery.param.BillDiscountProductQueryRequest;
import com.ai.baas.amc.api.billdiscountquery.param.BillDiscountProductQueryResponse;
import com.ai.baas.amc.api.billdiscountquery.param.ExtendInfo;
import com.ai.baas.bmc.api.baseInfo.params.BaseCode;
import com.ai.baas.bmc.api.marktableproduct.interfaces.IQueryProductSV;
import com.ai.baas.bmc.api.marktableproduct.params.ProductInfo;
import com.ai.baas.bmc.api.marktableproduct.params.ProductQueryByIdListVO;
import com.ai.baas.bmc.api.marktableproduct.params.ProductQueryVO;
import com.ai.baas.bmc.api.marktableproduct.params.ServiceVO;
import com.ai.baas.op.web.constants.BaaSOPConstants;
import com.ai.baas.op.web.constants.BaaSOPConstants.BDAttrName;
import com.ai.baas.op.web.constants.BaaSOPConstants.DiscountType;
import com.ai.baas.op.web.controller.common.ParamController;
import com.ai.baas.op.web.model.BillDiscountProductInfoVo;
import com.ai.baas.op.web.model.BillDiscountProductResp;
import com.ai.baas.op.web.model.BillDiscountQueryVo;
import com.ai.baas.op.web.model.BillDiscountVo;
import com.ai.baas.op.web.model.ProductInfoVo;
import com.ai.baas.op.web.model.ServiceInfo;
import com.ai.baas.op.web.util.DateUtil;
import com.ai.baas.op.web.util.TradeSeqUtil;
import com.ai.net.xss.util.CollectionUtil;
import com.ai.opt.base.vo.BaseResponse;
import com.ai.opt.base.vo.PageInfo;
import com.ai.opt.sdk.dubbo.util.DubboConsumerFactory;
import com.ai.opt.sdk.web.model.ResponseData;
import com.ai.opt.sso.client.filter.SSOClientConstants;
import com.ai.opt.sso.client.filter.SSOClientUser;
import com.ai.opt.sys.api.gnsubject.interfaces.IGnSubjectQuerySV;
import com.ai.opt.sys.api.gnsubject.param.GnSubjectInfoVo;
import com.ai.opt.sys.api.gnsubject.param.GnSubjectListResponse;
import com.ai.opt.sys.api.gnsubject.param.GnSubjectQueryVo;
import com.ai.opt.sys.api.gnsubject.param.GnSubjectTenantIdSubjectIdRequest;
import com.ai.paas.ipaas.util.StringUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 账单优惠
 */
@RestController
@RequestMapping("/billDiscount")
public class BillDiscountController {

	private static final Logger LOG = Logger.getLogger(BillDiscountController.class);
	
	@RequestMapping("/list")
    public ModelAndView list(HttpServletRequest request) {
	    SSOClientUser user = (SSOClientUser) request.getSession().getAttribute(SSOClientConstants.USER_SESSION_KEY);
	    String phone = user.getPhone();
	    request.setAttribute("phoneNum", phone);
        return new ModelAndView("jsp/billdiscount/billDiscountList");
    }
	
	@RequestMapping("/toAdd")
    public ModelAndView toAdd(HttpServletRequest request) {
        return new ModelAndView("jsp/billdiscount/addBillDiscount");
    }
	
	@RequestMapping("/toUpdate")
	public ModelAndView toUpdate(HttpServletRequest request) {
	    request.setAttribute("dt", request.getParameter("dt"));//计费类型
	    request.setAttribute("flag", request.getParameter("flag"));//查看、更新标记
	    request.setAttribute("productId", request.getParameter("productId"));
	    return new ModelAndView("jsp/billdiscount/addBillDiscount");
	}
	
	/**
     * 账单优惠产品列表查询
     */
    @RequestMapping("/getList")
    public ResponseData<PageInfo<BillDiscountProductInfoVo>> getList(BillDiscountQueryVo queryVo, HttpServletRequest request){
        
        ResponseData<PageInfo<BillDiscountProductInfoVo>> responseData = null;
        
        try {
            SSOClientUser user = (SSOClientUser) request.getSession().getAttribute(SSOClientConstants.USER_SESSION_KEY);
            String tenantId = user.getTenantId();
            
            BillDiscountProductListQueryRequest reqParam = new BillDiscountProductListQueryRequest();
            reqParam.setTenantId(tenantId);
            if(!StringUtil.isBlank(queryVo.getProductId())){
                reqParam.setProductId(queryVo.getProductId());//账单优惠产品ID
            }
            if(!StringUtil.isBlank(queryVo.getDiscountType())){
                reqParam.setDiscountType(queryVo.getDiscountType());//账单优惠类型 mz:满赠 mj:满减 dz:限时折扣 bd:保底 fd:封顶
            }
            if(!StringUtil.isBlank(queryVo.getProductName())){
                reqParam.setProductName(queryVo.getProductName());//优惠活动名称
            }
            if(!StringUtil.isBlank(queryVo.getEffectDate())){
                reqParam.setEffectDate(DateUtil.getTimestamp(queryVo.getEffectDate()+" 00:00:00", DateUtil.DATETIME_FORMAT));//生效日期
            }
            if(!StringUtil.isBlank(queryVo.getExpireDate())){
                reqParam.setExpireDate(DateUtil.getTimestamp(queryVo.getExpireDate()+" 23:59:59", DateUtil.DATETIME_FORMAT));//失效日期
            }
            
            String strPageNo=(null==request.getParameter(BaaSOPConstants.PAGENO))?"1":request.getParameter(BaaSOPConstants.PAGENO);
            String strPageSize=(null==request.getParameter(BaaSOPConstants.PAGESIZE))?"10":request.getParameter(BaaSOPConstants.PAGESIZE);
            PageInfo<BillDiscountProductInfo> pageInfo = new PageInfo<BillDiscountProductInfo>();
            pageInfo.setPageNo(Integer.parseInt(strPageNo));
            pageInfo.setPageSize(Integer.parseInt(strPageSize));
            reqParam.setPageInfo(pageInfo);
            
            IBillDiscountProductQuerySV service = DubboConsumerFactory.getService(IBillDiscountProductQuerySV.class);
            LOG.debug("账单优惠列表查询入参:" + JSONArray.fromObject(reqParam));
            BillDiscountProductListQueryResponse queryList = service.queryBillDiscountProductList(reqParam);
            LOG.debug("账单优惠列表查询出参:" + JSONArray.fromObject(queryList));
            
            PageInfo<BillDiscountProductInfoVo> result = new PageInfo<BillDiscountProductInfoVo>();
            pageInfo = queryList!=null ? queryList.getPageInfo() : null;
            if(pageInfo != null){
                result.setPageCount(pageInfo.getPageCount());
                result.setCount(pageInfo.getCount());
                result.setPageNo(pageInfo.getPageNo());
                result.setPageSize(pageInfo.getPageSize()); 
                List<BillDiscountProductInfoVo> productList = this.transalteBillDiscountEntity(tenantId, pageInfo.getResult());//翻译优惠类型
                result.setResult(productList);
            }
            
            responseData = new ResponseData<PageInfo<BillDiscountProductInfoVo>>(ResponseData.AJAX_STATUS_SUCCESS, "查询成功", result);
        } catch (Exception e) {
            responseData = new ResponseData<PageInfo<BillDiscountProductInfoVo>>(ResponseData.AJAX_STATUS_FAILURE, "账单优惠列表查询异常");
            LOG.error("账单优惠列表查询出错：", e);
        }
        return responseData;
    }
    
    /**
     * 新增、修改［账单优惠产品］保存方法
     */
    @RequestMapping("/save")
    public ResponseData<String> addOrUpdateBillDiscountProduct(BillDiscountVo paramVo, HttpServletRequest request){
        
        ResponseData<String> responseData = null;
        
        try {
            SSOClientUser user = (SSOClientUser) request.getSession().getAttribute(SSOClientConstants.USER_SESSION_KEY);
            IBillDiscountProductMaintainSV service = DubboConsumerFactory.getService(IBillDiscountProductMaintainSV.class);
            
            String resultMessage = null;
            String data = null;
            
            String productId = paramVo.getProductId();
            if(StringUtil.isBlank(productId)){//新增
                BillDiscountProductVo reqParam = new BillDiscountProductVo();
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
                if(!CollectionUtil.isEmpty(paramVo.getGiftProductIdList())){
                    GiftProduct giftProduct = new GiftProduct();
                    giftProduct.setProductIdList(paramVo.getGiftProductIdList());
                    giftProduct.setActiveMode(paramVo.getGiftActiveMode());
                    giftProduct.setActivePeriod(paramVo.getGiftActivePeriod());
                    if(!StringUtil.isBlank(paramVo.getGiftEffectDate())){
                        giftProduct.setEffectDate(DateUtil.getTimestamp(paramVo.getGiftEffectDate()+" 00:00:00", DateUtil.DATETIME_FORMAT).toString());
                    }
                    reqParam.setGiftProduct(giftProduct);
                }
                
                LOG.error("账单优惠新增入参:" + JSONArray.fromObject(reqParam));
                BillDiscountProductMaintainResponse resp = service.addBillDiscountProduct(reqParam);
                LOG.error("账单优惠新增出参:" + JSONArray.fromObject(resp));
                
                resultMessage = resp.getResponseHeader().getResultMessage();
                data = resp.getResponseHeader().getResultCode();
                
            }else{//修改
                BillDiscountProductUpdateVo reqParam = new BillDiscountProductUpdateVo();
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
                if(CollectionUtil.isEmpty(paramVo.getBillSubjectList())){
                    reqParam.setBillSubjectList(new ArrayList<String>());
                }
                
                //处理赠品
                if(!CollectionUtil.isEmpty(paramVo.getGiftProductIdList())){
                    GiftProduct giftProduct = new GiftProduct();
                    giftProduct.setProductIdList(paramVo.getGiftProductIdList());
                    giftProduct.setActiveMode(paramVo.getGiftActiveMode());
                    giftProduct.setActivePeriod(paramVo.getGiftActivePeriod());
                    if(!StringUtil.isBlank(paramVo.getGiftEffectDate())){
                        giftProduct.setEffectDate(DateUtil.getTimestamp(paramVo.getGiftEffectDate()+" 00:00:00", DateUtil.DATETIME_FORMAT).toString());
                    }else{
                        giftProduct.setEffectDate(null);
                    }
                    reqParam.setGiftProduct(giftProduct);
                }else{
                    GiftProduct giftProduct = new GiftProduct();
                    giftProduct.setProductIdList(new ArrayList<String>());
                    reqParam.setGiftProduct(giftProduct);
                }
                
                LOG.error("账单优惠更新入参:" + JSONArray.fromObject(reqParam));
                BaseResponse resp = service.updateBillDiscountProduct(reqParam);
                LOG.error("账单优惠更新出参:" + JSONArray.fromObject(resp));
                
                resultMessage = resp.getResponseHeader().getResultMessage();
                data = resp.getResponseHeader().getResultCode();
            }
            
            responseData = new ResponseData<String>(ResponseData.AJAX_STATUS_SUCCESS, resultMessage, data);
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
            
            BillDiscountProductDeleteRequest paramReq = new BillDiscountProductDeleteRequest();
            paramReq.setTenantId(user.getTenantId());//租户Id
            paramReq.setProductId(productId);//产品Id
            
            IBillDiscountProductMaintainSV service = DubboConsumerFactory.getService(IBillDiscountProductMaintainSV.class);
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
            
            BillDiscountProductUpdateVo paramReq = new BillDiscountProductUpdateVo();
            paramReq.setTenantId(user.getTenantId());//租户Id
            paramReq.setProductId(productId);//产品Id
            paramReq.setStatus(status);//状态（0:失效 1:生效 2:待生效）
            
            IBillDiscountProductMaintainSV service = DubboConsumerFactory.getService(IBillDiscountProductMaintainSV.class);
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
    public ResponseData<BillDiscountProductResp> getByProductId(HttpServletRequest request) {
        
        ResponseData<BillDiscountProductResp> responseData = null;
        try {
            SSOClientUser user = (SSOClientUser) request.getSession().getAttribute(SSOClientConstants.USER_SESSION_KEY);
            String tenantId = user.getTenantId();
            
            BillDiscountProductQueryRequest reqParam = new BillDiscountProductQueryRequest();
            reqParam.setTenantId(tenantId);
            reqParam.setProductId(request.getParameter("productId"));
            
            IBillDiscountProductQuerySV service = DubboConsumerFactory.getService(IBillDiscountProductQuerySV.class);
            LOG.error("账单优惠查询入参:" + JSONArray.fromObject(reqParam));
            BillDiscountProductQueryResponse resp = service.queryBillDiscountProduct(reqParam);
            LOG.error("账单优惠查询出参:" + JSONArray.fromObject(resp));
            
            BillDiscountProductResp result = new BillDiscountProductResp();
            if(resp != null){
                BillDiscountProductInfo billDiscountProduct = resp.getInfo();
                BeanUtils.copyProperties(billDiscountProduct, result);
                
                result = this.setAttValueToResp(tenantId, result, billDiscountProduct);
            }
            
            responseData = new ResponseData<BillDiscountProductResp>(ResponseData.AJAX_STATUS_SUCCESS, "查询成功", result);
        } catch (Exception e) {
            responseData = new ResponseData<BillDiscountProductResp>(ResponseData.AJAX_STATUS_FAILURE, "账单优惠查询异常");
            LOG.error("账单优惠查询出错：", e);
        }
        return responseData;
    }
    
    private BillDiscountProductResp setAttValueToResp(String tenantId, BillDiscountProductResp result, 
                    BillDiscountProductInfo billDiscountProduct){
        try {
            //关联账单科目
            List<String> relatedSubjectList = billDiscountProduct.getRelatedSubjectList();
            if(!CollectionUtil.isEmpty(relatedSubjectList)){
                result.setRelatedSubjectId(relatedSubjectList.get(0));
            }
            
            List<String> subjectIdList = new ArrayList<String>();//优惠科目ID
            List<String> productIdList = new ArrayList<String>();//满赠赠品ID
            
            List<ExtendInfo> extendInfoList = billDiscountProduct.getExtendInfoList();
            for(ExtendInfo extendInfo : extendInfoList){
                
                if(BDAttrName.BILL_SUBJECT_CODE.equals(extendInfo.getAttrName())){//优惠科目ID
                    if(DiscountType.MZ.equals(billDiscountProduct.getDiscountType())){
                        productIdList.add(extendInfo.getAttrValue());
                    }else{
                        subjectIdList.add(extendInfo.getAttrValue());
                    }
                }else if(BDAttrName.REFER_SUBJECT_CODE.equals(extendInfo.getAttrName())){//满赠赠品ID
                    if(DiscountType.MZ.equals(billDiscountProduct.getDiscountType())){
                        subjectIdList.add(extendInfo.getAttrValue());
                    }else{
                        productIdList.add(extendInfo.getAttrValue());
                    }
                }else if(BDAttrName.FULL_COST_AMOUNT.equals(extendInfo.getAttrName())){//满赠（满减）到达金额
                    result.setFullCostAmount(extendInfo.getAttrValue());
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
            List<GnSubjectInfoVo> subjectInfoList = new ArrayList<GnSubjectInfoVo>();
            if(!CollectionUtil.isEmpty(subjectIdList)){
                for(String subjectId : subjectIdList){
                    GnSubjectTenantIdSubjectIdRequest queryParam = new GnSubjectTenantIdSubjectIdRequest();
                    queryParam.setTenantId(result.getTenantId());
                    queryParam.setSubjectId(subjectId);
                    IGnSubjectQuerySV iGnSubjectQuerySV = DubboConsumerFactory.getService("iGnSubjectQuerySV");
                    LOG.error("优惠科目回显查询入参:" + JSONArray.fromObject(queryParam));
                    GnSubjectInfoVo gnSubjectInfo = iGnSubjectQuerySV.getGnSubjectByTenantIdSubjectId(queryParam);
                    LOG.error("优惠科目回显查询出参:" + JSONArray.fromObject(gnSubjectInfo));
                    subjectInfoList.add(gnSubjectInfo);
                }
            }
            if(!CollectionUtil.isEmpty(subjectInfoList)){
                result.setBillSubjectList(subjectInfoList);
            }
            
            //满赠赠品查询
            List<ProductInfo> productInfoList = null;
            if(!CollectionUtil.isEmpty(productIdList)){
                ProductQueryByIdListVO queryParam2 = new ProductQueryByIdListVO();
                queryParam2.setTenantId(result.getTenantId());
                queryParam2.setProductIdList(productIdList);
                IQueryProductSV iQueryProductSV = DubboConsumerFactory.getService("iQueryProductSV");
                LOG.error("满赠赠品回显查询入参:" + JSONArray.fromObject(queryParam2));
                PageInfo<ProductInfo> pageResult = iQueryProductSV.getProductInfoByProductIdList(queryParam2);
                LOG.error("满赠赠品回显查询出参:" + JSONArray.fromObject(pageResult.getResult()));
                productInfoList = pageResult.getResult();
            }
            if(!CollectionUtil.isEmpty(productInfoList)){
                productInfoList = this.transalteProductEntity(tenantId, productInfoList);//翻译字段
                result.setGiftProductList(productInfoList);
            }
            
        } catch (Exception e) {
            LOG.error("账单优惠查询出错：", e);
            return result;
        }
        
        return result;
    }
    
    /**
     * 关联账单科目列表查询
     */
    @RequestMapping("/getRelatedSubjectList")
    public ResponseData<PageInfo<GnSubjectListResponse>> getRelatedSubjectList(HttpServletRequest request, GnSubjectQueryVo queryInfo){
        
        ResponseData<PageInfo<GnSubjectListResponse>> responseData = null;
        try {
            SSOClientUser user = (SSOClientUser) request.getSession().getAttribute(SSOClientConstants.USER_SESSION_KEY);
            
            queryInfo.setIndustryCode(user.getIndustryCode());
            queryInfo.setSubjectType(BaaSOPConstants.SubjectCode.BILL_SUBJECT);
            queryInfo.setTenantId(user.getTenantId());
            
            IGnSubjectQuerySV iGnSubjectQuerySV = DubboConsumerFactory.getService("iGnSubjectQuerySV");
            LOG.debug("配置加载查询入参:"+JSONObject.fromObject(queryInfo).toString());
            PageInfo<GnSubjectListResponse> resultInfo = iGnSubjectQuerySV.getGnSubjectList(queryInfo);
            LOG.debug("配置加载查询出参:"+JSONArray.fromObject(resultInfo).toString());
            
            responseData = new ResponseData<PageInfo<GnSubjectListResponse>>(ResponseData.AJAX_STATUS_SUCCESS, "查询成功", resultInfo);
        } catch (Exception e) {
            responseData = new ResponseData<PageInfo<GnSubjectListResponse>>(ResponseData.AJAX_STATUS_FAILURE, "查询失败");
            LOG.error("获取信息出错：", e);
        }
        return responseData;
    }
    
    /**
     * 套餐产品分页查询
     * @param request
     * @param productQueryVO
     * @return
     */
    @RequestMapping("/getSalableProductList")
    public ResponseData<PageInfo<ProductInfoVo>> getSalableProductList(HttpServletRequest request, ProductQueryVO productQueryVO) {
        ResponseData<PageInfo<ProductInfoVo>> responseData;
        try {
            IQueryProductSV queryProductSV = DubboConsumerFactory.getService(IQueryProductSV.class);
            SSOClientUser user = (SSOClientUser) request.getSession().getAttribute(SSOClientConstants.USER_SESSION_KEY);
            String tradeSeq = TradeSeqUtil.newTradeSeq(user.getTenantId());
            String strPageNo=(null==request.getParameter(BaaSOPConstants.PAGENO))?"1":request.getParameter(BaaSOPConstants.PAGENO);
            String strPageSize=(null==request.getParameter(BaaSOPConstants.PAGESIZE))?"10":request.getParameter(BaaSOPConstants.PAGESIZE);
            productQueryVO.setTradeSeq(tradeSeq);
            productQueryVO.setTenantId(user.getTenantId());
            productQueryVO.setPageNo(Integer.parseInt(strPageNo));
            productQueryVO.setPageSize(Integer.parseInt(strPageSize));
            if(productQueryVO.getInvalidDate()!=null){
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                productQueryVO.setInvalidDate(Timestamp.valueOf(format.format(new Date(productQueryVO.getInvalidDate().getTime()))+" 23:59:59"));
            }

            PageInfo<ProductInfo> pageInfo = queryProductSV.getActiveProductInfo(productQueryVO);
            PageInfo<ProductInfoVo> translatedPageInfo = new PageInfo<ProductInfoVo>();
            List<ProductInfoVo> productInfoVos = new ArrayList<ProductInfoVo>();
            transalteEntity(pageInfo.getResult(),productInfoVos,user);
            translatedPageInfo.setPageCount(pageInfo.getPageCount());
            translatedPageInfo.setCount(pageInfo.getCount());
            translatedPageInfo.setPageSize(pageInfo.getPageSize());
            translatedPageInfo.setPageNo(pageInfo.getPageNo());
            translatedPageInfo.setResult(productInfoVos);
            responseData = new ResponseData<PageInfo<ProductInfoVo>>(ResponseData.AJAX_STATUS_SUCCESS,"可销售产品列表查询成功",translatedPageInfo);
        } catch (Exception e) {
            LOG.error("查询可销售产品列表异常",e);
            responseData = new ResponseData<PageInfo<ProductInfoVo>>(ResponseData.AJAX_STATUS_FAILURE,"可销售产品列表查询失败");
        }
        return responseData;
    }
    
    //翻译套餐产品
    private void transalteEntity(List<ProductInfo> result, List<ProductInfoVo> productInfoVos,SSOClientUser user) {
        if(!CollectionUtils.isEmpty(result)){
            List<BaseCode> billingTypes = ParamController.getSysParams(user.getTenantId(), "GROUP_BILLING_TYPE", TradeSeqUtil.newTradeSeq(user.getTenantId()));
            List<BaseCode> units = ParamController.getSysParams(user.getTenantId(), "UNIT", TradeSeqUtil.newTradeSeq(user.getTenantId()));
            List<BaseCode> serviceDetails = ParamController.getSysParams(user.getTenantId(), "SERVICE_DETAIL", TradeSeqUtil.newTradeSeq(user.getTenantId()));
            List<BaseCode> serviceTypes = ParamController.getSysParams(user.getTenantId(), "SERVICE_TYPE", TradeSeqUtil.newTradeSeq(user.getTenantId()));
            
            
            for(ProductInfo info:result){
                ProductInfoVo infoVo = new ProductInfoVo();
                List<ServiceInfo> details = new ArrayList<>();
                for(ServiceVO serviceVO:info.getUsageList()){
                    ServiceInfo serviceInfo = new ServiceInfo();
                    BeanUtils.copyProperties(serviceVO, serviceInfo);
                    details.add(serviceInfo);
                }
                BeanUtils.copyProperties(info, infoVo);
                infoVo.setServiceInfoList(details);
                for(BaseCode code:billingTypes){
                    if(code.getParamCode().equalsIgnoreCase(infoVo.getBillingType())){
                        infoVo.setBillingTypeName(code.getParamName());
                    }
                }
                for(ServiceInfo info1:infoVo.getServiceInfoList()){
                    for(BaseCode code:units){
                        if(code.getParamCode().equalsIgnoreCase(info1.getUnit())){
                            info1.setUsageAmountName(code.getParamName());
                        }
                    }
                    for(BaseCode code:serviceDetails){
                        if(code.getParamCode().equalsIgnoreCase(info1.getServiceTypeDetail())){
                            info1.setServiceDetailDesc(code.getParamName());
                        }
                    }
                    for(BaseCode code:serviceTypes){
                        if(code.getParamCode().equalsIgnoreCase(info1.getServiceType())){
                            info1.setServiceTypeDesc(code.getParamName());
                        }
                    }
                }

                productInfoVos.add(infoVo);
            }
        }
    }
    
    //翻译字段（优惠类型）
    private List<BillDiscountProductInfoVo> transalteBillDiscountEntity(String tenantId, List<BillDiscountProductInfo> productList){
        
        //优惠类型
        List<BaseCode> baseCodeList = ParamController.getSysParams(tenantId, "BILL_DISCOUNT_TYPE", TradeSeqUtil.newTradeSeq(tenantId));
        
        List<BillDiscountProductInfoVo> resultList = new ArrayList<BillDiscountProductInfoVo>();
        if(!CollectionUtil.isEmpty(productList)){
            for(BillDiscountProductInfo productInfo : productList){
                
                BillDiscountProductInfoVo result = new BillDiscountProductInfoVo();
                BeanUtils.copyProperties(productInfo, result);
                
                if(!CollectionUtil.isEmpty(baseCodeList)){
                    for(BaseCode code : baseCodeList){
                        if(code.getParamCode().equalsIgnoreCase(productInfo.getDiscountType())){
                            result.setDiscountTypeDesc(code.getParamName());
                        }
                    }
                }
                resultList.add(result);
            }
        }
        
        return resultList;
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
