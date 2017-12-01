package com.ai.baas.prd.service.business.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ai.baas.prd.api.convert.params.PolicyParams;
import com.ai.baas.prd.api.element.params.ConvertParams;
import com.ai.baas.prd.api.pricemaking.params.DetailInfoVO;
import com.ai.baas.prd.api.pricemaking.params.ElementInfoVO;
import com.ai.baas.prd.api.pricemaking.params.MemberVO;
import com.ai.baas.prd.api.pricemaking.params.PriceFactorVO;
import com.ai.baas.prd.api.pricemaking.params.PriceInfoVO;
import com.ai.baas.prd.api.pricemaking.params.PriceMakingAddParam;
import com.ai.baas.prd.api.pricemaking.params.PriceMakingDelParam;
import com.ai.baas.prd.api.pricemaking.params.SpecificationVO;
import com.ai.baas.prd.constants.PrdConstants;
import com.ai.baas.prd.dao.mapper.bo.PmCatalogInfo;
import com.ai.baas.prd.dao.mapper.bo.PmCategoryInfo;
import com.ai.baas.prd.dao.mapper.bo.PmPolicyDetail;
import com.ai.baas.prd.dao.mapper.bo.PmPolicyFactor;
import com.ai.baas.prd.dao.mapper.bo.PmPolicyInfo;
import com.ai.baas.prd.dao.mapper.bo.PmPolicyVariable;
import com.ai.baas.prd.dao.mapper.model.StepPrice;
import com.ai.baas.prd.service.atom.interfaces.IPmCatalogInfoAtomSV;
import com.ai.baas.prd.service.atom.interfaces.IPmCategoryInfoAtomSV;
import com.ai.baas.prd.service.atom.interfaces.IPmSpecTypeAtomSV;
import com.ai.baas.prd.service.atom.interfaces.PmPolicyDetailAtomSV;
import com.ai.baas.prd.service.atom.interfaces.PmPolicyFactorAtomSV;
import com.ai.baas.prd.service.atom.interfaces.PmPolicyInfoAtomSV;
import com.ai.baas.prd.service.atom.interfaces.PmPolicyVariableAtomSV;
import com.ai.baas.prd.service.business.interfaces.IConvertBusiSV;
import com.ai.baas.prd.service.business.interfaces.IPriceMakingBusiSV;
import com.ai.opt.base.exception.BusinessException;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

@Service
@Transactional
public class ConvertBusiSVImpl implements IConvertBusiSV{
    private static final Log LOG = LogFactory.getLog(ConvertBusiSVImpl.class);
    
