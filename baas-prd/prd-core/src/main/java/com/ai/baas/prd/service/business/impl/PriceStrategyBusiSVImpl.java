package com.ai.baas.prd.service.business.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ai.baas.prd.api.element.params.ConvertParams;
import com.ai.baas.prd.api.strategy.params.CheckPolicyParam;
import com.ai.baas.prd.api.strategy.params.DeleteStrategyParam;
import com.ai.baas.prd.api.strategy.params.DetailAddVO;
import com.ai.baas.prd.api.strategy.params.DetailShowVO;
import com.ai.baas.prd.api.strategy.params.FactorAddVO;
import com.ai.baas.prd.api.strategy.params.FactorShowVO;
import com.ai.baas.prd.api.strategy.params.PolicyShowVO;
import com.ai.baas.prd.api.strategy.params.PriceStrategyQueryReponse;
import com.ai.baas.prd.api.strategy.params.QueryDetailParam;
import com.ai.baas.prd.api.strategy.params.QueryParams;
import com.ai.baas.prd.api.strategy.params.StartegyRecordVO;
import com.ai.baas.prd.api.strategy.params.StrategyAddParams;
import com.ai.baas.prd.api.strategy.params.StrategyDetailQueryReponse;
import com.ai.baas.prd.api.strategy.params.StrategyShowVO;
import com.ai.baas.prd.api.strategy.params.VariableRecordVO;
import com.ai.baas.prd.constants.ExceptCodeConstants;
import com.ai.baas.prd.dao.mapper.bo.PmCatalogInfo;
import com.ai.baas.prd.dao.mapper.bo.PmPolicyDetail;
import com.ai.baas.prd.dao.mapper.bo.PmPolicyDetailCriteria;
import com.ai.baas.prd.dao.mapper.bo.PmPolicyFactor;
import com.ai.baas.prd.dao.mapper.bo.PmPolicyFactorCriteria;
import com.ai.baas.prd.dao.mapper.bo.PmPolicyInfo;
import com.ai.baas.prd.dao.mapper.bo.PmPolicyVariable;
import com.ai.baas.prd.dao.mapper.bo.PmPolicyVariableCriteria;
import com.ai.baas.prd.service.atom.interfaces.IPmCatalogInfoAtomSV;
import com.ai.baas.prd.service.atom.interfaces.PmPolicyDetailAtomSV;
import com.ai.baas.prd.service.atom.interfaces.PmPolicyFactorAtomSV;
import com.ai.baas.prd.service.atom.interfaces.PmPolicyInfoAtomSV;
import com.ai.baas.prd.service.atom.interfaces.PmPolicyVariableAtomSV;
import com.ai.baas.prd.service.business.interfaces.IPriceElementBusiSV;
import com.ai.baas.prd.service.business.interfaces.IPriceStrategyBusiSV;
import com.ai.baas.prd.util.PrdSeqUtil;
import com.ai.opt.base.vo.BaseResponse;
import com.ai.opt.base.vo.PageInfo;
import com.ai.opt.base.vo.ResponseHeader;
import com.ai.opt.sdk.util.BeanUtils;
import com.ai.opt.sdk.util.StringUtil;
import com.alibaba.fastjson.JSONArray;

@Service
@Transactional
public class PriceStrategyBusiSVImpl implements IPriceStrategyBusiSV {

	private static final Log LOG = LogFactory.getLog(PriceStrategyBusiSVImpl.class);
	public final String FACTOR_VALUE_SPLIT = "##";
	public final String CATEGORY_TYPE = "ZJ";
	@Autowired
	PmPolicyInfoAtomSV policyInfoAtomSV;
	@Autowired
	PmPolicyVariableAtomSV policyVariableAtomSV;
	@Autowired
	PmPolicyDetailAtomSV policyDetailAtomSV;
	@Autowired
	PmPolicyFactorAtomSV policyFactorAtomSV;
	@Autowired
    IPmCatalogInfoAtomSV iPmCatalogInfoAtomSv;
	@Autowired
	IPriceElementBusiSV priceElementBusiSV;
	
