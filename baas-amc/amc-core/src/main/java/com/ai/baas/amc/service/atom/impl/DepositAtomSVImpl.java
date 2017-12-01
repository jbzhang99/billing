package com.ai.baas.amc.service.atom.impl;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ai.baas.amc.constants.AmcConstants;
import com.ai.baas.amc.constants.ExceptCodeConstants;
import com.ai.baas.amc.dao.mapper.bo.AmcFundBook;
import com.ai.baas.amc.dao.mapper.bo.AmcFundDetail;
import com.ai.baas.amc.dao.mapper.bo.AmcFundSerial;
import com.ai.baas.amc.service.atom.interfaces.IAmcFundBookAtomSV;
import com.ai.baas.amc.service.atom.interfaces.IAmcFundDetailAtomSV;
import com.ai.baas.amc.service.atom.interfaces.IAmcFundSerialAtomSV;
import com.ai.baas.amc.service.atom.interfaces.IDepositAtomSV;
import com.ai.baas.amc.util.AmcSeqUtil;
import com.ai.baas.amc.util.FunSubjectUtil;
import com.ai.baas.amc.vo.DepositVo;
import com.ai.baas.amc.vo.DepositVo.TransSummaryVo;
import com.ai.baas.amc.vo.SubjectFundVo;
import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.sdk.util.CollectionUtil;
import com.ai.opt.sdk.util.DateUtil;
import com.ai.opt.sdk.util.StringUtil;

/**
 * 存款通用服务
 *
 * Date: 2016年3月24日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author fanpw
 */
@Component
public class DepositAtomSVImpl implements IDepositAtomSV {
    
    private static final Logger LOG = LogManager.getLogger(DepositAtomSVImpl.class);
    
    @Autowired
    private IAmcFundSerialAtomSV amcFundSerialAtomSV;
    
    @Autowired
    private IAmcFundBookAtomSV amcFundBookAtomSV;
    
    @Autowired
    private IAmcFundDetailAtomSV amcFundDetailAtomSV;

    /**
     * 科目校验
     */
    @Override
    public Map<Long, SubjectFundVo> validateSubject(DepositVo vo) {
        Map<Long, SubjectFundVo> subjectFundVoMap = new HashMap<Long, SubjectFundVo>();
        LOG.info("科目校验开始");
        for (TransSummaryVo summary : vo.getTransSummary()) {
            long subjectId = summary.getSubjectId();
            // 校验科目是否存在
            SubjectFundVo subjectFundVo = FunSubjectUtil.getSubjectFund(vo.getTenantId(), subjectId);
            if (subjectFundVo == null) {
                LOG.info("科目不存在,科目ID[" + subjectId + "]");
                throw new BusinessException(ExceptCodeConstants.Subject.SUBJECT_NOT_FOUND,
                        "科目不存在,科目ID[" + subjectId + "]");
            }
            // 校验科目类型是否匹配
            if (!AmcConstants.FunSubject.SubjectType.FUND.equals(subjectFundVo.getSubjectType())) {
                LOG.info("科目类型不是资金科目,科目ID[" + subjectId + "]");
                throw new BusinessException(ExceptCodeConstants.Subject.SUBJECT_NOT_VALID,
                        "科目类型不是资金科目,科目ID[" + subjectId + "]");
            }
            // 校验资金类型是否匹配
            if (!vo.getFundTypes().contains(subjectFundVo.getFundType())) {
                LOG.info("科目的资金类型不符合条件,科目ID[" + subjectId + "]");
                throw new BusinessException(ExceptCodeConstants.Subject.SUBJECT_NOT_VALID,
                        "科目的资金类型不符合条件,科目ID[" + subjectId + "]");
            }
            subjectFundVoMap.put(summary.getSubjectId(), subjectFundVo);
        }
        LOG.info("科目校验结束：OK");
        return subjectFundVoMap;
    }

