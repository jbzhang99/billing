package com.ai.baas.prd.service.business.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.ai.baas.prd.api.element.params.BaseSpecResponse;
import com.ai.baas.prd.api.element.params.CheckCategoryId;
import com.ai.baas.prd.api.element.params.CheckPolicyParam;
import com.ai.baas.prd.api.element.params.ConvertParams;
import com.ai.baas.prd.api.element.params.Element;
import com.ai.baas.prd.api.element.params.ElementDeleteVO;
import com.ai.baas.prd.api.element.params.ElementDetailRequireResult;
import com.ai.baas.prd.api.element.params.ElementDetailRequireVO;
import com.ai.baas.prd.api.element.params.ElementIncreaseVO;
import com.ai.baas.prd.api.element.params.ElementRequireResult;
import com.ai.baas.prd.api.element.params.ElementRequireVO;
import com.ai.baas.prd.api.element.params.PmSpecTypeRes;
import com.ai.baas.prd.api.element.params.Product;
import com.ai.baas.prd.api.element.params.SpecTypeQueryReq;
import com.ai.baas.prd.api.element.params.UpdateByProductVo;
import com.ai.baas.prd.api.pricemaking.params.PriceMakingAddParam;
import com.ai.baas.prd.api.pricemaking.params.PriceMakingDelParam;
import com.ai.baas.prd.constants.ExceptCodeConstants;
import com.ai.baas.prd.dao.mapper.bo.PmCatalogInfo;
import com.ai.baas.prd.dao.mapper.bo.PmCategoryInfo;
import com.ai.baas.prd.dao.mapper.bo.PmPolicyInfo;
import com.ai.baas.prd.dao.mapper.bo.PmSpecType;
import com.ai.baas.prd.service.atom.interfaces.IPmCatalogInfoAtomSV;
import com.ai.baas.prd.service.atom.interfaces.IPmCategoryInfoAtomSV;
import com.ai.baas.prd.service.atom.interfaces.IPmSpecTypeAtomSV;
import com.ai.baas.prd.service.atom.interfaces.PmPolicyInfoAtomSV;
import com.ai.baas.prd.service.business.interfaces.IConvertBusiSV;
import com.ai.baas.prd.service.business.interfaces.IPriceElementBusiSV;
import com.ai.baas.prd.service.business.interfaces.IPriceMakingBusiSV;
import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.vo.PageInfo;
import com.ai.opt.base.vo.ResponseHeader;
import com.ai.opt.sdk.util.BeanUtils;
import com.ai.opt.sdk.util.CollectionUtil;
import com.ai.opt.sdk.util.StringUtil;
import com.alibaba.fastjson.JSON;
@Service
@Transactional
public class PriceElementBusiSVImpl implements IPriceElementBusiSV{

