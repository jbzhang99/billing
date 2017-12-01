package com.ai.baas.ccp.service.business.interfaces;

import com.ai.baas.ccp.vo.ScoutActSmsObject;

public interface IScoutActSmsExtBusiSV {

    int warning(String serv, String subsid, ScoutActSmsObject scoutActSmsObject);

    int warnoff(String serv, String subsid, ScoutActSmsObject scoutActSmsObject);

}
