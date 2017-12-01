package com.ai.baas.cust.service.atom.interfaces;

import java.util.List;

import com.ai.baas.cust.dao.mapper.bo.BlUserinfoZx;

public interface IBlUserinfoZXAtomSV {

    public List<BlUserinfoZx> getUserInfoZx (String string);
    public void deleteByInstanceId(String id);
}