	@Override
	public void addStrategy(StrategyAddParams vo) {
		// TODO Auto-generated method stub
		String policyId = "";
		PmPolicyInfo policyInfo = new PmPolicyInfo();
		policyInfo.setPolicyName(vo.getPolicyName());
		policyInfo.setPolicyType(vo.getPolicyVo().getPolicyType());
		policyInfo.setComments("");
		policyInfo.setTenantId(vo.getTenantId());
		if(StringUtil.isBlank(vo.getPolicyId())){
			//保存策略信息
			policyId = String.valueOf(PrdSeqUtil.getPolicyInfoId());
			policyInfo.setPolicyId(policyId);
			policyInfoAtomSV.add(policyInfo);
		}else{
			policyId = vo.getPolicyId();
			
			PmPolicyDetailCriteria policyDetailCriteria = new PmPolicyDetailCriteria();
			PmPolicyDetailCriteria.Criteria dcCriteria = policyDetailCriteria.createCriteria();
			dcCriteria.andTenantIdEqualTo(vo.getTenantId());
			dcCriteria.andPolicyIdEqualTo(policyId);
			//删除因子
			List<PmPolicyDetail> details = policyDetailAtomSV.selectByExample(policyDetailCriteria);
			List<String> detailIds = new ArrayList<String>();
			for(PmPolicyDetail detail:details){
				detailIds.add(detail.getDetailId());
			}
			PmPolicyFactorCriteria policyFactorCriteria = new PmPolicyFactorCriteria();
			PmPolicyFactorCriteria.Criteria fcCriteria = policyFactorCriteria.createCriteria();
			fcCriteria.andTenantIdEqualTo(vo.getTenantId());
			fcCriteria.andDetailIdIn(detailIds);
			policyFactorAtomSV.deleteByExample(policyFactorCriteria);
			//删除变量
			PmPolicyVariableCriteria policyVariableCriteria = new PmPolicyVariableCriteria();
			PmPolicyVariableCriteria.Criteria vcCriteria = policyVariableCriteria.createCriteria();
			vcCriteria.andTenantIdEqualTo(vo.getTenantId());
			vcCriteria.andPolicyIdEqualTo(policyId);
			policyVariableAtomSV.deleteByExample(policyVariableCriteria);
			//删除明细
			policyDetailAtomSV.deleteByExample(policyDetailCriteria);
			//修改策略信息
			policyInfo.setPolicyId(policyId);
			policyInfoAtomSV.updateByPrimaryKey(policyInfo);
		}
		
		Map<String, PmPolicyVariable> variablesMap = new HashMap<String, PmPolicyVariable>();
		for(DetailAddVO infoVO:vo.getPolicyVo().getVariableVOs()){
			
			String detailId = String.valueOf(PrdSeqUtil.getPolicyDetailId());
			PmPolicyDetail detail = new PmPolicyDetail();
			detail.setDetailId(detailId);
			detail.setId(Long.valueOf(detailId));
			detail.setPolicyId(policyId);
			detail.setTenantId(vo.getTenantId());
			detail.setSortIndex(infoVO.getIndex());
			detail.setGroupId(policyId);
			detail.setPrice(infoVO.getPrice());
			policyDetailAtomSV.add(detail);
			for(FactorAddVO factorVo : infoVO.getFactorVos()){
				String varCode = factorVo.getVarCode();
				PmPolicyFactor factor = new PmPolicyFactor();
				factor.setDetailId(detailId);
				factor.setId(PrdSeqUtil.getPolicyFactorId());
				factor.setTenantId(vo.getTenantId());
				factor.setVarCode(varCode);
				if(StringUtils.equals("SINGLE", factorVo.getVarType())){
					factor.setFactorValue(factorVo.getFactorValue());
				}else if(StringUtils.equals("INTERVAL", factorVo.getVarType())){
					factor.setFactorValue(factorVo.getFactorValueStart() + 
							this.FACTOR_VALUE_SPLIT + factorVo.getFactorValueEnd());
				}
				policyFactorAtomSV.add(factor);
				if(!variablesMap.containsKey(varCode)){
					PmPolicyVariable variable = new PmPolicyVariable();
					variable.setId(PrdSeqUtil.getPolicyVarId());
					variable.setPolicyId(policyId);
					variable.setTenantId(vo.getTenantId());
					variable.setVarCode(varCode);
					variable.setVarName(factorVo.getVarName());
					variable.setVarType(factorVo.getVarType());
					variable.setUnitId(factorVo.getVarUnitId());
					variable.setUnitCode(factorVo.getVarUnitCode());
					variable.setUnitName(factorVo.getVarUnitName());
					
					variablesMap.put(varCode, variable);
				}
			}
		}
		for (Map.Entry<String, PmPolicyVariable> entry : variablesMap.entrySet()) {  
			policyVariableAtomSV.add(entry.getValue());
		}  
		
		if(!StringUtil.isBlank(vo.getPolicyId())){
			List<PmCatalogInfo> catalogInfos = this.iPmCatalogInfoAtomSv.queryByPolicyId(vo.getPolicyId(), vo.getTenantId());
			if(catalogInfos!=null && catalogInfos.size()>0){
				for(PmCatalogInfo catalogInfo : catalogInfos){
					LOG.info("策略更新通知接口: "+JSONArray.toJSONString(catalogInfo));
					ConvertParams params = new ConvertParams();
					params.setTenantId(vo.getTenantId());
					params.setCategoryId(catalogInfo.getCategoryId());
					params.setCategoryType(this.CATEGORY_TYPE);
					priceElementBusiSV.alterPriceMakingByPolicy(params);
				}
			}
		}
	}

