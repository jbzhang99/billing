package com.ai.baas.prd.service.atom.impl;

import java.util.List;

import com.ai.baas.prd.api.product.params.ProductQueryVo;
import com.ai.baas.prd.api.product.params.SpecQueryVo;
import com.ai.baas.prd.api.product.params.StandardProductRequest;
import com.ai.opt.sdk.util.BeanUtils;
import com.ai.opt.sdk.util.CollectionUtil;
import org.springframework.stereotype.Component;

import com.ai.baas.prd.api.element.params.CheckCategoryId;
import com.ai.baas.prd.api.element.params.ElementRequireVO;
import com.ai.baas.prd.dao.mapper.bo.PmCatalogInfo;
import com.ai.baas.prd.dao.mapper.bo.PmCatalogInfoCriteria;
import com.ai.baas.prd.dao.mapper.factory.MapperFactory;
import com.ai.baas.prd.service.atom.interfaces.IPmCatalogInfoAtomSV;
import com.ai.opt.sdk.util.StringUtil;
@Component
public class PmCatalogInfoAtomSVImpl implements IPmCatalogInfoAtomSV{

    @Override
    public List<PmCatalogInfo> queryElementByCategoryIds(List<String> categoryIds,ElementRequireVO vo) {
        PmCatalogInfoCriteria catalogInfoCriteria = new PmCatalogInfoCriteria();
        PmCatalogInfoCriteria.Criteria criteria = catalogInfoCriteria.createCriteria();
        
        criteria.andTenantIdEqualTo(vo.getTenantId());
        criteria.andCategoryIdIn(categoryIds);
        if(!StringUtil.isBlank(vo.getBillingCycle())){
            criteria.andBillingCycleEqualTo(vo.getBillingCycle());
        }

        return MapperFactory.getPmCatalogInfoMapper().selectByExample(catalogInfoCriteria);    
        }
    @Override
    public List<PmCatalogInfo> queryElementDetail(String categoryId,String tenantId){
        PmCatalogInfoCriteria catalogInfoCriteria = new PmCatalogInfoCriteria();
        PmCatalogInfoCriteria.Criteria criteria = catalogInfoCriteria.createCriteria();
        criteria.andTenantIdEqualTo(tenantId);
        criteria.andCategoryIdEqualTo(categoryId);
        return MapperFactory.getPmCatalogInfoMapper().selectByExample(catalogInfoCriteria);  
    }
    @Override
    public List<PmCatalogInfo> queryElementByMainProductId(String tenantId,String mainProductId){
        PmCatalogInfoCriteria catalogInfoCriteria = new PmCatalogInfoCriteria();
        PmCatalogInfoCriteria.Criteria criteria = catalogInfoCriteria.createCriteria();
        criteria.andTenantIdEqualTo(tenantId);
        criteria.andMainProductIdEqualTo(mainProductId);
        return MapperFactory.getPmCatalogInfoMapper().selectByExample(catalogInfoCriteria);  
    }
    
    @Override
    public List<PmCatalogInfo> queryElement(ElementRequireVO example) {
        // TODO Auto-generated method stub
        PmCatalogInfoCriteria catalogInfoCriteria = new PmCatalogInfoCriteria();
        PmCatalogInfoCriteria.Criteria criteria = catalogInfoCriteria.createCriteria();
        
        criteria.andTenantIdEqualTo(example.getTenantId());
        /*
         * 计费周期，主产品ID，主产品名称
         */
        if(!StringUtil.isBlank(example.getBillingCycle())){
            criteria.andBillingCycleEqualTo(example.getBillingCycle());
        }
        if(!StringUtil.isBlank(example.getMainProductId())){
            criteria.andMainProductIdLike("%"+example.getMainProductId()+"%");
        }
        if(!StringUtil.isBlank(example.getMainProductName())){
            criteria.andMainProductNameLike("%"+example.getMainProductName()+"%");
        }
        
        catalogInfoCriteria.setLimitStart((example.getPageNo()-1)*example.getPageSize());
        catalogInfoCriteria.setLimitEnd(example.getPageSize());
        return MapperFactory.getPmCatalogInfoMapper().selectByExample(catalogInfoCriteria);
    }
    
