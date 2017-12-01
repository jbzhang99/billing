package com.ai.baas.ccp.service.business.interfaces;

import com.ai.baas.ccp.vo.ScoutActSmsObject;

public interface IScoutActSmsBusiSV {

    int warning(String ownertype, String oid, ScoutActSmsObject scoutActSmsObject);

    int warnoff(String ownertype, String oid, ScoutActSmsObject scoutActSmsObject);

}