	@Override
	public PriceStrategyQueryReponse queryStrategy(QueryParams params) {
		// TODO Auto-generated method stub
		PriceStrategyQueryReponse response = new PriceStrategyQueryReponse();
		PageInfo<StartegyRecordVO> pageInfo = new PageInfo<>();
		List<StartegyRecordVO> records = new ArrayList<>();
		
		List<String> policyIds = new ArrayList<>();
		if(StringUtils.equals("1", params.getRelated().trim())){
			List<PmCatalogInfo> catalogInfos = iPmCatalogInfoAtomSv.getAll(params.getTenantId());
			if(catalogInfos!=null){
				for(PmCatalogInfo catalogInfo:catalogInfos){
					policyIds.add(catalogInfo.getPricePolicy());
				}
			}
		}
		List<PmPolicyInfo> policyInfos = this.policyInfoAtomSV.queryPolicy(params, policyIds);
		for(int i=0; i<policyInfos.size(); i++){
			PmPolicyInfo policyInfo = policyInfos.get(i);
			PmPolicyVariableCriteria variableCriteria = new PmPolicyVariableCriteria();
			PmPolicyVariableCriteria.Criteria criteria = variableCriteria.createCriteria();
			criteria.andTenantIdEqualTo(policyInfo.getTenantId());
			criteria.andPolicyIdEqualTo(policyInfo.getPolicyId());
			List<PmPolicyVariable> list = this.policyVariableAtomSV.selectByExample(variableCriteria);
			
			StartegyRecordVO recordVo = new StartegyRecordVO();
			recordVo.setIndex(String.valueOf((params.getPageNo()-1)*params.getPageSize()+1+i));
			recordVo.setPolicyId(policyInfo.getPolicyId());
			recordVo.setPolicyName(policyInfo.getPolicyName());
			recordVo.setPolicyType(policyInfo.getPolicyType());
			recordVo.setTenantId(policyInfo.getTenantId());
			
			List<VariableRecordVO> variableRecordVos = new ArrayList<>();
			for(PmPolicyVariable variable:list){
				VariableRecordVO vo = new VariableRecordVO();
				BeanUtils.copyProperties(vo, variable);
				variableRecordVos.add(vo);
			}
			recordVo.setVariableRecordVos(variableRecordVos);
			records.add(recordVo);
		}
		pageInfo.setResult(records);
		pageInfo.setPageNo(params.getPageNo());
		pageInfo.setPageSize(params.getPageSize());
		
		int count = this.policyInfoAtomSV.countByExample(params, policyIds);
		int pageCount = (count + params.getPageSize() - 1) / params.getPageSize();
		pageInfo.setCount(count);
        pageInfo.setPageCount(pageCount);
        response.setRecords(pageInfo);
        response.setTenantId(params.getTenantId());
        response.setResponseHeader(new ResponseHeader(true, ExceptCodeConstants.SUCCESS, "成功"));
		return response;
	}