    /**
     * 账本确定
     */
    @Override
    public List<AmcFundBook> locateFundBook(DepositVo vo, Map<Long, SubjectFundVo> subjectFundMap) {
        List<AmcFundBook> funFundBookList = new ArrayList<AmcFundBook>();
        for (TransSummaryVo summary : vo.getTransSummary()) {
            SubjectFundVo subject = subjectFundMap.get(summary.getSubjectId());
            // 1.累加账本金额
            vo.setTotalAmount(vo.getTotalAmount() + summary.getAmount());
            // 2.确定账本
            AmcFundBook fundBook = null;
            boolean newBook = false;
            if (subject == null) {
                throw new BusinessException(ExceptCodeConstants.Subject.SUBJECT_NOT_FOUND,
                        "资金科目不存在,科目ID[" + summary.getSubjectId() + "]");
            }
            // subsId {0-非专款,其他-用户专款],
            List<AmcFundBook> existBookList = amcFundBookAtomSV.getSubsValidFundBooksBySubjectId(
                    vo.getTenantId(), vo.getAccountId(), summary.getSubjectId(), vo.getSubsId());
            // 账本ID，金额，生失效时间特殊处理
            if (CollectionUtil.isEmpty(existBookList)
                    || AmcConstants.FunSubject.ValidType.ADD.equals(subject.getValidType())) {
                // 创建新账本
                newBook = true;
                fundBook = new AmcFundBook();
                fundBook.setBookId(AmcSeqUtil.createBookId());
                fundBook.setBalance(summary.getAmount());
                // 新创建时赋值
                fundBook.setAcctId(vo.getAccountId());
                fundBook.setCustId(vo.getCustId());
                fundBook.setTenantId(vo.getTenantId());
                fundBook.setSubjectId(subject.getSubjectId());
                fundBook.setSubjectType(subject.getFundType());
                fundBook.setCreateTime(DateUtil.getSysDate());
                fundBook.setBookStatus(AmcConstants.FunFundBook.BookStatus.VALID);
                fundBook.setSubsId(vo.getSubsId());
               
            } else {
                // 在旧账本上叠加
                fundBook = existBookList.get(0);
                fundBook.setBalance(fundBook.getBalance() + summary.getAmount());
            }
            if (AmcConstants.FunSubject.ValidType.MERGE.equals(subject.getValidType())) {
                if (fundBook.getEffectDate() == null) {
                    fundBook.setEffectDate((DateUtil.getSysDate()));
                }
                if (fundBook.getExpireDate() == null) {
                    fundBook.setExpireDate(AmcConstants.FunFundBook.DefaultDate.EXPIREDATE);
                }
            }
            if (AmcConstants.FunSubject.ValidType.DELAY.equals(subject.getValidType())) {
                if (fundBook.getEffectDate() == null) {
                    fundBook.setEffectDate(StringUtil.isBlank(summary.getFundeffDate()) ? DateUtil
                            .getSysDate() : DateUtil.getTimestamp(summary.getFundeffDate(),
                            DateUtil.DATETIME_FORMAT));
                }
                // 结束时间顺延
                Timestamp expireDate = fundBook.getExpireDate();
                if (!StringUtil.isBlank(summary.getFundexpDate())) {
                    Timestamp summaryDate = DateUtil.getTimestamp(summary.getFundexpDate(),
                            DateUtil.DATETIME_FORMAT);
                    if (expireDate == null) {
                        expireDate = summaryDate;
                    } else {
                        expireDate = expireDate.compareTo(summaryDate) > 0 ? expireDate
                                : summaryDate;
                    }
                } else {
                    if (expireDate == null) {
                        expireDate = AmcConstants.FunFundBook.DefaultDate.EXPIREDATE;
                    }
                }
                fundBook.setExpireDate(expireDate);
            }
            if (AmcConstants.FunSubject.ValidType.ADD.equals(subject.getValidType())) {
                fundBook.setEffectDate(StringUtil.isBlank(summary.getFundeffDate()) ? DateUtil
                        .getSysDate() : DateUtil.getTimestamp(summary.getFundeffDate(),
                        DateUtil.DATETIME_FORMAT));
                fundBook.setExpireDate(StringUtil.isBlank(summary.getFundexpDate()) ? AmcConstants.FunFundBook.DefaultDate.EXPIREDATE
                        : DateUtil.getTimestamp(summary.getFundexpDate(), DateUtil.DATETIME_FORMAT));
            }
            // 3.更新账本
            if (newBook) {
                LOG.info("开始创建新账本");
                amcFundBookAtomSV.saveAmcFundBook(fundBook);
                LOG.info("创建新账本成功，账本ID: " + fundBook.getBookId());
            } else {
                LOG.info("开始更新账本余额");
                // TODO 需要考虑实效时间按规则延期的
                amcFundBookAtomSV.depositBalance(fundBook.getAcctId(), fundBook.getBookId(),
                        summary.getAmount(), fundBook.getEffectDate(), fundBook.getExpireDate());
                LOG.info("更新账本余额成功，账本ID: " + fundBook.getBookId());
            }
            // 4.账本ID暂存在业务参数里
            summary.setBookId(fundBook.getBookId());
            funFundBookList.add(fundBook);
        }
        return funFundBookList;
    }
    
