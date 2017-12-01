package com.ai.baas.bmc.api.failedbills;

import com.ai.baas.bmc.api.businessdatamaintain.interfaces.IBillingBusinessDataMaintainSV;
import com.ai.baas.bmc.api.failedbills.interfaces.IFailedBillsSV;
import com.ai.baas.bmc.api.failedbills.params.BillIdList;
import com.ai.baas.bmc.api.failedbills.params.FailedBillDetail;
import com.ai.baas.bmc.api.failedbills.params.FailedBillQueryVo;
import com.ai.baas.bmc.api.failedbills.params.FailedBillVo;
import com.ai.baas.bmc.service.business.interfaces.IFailedBillsBusiSV;
import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.BaseResponse;
import com.ai.opt.base.vo.PageInfoResponse;
import com.ai.opt.base.vo.ResponseHeader;
import com.ai.opt.sdk.util.CollectionUtil;
import com.ai.opt.sdk.util.StringUtil;
import com.alibaba.dubbo.config.annotation.Service;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Service
@Component
public class FailedBillsSVImpl implements IFailedBillsSV{
    private static final Logger LOG = LogManager.getLogger(IBillingBusinessDataMaintainSV.class);

    @Resource
    private IFailedBillsBusiSV failedBillsBusiSV;

    @Override
    public PageInfoResponse<FailedBillVo> queryFailedBills(FailedBillQueryVo queryVo) throws BusinessException, SystemException {
        if (StringUtil.isBlank(queryVo.getTenantId())) {
            throw new BusinessException("租户id为空");
        }
        if (queryVo.getPageNo()==null) {
            throw new BusinessException("pageNo为空");
        }
        if (queryVo.getPageSize()==null) {
            throw new BusinessException("pageSize为空");
        }
        PageInfoResponse<FailedBillVo> pageInfoResponse = null;
        ResponseHeader header = new ResponseHeader();
        try {
            Integer count = failedBillsBusiSV.countFailedBills(queryVo);
            pageInfoResponse = new PageInfoResponse<FailedBillVo>();
            header = new ResponseHeader();
            if(count!=null&&count>0){
                List<FailedBillVo> billVos = failedBillsBusiSV.queryFailedBills(queryVo);
                pageInfoResponse.setPageNo(queryVo.getPageNo());
                pageInfoResponse.setPageSize(queryVo.getPageSize());
                pageInfoResponse.setCount(count);
                pageInfoResponse.setResult(billVos);

                header.setResultMessage("错单查询成功");
                header.setIsSuccess(true);
                header.setResultCode("000000");
            }else{
                pageInfoResponse.setPageNo(queryVo.getPageNo());
                pageInfoResponse.setPageSize(queryVo.getPageSize());
                pageInfoResponse.setCount(count);
            }
        } catch (Exception e) {
            LOG.error("查询错单失败",e);
            throw new BusinessException("查询错单失败",e);
        }
        pageInfoResponse.setResponseHeader(header);
        return pageInfoResponse;
    }

    @Override
    public FailedBillDetail getFailedBillDetail(FailedBillVo queryVo) throws BusinessException, SystemException {
        if(StringUtil.isBlank(queryVo.getTenantId())){
            throw new BusinessException("租户id为空");
        }
        if(queryVo.getId()==null){
            throw new BusinessException("id为空");
        }

        FailedBillDetail billDetail = new FailedBillDetail();
        ResponseHeader header = new ResponseHeader();
        try {
            FailedBillVo billVo = failedBillsBusiSV.getFailedBillById(queryVo.getId());
            billDetail.setFailedBillVo(billVo);

            header.setSuccess(true);
            header.setResultCode("000000");
            header.setResultMessage("查询错单成功");
        } catch (Exception e) {
            LOG.error("错单查询失败",e);
            header.setSuccess(false);
            header.setResultCode("000001");
            header.setResultMessage("查询错单失败:"+e.getMessage());
        }
        billDetail.setResponseHeader(header);

        return billDetail;
    }

    @Override
    public BaseResponse resendFailedBill(FailedBillVo billVo) throws BusinessException, SystemException {
        if(StringUtil.isBlank(billVo.getTenantId())){
            throw new BusinessException("租户id为空");
        }
        if(billVo.getId()==null){
            throw new BusinessException("id为空");
        }

        BaseResponse response = new BaseResponse();
        ResponseHeader header = new ResponseHeader();
        try {
            failedBillsBusiSV.resendBill(billVo);
            header.setSuccess(true);
            header.setResultCode("000000");
            header.setResultMessage("重发编辑错单成功");
        } catch (Exception e) {
            LOG.error("重发编辑错单失败",e);
            header.setSuccess(false);
            header.setResultCode("000001");
            header.setResultMessage("重发编辑错单失败:"+e.getMessage());
        }
        response.setResponseHeader(header);
        return response;
    }

    @Override
    public BaseResponse batchResendBill(BillIdList idList) throws BusinessException, SystemException {
        if(StringUtil.isBlank(idList.getTenantId())){
            throw new BusinessException("租户id为空");
        }
        if(CollectionUtil.isEmpty(idList.getBillIds())){
            throw new BusinessException("错单id列表为空");
        }
        BaseResponse response = new BaseResponse();
        ResponseHeader header = new ResponseHeader();
        try {
            failedBillsBusiSV.batchResendBill(idList);
            header.setSuccess(true);
            header.setResultCode("000000");
            header.setResultMessage("批量重发错单成功");
        } catch (Exception e) {
            LOG.error("批量重发错单失败",e);
            header.setSuccess(false);
            header.setResultCode("000001");
            header.setResultMessage("批量重发错单失败:"+e.getMessage());
        }
        response.setResponseHeader(header);
        return response;
    }

    @Override
    public BaseResponse batchDeleteBill(BillIdList idList) throws BusinessException, SystemException {
        if(StringUtil.isBlank(idList.getTenantId())){
            throw new BusinessException("租户id为空");
        }
        if(CollectionUtil.isEmpty(idList.getBillIds())){
            throw new BusinessException("错单id列表为空");
        }
        BaseResponse response = new BaseResponse();
        ResponseHeader header = new ResponseHeader();
        try {
            failedBillsBusiSV.batchDeleteBill(idList);
            header.setSuccess(true);
            header.setResultCode("000000");
            header.setResultMessage("批量删除错单成功");
        } catch (Exception e) {
            LOG.error("批量删除错单失败",e);
            header.setSuccess(false);
            header.setResultCode("000001");
            header.setResultMessage("批量删除错单失败:"+e.getMessage());
        }
        response.setResponseHeader(header);
        return response;
    }
}