	@Override
	public StrategyDetailQueryReponse getStrategyDetail(QueryDetailParam params) {
		// TODO Auto-generated method stub
		StrategyDetailQueryReponse response = new StrategyDetailQueryReponse();
		StrategyShowVO strategyShowVO = new StrategyShowVO();
		PmPolicyInfo policyInfo = this.policyInfoAtomSV.selectByPrimaryKey(params.getPolicyId());
		if(policyInfo!=null){
			strategyShowVO.setPolicyId(policyInfo.getPolicyId());
			strategyShowVO.setPolicyName(policyInfo.getPolicyName());
			strategyShowVO.setPolicyId(policyInfo.getPolicyId());
			
			//增加变量信息
			PmPolicyVariableCriteria variableCriteria = new PmPolicyVariableCriteria();
			PmPolicyVariableCriteria.Criteria criteria = variableCriteria.createCriteria();
			criteria.andTenantIdEqualTo(policyInfo.getTenantId());
			criteria.andPolicyIdEqualTo(policyInfo.getPolicyId());
			variableCriteria.setOrderByClause("ID desc");
			List<PmPolicyVariable> policyVariables = this.policyVariableAtomSV.selectByExample(variableCriteria);
			List<VariableRecordVO> variableRecordVOs = new ArrayList<>();
			for(PmPolicyVariable variable:policyVariables){
				VariableRecordVO vo = new VariableRecordVO();
				BeanUtils.copyProperties(vo, variable);
				variableRecordVOs.add(vo);
			}
			strategyShowVO.setVariableRecordVos(variableRecordVOs);
			
			PolicyShowVO policyShowVO = new PolicyShowVO();
			policyShowVO.setPolicyType(policyInfo.getPolicyType());
			List<DetailShowVO> detailShowVOs = new ArrayList<>();
			//增加详细信息
			PmPolicyDetailCriteria policyDetailCriteria = new PmPolicyDetailCriteria();
			PmPolicyDetailCriteria.Criteria detailCriteria = policyDetailCriteria.createCriteria();
			detailCriteria.andPolicyIdEqualTo(policyInfo.getPolicyId());
			detailCriteria.andTenantIdEqualTo(policyInfo.getTenantId());
			policyDetailCriteria.setOrderByClause("SORT_INDEX asc");
			List<PmPolicyDetail> policyDetails = this.policyDetailAtomSV.selectByExample(policyDetailCriteria);
			if(policyDetails!=null){
				for(PmPolicyDetail policyDetail:policyDetails){
					DetailShowVO detailShowVO = new DetailShowVO();
					detailShowVO.setDetailId(policyDetail.getDetailId());
					detailShowVO.setPrice(policyDetail.getPrice());
					detailShowVO.setGroupId(policyDetail.getGroupId());
					detailShowVO.setIndex(policyDetail.getSortIndex());
					
					//增加策略因子
					List<FactorShowVO> factorVo = new ArrayList<>();
					PmPolicyFactorCriteria policyFactorCriteria = new PmPolicyFactorCriteria();
					PmPolicyFactorCriteria.Criteria factorCriteria = policyFactorCriteria.createCriteria();
					factorCriteria.andDetailIdEqualTo(policyDetail.getDetailId());
					factorCriteria.andTenantIdEqualTo(policyDetail.getTenantId());
					List<PmPolicyFactor> policyFactors = this.policyFactorAtomSV.selectByExample(policyFactorCriteria);
					if(policyFactors!=null){
						Map<String, PmPolicyFactor> factorMap = new HashMap<>();
						for(PmPolicyFactor factor:policyFactors){
							factorMap.put(factor.getVarCode(), factor);
						}
						for(PmPolicyVariable policyVariable:policyVariables){
							FactorShowVO factorShowVO = new FactorShowVO();
							PmPolicyFactor policyFactor = factorMap.get(policyVariable.getVarCode());
							factorShowVO.setFactorId(String.valueOf(policyFactor.getId()));
							factorShowVO.setFactorValue(policyFactor.getFactorValue());
							factorShowVO.setVarCode(policyVariable.getVarCode());
							factorShowVO.setVarName(policyVariable.getVarName());
							factorShowVO.setVarType(policyVariable.getVarType());
							if(StringUtils.equals("SINGLE", policyVariable.getVarType())){
								factorShowVO.setFactorValue(policyFactor.getFactorValue());
							}else if(StringUtils.equals("INTERVAL", policyVariable.getVarType())){
								factorShowVO.setFactorValueStart(policyFactor.getFactorValue().split(this.FACTOR_VALUE_SPLIT)[0]);
								factorShowVO.setFactorValueEnd(policyFactor.getFactorValue().split(this.FACTOR_VALUE_SPLIT)[1]);
							}
							factorShowVO.setVarUnitId(policyVariable.getUnitId());
							factorShowVO.setVarUnitCode(policyVariable.getUnitCode());
							factorShowVO.setVarUnitName(policyVariable.getUnitName());
							factorVo.add(factorShowVO);
						}
					}
					detailShowVO.setFactorVos(factorVo);
					detailShowVOs.add(detailShowVO);
				}
			}
			policyShowVO.setVariableVOs(detailShowVOs);
			strategyShowVO.setPolicyVo(policyShowVO);
		}
		response.setTenantId(params.getTenantId());
		response.setStrategyShowVO(strategyShowVO);
		response.setResponseHeader(new ResponseHeader(true, ExceptCodeConstants.SUCCESS, "成功"));
		return response;
	}