    @Override
    public void insertElement(List<PmCatalogInfo> pmCatalogInfoList) {
        
        for(PmCatalogInfo pm : pmCatalogInfoList){        
            MapperFactory.getPmCatalogInfoMapper().insert(pm);
        }
    
    }

    @Override
    public void deleteElement(List<PmCatalogInfo> pmCatalogInfoList) {
        for(PmCatalogInfo pm : pmCatalogInfoList){        
            PmCatalogInfoCriteria catalogInfoCriteria = new PmCatalogInfoCriteria();
            PmCatalogInfoCriteria.Criteria criteria = catalogInfoCriteria.createCriteria();
            criteria.andTenantIdEqualTo(pm.getTenantId());
            if(!StringUtil.isBlank(pm.getCategoryId())){
                criteria.andCategoryIdEqualTo(pm.getCategoryId());
            }
            if(!StringUtil.isBlank(pm.getMainProductId())){
                criteria.andMainProductIdEqualTo(pm.getMainProductId());
            }
            MapperFactory.getPmCatalogInfoMapper().deleteByExample(catalogInfoCriteria);
        }
    }
    
    @Override
    public void deleteElementByCategoryId(String tenantId,String categoryId){
        PmCatalogInfoCriteria catalogInfoCriteria = new PmCatalogInfoCriteria();
        PmCatalogInfoCriteria.Criteria criteria = catalogInfoCriteria.createCriteria();
        criteria.andTenantIdEqualTo(tenantId);
        criteria.andCategoryIdEqualTo(categoryId);
        MapperFactory.getPmCatalogInfoMapper().deleteByExample(catalogInfoCriteria);
    }

    @Override
    public void addCataLog(PmCatalogInfo catalogInfo) {
        MapperFactory.getPmCatalogInfoMapper().insertSelective(catalogInfo);
    }

    @Override
    public List<PmCatalogInfo> getAllSpec(SpecQueryVo req) {
        PmCatalogInfoCriteria infoCriteria = new PmCatalogInfoCriteria();
        PmCatalogInfoCriteria.Criteria criteria = infoCriteria.createCriteria();
        criteria.andTradeCodeEqualTo(req.getTradeCode());
        criteria.andTenantIdEqualTo(req.getTenantId());
        return MapperFactory.getPmCatalogInfoMapper().getAllSpecByTradeType(infoCriteria);
    }

    @Override
    public Integer getProductCount(ProductQueryVo req) {
        PmCatalogInfoCriteria con = new PmCatalogInfoCriteria();
        PmCatalogInfoCriteria.Criteria criteria = con.createCriteria();
        criteria.andTenantIdEqualTo(req.getTenantId());
        criteria.andTradeCodeEqualTo(req.getTradeCode());
        if(!StringUtil.isBlank(req.getProductName())){
            criteria.andMainProductNameLike("%"+req.getProductName()+"%");
        }
        if(!StringUtil.isBlank(req.getProductId())){
            criteria.andIdEqualTo(Long.parseLong(req.getProductId()));
        }
        if(!StringUtil.isBlank(req.getBillingCycle())){
            criteria.andBillingCycleEqualTo(req.getBillingCycle());
        }
        return MapperFactory.getPmCatalogInfoMapper().countByExample(con);
    }

    @Override
    public List<PmCatalogInfo> getProductList(ProductQueryVo req) {
        PmCatalogInfoCriteria con = new PmCatalogInfoCriteria();
        con.setLimitStart((req.getPageNo()-1)*req.getPageSize());
        con.setLimitEnd(req.getPageSize());
        PmCatalogInfoCriteria.Criteria criteria = con.createCriteria();
        criteria.andTenantIdEqualTo(req.getTenantId());
        criteria.andTradeCodeEqualTo(req.getTradeCode());
        if(!StringUtil.isBlank(req.getProductName())){
            criteria.andMainProductNameLike("%"+req.getProductName()+"%");
        }
        if(!StringUtil.isBlank(req.getProductId())){
            criteria.andIdEqualTo(Long.parseLong(req.getProductId()));
        }
        if(!StringUtil.isBlank(req.getBillingCycle())){
            criteria.andBillingCycleEqualTo(req.getBillingCycle());
        }
        return MapperFactory.getPmCatalogInfoMapper().selectByExample(con);
    }

