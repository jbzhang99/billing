package com.ai.baas.smc.service.busi.interfaces;

import com.ai.baas.smc.api.billdetail.param.BillDetailDataImportRequest;
import com.ai.baas.smc.api.billdetail.param.CheckResultDiffDetailQueryRequest;
import com.ai.baas.smc.api.billdetail.param.CheckResultQueryRequest;
import com.ai.baas.smc.api.billdetail.param.CheckResultQueryResponse;
import com.ai.baas.smc.api.billdetail.param.DiffDetailDataInfo;
import com.ai.baas.smc.api.billdetail.param.SettlementCheckStartRequest;
import com.ai.opt.base.vo.BaseResponse;
import com.ai.opt.base.vo.HBasePager;

public interface IBillDetailBusiSV {

    String importBillDetailData(BillDetailDataImportRequest request);

    BaseResponse startSettlementCheck(SettlementCheckStartRequest request);

    CheckResultQueryResponse queryCheckResult(CheckResultQueryRequest request);

    HBasePager<DiffDetailDataInfo> queryCheckResultDiffDetail(
            CheckResultDiffDetailQueryRequest request);

}
