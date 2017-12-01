package com.ai.baas.omc.service.atom.interfaces;

import java.util.List;

import com.ai.baas.omc.dao.mapper.bo.OmcAvoidScout;

public interface IOmcAvoidScoutAtom {

	public void addOmcAvoidScout(OmcAvoidScout avoidScout);

	public void addDshmData(OmcAvoidScout avoidScout);
	
	public void updateOmcAvoidScout(OmcAvoidScout avoidScout);
	
	public void updDshmData(OmcAvoidScout avoidScout);

	public void deleteOmcAvoidScout(OmcAvoidScout avoidScout);
	
	public void delDshmData(OmcAvoidScout avoidScout);

	public List<OmcAvoidScout> queryByData(OmcAvoidScout avoidScout);
}
