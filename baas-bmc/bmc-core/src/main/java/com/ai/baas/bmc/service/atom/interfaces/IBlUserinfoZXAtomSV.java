package com.ai.baas.bmc.service.atom.interfaces;

import java.util.List;

import com.ai.baas.bmc.dao.mapper.bo.BlUserinfoZx;

public interface IBlUserinfoZXAtomSV {

    public List<BlUserinfoZx> getUserInfoZx (String string);
    public void deleteByInstanceId(String id);
}