    @Autowired
    IPmCatalogInfoAtomSV iPmCatalogInfoAtomSV;
    @Autowired
    IPmCategoryInfoAtomSV iPmCategoryInfoAtomSV;
    @Autowired
    IPmSpecTypeAtomSV pmSpecTypeAtomSV;
    @Autowired
    PmPolicyInfoAtomSV pmPolicyInfoAtomSV;
    @Autowired
    PmPolicyDetailAtomSV pmPolicyDetailAtomSV;
    @Autowired
    PmPolicyFactorAtomSV pmPolicyFactorAtomSV;
    @Autowired
    PmPolicyVariableAtomSV pmPolicyVariableAtomSV;
    @Autowired
    IPriceMakingBusiSV priceMakingBusiSV;
//    @Override
//    public String 
    @Override
    public PriceMakingAddParam buildMsg(ConvertParams vo) {
        List<PmCategoryInfo> categoryInfos = iPmCategoryInfoAtomSV.getPmCategoryInfoByConvert(vo);
        List<PmCatalogInfo>catalogInfos = iPmCatalogInfoAtomSV.queryElementDetail(vo.getCategoryId(), vo.getTenantId());
        if(categoryInfos==null||categoryInfos.isEmpty()){
            throw new BusinessException("categoryId: "+vo.getCategoryId()+"is not exist");
       }
        if(catalogInfos==null||catalogInfos.isEmpty()){
            throw new BusinessException("categoryId: "+vo.getCategoryId()+"is not exist");
       }
//        JSONObject convertJSON = new JSONObject();
        
        List<PriceFactorVO> priceFactorList = new ArrayList<>();
        List<MemberVO> memberList = new ArrayList<>();
        List<SpecificationVO> specList = new ArrayList<>();
        PriceMakingAddParam addParam = new PriceMakingAddParam();
        PriceFactorVO priceFactorVO = new PriceFactorVO();
        
        addParam.setMainProductId(catalogInfos.get(0).getMainProductId());
        addParam.setMainProductName(catalogInfos.get(0).getMainProductName());
        addParam.setTenantId(catalogInfos.get(0).getTenantId());
        addParam.setPriceFactorList(priceFactorList);
        
        priceFactorVO.setCategoryId(categoryInfos.get(0).getCategoryId());
        priceFactorVO.setCategoryName(categoryInfos.get(0).getCategoryName());
        
        //members转换
        String member = categoryInfos.get(0).getMembers();
        JSONObject jsonObject = JSON.parseObject(member);
        System.out.println("member:::::"+jsonObject.toString());  
        for(int i= 0;i<jsonObject.getJSONArray("members").size();i++){
//            MemberVO memberVO = new MemberVO();
            MemberVO memberVO = JSON.parseObject(jsonObject.getJSONArray("members").get(i).toString(),MemberVO.class);
            memberList.add(memberVO);
            System.out.println(JSON.toJSONString(memberVO));  
        }
        priceFactorVO.setMemberList(memberList);
        priceFactorVO.setSpecList(specList);
        for(PmCatalogInfo catalogInfo : catalogInfos){
            SpecificationVO specificationVO = getSpecificationVO(catalogInfo);
            specList.add(specificationVO);
        }
        priceFactorList.add(priceFactorVO);
        return addParam;
        
    }
    
    private SpecificationVO getSpecificationVO(PmCatalogInfo catalogInfo) {
		SpecificationVO specificationVO = new SpecificationVO();
		List<PmPolicyInfo> pmPolicyInfos = pmPolicyInfoAtomSV.queryPolicyByPolicyId(catalogInfo.getPricePolicy(),catalogInfo.getTenantId());
		if (pmPolicyInfos == null || pmPolicyInfos.isEmpty()) {
			throw new BusinessException("the policyInfo of policyId: " + catalogInfo.getPricePolicy() + " is not exist");
		}
		String policyType = pmPolicyInfos.get(0).getPolicyType();
		List<PriceInfoVO> priceInfoList = null;
		if (policyType.equalsIgnoreCase(PrdConstants.POLICY_TYPE_STEP)) {
			priceInfoList = assembleStepPolicy(catalogInfo);
		}else{
			priceInfoList = assembleGeneralPolicy(catalogInfo);
		}
        specificationVO.setBillingCycle(catalogInfo.getBillingCycle());
        //specificationVO.setBillingRule(pmPolicyInfos.get(0).getPolicyType());
        specificationVO.setBillingRule(policyType);
        specificationVO.setSpecTypeId(catalogInfo.getSpecTypeId());
        specificationVO.setSpecTypeName(catalogInfo.getSpecTypeName());

        specificationVO.setPriceInfoList(priceInfoList);
        return specificationVO;
    }
    