    @Override
    public PmCatalogInfo getProductById(String productId) {
        return MapperFactory.getPmCatalogInfoMapper().selectByPrimaryKey(Long.parseLong(productId));
    }

    @Override
    public void deleteProductById(String productId) {
        MapperFactory.getPmCatalogInfoMapper().deleteByPrimaryKey(Long.parseLong(productId));
    }

    @Override
    public void updateProduct(StandardProductRequest req) {
        PmCatalogInfo record = new PmCatalogInfo();
        BeanUtils.copyProperties(record,req);
        record.setId(Long.parseLong(req.getProductId()));
        MapperFactory.getPmCatalogInfoMapper().updateByPrimaryKeySelective(record);
    }

    @Override
    public PmCatalogInfo getProductByName(String productName) {
        PmCatalogInfoCriteria con = new PmCatalogInfoCriteria();
        PmCatalogInfoCriteria.Criteria criteria = con.createCriteria();
        criteria.andMainProductNameEqualTo(productName);
        List<PmCatalogInfo> catalogInfos = MapperFactory.getPmCatalogInfoMapper().selectByExample(con);
        if(!CollectionUtil.isEmpty(catalogInfos)){
            return catalogInfos.get(0);
        }
        return null;
    }

    @Override
    public void alterElement(List<PmCatalogInfo> pmCatalogInfoList) {

    }

    @Override
    public List<PmCatalogInfo> checkExist(CheckCategoryId example) {
        PmCatalogInfoCriteria catalogInfoCriteria = new PmCatalogInfoCriteria();
        PmCatalogInfoCriteria.Criteria criteria = catalogInfoCriteria.createCriteria();
        criteria.andTenantIdEqualTo(example.getTenantId());
        criteria.andCategoryIdEqualTo(example.getCategoryId());
       
        return MapperFactory.getPmCatalogInfoMapper().selectByExample(catalogInfoCriteria);
    }

    @Override
    public int checkExistPolicyId(String policyId, String tenantId) {
        // TODO Auto-generated method stub
        
        PmCatalogInfoCriteria catalogInfoCriteria = new PmCatalogInfoCriteria();
        PmCatalogInfoCriteria.Criteria criteria = catalogInfoCriteria.createCriteria();
        criteria.andTenantIdEqualTo(tenantId);
        criteria.andPricePolicyEqualTo(policyId);
        
        return MapperFactory.getPmCatalogInfoMapper().countByExample(catalogInfoCriteria);
    }
	@Override
	public List<PmCatalogInfo> queryByPolicyId(String policyId, String tenantId) {
		// TODO Auto-generated method stub
		PmCatalogInfoCriteria catalogInfoCriteria = new PmCatalogInfoCriteria();
        PmCatalogInfoCriteria.Criteria criteria = catalogInfoCriteria.createCriteria();
        
        criteria.andTenantIdEqualTo(tenantId);
        criteria.andPricePolicyEqualTo(policyId);

        return MapperFactory.getPmCatalogInfoMapper().selectByExample(catalogInfoCriteria);
	}
	@Override
	public List<PmCatalogInfo> getAll(String tenantId) {
		// TODO Auto-generated method stub
		PmCatalogInfoCriteria infoCriteria = new PmCatalogInfoCriteria();
        PmCatalogInfoCriteria.Criteria criteria = infoCriteria.createCriteria();
        criteria.andTenantIdEqualTo(tenantId);
        return MapperFactory.getPmCatalogInfoMapper().getAllSpecByTradeType(infoCriteria);
	}
}
