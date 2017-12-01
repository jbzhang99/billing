package com.ai.baas.job.service.busi.interfaces;

import java.util.Map;

public interface ICiticBankChargeBusiSV {

    void charge(Map<String, String> map, String dayStr);

}
