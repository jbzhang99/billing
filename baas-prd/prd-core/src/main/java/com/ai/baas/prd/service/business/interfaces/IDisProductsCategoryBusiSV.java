package com.ai.baas.prd.service.business.interfaces;

import java.util.List;

public interface IDisProductsCategoryBusiSV {
    public String getDisProsCategoryID(List<String> productIDs, String tenantId);
}
