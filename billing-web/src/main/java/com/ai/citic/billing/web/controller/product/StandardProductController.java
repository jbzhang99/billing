package com.ai.citic.billing.web.controller.product;

import com.ai.baas.bmc.api.baseInfo.params.BaseCode;
import com.ai.baas.prd.api.category.interfaces.ICategorySV;
import com.ai.baas.prd.api.category.params.CategoryQuery;
import com.ai.baas.prd.api.category.params.CategoryQueryResponse;
import com.ai.baas.prd.api.category.params.CategoryVo;
import com.ai.baas.prd.api.element.interfaces.IPriceElementSV;
import com.ai.baas.prd.api.element.params.Element;
import com.ai.baas.prd.api.element.params.ElementDeleteVO;
import com.ai.baas.prd.api.element.params.ElementIncreaseVO;
import com.ai.baas.prd.api.product.interfaces.IStandardProductSV;
import com.ai.baas.prd.api.product.params.*;
import com.ai.baas.prd.api.strategy.interfaces.IPriceStrategySV;
import com.ai.baas.prd.api.strategy.params.QueryDetailParam;
import com.ai.baas.prd.api.strategy.params.StrategyDetailQueryReponse;
import com.ai.baas.prd.api.strategy.params.StrategyShowVO;
import com.ai.citic.billing.web.common.ParamController;
import com.ai.citic.billing.web.constants.CiticWebConstants;
import com.ai.citic.billing.web.model.sso.CiticSSOClientUser;
import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.BaseResponse;
import com.ai.opt.base.vo.PageInfoResponse;
import com.ai.opt.sdk.dubbo.util.DubboConsumerFactory;
import com.ai.opt.sdk.util.BeanUtils;
import com.ai.opt.sdk.util.CollectionUtil;
import com.ai.opt.sdk.util.StringUtil;
import com.ai.opt.sdk.util.UUIDUtil;
import com.ai.opt.sdk.web.model.ResponseData;
import com.ai.opt.sso.client.filter.SSOClientConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 定义产品目录控
 *
 * Date: 2016年12月8日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author wangyx13
 */
@RestController
@RequestMapping("/standardProduct")
public class StandardProductController {
    public static final Logger LOGGER = LoggerFactory.getLogger(StandardProductController.class);

    /**
     * 标准产品添加页面
     * @return
     */
    @RequestMapping("/toAddProduct")
    public ModelAndView toAddProduct(){
        return new ModelAndView("jsp/standardProduct/addProduct");
    }

    /**
     * 标准产品添加页面
     * @return
     */
    @RequestMapping("/toStandardProductList")
    public ModelAndView toStandardProductList(){
        return new ModelAndView("jsp/standardProduct/productList");
    }

    /**
     * 标准产品添加页面
     * @return
     */
    @RequestMapping("/toEditStandardProduct/{productId}")
    public ModelAndView toEditStandardProduct(@PathVariable String productId){
        ModelAndView modelAndView = new ModelAndView("jsp/standardProduct/editProduct");
        modelAndView.addObject("productId",productId);
        return modelAndView;
    }

    /**
     * 标准产品添加页面
     * @return
     */
    @RequestMapping("/toViewStandardProduct/{productId}")
    public ModelAndView toViewStandardProduct(@PathVariable String productId){
        ModelAndView modelAndView = new ModelAndView("jsp/standardProduct/viewProduct");
        modelAndView.addObject("productId",productId);
        return modelAndView;
    }

    /**
     * 查询类目信息
     * @param queryVo
     * @param request
     * @return
     */
    @RequestMapping("/getCategoryList")
    public ResponseData<List<CategoryVo>> getCategoryList(CategoryQuery queryVo,HttpServletRequest request){
        ResponseData<List<CategoryVo>> responseData;
        try {
            CiticSSOClientUser user = (CiticSSOClientUser) request.getSession().getAttribute(SSOClientConstants.USER_SESSION_KEY);
            ICategorySV categorySV = DubboConsumerFactory.getService(ICategorySV.class);
            queryVo.setTenantId(user.getTenantId());
            queryVo.setCategoryType(CiticWebConstants.CategoryType.STANDARD);
            CategoryQueryResponse categoryList = categorySV.getCategoryList(queryVo);
            if(categoryList.getResponseHeader().isSuccess()&&!CollectionUtil.isEmpty(categoryList.getCategoryList())){
                responseData = new ResponseData<List<CategoryVo>>(ResponseData.AJAX_STATUS_SUCCESS,"类目信息查询成功",categoryList.getCategoryList());
            }else{
                responseData = new ResponseData<List<CategoryVo>>(ResponseData.AJAX_STATUS_SUCCESS,"类目信息加载失败");
            }
        } catch (BusinessException|SystemException e) {
            LOGGER.error("类目信息查询异常",e);
            responseData = new ResponseData<List<CategoryVo>>(ResponseData.AJAX_STATUS_SUCCESS,"类目信息加载失败");
        }
        return responseData;
    }