	@Override
	public BaseResponse deleteStrategy(DeleteStrategyParam vo) {
		// TODO Auto-generated method stub
		
		String policyId = vo.getPolicyId();
		
		PmPolicyDetailCriteria policyDetailCriteria = new PmPolicyDetailCriteria();
		PmPolicyDetailCriteria.Criteria dcCriteria = policyDetailCriteria.createCriteria();
		dcCriteria.andTenantIdEqualTo(vo.getTenantId());
		dcCriteria.andPolicyIdEqualTo(policyId);
		//删除因子
		List<PmPolicyDetail> details = policyDetailAtomSV.selectByExample(policyDetailCriteria);
		List<String> detailIds = new ArrayList<String>();
		for(PmPolicyDetail detail:details){
			detailIds.add(detail.getDetailId());
		}
		PmPolicyFactorCriteria policyFactorCriteria = new PmPolicyFactorCriteria();
		PmPolicyFactorCriteria.Criteria fcCriteria = policyFactorCriteria.createCriteria();
		fcCriteria.andTenantIdEqualTo(vo.getTenantId());
		fcCriteria.andDetailIdIn(detailIds);
		policyFactorAtomSV.deleteByExample(policyFactorCriteria);
		//删除变量
		PmPolicyVariableCriteria policyVariableCriteria = new PmPolicyVariableCriteria();
		PmPolicyVariableCriteria.Criteria vcCriteria = policyVariableCriteria.createCriteria();
		vcCriteria.andTenantIdEqualTo(vo.getTenantId());
		vcCriteria.andPolicyIdEqualTo(policyId);
		policyVariableAtomSV.deleteByExample(policyVariableCriteria);
		//删除明细
		policyDetailAtomSV.deleteByExample(policyDetailCriteria);
		//删除策略信息
		this.policyInfoAtomSV.deleteById(policyId);
		BaseResponse baseResponse = new BaseResponse();
		ResponseHeader responseHeader = new ResponseHeader(true, ExceptCodeConstants.SUCCESS, "成功");
        baseResponse.setResponseHeader(responseHeader);
		return baseResponse;
	}

	@Override
	public BaseResponse checkExistPolicyName(CheckPolicyParam vo) {
		// TODO Auto-generated method stub
		BaseResponse baseResponse = new BaseResponse();
		if(this.policyInfoAtomSV.existPolicyName(vo.getTenantId(), vo.getPolicyName())>0){
			 baseResponse.setResponseHeader(new ResponseHeader(true,"000000","已存在"));
		}else{
			baseResponse.setResponseHeader(new ResponseHeader(false,"000001","不存在"));
		}
		return baseResponse;
	}

}