    private List<PriceInfoVO> assembleStepPolicy(PmCatalogInfo catalogInfo){
    	List<PriceInfoVO> priceInfoList = new ArrayList<>();
    	String tenantId = catalogInfo.getTenantId();
    	String policyId = catalogInfo.getPricePolicy();
    	List<PmPolicyDetail> pmPolicyDetails = pmPolicyDetailAtomSV.selectByPolicyID(policyId,tenantId);
    	if(pmPolicyDetails==null||pmPolicyDetails.isEmpty()){
            throw new BusinessException("the policyDetail of policyId: "+policyId+" is not exist");
        }
    	ListMultimap<String,PmPolicyDetail> policyDetailGroup = ArrayListMultimap.create();
    	for(PmPolicyDetail policyDetail:pmPolicyDetails){
    		policyDetailGroup.put(policyDetail.getGroupId(), policyDetail);
    	}
    	Map<String,PmPolicyVariable> varMapping = getPolicyVariableMapping(policyId,tenantId);
		for (String groupKey:policyDetailGroup.keySet()) {
			List<PmPolicyDetail> policyDetails = policyDetailGroup.get(groupKey);
			Map<String,String> priceMapping = getDetailPriceMapping(policyDetails);
			List<String> detailIds = getDetailIds(policyDetails);
			List<PmPolicyFactor> policyFactors = pmPolicyFactorAtomSV.queryPolicyByDetailIds(detailIds, tenantId);
			List<ElementInfoVO> elementInfoList = Lists.newArrayList();
			List<StepPrice> stepPrices= Lists.newArrayList();
			Set<String> varCodes = Sets.newHashSet();
			String extInfo = "";
			for(PmPolicyFactor policyFactor:policyFactors){
				String varCode = StringUtils.upperCase(policyFactor.getVarCode());
				if (varCodes.contains(varCode)) {
					continue;
				}
				PmPolicyVariable policyVariable = varMapping.get(varCode);
				if(policyVariable.getVarType().equals(PrdConstants.VAR_TYPE_SINGLE)){
					ElementInfoVO elementInfo = new ElementInfoVO();
					elementInfo.setVarCode(policyFactor.getVarCode());
					elementInfo.setVarValue(policyFactor.getFactorValue());
					elementInfo.setVarName(policyVariable.getVarName());
					elementInfoList.add(elementInfo);
					varCodes.add(varCode);
				}else if(policyVariable.getVarType().equals(PrdConstants.VAR_TYPE_INTERVAL)){
					String[] range = StringUtils.splitByWholeSeparator(policyFactor.getFactorValue(),PrdConstants.STEP_PRICE_DELIMITER);
					StepPrice stepPrice = new StepPrice();
					if (range.length == 2) {
						stepPrice.setStart(range[0]);
						stepPrice.setEnd(range[1]);
						stepPrice.setValue(priceMapping.get(policyFactor.getDetailId()));
					}
					stepPrices.add(stepPrice);
					extInfo = policyFactor.getVarCode();
				}else{
					throw new BusinessException("unknown var type");
				}
			}
			PriceInfoVO priceInfoVO = new PriceInfoVO();
			priceInfoVO.setElementId(groupKey);
			priceInfoVO.setPrice(JSON.toJSONString(stepPrices));
			priceInfoVO.setElementInfoList(elementInfoList);
			priceInfoVO.setExtInfo(extInfo);
			priceInfoList.add(priceInfoVO);
    	}
    	return priceInfoList;
    }
    
    private List<String> getDetailIds(List<PmPolicyDetail> policyDetails){
    	List<String> detailIds = Lists.newArrayList();
    	for(PmPolicyDetail detail:policyDetails){
    		detailIds.add(detail.getDetailId());
    	}
    	return detailIds;
    }
    
    private Map<String,String> getDetailPriceMapping(List<PmPolicyDetail> policyDetails){
    	Map<String,String> priceMapping = Maps.newHashMap();
    	for(PmPolicyDetail policyDetail:policyDetails){
    		priceMapping.put(policyDetail.getDetailId(), policyDetail.getPrice());
    	}
    	return priceMapping;
    }
    
    private Map<String,PmPolicyVariable> getPolicyVariableMapping(String policyId,String tenantId){
    	Map<String,PmPolicyVariable> mappping = Maps.newHashMap();
    	List<PmPolicyVariable> policyVariables = pmPolicyVariableAtomSV.queryVariablesByPolicyId(policyId, tenantId);
    	for(PmPolicyVariable policyVariable:policyVariables){
    		mappping.put(StringUtils.upperCase(policyVariable.getVarCode()), policyVariable);
    	}
    	return mappping;
    }
    
