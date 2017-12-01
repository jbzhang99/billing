package com.ai.baas.omc.service.atom.interfaces;

import java.util.List;

import com.ai.baas.omc.dao.mapper.bo.OmcScoutRule;
import com.ai.baas.omc.dao.mapper.bo.OmcScoutRuleCriteria;

public interface IOmcRuleAtom {

	public void addOmcScoutRule(OmcScoutRule rule);

	public void updateOmcScoutRule(OmcScoutRule rule);

	public void deleteOmcScoutRule(OmcScoutRule rule);

	public List<OmcScoutRule> getOmcScoutRule(OmcScoutRuleCriteria criteria);

	public int getCount(OmcScoutRuleCriteria criteria);
}
