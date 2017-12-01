package com.ai.baas.op.web.controller.salableproduct;

import com.ai.baas.bmc.api.baseInfo.params.BaseCode;
import com.ai.baas.bmc.api.marktableproduct.interfaces.IProductManageSV;
import com.ai.baas.bmc.api.marktableproduct.interfaces.IQueryProductSV;
import com.ai.baas.bmc.api.marktableproduct.params.*;
import com.ai.baas.op.web.constants.BaaSOPConstants;
import com.ai.baas.op.web.controller.common.ParamController;
import com.ai.baas.op.web.model.ProductInfoVo;
import com.ai.baas.op.web.model.ServiceInfo;
import com.ai.baas.op.web.util.TradeSeqUtil;
import com.ai.opt.base.vo.BaseResponse;
import com.ai.opt.base.vo.PageInfo;
import com.ai.opt.sdk.dubbo.util.DubboConsumerFactory;
import com.ai.opt.sdk.util.BeanUtils;
import com.ai.opt.sdk.util.StringUtil;
import com.ai.opt.sdk.web.model.ResponseData;
import com.ai.opt.sso.client.filter.SSOClientConstants;
import com.ai.opt.sso.client.filter.SSOClientUser;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import org.apache.log4j.Logger;
import org.springframework.beans.propertyeditors.PropertiesEditor;
import org.springframework.beans.propertyeditors.StringArrayPropertyEditor;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 可销售产品
 */
@RestController
@RequestMapping("/salableProduct")
public class SalableProductController {

    private static final Logger LOG = Logger.getLogger(SalableProductController.class);

