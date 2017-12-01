package com.ai.baas.prd.api.category.impl;

import com.ai.baas.prd.api.category.interfaces.ICategorySV;
import com.ai.baas.prd.api.category.params.CategoryQuery;
import com.ai.baas.prd.api.category.params.CategoryQueryResponse;
import com.ai.baas.prd.api.category.params.CategoryVo;
import com.ai.baas.prd.dao.mapper.bo.PmCategoryInfo;
import com.ai.baas.prd.service.business.interfaces.ICategoryBusiSV;
import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.ResponseHeader;
import com.ai.opt.sdk.util.BeanUtils;
import com.ai.opt.sdk.util.CollectionUtil;
import com.ai.opt.sdk.util.StringUtil;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@Component
public class CategorySVImpl implements ICategorySV{

    @Autowired
    private ICategoryBusiSV categoryBusiSV;

    @Override
    public CategoryQueryResponse getCategoryList(CategoryQuery req) throws BusinessException, SystemException {
        if(StringUtil.isBlank(req.getTenantId())){
            throw new BusinessException("888888", "租户Id不能为空");
        }
        List<PmCategoryInfo> categoryList = categoryBusiSV.getCategoryList(req);
        List<CategoryVo> vos = new ArrayList<>();
        CategoryQueryResponse response = new CategoryQueryResponse();
        if(!CollectionUtil.isEmpty(categoryList)){
            for(PmCategoryInfo pmCategoryInfo:categoryList){
                CategoryVo categoryVo = new CategoryVo();
                BeanUtils.copyProperties(categoryVo,pmCategoryInfo);
                vos.add(categoryVo);
            }
            response.setCategoryList(vos);
        }

        ResponseHeader header = new ResponseHeader();
        header.setResultCode("000000");
        header.setResultMessage("类目信息查询成功");
        header.setIsSuccess(true);
        response.setResponseHeader(header);

        return response;
    }
}
