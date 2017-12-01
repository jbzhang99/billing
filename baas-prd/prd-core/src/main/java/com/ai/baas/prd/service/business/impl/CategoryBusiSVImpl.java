package com.ai.baas.prd.service.business.impl;

import com.ai.baas.prd.api.category.params.CategoryQuery;
import com.ai.baas.prd.api.category.params.CategoryVo;
import com.ai.baas.prd.dao.mapper.bo.PmCategoryInfo;
import com.ai.baas.prd.dao.mapper.bo.PmCategoryInfoCriteria;
import com.ai.baas.prd.service.atom.interfaces.IPmCategoryInfoAtomSV;
import com.ai.baas.prd.service.business.interfaces.ICategoryBusiSV;
import com.ai.opt.sdk.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CategoryBusiSVImpl implements ICategoryBusiSV{

    @Autowired
    private IPmCategoryInfoAtomSV pmCategoryInfoAtomSV;

    @Override
    public List<PmCategoryInfo> getCategoryList(CategoryQuery req) {
        return pmCategoryInfoAtomSV.queryCategoryList(req);
    }
}