    @InitBinder
    public void initBinder(WebDataBinder binder){
        binder.registerCustomEditor(Timestamp.class, new PropertiesEditor(){
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                try {
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    if(!StringUtil.isBlank(text)){
                        Date date = format.parse(text);
                        setValue(new Timestamp(date.getTime()));
                    }else{
                        setValue(null);
                    }
                } catch (ParseException e) {
                    LOG.error("日期格式解析异常",e);
                }
            }
        });
        binder.registerCustomEditor(String[].class,new StringArrayPropertyEditor());
    }

    @RequestMapping("/list")
    public ModelAndView salableProductList() {
        
    	return new ModelAndView("jsp/salableproduct/salableProductList");
    }

    @RequestMapping("/toRelateSubject")
    public ModelAndView toRelateSubject(HttpServletRequest request,ProductRelatedRequest queryVo) {
        if(!StringUtil.isBlank(queryVo.getProductId())&&!StringUtil.isBlank(queryVo.getBillingType())){
            IQueryProductSV queryProductSV = DubboConsumerFactory.getService(IQueryProductSV.class);
            SSOClientUser user = (SSOClientUser) request.getSession().getAttribute(SSOClientConstants.USER_SESSION_KEY);
            queryVo.setTenantId(user.getTenantId());
            ProductRelatedResponse relatedResponse = queryProductSV.getProductRelated(queryVo);
            request.setAttribute("salableProductRelated",relatedResponse);
        }
        return new ModelAndView("jsp/salableproduct/relateSubject");
    }
    
    @RequestMapping("/toAdd")
    public ModelAndView newSalableProduct() {
        
    	return new ModelAndView("jsp/salableproduct/addSalableProduct");
    }

    @RequestMapping("/toEdit")
    public ModelAndView editSalableProduct(HttpServletRequest request,String productId,String billingType) {
        if(!StringUtil.isBlank(productId)){
            request.setAttribute("productId",productId);
            request.setAttribute("billingType",billingType);
        }
        return new ModelAndView("jsp/salableproduct/updateSalableProduct");
    }

    /**
     * 组合产品分页查询
     * @param request
     * @param productQueryVO
     * @return
     */
    @RequestMapping("/getSalableProductList")
    public ResponseData<PageInfo<ProductInfoVo>> getSalableProductList(HttpServletRequest request,ProductQueryVO productQueryVO) {
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

            PageInfo<ProductInfo> pageInfo = queryProductSV.getProductInfo(productQueryVO);
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
                    BeanUtils.copyProperties(serviceInfo,serviceVO);
                    details.add(serviceInfo);
                }
                BeanUtils.copyProperties(infoVo,info);
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

    /**
     * 组合产品添加
     * @param request
     * @param productParams
     * @param mainProducts
     * @return
     */
    @RequestMapping("/addSalableProduct")
    public ResponseData<String> addSalableProduct(HttpServletRequest request,String productParams,String mainProducts){
        ResponseData<String> responseData = null;
        try {
            SSOClientUser user = (SSOClientUser) request.getSession().getAttribute(SSOClientConstants.USER_SESSION_KEY);
            IProductManageSV productManageSV = DubboConsumerFactory.getService(IProductManageSV.class);
            ProductVO product = packJsonToProductVO(productParams,mainProducts);
            String tradeSeq = TradeSeqUtil.newTradeSeq(user.getTenantId());
            product.setTradeSeq(tradeSeq);
            product.setTenantId(user.getTenantId());
            LOG.info(JSON.toJSONString(product));
            BaseResponse response = productManageSV.addProduct(product);
            if(response.getResponseHeader().isSuccess()){
                responseData = new ResponseData<String>(ResponseData.AJAX_STATUS_SUCCESS,"添加成功");
            }else{
                responseData = new ResponseData<String>(ResponseData.AJAX_STATUS_FAILURE,response.getResponseHeader().getResultMessage());
            }
        } catch (Exception e) {
            responseData = new ResponseData<String>(ResponseData.AJAX_STATUS_FAILURE,"添加异常，请联系管理员");
            LOG.error("添加可销售产品失败",e);
        }
        return responseData;
    }

    /**
     * 更改产品状态
     * @param productActiveVo
     * @return
     */
    @RequestMapping("/updateProductStatus")
    public ResponseData<String> updateProductStatus(HttpServletRequest request,ProductActiveVO productActiveVo){
        ResponseData<String> responseData;
        try {
            SSOClientUser user = (SSOClientUser) request.getSession().getAttribute(SSOClientConstants.USER_SESSION_KEY);
            IProductManageSV productManageSV = DubboConsumerFactory.getService(IProductManageSV.class);
            if(!StringUtil.isBlank(productActiveVo.getProductId())){
                productActiveVo.setTenantId(user.getTenantId());
                BaseResponse response = productManageSV.updateProductStatus(productActiveVo);
                if(response!=null&&response.getResponseHeader().isSuccess()){
                    responseData = new ResponseData<String>(ResponseData.AJAX_STATUS_SUCCESS,"更改状态成功");
                }else{
                    responseData = new ResponseData<String>(ResponseData.AJAX_STATUS_FAILURE,"更改状态失败",
                    		(response!=null&&response.getResponseHeader()!=null)?response.getResponseHeader().getResultMessage():null);
                }
            }else{
                responseData = new ResponseData<String>(ResponseData.AJAX_STATUS_FAILURE,"产品id不存在，无法更新状态");
            }
        } catch (Exception e) {
            LOG.error("更新组合产品["+productActiveVo.getProductId()+"]失败",e);
            responseData = new ResponseData<String>(ResponseData.AJAX_STATUS_FAILURE,"系统异常，更新状态失败");
        }

        return responseData;
    }

    /**
     * 查询组合产品信息
     * @param queryVo
     * @return
     */
    @RequestMapping("/getProductInfo")
    public ResponseData<ProductVO> getProductInfo(ProductParamKeyVo queryVo){
        ResponseData<ProductVO> responseData;
        try {
            IProductManageSV productManageSV = DubboConsumerFactory.getService(IProductManageSV.class);
            if(!StringUtil.isBlank(queryVo.getProductId())){
                ProductVO productVO = productManageSV.editProduct(queryVo);
                if(productVO!=null){
                    responseData = new ResponseData<ProductVO>(ResponseData.AJAX_STATUS_SUCCESS,"查询成功",productVO);
                }else{
                    responseData = new ResponseData<ProductVO>(ResponseData.AJAX_STATUS_FAILURE,"查询失败");
                }
            }else{
                responseData = new ResponseData<ProductVO>(ResponseData.AJAX_STATUS_FAILURE,"产品id不存在，无法查询");
            }
        } catch (Exception e) {
            LOG.error("查询组合产品["+queryVo.getProductId()+"]失败",e);
            responseData = new ResponseData<ProductVO>(ResponseData.AJAX_STATUS_FAILURE,"系统异常，获取产品信息失败");
        }

        return responseData;
    }

    /**
     * 修改组合产品信息
     * @param request
     * @param productParams
     * @param mainProducts
     * @return
     */
    @RequestMapping("/updateProduct")
    public ResponseData<String> updateProduct(HttpServletRequest request,String productParams,String mainProducts){
        ResponseData<String> responseData;
        try {
            SSOClientUser user = (SSOClientUser) request.getSession().getAttribute(SSOClientConstants.USER_SESSION_KEY);
            IProductManageSV productManageSV = DubboConsumerFactory.getService(IProductManageSV.class);
            ProductVO product = packJsonToProductVO(productParams,mainProducts);
            product.setTenantId(user.getTenantId());
            product.setTradeSeq(TradeSeqUtil.newTradeSeq(user.getTenantId()));
            LOG.info(JSON.toJSONString(product));
            BaseResponse response = productManageSV.updateProduct(product);
            if(response!=null&&response.getResponseHeader().isSuccess()){
                responseData = new ResponseData<String>(ResponseData.AJAX_STATUS_SUCCESS,"修改产品信息成功");
            }else{
                responseData = new ResponseData<String>(ResponseData.AJAX_STATUS_SUCCESS,"修改产品信息失败",
                		(response!=null&&response.getResponseHeader()!=null)?response.getResponseHeader().getResultMessage():null);
                LOG.error("修改产品信息失败:"+JSON.toJSONString(response));
            }
        } catch (Exception e) {
            LOG.error("修改组合产品失败",e);
            responseData = new ResponseData<String>(ResponseData.AJAX_STATUS_FAILURE,"系统异常，修改产品信息失败");
        }

        return responseData;
    }

    /**
     * 删除组合产品
     * @param productDelVo
     * @return
     */
    @RequestMapping("/deleteProduct")
    public ResponseData<String> deleteProduct(ProductDelVO productDelVo){
        ResponseData<String> responseData;
        try {
            IProductManageSV productManageSV = DubboConsumerFactory.getService(IProductManageSV.class);
            productManageSV.delProduct(productDelVo);
            responseData = new ResponseData<String>(ResponseData.AJAX_STATUS_SUCCESS,"删除产品成功");
        } catch (Exception e) {
            LOG.error("删除组合产品["+productDelVo.getProductId()+"]失败",e);
            responseData = new ResponseData<String>(ResponseData.AJAX_STATUS_FAILURE,"系统异常，删除产品失败");
        }

        return responseData;
    }

    /**
     * 可销售产品关联详单
     * @param request
     * @param relateVo
     * @return
     */
    @RequestMapping("/saveRelateSubject")
    public ResponseData<String> saveRelateSubject(HttpServletRequest request,ProductRelatedRequest relateVo){
        ResponseData<String> responseData;
        try {
            IProductManageSV productManageSV = DubboConsumerFactory.getService(IProductManageSV.class);
            SSOClientUser user = (SSOClientUser) request.getSession().getAttribute(SSOClientConstants.USER_SESSION_KEY);
            relateVo.setTenantId(user.getTenantId());
            productManageSV.updateProductRelated(relateVo);
            responseData = new ResponseData<String>(ResponseData.AJAX_STATUS_SUCCESS,"产品关联详单成功");
        } catch (Exception e) {
            LOG.error("产品["+relateVo.getProductId()+"]关联详单失败",e);
            responseData = new ResponseData<String>(ResponseData.AJAX_STATUS_FAILURE,"系统异常，产品关联详单失败");
        }

        return responseData;
    }
    /**
     * ProductVO反序列化
     * @param productParams 产品基础信息
     * @param mainProducts  主产品使用量配置
     * @return
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws ParseException
     */
    private ProductVO packJsonToProductVO(String productParams,String mainProducts) throws IllegalAccessException, InstantiationException, ParseException {
        ProductVO productVO = null;
        if(!StringUtil.isBlank(productParams)){
            JSONObject object = JSON.parseObject(productParams);
            productVO = ProductVO.class.newInstance();
            Field[] fields = ProductVO.class.getDeclaredFields();
            for(Field field:fields){
                if(object.containsKey(field.getName())){
                    field.setAccessible(true);
                    if(String.class.equals(field.getType())){
                        field.set(productVO,object.get(field.getName()));
                    }else if(Timestamp.class.equals(field.getType())){
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        Date date = dateFormat.parse(object.getString(field.getName()));
                        field.set(productVO,new Timestamp(date.getTime()));
                    }else if(BigDecimal.class.equals(field.getType())){
                        field.set(productVO,object.getBigDecimal(field.getName()));
                    }
                }
            }
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            productVO.setInvalidDate(Timestamp.valueOf(format.format(new Date(productVO.getInvalidDate().getTime()))+" 23:59:59"));
        }

        if(productVO!=null&&!StringUtil.isBlank(mainProducts)){
            List<ServiceVO> mainServiceVOs = JSON.parseObject(mainProducts, new TypeReference<List<ServiceVO>>(){});
            if(!CollectionUtils.isEmpty(mainServiceVOs)){
                productVO.setMajorProductAmount(mainServiceVOs);
            }
        }

        return productVO;
    }
}
