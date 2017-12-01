package com.ai.baas.prd.service.atom.impl;

import java.util.List;

import com.ai.baas.prd.api.category.params.CategoryQuery;
import com.ai.opt.sdk.util.CollectionUtil;

import org.springframework.stereotype.Component;

import com.ai.baas.prd.api.element.params.ConvertParams;
import com.ai.baas.prd.api.element.params.ElementRequireVO;
import com.ai.baas.prd.api.product.params.QueryPmCategoryInfoReq;
import com.ai.baas.prd.api.product.params.SubQueryReq;
import com.ai.baas.prd.constants.PrdConstants;
import com.ai.baas.prd.dao.mapper.bo.PmCategoryInfo;
import com.ai.baas.prd.dao.mapper.bo.PmCategoryInfoCriteria;
import com.ai.baas.prd.dao.mapper.factory.MapperFactory;
import com.ai.baas.prd.service.atom.interfaces.IPmCategoryInfoAtomSV;
import com.ai.opt.sdk.util.StringUtil;
@Component
public class PmcategoryInfoAtomSVImpl implements IPmCategoryInfoAtomSV{

	@Override
	public int addPmCategoryInfo(PmCategoryInfo info) {
		
		return MapperFactory.getPmCategoryInfoMapper().insert(info);
	}

	@Override
	public int delPmCategoryInfo(String tenantId, String mainProcode) {
		PmCategoryInfoCriteria example=new PmCategoryInfoCriteria();
		example.or().andTenantIdEqualTo(tenantId).andCategoryIdEqualTo(mainProcode).andCategoryTypeEqualTo(PrdConstants.PRODUCT.CATEGORY_TYPE);
		int p=MapperFactory.getPmCategoryInfoMapper().deleteByExample(example);
		
		
		PmCategoryInfoCriteria example1=new PmCategoryInfoCriteria();
		example1.or().andTenantIdEqualTo(tenantId).andParentIdEqualTo(mainProcode).andCategoryTypeEqualTo(PrdConstants.PRODUCT.CATEGORY_TYPE);
		int c=MapperFactory.getPmCategoryInfoMapper().deleteByExample(example1);
		
		if(c>0&&p>0){
			return 1;
		}
		
		return 0;
	}

	@Override
	public List<PmCategoryInfo> getPmCategoryInfo(String tenantId, String mainProcode) {
		PmCategoryInfoCriteria example=new PmCategoryInfoCriteria();
		example.or().andTenantIdEqualTo(tenantId).andCategoryIdEqualTo(mainProcode).andCategoryTypeEqualTo(PrdConstants.PRODUCT.CATEGORY_TYPE);
		return MapperFactory.getPmCategoryInfoMapper().selectByExample(example);
	}
	
   @Override
    public List<PmCategoryInfo> getPmCategoryInfoByCategoryId(String tenantId, String categoryId) {
        PmCategoryInfoCriteria example=new PmCategoryInfoCriteria();
        example.or().andTenantIdEqualTo(tenantId).andCategoryIdEqualTo(categoryId).andCategoryTypeEqualTo(PrdConstants.PRODUCT.CATEGORY_TYPE);
        return MapperFactory.getPmCategoryInfoMapper().selectByExample(example);
    }


	@Override
	public List<PmCategoryInfo> getPmCategoryInfos(QueryPmCategoryInfoReq req) {
		PmCategoryInfoCriteria example=new PmCategoryInfoCriteria();
		PmCategoryInfoCriteria.Criteria cre=example.createCriteria();
		cre.andTenantIdEqualTo(req.getTenantId()).andCategoryTypeEqualTo(PrdConstants.PRODUCT.CATEGORY_TYPE);
		if(!StringUtil.isBlank(req.getMainProCode())){
			cre.andCategoryIdEqualTo(req.getMainProCode());
		}
		if(!StringUtil.isBlank(req.getMainProName())){
			cre.andCategoryNameLike("%"+req.getMainProName()+"%");
		}
		cre.andCategoryLevelEqualTo(PrdConstants.PRODUCT.LEVEL1);
		if(req.getPageNO()!=null&&req.getPageSize()!=null){
			example.setLimitStart((req.getPageNO()-1)*req.getPageSize());
			example.setLimitEnd(req.getPageSize());
		}
		return MapperFactory.getPmCategoryInfoMapper().selectByExample(example);
	}

