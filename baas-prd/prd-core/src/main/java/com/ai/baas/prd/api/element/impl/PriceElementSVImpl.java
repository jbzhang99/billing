package com.ai.baas.prd.api.element.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ai.baas.prd.api.element.interfaces.IPriceElementSV;
import com.ai.baas.prd.api.element.params.BaseSpecResponse;
import com.ai.baas.prd.api.element.params.CheckCategoryId;
import com.ai.baas.prd.api.element.params.CheckPolicyParam;
import com.ai.baas.prd.api.element.params.ConvertParams;
import com.ai.baas.prd.api.element.params.ElementDeleteVO;
import com.ai.baas.prd.api.element.params.ElementDetailRequireResult;
import com.ai.baas.prd.api.element.params.ElementDetailRequireVO;
import com.ai.baas.prd.api.element.params.ElementIncreaseVO;
import com.ai.baas.prd.api.element.params.ElementRequireResult;
import com.ai.baas.prd.api.element.params.ElementRequireVO;
import com.ai.baas.prd.api.element.params.SpecTypeQueryReq;
import com.ai.baas.prd.api.element.params.UpdateByProductVo;
import com.ai.baas.prd.service.business.interfaces.IPriceElementBusiSV;
import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.BaseResponse;
import com.ai.opt.base.vo.ResponseHeader;
import com.ai.opt.sdk.util.StringUtil;
import com.alibaba.dubbo.config.annotation.Service;

/**
 * 定价元素
 * @author wangkai16
 *
 */
@Service
public class PriceElementSVImpl implements IPriceElementSV{
    
    private static final Log LOG = LogFactory.getLog(PriceElementSVImpl.class);
    @Autowired
    private IPriceElementBusiSV priceElementSVImpl;

    @Override
    public ElementRequireResult searchElement(ElementRequireVO vo)throws BusinessException, SystemException {
        if(StringUtil.isBlank(vo.getTenantId())){
            throw new BusinessException("租户id不能为空");
        }
        ElementRequireResult re = new ElementRequireResult();
        re = priceElementSVImpl.queryPriceElement(vo); 
        re.setResponseHeader(new ResponseHeader(true, "000000", "查询成功"));
        return re; 
    }

    @Override
    public BaseResponse addElement(ElementIncreaseVO vo) throws BusinessException, SystemException {
        BaseResponse baseResponse = new BaseResponse();
        if(StringUtil.isBlank(vo.getTenantId())){
            throw new BusinessException("租户id不能为空");
        }
        if(StringUtil.isBlank(vo.getCategoryType())){
            throw new BusinessException("产品类型不能为空");
        }
        if(StringUtil.isBlank(vo.getCategoryId())){
             throw new BusinessException("类目ID不能为空");
        }
        if(StringUtil.isBlank(vo.getMainProductId())){
            throw new BusinessException("主产品ID不能为空");
       }
        if(StringUtil.isBlank(vo.getMainProductName())){
            throw new BusinessException("主产品名称不能为空");
       }
        if(StringUtil.isBlank(vo.getBillingCycle())){
            throw new BusinessException("计费周期不能为空");
        }
        if(vo.getElements()==null||vo.getElements().isEmpty()){
            throw new BusinessException("至少存在一个元素！");
       }
        String flag = priceElementSVImpl.addElement(vo);
        if(flag.equals("1")){
            baseResponse.setResponseHeader(new ResponseHeader(true, "000000", "新增定价元素成功"));
            
        }else{
            baseResponse.setResponseHeader(new ResponseHeader(false,"000001","新增定价元素失败"));
        }
        return baseResponse;
    }

    @Override
    public BaseResponse deleteElement(ElementDeleteVO vo)throws BusinessException, SystemException {
        BaseResponse baseResponse = new BaseResponse();
        if(StringUtil.isBlank(vo.getCategoryId())&&StringUtil.isBlank(vo.getMainProductId())){
            throw new BusinessException("主产品ID和类目ID都为空,无法执行删除操作");
        }
        if(!StringUtil.isBlank(vo.getCategoryId())&&!StringUtil.isBlank(vo.getMainProductId())){
            throw new BusinessException("主产品ID和类目ID同时存在，无法执行删除操作！");
        }
        if(StringUtil.isBlank(vo.getCategoryId())){
            LOG.info("【DELETE PriceElement】based on Main_Product_Id");
            if(vo.getCategoryIds()==null&&vo.getCategoryIds().isEmpty()){
                throw new BusinessException("categoryIdList不能为空，无法执行删除操作！");
            } 
        }
        if(StringUtil.isBlank(vo.getMainProductId())){
            LOG.info("【DELETE PriceElement】based on Category_Id");
        }
        
        String flag = priceElementSVImpl.deleteElement(vo);
        if(flag.equals("1")){
            baseResponse.setResponseHeader(new ResponseHeader(true,"000000","删除成功")); 
            
        }else{
            baseResponse.setResponseHeader(new ResponseHeader(false,"000001","删除失败"));
        }
        return baseResponse;
    }

