package com.ai.baas.amc.api.BillQuery.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ai.baas.amc.api.billQuery.interfaces.IBillQuerySV;
import com.ai.baas.amc.api.billQuery.params.BillDetailInput;
import com.ai.baas.amc.api.billQuery.params.BillDetailOutput;
import com.ai.baas.amc.api.billQuery.params.BillInput;
import com.ai.baas.amc.api.billQuery.params.BillOutput;
import com.ai.baas.amc.service.business.interfaces.IGetBillDetailInfoBusiSV;
import com.ai.baas.amc.service.business.interfaces.IGetBillInfoBussinessSV;
import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.ResponseHeader;
import com.ai.opt.sdk.util.StringUtil;
import com.alibaba.dubbo.config.annotation.Service;

@Service(validation="true")
@Component
public class BillQuerySVImpl implements IBillQuerySV{

    @Autowired
    private IGetBillInfoBussinessSV iGetBillInfoBussiness;
    
    @Autowired
    private IGetBillDetailInfoBusiSV iGetBillDetailInfoBusiSV;

    @Override
    public BillOutput getBillInfo(BillInput record) throws BusinessException, SystemException {
        BillOutput billOutput = new BillOutput();
        //字段 判断
        if(StringUtil.isBlank(record.getTenantId())){
            billOutput.setResponseHeader(new ResponseHeader(false,"000001","租户id不能为空"));
            return billOutput;
        }
        
        return iGetBillInfoBussiness.getBillInfo(record);
    }
    @Override
    public BillDetailOutput getBillDetail(BillDetailInput record) throws BusinessException, SystemException {
        BillDetailOutput output = new BillDetailOutput();
        //字段 判断
        if(StringUtil.isBlank(record.getTenantId())){
            output.setResponseHeader(new ResponseHeader(false,"000001","租户id不能为空"));
            return output;
        }
        return iGetBillDetailInfoBusiSV.getBillDetail(record);
    }

}