	@Override
	public int getPmCategoryInfosCount(QueryPmCategoryInfoReq req) {
		PmCategoryInfoCriteria example=new PmCategoryInfoCriteria();
		PmCategoryInfoCriteria.Criteria cre=example.createCriteria();
		
		cre.andTenantIdEqualTo(req.getTenantId());
		if(!StringUtil.isBlank(req.getMainProCode())){
			cre.andCategoryIdEqualTo(req.getMainProCode());
		}
		if(!StringUtil.isBlank(req.getMainProName())){
			cre.andCategoryNameLike("%"+req.getMainProName()+"%");
		}
		cre.andCategoryLevelEqualTo(PrdConstants.PRODUCT.LEVEL1);
		cre.andCategoryTypeEqualTo(PrdConstants.PRODUCT.CATEGORY_TYPE);
		return MapperFactory.getPmCategoryInfoMapper().countByExample(example);
	}

	@Override
	public List<PmCategoryInfo> getPmCategoryByPId(String tenantId, String mainProCode) {
		PmCategoryInfoCriteria example=new PmCategoryInfoCriteria();
		example.or().andTenantIdEqualTo(tenantId).andParentIdEqualTo(mainProCode).andCategoryTypeEqualTo(PrdConstants.PRODUCT.CATEGORY_TYPE);
		return MapperFactory.getPmCategoryInfoMapper().selectByExample(example);
		
	}

	@Override
	public int updatePmcategoryInfo(String tenantId, String parentId, String categoryId, PmCategoryInfo info) {
		PmCategoryInfoCriteria example=new PmCategoryInfoCriteria();
		example.or().andTenantIdEqualTo(tenantId).andParentIdEqualTo(parentId).andCategoryIdEqualTo(categoryId).andCategoryTypeEqualTo(PrdConstants.PRODUCT.CATEGORY_TYPE);
		return  MapperFactory.getPmCategoryInfoMapper().updateByExampleSelective(info, example);
	}

	@Override
	public int delPmcategoryInfo(String tenantId, String mainProCode, String categoryId) {
		PmCategoryInfoCriteria example=new PmCategoryInfoCriteria();
		example.or().andTenantIdEqualTo(tenantId).andParentIdEqualTo(mainProCode).andCategoryIdEqualTo(categoryId).andCategoryTypeEqualTo(PrdConstants.PRODUCT.CATEGORY_TYPE);
		
		return MapperFactory.getPmCategoryInfoMapper().deleteByExample(example);
	}

	@Override
	public int updateMaincategoryInfo(String tenantId, String categoryId, PmCategoryInfo info) {
		PmCategoryInfoCriteria example=new PmCategoryInfoCriteria();
		example.or().andTenantIdEqualTo(tenantId).andCategoryIdEqualTo(categoryId);
		return  MapperFactory.getPmCategoryInfoMapper().updateByExampleSelective(info, example);
	}

    @Override
    public List<PmCategoryInfo> queryLv1Like(ElementRequireVO req) {
        PmCategoryInfoCriteria example=new PmCategoryInfoCriteria();
        PmCategoryInfoCriteria.Criteria cre=example.createCriteria();
        cre.andTenantIdEqualTo(req.getTenantId());
        
        if(!StringUtil.isBlank(req.getMainProductId())){
            cre.andCategoryIdLike("%"+req.getMainProductId()+"%");
        }
        if(!StringUtil.isBlank(req.getMainProductName())){
            cre.andCategoryNameLike("%"+req.getMainProductName()+"%");
        }
        
        cre.andCategoryLevelEqualTo(PrdConstants.PRODUCT.LEVEL1);
        cre.andCategoryTypeEqualTo(PrdConstants.PRODUCT.CATEGORY_TYPE);

        return MapperFactory.getPmCategoryInfoMapper().selectByExample(example);
    }
    @Override
    public List<PmCategoryInfo> queryLv2In(List<String> parentIdList,ElementRequireVO req) {
        PmCategoryInfoCriteria example=new PmCategoryInfoCriteria();
        PmCategoryInfoCriteria.Criteria cre=example.createCriteria();
        cre.andTenantIdEqualTo(req.getTenantId());
        if(parentIdList!=null&&!parentIdList.isEmpty()){
            cre.andParentIdIn(parentIdList);
        }
        cre.andCategoryLevelEqualTo(PrdConstants.PRODUCT.LEVEL2);
        cre.andCategoryTypeEqualTo(PrdConstants.PRODUCT.CATEGORY_TYPE);
        
//        if(req.getPageNo()!=null&&req.getPageSize()!=null){
//            example.setLimitStart((req.getPageNo()-1)*req.getPageSize());
//            example.setLimitEnd(req.getPageSize());
//        }
        
        return MapperFactory.getPmCategoryInfoMapper().selectByExample(example);
    }


