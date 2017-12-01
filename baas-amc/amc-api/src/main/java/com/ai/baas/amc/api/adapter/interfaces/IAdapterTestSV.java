package com.ai.baas.amc.api.adapter.interfaces;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
@Path("/adapter-url")
@Produces({MediaType.APPLICATION_JSON,MediaType.TEXT_XML,MediaType.TEXT_PLAIN})
public interface IAdapterTestSV {
    @GET
    @Path("/orders")
    String orders(String vo) throws BusinessException,SystemException;  
    @GET
    @Path("/service_instance/cost")
    String costs(String vo) throws BusinessException,SystemException;    
    @GET
    @Path("/suppliers_service/total_bill/:576203a86ae6ca04e1459582")
    String totalBill(String vo) throws BusinessException,SystemException;    
    @GET
    @Path("/suppliers_service/consume_detail")
    String detail(String vo) throws BusinessException,SystemException;    
    @GET
    @Path("/usrmgt/service_org")
    String org(String vo) throws BusinessException,SystemException;    
    @GET
    @Path("/usage_record/:service_id")
    String usage(String vo) throws BusinessException,SystemException;    
    @GET
    @Path("/catalog")
    String catalog(String vo) throws BusinessException,SystemException;    
}
