package com.ai.baas.prd.service.atom.interfaces;

import java.util.List;

import com.ai.baas.prd.api.category.params.CategoryQuery;
import com.ai.baas.prd.api.element.params.ConvertParams;
import com.ai.baas.prd.api.element.params.ElementRequireVO;
import com.ai.baas.prd.api.product.params.QueryPmCategoryInfoReq;
import com.ai.baas.prd.api.product.params.SubQueryReq;
import com.ai.baas.prd.dao.mapper.bo.PmCategoryInfo;

public interface IPmCategoryInfoAtomSV {
	
    public int addPmCategoryInfo(PmCategoryInfo info );
    public int delPmCategoryInfo(String tenantId,String mainProcode);
    public List<PmCategoryInfo> getPmCategoryInfo(String tenantId,String mainProcode);
    public List<PmCategoryInfo> getPmCategoryInfos(QueryPmCategoryInfoReq req);
    public int  getPmCategoryInfosCount(QueryPmCategoryInfoReq req);
    public List<PmCategoryInfo> getPmCategoryByPId(String tenantId,String mainProCode);
    public int updatePmcategoryInfo(String tenantId,String parentId,String categoryId,PmCategoryInfo info);
    public int delPmcategoryInfo(String tenantId,String mainProCode,String categoryId);
    public int updateMaincategoryInfo(String tenantId, String categoryId, PmCategoryInfo info);
    public  List<PmCategoryInfo> getPmCategoryInfoByCategoryId(String tenantId, String categoryId);
    public List<PmCategoryInfo> queryLv1Like(ElementRequireVO vo);
    public List<PmCategoryInfo> queryLv2In(List<String> parentIdList,ElementRequireVO req);
    int countByCategoryId(List<String> parentIdList, ElementRequireVO req);

    List<PmCategoryInfo> queryCategoryList(CategoryQuery req);

    PmCategoryInfo getCategoryInfoByCategoryId(String tenantId, String mainProductId);
    public List<PmCategoryInfo> getPmCategoryInfoByConvert(ConvertParams vo);
    
    List<PmCategoryInfo> getCategoryInfo(SubQueryReq req);
    int getCategoryInfoCount(SubQueryReq req);
}
