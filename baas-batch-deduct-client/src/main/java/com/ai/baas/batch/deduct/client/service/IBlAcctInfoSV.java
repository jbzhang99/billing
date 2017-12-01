package com.ai.baas.batch.deduct.client.service;

import com.ai.baas.batch.deduct.client.dao.mapper.bo.BlAcctInfo;

import java.util.List;

public interface IBlAcctInfoSV {
    List<BlAcctInfo> getAcctList(int i, int pagesize);
}