    /**
     * 保存标准产品
     * @param product
     * @param request
     * @return
     */
    @RequestMapping("/saveStandardProduct")
    public ResponseData<String> saveStandardProduct(StandardProductRequest product,HttpServletRequest request){
        ResponseData<String> responseData;
        try {
            CiticSSOClientUser user = (CiticSSOClientUser) request.getSession().getAttribute(SSOClientConstants.USER_SESSION_KEY);
            IPriceElementSV priceElementSV = DubboConsumerFactory.getService(IPriceElementSV.class);
            ElementIncreaseVO elementVo = new ElementIncreaseVO();
            BeanUtils.copyProperties(elementVo,product);
            elementVo.setCategoryType(CiticWebConstants.CategoryType.STANDARD);
            elementVo.setTradeCode(CiticWebConstants.TradeCode.STANDARD);
            elementVo.setTenantId(user.getTenantId());
            List<Element> elements = new ArrayList<>();
            Element element = new Element();
            element.setPricePolicy(product.getPricePolicy());
            element.setBillingCycle(product.getBillingCycle());
            element.setSpecTypeId(product.getCategoryId());
            element.setSpecTypeName(product.getMainProductName());
            elements.add(element);
            elementVo.setElements(elements);
            BaseResponse baseResponse = priceElementSV.addElement(elementVo);
            if(baseResponse!=null&&baseResponse.getResponseHeader().isSuccess()){
                responseData = new ResponseData<String>(ResponseData.AJAX_STATUS_SUCCESS,"保存产品成功");
            }else{
                responseData = new ResponseData<String>(ResponseData.AJAX_STATUS_FAILURE,"保存产品失败");
            }
        } catch (BusinessException|SystemException e) {
            responseData = new ResponseData<String>(ResponseData.AJAX_STATUS_FAILURE,"保存产品失败");
            LOGGER.error("标准产品保存失败",e);
        }
        return responseData;
    }

    /**
     * 获取所有规格
     * @param request
     * @return
     */
    @RequestMapping("/getAllSpec")
    public ResponseData<List<SpecVo>> getAllSpec(HttpServletRequest request){
        ResponseData<List<SpecVo>> responseData;
        try {
            CiticSSOClientUser user = (CiticSSOClientUser) request.getSession().getAttribute(SSOClientConstants.USER_SESSION_KEY);
            IStandardProductSV standardProductSV = DubboConsumerFactory.getService(IStandardProductSV.class);
            SpecQueryVo query = new SpecQueryVo();
            query.setTenantId(user.getTenantId());
            query.setTradeCode(CiticWebConstants.TradeCode.STANDARD);
            SpecResponse specResponse = standardProductSV.getAllSpec(query);
            if(specResponse.getResponseHeader().isSuccess()){
                responseData = new ResponseData<List<SpecVo>>(ResponseData.AJAX_STATUS_SUCCESS,"查询规格成功",specResponse.getSpecVos());
            }else{
                responseData = new ResponseData<List<SpecVo>>(ResponseData.AJAX_STATUS_SUCCESS,"查询规格失败");
            }
        } catch (BusinessException|SystemException e) {
            responseData = new ResponseData<List<SpecVo>>(ResponseData.AJAX_STATUS_FAILURE,"查询规格异常");
            LOGGER.error("查询规格失败",e);
        }
        return responseData;
    }

    /**
     * 分页查询标准产品
     * @param queryVo
     * @param request
     * @return
     */
    @RequestMapping("/getProductList")
    public ResponseData<PageInfoResponse<StandardProductVo>> getProductList(ProductQueryVo queryVo,HttpServletRequest request){
        ResponseData<PageInfoResponse<StandardProductVo>> responseData;
        try {
            CiticSSOClientUser user = (CiticSSOClientUser) request.getSession().getAttribute(SSOClientConstants.USER_SESSION_KEY);
            IStandardProductSV standardProductSV = DubboConsumerFactory.getService(IStandardProductSV.class);
            queryVo.setTenantId(user.getTenantId());
            queryVo.setTradeCode(CiticWebConstants.TradeCode.STANDARD);
            PageInfoResponse<StandardProductVo> productList = standardProductSV.getProductList(queryVo);
            if(productList.getResponseHeader().isSuccess()&&!CollectionUtil.isEmpty(productList.getResult())){
                List<StandardProductVo> productVoList = productList.getResult();
                for(StandardProductVo productVo:productVoList){
                    List<BaseCode> params = ParamController.getSysParams(user.getTenantId(), "CYCLE", UUIDUtil.genId32());
                    if(!CollectionUtil.isEmpty(params)){
                        for(BaseCode baseCode:params){
                            if(productVo.getBillingCycle().equalsIgnoreCase(baseCode.getParamCode())){
                                productVo.setBillingCycle(baseCode.getParamName());
                            }
                        }
                    }
                }
                responseData = new ResponseData<PageInfoResponse<StandardProductVo>>(ResponseData.AJAX_STATUS_SUCCESS,"标准产品查询成功",productList);
            }else {
                responseData = new ResponseData<PageInfoResponse<StandardProductVo>>(ResponseData.AJAX_STATUS_SUCCESS,"标准产品查询失败");
            }
        } catch (BusinessException|SystemException e) {
            responseData = new ResponseData<PageInfoResponse<StandardProductVo>>(ResponseData.AJAX_STATUS_FAILURE,"标准产品查询异常");
            LOGGER.error("标准产品查询异常",e);
        }
        return responseData;
    }

