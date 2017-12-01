package com.ai.baas.abm.api.invoice.interfaces;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.ai.baas.abm.api.invoice.params.InvoiceInfoDetailVO;
import com.ai.baas.abm.api.invoice.params.InvoiceInfoParam;
import com.ai.baas.abm.api.invoice.params.InvoiceInfoQueryResponse;
import com.ai.baas.abm.api.invoice.params.InvoiceQueryParam;
import com.ai.baas.abm.api.invoice.params.InvoiceRecordInsertParam;
import com.ai.baas.abm.api.invoice.params.InvoiceRecordQueryParam;
import com.ai.baas.abm.api.invoice.params.InvoiceRecordQueryReponse;
import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.BaseResponse;

/**
 * 发票详细信息管理
 * @author wangluyang
 *
 */
@Path("/invoice")
@Consumes(MediaType.APPLICATION_JSON)
@Produces({MediaType.APPLICATION_JSON,MediaType.TEXT_XML})
public interface IInvoiceInfoSV {

	 /**
     * 发票详细信息新增更新接口
     * @param param
     * @return 000000成功；其他失败
     * @throws BusinessException
     * @throws SystemException
     * @author wangly8
     * @ApiDocMethod
     * @ApiCode
     * @RestRelativeURL invoice/saveInvoiceInfo
     */
    @POST
    @Path("/saveInvoiceInfo")
    BaseResponse saveInvoiceInfo(InvoiceInfoParam param)throws BusinessException,SystemException;
    @interface saveInvoiceInfo{}
    
    /**
     * 发票查看明细接口
     * @param request 发票查看明细请求
     * @return 发票明细
     * @throws BusinessException
     * @throws SystemException
     * @author wangly8
     * @ApiDocMethod
     * @ApiCode
     * @RestRelativeURL invoice/queryInvoiceInfo
     */
    @POST
    @Path("/queryInvoiceInfo")
    InvoiceInfoQueryResponse queryInvoiceInfo(InvoiceQueryParam request) throws BusinessException, SystemException;
    
    /**
     * 发票开具记录查看
     * @param request 查询入參
     * @return 发票开具记录
     * @throws BusinessException
     * @throws SystemException
     * @author wangly8
     * @ApiDocMethod
     * @ApiCode
     * @RestRelativeURL invoice/queryInvoiceRecord
     */
    @POST
    @Path("/queryInvoiceRecord")
    InvoiceRecordQueryReponse queryInvoiceRecord(InvoiceRecordQueryParam request) throws BusinessException, SystemException;
    
    /**
     * 发票开具记录新增
     * @param param 新增入參
     * @return 000000成功；其他失败
     * @throws BusinessException
     * @throws SystemException
     * @author wangly8
     * @ApiDocMethod
     * @ApiCode
     * @RestRelativeURL invoice/saveInvoiceRecord
     */
    @POST
    @Path("/saveInvoiceRecord")
    BaseResponse saveInvoiceRecord(InvoiceRecordInsertParam param) throws BusinessException, SystemException;
    
    /**
     * 发票开具详细记录查询
     * @param request 查询入參
     * @return 发票开具详细记录
     * @throws BusinessException
     * @throws SystemException
     * @author wangly8
     * @ApiDocMethod
     * @ApiCode
     * @RestRelativeURL invoice/queryInvoiceInfoDetailRecord
     */
    @POST
    @Path("/queryInvoiceInfoDetailRecord")
    List<InvoiceInfoDetailVO> queryInvoiceInfoDetailRecord(InvoiceRecordQueryParam request) throws BusinessException, SystemException;
    
}