    private static final Log LOG = LogFactory.getLog(PriceElementBusiSVImpl.class);
    @Autowired
    IConvertBusiSV iConvertBusiSV;
    @Autowired
    IPriceMakingBusiSV priceMakingBusiSV;
    @Autowired
    IPmCatalogInfoAtomSV iPmCatalogInfoAtomSv;
    @Autowired
    IPmCategoryInfoAtomSV iPmCategoryInfoAtomSV;
    @Autowired
    IPmSpecTypeAtomSV pmSpecTypeAtomSV;
    @Autowired
    PmPolicyInfoAtomSV pmPolicyInfoAtomSV;
    @Override
    public ElementRequireResult queryPriceElement(ElementRequireVO vo) {
        ElementRequireResult result = new ElementRequireResult();        
        result.setTenantId(vo.getTenantId()); 
        PageInfo<Product> pageInfo = new PageInfo<>();
        
        //根据查询条件选出主产品ID
        List<PmCategoryInfo> parentIdList = iPmCategoryInfoAtomSV.queryLv1Like(vo); 
        List<String> parentIds = new ArrayList<>();
        for(PmCategoryInfo pm : parentIdList){
            parentIds.add(pm.getCategoryId());
        }
        if(parentIds==null||parentIds.isEmpty()){   //判空
            result.setResponseHeader(new ResponseHeader(false, "000001", "no result with parentIds"));
            return result;
        }
        //分页信息
        System.out.println(JSON.toJSONString(parentIds)); 
        int count = iPmCategoryInfoAtomSV.countByCategoryId(parentIds, vo);
        int pageCount = (count + vo.getPageSize() - 1) / vo.getPageSize();
        pageInfo.setPageNo(vo.getPageNo());
        pageInfo.setPageSize(vo.getPageSize());
        pageInfo.setCount(count);
        pageInfo.setPageCount(pageCount);
//        System.out.println(JSON.toJSONString(parentIds)); 
        //根据主产品ID选出类目ID(分页)
        List<PmCategoryInfo>  categoryInfoList = iPmCategoryInfoAtomSV.queryLv2In(parentIds, vo);
        List<String>categoryIds = new ArrayList<>();
        for(PmCategoryInfo pm:categoryInfoList){
            categoryIds.add(pm.getCategoryId());
        }
        if(categoryIds==null||categoryIds.isEmpty()){
            result.setResponseHeader(new ResponseHeader(false, "000001", "no result with categoryIds"));
            return result;
            //判空
        }
        
        List<PmCatalogInfo> catalogInfos = iPmCatalogInfoAtomSv.queryElementByCategoryIds(categoryIds,vo);
        for(PmCatalogInfo catalogInfo:catalogInfos){
            String tenantId = catalogInfo.getTenantId();
            String categoryId = catalogInfo.getCategoryId();
            boolean categoryIdIsExist = false;
            List<Product> productList;  
            if(pageInfo.getResult()!=null&&!pageInfo.getResult().isEmpty()){
                productList = pageInfo.getResult();
            }else{
                productList = new ArrayList<>();
            }
            /*遍历判断当前科目ID是否已经存在*/
            if(productList!=null&&!productList.isEmpty()){
                for(Product product : productList){
                        if(categoryId.equals(product.getCategoryId())){
                            LOG.info("匹配到产品目录,CategoryId:"+product.getCategoryId()+" CategoryName : "+product.getCategoryName());
                            List<Element>  elements = product.getElements();
                            Element el = new Element();
                            el.setBillingCycle(catalogInfo.getBillingCycle());
                            el.setPricePolicy(catalogInfo.getPricePolicy());
                            el.setPricePolicyName(QueryPolicyName(catalogInfo,tenantId));
                            el.setSpecTypeId(catalogInfo.getSpecTypeId());
                            el.setSpecTypeName(catalogInfo.getSpecTypeName());
                            elements.add(el);
                            product.setElements(elements); 
//                            productList.add(product);
                            categoryIdIsExist = true;
                            break;
                        }
                }
            }
            if(!categoryIdIsExist){

                Product product = new Product();
                product.setCategoryId(categoryId);
                List<PmCategoryInfo> pmCategoryInfos =  iPmCategoryInfoAtomSV.getPmCategoryInfoByCategoryId(tenantId, categoryId);
                String categoryName; 
                if(pmCategoryInfos == null||pmCategoryInfos.isEmpty()){
                    LOG.error("未找到类目ID: "+categoryId+" 对应的子产品名称"); 
                    result.setResponseHeader(new ResponseHeader(false, "000001", "未找到类目ID: "+categoryId+" 对应的子产品名称"));
                    return result;
                }
                categoryName = pmCategoryInfos.get(0).getCategoryName();
                product.setCategoryName(categoryName);//从产品类目信息表中获取 
                product.setBillingCycle(catalogInfo.getBillingCycle());
                product.setMainProductId(catalogInfo.getMainProductId());
                product.setMainProductName(catalogInfo.getMainProductName());
                product.setTradeCode(catalogInfo.getTradeCode());
                Element el = new Element();
                el.setBillingCycle(catalogInfo.getBillingCycle());
                el.setPricePolicy(catalogInfo.getPricePolicy());
                el.setPricePolicyName(QueryPolicyName(catalogInfo,tenantId));
                el.setSpecTypeId(catalogInfo.getSpecTypeId());
                el.setSpecTypeName(catalogInfo.getSpecTypeName());
                List<Element>  elements = new ArrayList<>();
                elements.add(el);
                product.setElements(elements);
                productList.add(product);
            }
            pageInfo.setResult(productList);
        }
        result.setProducts(pageInfo);
        
        LOG.info("result"+JSON.toJSONString(result)); 
        result.setResponseHeader(new ResponseHeader(true, "000000", "query success"));
        return result;
    }

