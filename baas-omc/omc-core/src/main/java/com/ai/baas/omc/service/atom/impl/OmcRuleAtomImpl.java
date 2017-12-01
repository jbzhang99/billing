package com.ai.baas.omc.service.atom.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ai.baas.omc.dao.mapper.bo.OmcScoutRule;
import com.ai.baas.omc.dao.mapper.bo.OmcScoutRuleCriteria;
import com.ai.baas.omc.dao.mapper.interfaces.OmcScoutRuleMapper;
import com.ai.baas.omc.service.atom.interfaces.IOmcRuleAtom;

@Service
public class OmcRuleAtomImpl implements IOmcRuleAtom {

	@Autowired
	private OmcScoutRuleMapper omcScoutRuleMapper;

	@Override
	public void addOmcScoutRule(OmcScoutRule rule) {
		omcScoutRuleMapper.insert(rule);
	}

	@Override
	public void updateOmcScoutRule(OmcScoutRule rule) {
		OmcScoutRuleCriteria omcScoutRuleCriteria = new OmcScoutRuleCriteria();
		OmcScoutRuleCriteria.Criteria criteria = omcScoutRuleCriteria.createCriteria();
		criteria.andRuleIdEqualTo(rule.getRuleId());
		criteria.andScoutTypeEqualTo(rule.getScoutType());

		omcScoutRuleMapper.updateByExampleSelective(rule, omcScoutRuleCriteria);
	}

	@Override
	public void deleteOmcScoutRule(OmcScoutRule rule) {
		OmcScoutRuleCriteria omcScoutRuleCriteria = new OmcScoutRuleCriteria();
		OmcScoutRuleCriteria.Criteria criteria = omcScoutRuleCriteria.createCriteria();
		criteria.andRuleIdEqualTo(rule.getRuleId());

		omcScoutRuleMapper.deleteByExample(omcScoutRuleCriteria);
	}

	@Override
	public List<OmcScoutRule> getOmcScoutRule(OmcScoutRuleCriteria criteria) {
		return omcScoutRuleMapper.selectByExample(criteria);
	}

	@Override
	public int getCount(OmcScoutRuleCriteria criteria) {
		return omcScoutRuleMapper.countByExample(criteria);
	}

}