    /**
     * 查询标准产品(通过id)
     * @param productId
     * @param request
     * @return
     */
    @RequestMapping("/getStandardProduct/{productId}")
    public ResponseData<StandardProductVo> getStandardProduct(@PathVariable("productId") String productId, HttpServletRequest request){
        ResponseData<StandardProductVo> responseData;
        try {
            if(!StringUtil.isBlank(productId)){
                CiticSSOClientUser user = (CiticSSOClientUser) request.getSession().getAttribute(SSOClientConstants.USER_SESSION_KEY);
                IStandardProductSV standardProductSV = DubboConsumerFactory.getService(IStandardProductSV.class);
                ProductQueryVo query = new ProductQueryVo();
                query.setProductId(productId);
                query.setTenantId(user.getTenantId());
                StandardProductVo standardProductVo = standardProductSV.getStandardProduct(query);
                if(standardProductVo!=null){
                    responseData = new ResponseData<StandardProductVo>(ResponseData.AJAX_STATUS_SUCCESS,"标准产品查询成功",standardProductVo);
                }else {
                    responseData = new ResponseData<StandardProductVo>(ResponseData.AJAX_STATUS_FAILURE,"产品不存在");
                }
            }else{
                responseData = new ResponseData<StandardProductVo>(ResponseData.AJAX_STATUS_FAILURE,"产品id不存在，无法查询");
            }
        } catch (BusinessException|SystemException e) {
            responseData = new ResponseData<StandardProductVo>(ResponseData.AJAX_STATUS_FAILURE,"标准产品查询异常");
            LOGGER.error("标准产品查询异常",e);
        }
        return responseData;
    }

    /**
     * 查询标准产品(通过名称)
     * @param productName
     * @param request
     * @return
     */
    @RequestMapping("/getStandardProductByName")
    public ResponseData<StandardProductVo> getStandardProductByName(String productName, HttpServletRequest request){
        ResponseData<StandardProductVo> responseData;
        try {
            if(!StringUtil.isBlank(productName)){
                CiticSSOClientUser user = (CiticSSOClientUser) request.getSession().getAttribute(SSOClientConstants.USER_SESSION_KEY);
                IStandardProductSV standardProductSV = DubboConsumerFactory.getService(IStandardProductSV.class);
                ProductQueryVo query = new ProductQueryVo();
                query.setProductName(productName);
                query.setTenantId(user.getTenantId());
                StandardProductVo standardProductVo = standardProductSV.getStandardProduct(query);
                if(standardProductVo!=null){
                    responseData = new ResponseData<StandardProductVo>(ResponseData.AJAX_STATUS_SUCCESS,"标准产品查询成功",standardProductVo);
                }else {
                    responseData = new ResponseData<StandardProductVo>(ResponseData.AJAX_STATUS_SUCCESS,"产品不存在");
                }
            }else{
                responseData = new ResponseData<StandardProductVo>(ResponseData.AJAX_STATUS_FAILURE,"产品名称不存在");
            }
        } catch (BusinessException|SystemException e) {
            responseData = new ResponseData<StandardProductVo>(ResponseData.AJAX_STATUS_FAILURE,"标准产品查询异常");
            LOGGER.error("标准产品查询异常",e);
        }
        return responseData;
    }