    /**
     * 保存资金交易信息并记录账本交易明细
     */
    @Override
    public String savePaySerial(DepositVo vo) throws SystemException {
        String currentMonth = DateUtil.getDateString(DateUtil.getSysDate(), DateUtil.YYYYMM);
        String paySerialCode = this.saveFundSerial(currentMonth, vo);
        this.saveFundDetail(currentMonth, vo, paySerialCode);
        return paySerialCode;
    }
    
    /**
     * 保存资金交易信息
     */
    private String saveFundSerial(String currentMonth, DepositVo vo) {
        String paySerialCode = AmcSeqUtil.createPaySerialCode().toString();
        LOG.info("开始保存资金交易信息：pay_serial_code=" + paySerialCode + "]");
        AmcFundSerial funFundSerial = new AmcFundSerial();
        funFundSerial.setPaySerialCode(paySerialCode);
        funFundSerial.setTenantId(vo.getTenantId());
        funFundSerial.setSystemId(vo.getSystemId());
        funFundSerial.setPeerSerialCode(vo.getBusiSerialNo());
        funFundSerial.setTotalAmount(vo.getTotalAmount());
        funFundSerial.setAcctId1(vo.getAccountId());
        funFundSerial.setCustId1(vo.getCustId());
        // funFundSerial.setAcctName1(vo.getAccountInfo().getAcctName());// TODO 是否要保存账户姓名
        funFundSerial.setRemark(vo.getBusiDesc());
        funFundSerial.setOptType(AmcConstants.FunFundSerial.OptType.DEPOSIT);
        funFundSerial.setPayStatus(AmcConstants.FunFundSerial.PayStatus.SUCCESS);// TODO 成功？
        funFundSerial.setCreateTime(DateUtil.getSysDate());// TODO 不需要交易时间，只保留创建时间
        if(!StringUtil.isBlank(vo.getDepositTime())){
        	 try {
     			Date date = new SimpleDateFormat("yyyy-MM-dd").parse(vo.getDepositTime());
     			Timestamp tim = new Timestamp(date.getTime());
     	        funFundSerial.setPayTime(tim); // FIXME 应该修改表模型，删掉字段
     		} catch (Exception e) {
     			throw new BusinessException("日期类型转换失败", e);
     		}
        }else{
        	funFundSerial.setPayTime(DateUtil.getSysDate());
        }     
        funFundSerial.setLastStatusDate(DateUtil.getSysDate());
        funFundSerial.setTransSummary("无");// FIXME
        
        amcFundSerialAtomSV.saveAmcFundSerial(currentMonth, funFundSerial);
        LOG.info("保存资金交易信息结束：OK");
        return paySerialCode;
    }

    /**
     * 记录资金异动 
     */
    private void saveFundDetail(String currentMonth, DepositVo depositVo, String paySerialCode) {
        LOG.info("开始保存资金异动交易明细");
        for (TransSummaryVo summary : depositVo.getTransSummary()) {
            AmcFundDetail funFundDetail = new AmcFundDetail();
            funFundDetail.setSerialCode(AmcSeqUtil.createFundDetailSerialCode().toString());
            funFundDetail.setPaySerialCode(paySerialCode);
            funFundDetail.setAcctId(depositVo.getAccountId());
            funFundDetail.setCustId(depositVo.getCustId());
            funFundDetail.setTotalAmount(summary.getAmount());
            funFundDetail.setBookId(summary.getBookId());
            funFundDetail.setCreateTime(DateUtil.getSysDate());
            funFundDetail.setValueDate(DateUtil.getSysDate());// FIXME 应该修改表模型，删掉字段
            funFundDetail.setBalancePre(0l);// FIXME 应该修改表模型，删掉字段
            funFundDetail.setOptType(AmcConstants.FunFundSerial.OptType.DEPOSIT);
            funFundDetail.setRemark(depositVo.getBusiDesc());
            funFundDetail.setSubjectId(summary.getSubjectId());
            LOG.debug("记录资金流水异动交易明细:serial_code=" + funFundDetail.getSerialCode());
            amcFundDetailAtomSV.saveAmcFundDetail(currentMonth, funFundDetail);
        }
        LOG.info("保存资金异动交易明细结束：OK");
    }
    
}