    @Override
    public BaseResponse alterElement(ElementIncreaseVO vo) throws BusinessException, SystemException {
        BaseResponse baseResponse = new BaseResponse();
        if(StringUtil.isBlank(vo.getTenantId())){
            throw new BusinessException("租户id不能为空");
        }
        if(StringUtil.isBlank(vo.getCategoryType())){
            throw new BusinessException("产品类型不能为空");
        }
        if(StringUtil.isBlank(vo.getCategoryId())){
             throw new BusinessException("类目ID不能为空");
        }
        if(StringUtil.isBlank(vo.getMainProductId())){
            throw new BusinessException("主产品ID不能为空");
       }
        if(StringUtil.isBlank(vo.getMainProductName())){
            throw new BusinessException("主产品名称不能为空");
       }
        if(StringUtil.isBlank(vo.getBillingCycle())){
            throw new BusinessException("计费周期不能为空");
        }
        if(vo.getElements()==null&&vo.getElements().isEmpty()){
            throw new BusinessException("至少存在一个元素！");
       }
        String flag = priceElementSVImpl.alterElement(vo);
      //拼装修改参数
        if(flag.equals("1")){
            baseResponse.setResponseHeader(new ResponseHeader(true,"000000","修改成功")); 
            //调用转换接口 修改
        }else{
            baseResponse.setResponseHeader(new ResponseHeader(false,"000001","修改失败"));
        }
        return baseResponse;
    }

    @Override
    public BaseResponse checkElement(CheckCategoryId vo) throws BusinessException, SystemException {
        BaseResponse baseResponse = new BaseResponse();
        if(StringUtil.isBlank(vo.getTenantId())){
            throw new BusinessException("租户id不能为空");
        }
        if(StringUtil.isBlank(vo.getCategoryId())){
            throw new BusinessException("类目ID不能为空");
       }
        
        String flag = priceElementSVImpl.checkElement(vo);
        if(flag.equals("1")){
            baseResponse.setResponseHeader(new ResponseHeader(true,"000000","可新增")); 
        }else{
            baseResponse.setResponseHeader(new ResponseHeader(false,"000001","当前类目ID已经存在！")); 
        }
        
        return baseResponse;
    }
    
    @Override
    public BaseResponse updateFromCategoryInfo(UpdateByProductVo vo)throws BusinessException, SystemException{
        BaseResponse baseResponse = new BaseResponse();
        if(StringUtil.isBlank(vo.getCategoryId())){
            throw new BusinessException("类目ID不能为空");
       }
        if(StringUtil.isBlank(vo.getTenantId())){
            throw new BusinessException("租户id不能为空");
        }
        priceElementSVImpl.alterElementByProduct(vo);
        
        baseResponse.setResponseHeader(new ResponseHeader(true,"000000","update success")); 
        return baseResponse;
    }

	@Override
	public BaseSpecResponse querySpecTypeList(SpecTypeQueryReq req) throws BusinessException, SystemException {
		// TODO Auto-generated method stub
		if(StringUtil.isBlank(req.getTenantId())){
            throw new BusinessException("租户id不能为空");
        }
        if(StringUtil.isBlank(req.getMainProductId())){
            throw new BusinessException("主产品ID不能为空");
       }
		
		return this.priceElementSVImpl.querySpecTypeList(req);
	}

    @Override
    public ElementDetailRequireResult searchElementDetail(ElementDetailRequireVO vo) throws BusinessException, SystemException {
        if(StringUtil.isBlank(vo.getTenantId())){
            throw new BusinessException("租户id不能为空");
        }
        if(StringUtil.isBlank(vo.getCategoryId())){
            throw new BusinessException("类目ID不能为空");
       }
        
        ElementDetailRequireResult re = new ElementDetailRequireResult();
        re = priceElementSVImpl.queryPriceElementDetail(vo);
        re.setResponseHeader(new ResponseHeader(true, "000000", "查询子产品成功"));
        return re; 
    }

    @Override
    public BaseResponse updateFromPolicy(ConvertParams vo)
            throws BusinessException, SystemException {
        BaseResponse baseResponse = new BaseResponse();
        if(StringUtil.isBlank(vo.getTenantId())){
            throw new BusinessException("租户id不能为空");
        }
        if(StringUtil.isBlank(vo.getCategoryId())){
            throw new BusinessException("类目ID不能为空");
       }
        
        String flag = priceElementSVImpl.alterPriceMakingByPolicy(vo);
        
        if(flag.equals("1")){
            baseResponse.setResponseHeader(new ResponseHeader(true,"000000","策略修改更新定价成功")); 
        }else{
            baseResponse.setResponseHeader(new ResponseHeader(false,"000001","策略修改更新定价失败")); 
        }
        return baseResponse;
    }
    
    @Override
	public BaseResponse checkExistPolicyId(CheckPolicyParam vo) throws BusinessException, SystemException {
	    // TODO Auto-generated method stub
	    
	    if(StringUtil.isBlank(vo.getTenantId())){
	        throw new BusinessException("租户id不能为空");
	    }
	    if(StringUtil.isBlank(vo.getPolicyId())){
	        throw new BusinessException("策略ID不能为空");
	    }
	    
	    BaseResponse baseResponse = new BaseResponse();
	    boolean flag = this.priceElementSVImpl.checkExistPolicyId(vo);
	    
	    if(flag){
	        baseResponse.setResponseHeader(new ResponseHeader(true,"000000","已存在")); 
	    }else{
	        baseResponse.setResponseHeader(new ResponseHeader(false,"000001","不存在")); 
	    }
	    return baseResponse;
	}

//    @Override 
//    public BaseResponse convertToPriceMaking(ConvertParams vo)throws BusinessException, SystemException {
//       
//
//        return null;
//    }

}
