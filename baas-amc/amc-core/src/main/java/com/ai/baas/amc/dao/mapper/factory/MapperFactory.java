package com.ai.baas.amc.dao.mapper.factory;

import javax.annotation.PostConstruct;

import com.ai.baas.amc.dao.mapper.interfaces.*;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ai.baas.amc.dao.mapper.attach.AmcFundBookAttachMapper;

@Component
public class MapperFactory {

    @Autowired
    private SqlSessionTemplate st;

    private static SqlSessionTemplate sqlSessionTemplate;

    @PostConstruct
    void init() {
        setSqlSessionTemplate(st);
    }
    
    public static void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        MapperFactory.sqlSessionTemplate = sqlSessionTemplate;
    }
    
    /**
     * 账本表
     * @return
     * @author fanpw
     * @ApiDocMethod
     * @ApiCode
     */
    public static AmcFundBookMapper getAmcFundBookMapper() {
        return sqlSessionTemplate.getMapper(AmcFundBookMapper.class);
    }
    
    /**
     * 资金流水明细表
     * @return
     * @author fanpw
     * @ApiDocMethod
     * @ApiCode
     */
    public static AmcFundDetailMapper getAmcFundDetailMapper() {
        return sqlSessionTemplate.getMapper(AmcFundDetailMapper.class);
    }
    
    /**
     * 交易流水表
     * @return
     * @author fanpw
     * @ApiDocMethod
     * @ApiCode
     */
    public static AmcFundSerialSingleMapper getAmcFundSerialSingleMapper() {
        return sqlSessionTemplate.getMapper(AmcFundSerialSingleMapper.class);
    }
    
    /**
     * 资金流水明细表
     * @return
     * @author fanpw
     * @ApiDocMethod
     * @ApiCode
     */
    public static AmcFundDetailSingleMapper getAmcFundDetailSingleMapper() {
        return sqlSessionTemplate.getMapper(AmcFundDetailSingleMapper.class);
    }
    
    /**
     * 交易流水表
     * @return
     * @author fanpw
     * @ApiDocMethod
     * @ApiCode
     */
    public static AmcFundSerialMapper getAmcFundSerialMapper() {
        return sqlSessionTemplate.getMapper(AmcFundSerialMapper.class);
    }
    
    public static AmcFundBookAttachMapper getAmcFundBookAttachMapper() {
        return sqlSessionTemplate.getMapper(AmcFundBookAttachMapper.class);
    }

    /**
     * 账单明细表
     * @return
     */
    public static AmcChargeMapper getAmcChargeMapper(){
        return sqlSessionTemplate.getMapper(AmcChargeMapper.class);
    }

    /**
     * 成本中信明细表
     * @return
     */
    public static AmcCcDetailMapper getAmcCcDetailMapper(){
        return sqlSessionTemplate.getMapper(AmcCcDetailMapper.class);
    }
    /**
     * 账单表
     * @return
     */
    public static AmcInvoiceMapper getAmcInvoiceMapper(){
        return sqlSessionTemplate.getMapper(AmcInvoiceMapper.class);
    }
    
    /**
     * 账单明细表
     * @return
     */
    public static AmcChargeYyyyddMapper getAmcChargeYyyyddMapper(){
        return sqlSessionTemplate.getMapper(AmcChargeYyyyddMapper.class);
    }
  

    /**
     * 账单表
     * @return
     */
    public static AmcInvoiceYyyyddMapper getAmcInvoiceYyyyddMapper(){
        return sqlSessionTemplate.getMapper(AmcInvoiceYyyyddMapper.class);
    }
    /**
     * 欠费总表
     * @return
     */
    public static AmcOweInfoMapper getAmcOweInfoMapper(){
        return sqlSessionTemplate.getMapper(AmcOweInfoMapper.class);
    }
    
    /**
     * 账单优惠产品信息表
     * @return
     * @author fanpw
     * @ApiDocMethod
     * @ApiCode
     */
    public static AmcProductInfoMapper getAmcProductInfoMapper() {
        return sqlSessionTemplate.getMapper(AmcProductInfoMapper.class);
    }
    
    /**
     * 账单优惠产品明细表
     * @return
     * @author fanpw
     * @ApiDocMethod
     * @ApiCode
     */
    public static AmcProductDetailMapper getAmcProductDetailMapper() {
        return sqlSessionTemplate.getMapper(AmcProductDetailMapper.class);
    }
    
    /**
     * 账单优惠产品扩展表
     * @return
     * @author fanpw
     * @ApiDocMethod
     * @ApiCode
     */
    public static AmcProductExtMapper getAmcProductExtMapper() {
        return sqlSessionTemplate.getMapper(AmcProductExtMapper.class);
    }
    
    public static AmcDrBillSubjectMapMapper getAmcDrBillSubjectMapMapper() {
    	return sqlSessionTemplate.getMapper(AmcDrBillSubjectMapMapper.class);
    }
    
    /**
     * 销账明细
     * @return
     * @author LiangMeng
     */
    public static AmcSettleDetailMapper getAmcSettleDetailMapper(){
        return sqlSessionTemplate.getMapper(AmcSettleDetailMapper.class);
    }
  

    /**
     * 销账记录
     * @return
     * @author LiangMeng
     */
    public static AmcSettleLogMapper getAmcSettleLogMapper(){
        return sqlSessionTemplate.getMapper(AmcSettleLogMapper.class);
    }
    
    /**
     * 发票信息明细表
     * @return
     */
    public static AmcInvoiceInfoMapper getAmcInvoiceInfoMapper(){
    	return sqlSessionTemplate.getMapper(AmcInvoiceInfoMapper.class);
    }
    
    /**
     * 发票历史记录表
     * @return
     */
    public static AmcInvoiceLogMapper getAmcInvoiceLogMapper(){
    	return sqlSessionTemplate.getMapper(AmcInvoiceLogMapper.class);
    }
    
    /**
     * 错单记录表
     * @return
     * @author LiangMeng
     */
    public static AmcFailureBillMapper getAmcFailureBillMapper(){
        return sqlSessionTemplate.getMapper(AmcFailureBillMapper.class);
    }

    /**
     * 成本中心汇总表
     * @return
     */
    public static AmcCcChargeYyyyddMapper getAmcCcChargeYyyyddMapper(){
        return sqlSessionTemplate.getMapper(AmcCcChargeYyyyddMapper.class);
    }

    /**
     * 成本中心明细表
     * @return
     */
    public static AmcCcDetailYyyyddMapper getAmcCcDetailYyyyddMapper(){
        return sqlSessionTemplate.getMapper(AmcCcDetailYyyyddMapper.class);
    }

}