    @Override
    public String addElement(ElementIncreaseVO vo) {
        try{
            List<PmCatalogInfo> pmCatalogInfos= new ArrayList<>();
    
            List<Element> elements = vo.getElements();
            for(Element el : elements){
                PmCatalogInfo pm = new PmCatalogInfo();
                pm.setTradeCode(vo.getTradeCode());//行业类型编码
                pm.setBillingCycle(vo.getBillingCycle());
                pm.setCategoryId(vo.getCategoryId());
                pm.setMainProductId(vo.getMainProductId());
                pm.setMainProductName(vo.getMainProductName());
                pm.setTenantId(vo.getTenantId());
                pm.setPricePolicy(el.getPricePolicy());
    //            pm.setSpecDetailId();
                pm.setSpecTypeId(el.getSpecTypeId());
                pm.setSpecTypeName(el.getSpecTypeName());
                pmCatalogInfos.add(pm);
            }
            iPmCatalogInfoAtomSv.insertElement(pmCatalogInfos);
        }catch(Exception e){
            LOG.error("CREATE NEW ELEMENT FAIL!", e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return "0";
        }

        String flag = notifyAddPriceMaking(vo.getCategoryId(),vo.getTenantId(),vo.getCategoryType());
        return flag;
    }
    
    @Override
    public String deleteElement(ElementDeleteVO vo) {
        
        try{
            List<PmCatalogInfo> pmCatalogInfoList = new ArrayList<>();
            PmCatalogInfo pm = new PmCatalogInfo();
            pm.setTenantId(vo.getTenantId());
            if(StringUtil.isBlank(vo.getCategoryId())){
                pm.setMainProductId(vo.getMainProductId());
                for(String categoryId : vo.getCategoryIds()){
                    notifyDelPriceMaking(categoryId,vo.getTenantId());  
                }
            }
            if(StringUtil.isBlank(vo.getMainProductId())){
                pm.setCategoryId(vo.getCategoryId());
                notifyDelPriceMaking(vo.getCategoryId(),vo.getTenantId());  
            }
            pmCatalogInfoList.add(pm);
            iPmCatalogInfoAtomSv.deleteElement(pmCatalogInfoList);
        }catch(Exception e){
            LOG.error("DELETE NEW ELEMENT FAIL!", e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return "0";
        }
        
        return "1";
    }

    

    private boolean checkCategoryIsNotExist(String categoryId, String tenantId) {
        List<PmCategoryInfo> pmCategoryInfos =  iPmCategoryInfoAtomSV.getPmCategoryInfoByCategoryId(tenantId, categoryId);
        if(pmCategoryInfos==null||pmCategoryInfos.isEmpty()){
            return true;
        }
        return false;
    }
    
    private boolean checkCatelogIsNotExist(String categoryId , String tenantId){
        List<PmCatalogInfo>pmCatalogInfos = iPmCatalogInfoAtomSv.queryElementDetail(categoryId, tenantId) ;
        if(pmCatalogInfos == null||pmCatalogInfos.isEmpty()){
            return true;
        }
        return false;
        
    }



    @Override
    public String alterElement(ElementIncreaseVO vo){
        String deleteflag;
        String addflag;
        ElementDeleteVO deleteVO = new ElementDeleteVO();
        deleteVO.setCategoryId(vo.getCategoryId());
        deleteVO.setTenantId(vo.getTenantId());
        deleteflag = deleteElement(deleteVO);
        addflag = addElement(vo);
        if(deleteflag.equals("1")&&addflag.equals("1")){
//            拼装参数  调用更新接口
//            notifyAlterPriceMaking(vo.getCategoryId(),vo.getTenantId());
            return "1";
        }else{
            return "0";
        }
    }




    @Override
    public String checkElement(CheckCategoryId vo) {
        List<PmCatalogInfo> pmList = iPmCatalogInfoAtomSv.checkExist(vo);
        
        if(pmList == null || pmList.isEmpty()){
            return "1";
        }else{
            LOG.info("List<PmCatalogInfo>: "+JSON.toJSONString(pmList)); 
            return "0";
        }
    }
    @Override
    public void alterElementByProduct(UpdateByProductVo vo) {
        String tenantId = vo.getTenantId();
        String mainProCode = vo.getCategoryId();
                       
        List<PmCategoryInfo>pmCategoryInfos =  iPmCategoryInfoAtomSV.getPmCategoryByPId(tenantId, mainProCode);     
        Set<String> idinCategoryInfo = new HashSet<>();  
        for(PmCategoryInfo cg: pmCategoryInfos){
            idinCategoryInfo.add(cg.getCategoryId());
        }
        
        List<PmCatalogInfo> pmCatalogInfos = iPmCatalogInfoAtomSv.queryElementByMainProductId(tenantId,mainProCode);
        Set<String> idinCatalogInfo = new HashSet<>();
        for(PmCatalogInfo cl: pmCatalogInfos){
            idinCatalogInfo.add(cl.getCategoryId());
        }
        
        for(String categoryId : idinCatalogInfo){
                boolean notExistFlag = true;
                for(String IdinCategory :idinCategoryInfo){
                    if(categoryId.equals(IdinCategory)){
                        notExistFlag = false;
                        break;
                    }   
                }
                if(notExistFlag){
                    LOG.info("删除类目ID为: "+categoryId+" 的子产品");
                    //拼装参数
                    //调用删除接口
                    notifyDelPriceMaking(categoryId,tenantId);  
                    iPmCatalogInfoAtomSv.deleteElementByCategoryId(tenantId,categoryId);
                }else{
                    //拼装参数
                    //调用更新接口
                    notifyAlterPriceMaking(categoryId, tenantId,"ZJ"); 
                }
        }
    }
    
    
	@Override
	public BaseSpecResponse querySpecTypeList(SpecTypeQueryReq req) {
		// TODO Auto-generated method stub
		
		List<PmSpecType> pmSpecTypes = this.pmSpecTypeAtomSV.selectByCategoryId(req.getMainProductId(), req.getTenantId());
		
		List<PmSpecTypeRes> list = new ArrayList<>();
		if(pmSpecTypes!=null){
			for(PmSpecType specType:pmSpecTypes){
				PmSpecTypeRes res = new PmSpecTypeRes();
				BeanUtils.copyProperties(res, specType);
				list.add(res);
			}
		}
		BaseSpecResponse response = new BaseSpecResponse();
		response.setSpecTypes(list);
        response.setTenantId(req.getTenantId());
        response.setResponseHeader(new ResponseHeader(true, ExceptCodeConstants.SUCCESS, "成功"));
		return response;
	}

	public String QueryPolicyName(PmCatalogInfo catalogInfo,String tenantId){
	    List<PmPolicyInfo> pmPolicyInfos = pmPolicyInfoAtomSV.queryPolicyByPolicyId(catalogInfo.getPricePolicy(), tenantId);
        if(pmPolicyInfos == null||pmPolicyInfos.isEmpty()){
            LOG.error("未找到策略ID: "+catalogInfo.getPricePolicy()+" 对应的策略名称"); 
            throw new BusinessException("未找到策略ID: "+catalogInfo.getPricePolicy()+" 对应的策略名称");
        }
        return  pmPolicyInfos.get(0).getPolicyName();
	}



    @Override
    public ElementDetailRequireResult queryPriceElementDetail(ElementDetailRequireVO vo) {
        ElementDetailRequireResult result = new ElementDetailRequireResult();
        List<Element> elements = new ArrayList<>(); 
        result.setElements(elements);

        List<PmCatalogInfo> catalogInfos = iPmCatalogInfoAtomSv.queryElementDetail(vo.getCategoryId(), vo.getTenantId());


        List<PmCategoryInfo> pmCategoryInfos =  iPmCategoryInfoAtomSV.getPmCategoryInfoByCategoryId(catalogInfos.get(0).getTenantId(), catalogInfos.get(0).getCategoryId());
        String categoryName; 
        if(pmCategoryInfos == null||pmCategoryInfos.isEmpty()){
            LOG.error("未找到类目ID: "+catalogInfos.get(0).getCategoryId()+" 对应的子产品名称"); 
            result.setResponseHeader(new ResponseHeader(false, "000001", "未找到类目ID: "+catalogInfos.get(0).getCategoryId()+" 对应的子产品名称"));
            return result;
        }
        categoryName = pmCategoryInfos.get(0).getCategoryName();
        result.setBillingCycle(catalogInfos.get(0).getBillingCycle());
        result.setCategoryId(catalogInfos.get(0).getCategoryId());
        result.setCategoryName(categoryName);
        result.setMainProductId(catalogInfos.get(0).getMainProductId());
        result.setMainProductName(catalogInfos.get(0).getMainProductName());
        result.setTradeCode(catalogInfos.get(0).getTradeCode());     
        
        for(PmCatalogInfo catalogInfo:catalogInfos){
                Element el = new Element();
                el.setBillingCycle(catalogInfo.getBillingCycle());
                el.setPricePolicy(catalogInfo.getPricePolicy());
                el.setPricePolicyName(QueryPolicyName(catalogInfo,catalogInfo.getTenantId()));
                el.setSpecTypeId(catalogInfo.getSpecTypeId());
                el.setSpecTypeName(catalogInfo.getSpecTypeName());
                elements.add(el);

        }
        
        LOG.info(JSON.toJSONString(result)); 
        return result;
        
    }
    
    @Override
    public String alterPriceMakingByPolicy(ConvertParams vo) {
        try{
            notifyAlterPriceMaking(vo.getCategoryId(),vo.getTenantId(),vo.getCategoryType());
        }catch(Exception e){
            LOG.error("UPDATE PRICEMAKING FAIL!", e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return "0";
        }
        return "1";
    }
    
    
    private void verifyPriceMakingRequestParam(PriceMakingAddParam param) throws BusinessException{
        if(StringUtils.isBlank(param.getTenantId())){
            throw new BusinessException("租户ID不能为空!");
        }
        if(StringUtils.isBlank(param.getMainProductId())){
            throw new BusinessException("主产品ID不能为空!");
        }
        if(CollectionUtil.isEmpty(param.getPriceFactorList())){
            throw new BusinessException("价格因子列表不能为空!");
        }
    }
    
    private String notifyDelPriceMaking(String categoryId, String tenantId) {
        if(checkCategoryIsNotExist(categoryId,tenantId)){
            LOG.info("类目ID "+categoryId+"未配置产品目录"); 
//            return "0";
        }
        if(checkCatelogIsNotExist(categoryId,tenantId)){
            LOG.info("类目ID "+categoryId+"未配置产品元素"); 
            return "0";
        }
        ConvertParams convert = new ConvertParams();
        PriceMakingDelParam param = new PriceMakingDelParam();
        convert.setCategoryId(categoryId);
        convert.setTenantId(tenantId);
        param = iConvertBusiSV.buildDelMsg(convert);
        LOG.info("调用删除定价信息接口: "+JSON.toJSONString(param));
        priceMakingBusiSV.deletePriceMaking(param);

        return "1";
    }

    private String notifyAlterPriceMaking(String categoryId, String tenantId,String categoryType) {
        try{
            ConvertParams convert = new ConvertParams();
            PriceMakingAddParam param = new PriceMakingAddParam();
            //拼装转换参数
            convert.setCategoryId(categoryId);
            convert.setTenantId(tenantId);
            convert.setCategoryType(categoryType);
            param = iConvertBusiSV.buildMsg(convert);
            LOG.info("调用修改定价信息接口: "+JSON.toJSONString(param));
            verifyPriceMakingRequestParam(param);
            //调用转换接口 add
            priceMakingBusiSV.modifyPriceMaking(param);
        }catch(Exception e){
            LOG.error("AlterPriceMaking FAIL!", e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return "0";
        }
        return "1";
    }

    private String notifyAddPriceMaking(String categoryId, String tenantId,String categoryType) {
        try{
            ConvertParams convert = new ConvertParams();
            PriceMakingAddParam param = new PriceMakingAddParam();
            //拼装转换参数
            convert.setCategoryId(categoryId);
            convert.setTenantId(tenantId);
            convert.setCategoryType(categoryType);
            param = iConvertBusiSV.buildMsg(convert);
            LOG.info("调用增加定价信息接口: "+JSON.toJSONString(param));
            verifyPriceMakingRequestParam(param);
            //调用转换接口 add
            priceMakingBusiSV.addPriceMaking(param);
        }catch(Exception e){
            LOG.error("AddPriceMaking FAIL!", e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return "0";
        }
        return "1";
    }

    @Override
    public boolean checkExistPolicyId(CheckPolicyParam vo) {
        // TODO Auto-generated method stub
        return iPmCatalogInfoAtomSv.checkExistPolicyId(vo.getPolicyId(), vo.getTenantId())>0?true:false;
    }

	
}
