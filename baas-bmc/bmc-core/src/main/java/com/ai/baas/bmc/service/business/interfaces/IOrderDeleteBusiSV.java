package com.ai.baas.bmc.service.business.interfaces;

import com.ai.baas.bmc.api.citic.params.InstanceInfo;
import com.ai.baas.bmc.api.citic.params.InstanceUpdateInfo;

public interface IOrderDeleteBusiSV {

    String OrderDelete(InstanceUpdateInfo instanceUpdateInfo);
    
   
}