    @Override
    public int countByCategoryId(List<String> parentIdList,ElementRequireVO req){
        PmCategoryInfoCriteria example=new PmCategoryInfoCriteria();
        PmCategoryInfoCriteria.Criteria cre=example.createCriteria();
        cre.andTenantIdEqualTo(req.getTenantId());
        if(parentIdList!=null&&!parentIdList.isEmpty()){
            cre.andParentIdIn(parentIdList);
        }
        cre.andCategoryLevelEqualTo(PrdConstants.PRODUCT.LEVEL2);
        cre.andCategoryTypeEqualTo(PrdConstants.PRODUCT.CATEGORY_TYPE);
        if(req.getPageNo()!=null&&req.getPageSize()!=null){
            example.setLimitStart((req.getPageNo()-1)*req.getPageSize());
            example.setLimitEnd(req.getPageSize());
        }
        
        return MapperFactory.getPmCategoryInfoMapper().countByExample(example);
    }

	@Override
	public List<PmCategoryInfo> queryCategoryList(CategoryQuery req) {
		PmCategoryInfoCriteria categoryInfoCriteria = new PmCategoryInfoCriteria();
		PmCategoryInfoCriteria.Criteria criteria = categoryInfoCriteria.createCriteria();
		if(StringUtil.isBlank(req.getParentId())){
			criteria.andParentIdIsNull();
		}else{
			criteria.andParentIdEqualTo(req.getParentId());
		}
		criteria.andCategoryLevelEqualTo(req.getCategoryLevel());
		criteria.andCategoryTypeEqualTo(req.getCategoryType());
		return MapperFactory.getPmCategoryInfoMapper().selectByExample(categoryInfoCriteria);
	}

	@Override
	public PmCategoryInfo getCategoryInfoByCategoryId(String tenantId, String categoryId) {
		PmCategoryInfoCriteria categoryInfoCriteria = new PmCategoryInfoCriteria();
		PmCategoryInfoCriteria.Criteria criteria = categoryInfoCriteria.createCriteria();
		criteria.andTenantIdEqualTo(tenantId);
		criteria.andCategoryIdEqualTo(categoryId);
        List<PmCategoryInfo> pmCategoryInfos = MapperFactory.getPmCategoryInfoMapper().selectByExample(categoryInfoCriteria);
        if(!CollectionUtil.isEmpty(pmCategoryInfos)){
            return pmCategoryInfos.get(0);
        }
        return null;
	}

    @Override
    public List<PmCategoryInfo> getPmCategoryInfoByConvert(ConvertParams vo) {
        PmCategoryInfoCriteria categoryInfoCriteria = new PmCategoryInfoCriteria();
        PmCategoryInfoCriteria.Criteria criteria = categoryInfoCriteria.createCriteria();
        criteria.andTenantIdEqualTo(vo.getTenantId());
        criteria.andCategoryIdEqualTo(vo.getCategoryId());
        //criteria.andCategoryTypeEqualTo(vo.getCategoryType());
        List<PmCategoryInfo> pmCategoryInfos = MapperFactory.getPmCategoryInfoMapper().selectByExample(categoryInfoCriteria);
        if(!CollectionUtil.isEmpty(pmCategoryInfos)){
            return pmCategoryInfos;
        }
        return null;
    }

	@Override
	public List<PmCategoryInfo> getCategoryInfo(SubQueryReq req) {
		PmCategoryInfoCriteria example = new PmCategoryInfoCriteria();
        PmCategoryInfoCriteria.Criteria criteria = example.createCriteria();
        criteria.andTenantIdEqualTo(req.getTenantId());
        if(!StringUtil.isBlank(req.getProductId())){
        	 criteria.andCategoryIdEqualTo(req.getProductId());
        }
        if(!StringUtil.isBlank(req.getProductName())){
        	criteria.andCategoryNameLike("%"+req.getProductName()+"%");
        }
        criteria.andCategoryTypeEqualTo("ZJ");
        criteria.andCategoryLevelEqualTo("2");
        if(req.getPageNO()!=null&&req.getPageSize()!=null){
            example.setLimitStart((req.getPageNO()-1)*req.getPageSize());
            example.setLimitEnd(req.getPageSize());
        }
        List<PmCategoryInfo> pmCategoryInfos = MapperFactory.getPmCategoryInfoMapper().selectByExample(example);
        
        return pmCategoryInfos;
	}

	@Override
	public int getCategoryInfoCount(SubQueryReq req) {
		PmCategoryInfoCriteria example = new PmCategoryInfoCriteria();
        PmCategoryInfoCriteria.Criteria criteria = example.createCriteria();
        criteria.andTenantIdEqualTo(req.getTenantId());
        if(!StringUtil.isBlank(req.getProductId())){
        	 criteria.andCategoryIdEqualTo(req.getProductId());
        }
        if(!StringUtil.isBlank(req.getProductName())){
        	criteria.andCategoryNameLike("%"+req.getProductName()+"%");
        }
        criteria.andCategoryTypeEqualTo("ZJ");
        criteria.andCategoryLevelEqualTo("2");
        return MapperFactory.getPmCategoryInfoMapper().countByExample(example);
	}


}
