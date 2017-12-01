package com.ai.baas.ccp.service.business.interfaces;

import com.ai.baas.ccp.topoligy.core.pojo.User;
import com.ai.baas.ccp.vo.ScoutActBmsObject;

public interface IScoutActBmsBusiSV {

    int stop(User user, ScoutActBmsObject scoutActBmsObject);

    int start(User user, ScoutActBmsObject scoutActBmsObject);

    int halfstop(User user, ScoutActBmsObject scoutActBmsExtObject);

}