    /**
     * 删除标准产品
     * @param categoryId
     * @param request
     * @return
     */
    @RequestMapping("/deleteStandardProduct/{categoryId}")
    public ResponseData<String> deleteStandardProduct(@PathVariable("categoryId") String categoryId, HttpServletRequest request){
        ResponseData<String> responseData;
        try {
            if(!StringUtil.isBlank(categoryId)){
                CiticSSOClientUser user = (CiticSSOClientUser) request.getSession().getAttribute(SSOClientConstants.USER_SESSION_KEY);
                IPriceElementSV priceElementSV = DubboConsumerFactory.getService(IPriceElementSV.class);
                ElementDeleteVO elementVo = new ElementDeleteVO();
                elementVo.setCategoryId(categoryId);
                elementVo.setTenantId(user.getTenantId());
                BaseResponse baseResponse = priceElementSV.deleteElement(elementVo);
                if(baseResponse.getResponseHeader().isSuccess()){
                    responseData = new ResponseData<String>(ResponseData.AJAX_STATUS_SUCCESS,"删除成功");
                }else{
                    responseData = new ResponseData<String>(ResponseData.AJAX_STATUS_FAILURE,"删除失败");
                }
            }else{
                responseData = new ResponseData<String>(ResponseData.AJAX_STATUS_FAILURE,"产品id不存在，删除失败");
            }
        } catch (BusinessException|SystemException e) {
            responseData = new ResponseData<String>(ResponseData.AJAX_STATUS_FAILURE,"删除异常");
            LOGGER.error("删除异常",e);
        }
        return responseData;
    }

    /**
     * 编辑标准产品
     * @param product
     * @param request
     * @return
     */
    @RequestMapping("/editStandardProduct")
    public ResponseData<String> editStandardProduct(StandardProductRequest product,HttpServletRequest request){
        ResponseData<String> responseData;
        try {
            CiticSSOClientUser user = (CiticSSOClientUser) request.getSession().getAttribute(SSOClientConstants.USER_SESSION_KEY);
            IPriceElementSV priceElementSV = DubboConsumerFactory.getService(IPriceElementSV.class);
            ElementIncreaseVO elementVo = new ElementIncreaseVO();
            BeanUtils.copyProperties(elementVo,product);
            elementVo.setCategoryType(CiticWebConstants.CategoryType.STANDARD);
            elementVo.setTradeCode(CiticWebConstants.TradeCode.STANDARD);
            elementVo.setTenantId(user.getTenantId());
            List<Element> elements = new ArrayList<>();
            Element element = new Element();
            element.setPricePolicy(product.getPricePolicy());
            element.setBillingCycle(product.getBillingCycle());
            element.setSpecTypeId(product.getCategoryId());
            element.setSpecTypeName(product.getMainProductName());
            elements.add(element);
            elementVo.setElements(elements);
            BaseResponse baseResponse = priceElementSV.alterElement(elementVo);
            if(baseResponse!=null&&baseResponse.getResponseHeader().isSuccess()){
                responseData = new ResponseData<String>(ResponseData.AJAX_STATUS_SUCCESS,"编辑产品成功");
            }else{
                responseData = new ResponseData<String>(ResponseData.AJAX_STATUS_FAILURE,"编辑产品失败");
            }
        } catch (BusinessException|SystemException e) {
            responseData = new ResponseData<String>(ResponseData.AJAX_STATUS_FAILURE,"编辑产品失败");
            LOGGER.error("编辑产品失败",e);
        }
        return responseData;
    }

    /**
     * 获取定价策略信息
     * @param policyId
     * @param request
     * @return
     */
    @RequestMapping("/getPricePolicyInfo/{policyId}")
    public ResponseData<StrategyShowVO> getPricePolicyInfo(@PathVariable("policyId") String policyId,HttpServletRequest request){
        ResponseData<StrategyShowVO> responseData;
        try {
            if(!StringUtil.isBlank(policyId)){
                CiticSSOClientUser user = (CiticSSOClientUser) request.getSession().getAttribute(SSOClientConstants.USER_SESSION_KEY);
                IPriceStrategySV strategySV = DubboConsumerFactory.getService(IPriceStrategySV.class);
                QueryDetailParam param = new QueryDetailParam();
                param.setPolicyId(policyId);
                param.setTenantId(user.getTenantId());
                StrategyDetailQueryReponse response = strategySV.getStrategyDetail(param);
                if(response.getResponseHeader().isSuccess()){
                    responseData = new ResponseData<StrategyShowVO>(ResponseData.AJAX_STATUS_SUCCESS,"获取策略信息成功",response.getStrategyShowVO());
                }else{
                    responseData = new ResponseData<StrategyShowVO>(ResponseData.AJAX_STATUS_FAILURE,"获取策略信息失败");
                }
            }else{
                responseData = new ResponseData<StrategyShowVO>(ResponseData.AJAX_STATUS_FAILURE,"策略Id为空，获取策略信息失败");
            }
        } catch (BusinessException|SystemException e) {
            responseData = new ResponseData<StrategyShowVO>(ResponseData.AJAX_STATUS_FAILURE,"获取策略信息失败");
            LOGGER.error("编辑产品失败",e);
        }
        return responseData;
    }
}