    private List<PriceInfoVO> assembleGeneralPolicy(PmCatalogInfo catalogInfo){
    	List<PriceInfoVO> priceInfoList = new ArrayList<>();
        List<PmPolicyDetail> pmPolicyDetails = pmPolicyDetailAtomSV.selectByPolicyID(catalogInfo.getPricePolicy(),catalogInfo.getTenantId());
        if(pmPolicyDetails==null||pmPolicyDetails.isEmpty()){
            throw new BusinessException("the policyDetail of policyId: "+catalogInfo.getPricePolicy()+" is not exist");
        }
        for(PmPolicyDetail detail : pmPolicyDetails){
            PriceInfoVO priceInfoVO = new PriceInfoVO();
            priceInfoVO.setElementId(detail.getDetailId());
            priceInfoVO.setPrice(detail.getPrice());
            List<ElementInfoVO> elementInfoList = new ArrayList<>();
            priceInfoVO.setElementInfoList(elementInfoList);
            List<PmPolicyFactor> pmPolicyFactors = pmPolicyFactorAtomSV.queryPolicyByDetailId(detail.getDetailId(), detail.getTenantId());
            for(PmPolicyFactor pmPolicyFactor : pmPolicyFactors){
                ElementInfoVO ele = new ElementInfoVO();
                ele.setVarCode(pmPolicyFactor.getVarCode());
                List<PmPolicyVariable> pmPolicyVariables = pmPolicyVariableAtomSV.queryPolicyByPolicy(catalogInfo.getPricePolicy(),pmPolicyFactor.getTenantId(),pmPolicyFactor.getVarCode());
                ele.setVarName(pmPolicyVariables.get(0).getVarName());
                ele.setVarValue(pmPolicyFactor.getFactorValue());
                elementInfoList.add(ele);
            }
            priceInfoList.add(priceInfoVO);
        }
    	return priceInfoList;
    }
    
    
    @Override
    public PriceMakingDelParam buildDelMsg(ConvertParams vo) {
        PriceMakingDelParam delParam = new PriceMakingDelParam();
        List<DetailInfoVO> detailInfoList = new ArrayList<>();
        delParam.setDetailInfoList(detailInfoList);
        List<PmCatalogInfo>catalogInfos = iPmCatalogInfoAtomSV.queryElementDetail(vo.getCategoryId(), vo.getTenantId());
        for(PmCatalogInfo catalogInfo : catalogInfos){
//            List<PmPolicyInfo> pmPolicyInfos = pmPolicyInfoAtomSV.queryPolicyByPolicyId(catalogInfo.getPricePolicy(), catalogInfo.getTenantId());
            List<PmPolicyDetail>  pmPolicyDetails = pmPolicyDetailAtomSV.selectByPolicyID(catalogInfo.getPricePolicy(),catalogInfo.getTenantId());
            for(PmPolicyDetail policyDetail : pmPolicyDetails){
                DetailInfoVO detailVO = new DetailInfoVO();
                detailVO.setDetailId(policyDetail.getDetailId());
                detailVO.setTenantId(policyDetail.getTenantId());
                detailInfoList.add(detailVO);
            }
        }
        return delParam;
    }
    
    @Override
    public PriceMakingDelParam buildDelMsgByPolicyId(PolicyParams vo) {
        PriceMakingDelParam delParam = new PriceMakingDelParam();
        List<DetailInfoVO> detailInfoList = new ArrayList<>();
        delParam.setDetailInfoList(detailInfoList);
        List<PmPolicyDetail>  pmPolicyDetails = pmPolicyDetailAtomSV.selectByPolicyID(vo.getPolicyId(),vo.getTenantId());
        for(PmPolicyDetail policyDetail : pmPolicyDetails){
            DetailInfoVO detailVO = new DetailInfoVO();
            detailVO.setDetailId(policyDetail.getDetailId());
            detailVO.setTenantId(policyDetail.getTenantId());
            detailInfoList.add(detailVO);
        }
        
        return delParam;
    }

}
