package com.ai.baas.amc.service.atom.impl;

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
import com.ai.baas.amc.dao.mapper.bo.AmcFundDetailSingle;
import com.ai.baas.amc.dao.mapper.bo.AmcFundSerialSingle;
import com.ai.baas.amc.service.atom.interfaces.IAmcFundBookAtomSV;
import com.ai.baas.amc.service.atom.interfaces.IAmcFundDetailAtomSV;
import com.ai.baas.amc.service.atom.interfaces.IAmcFundSerialAtomSV;
import com.ai.baas.amc.service.atom.interfaces.IDeductAtomSV;
import com.ai.baas.amc.util.AmcSeqUtil;
import com.ai.baas.amc.util.FunSubjectUtil;
import com.ai.baas.amc.vo.DeductVo;
import com.ai.baas.amc.vo.DeductVo.TransSummaryVo;
import com.ai.baas.amc.vo.SubjectFundVo;
import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.sdk.util.CollectionUtil;
import com.ai.opt.sdk.util.DateUtil;

/**
 * 扣款原子服务实现类
 *
 * Date: 2016年3月29日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author fanpw
 */
@Component
public class DeductAtomSVImpl implements IDeductAtomSV {
    
    private static final Logger LOG = LogManager.getLogger(DeductAtomSVImpl.class);
    
    @Autowired
    private IAmcFundSerialAtomSV amcFundSerialAtomSV;
    
    @Autowired
    private IAmcFundBookAtomSV amcFundBookAtomSV;
    
    @Autowired
    private IAmcFundDetailAtomSV amcFundDetailAtomSV;
    
    @Override
    public Map<Long, SubjectFundVo> validateSubject(DeductVo vo) {
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

    @Override
    public void validateFundBook(String acctId, String tenantId, long bookId) {
        // 1.账本校验
        AmcFundBook fundBook = amcFundBookAtomSV.getAmcFundBookByBookId(tenantId, acctId, bookId);
        if (fundBook == null) {
            LOG.error("账本不存在,账本ID[" + bookId + "]");
            throw new BusinessException(ExceptCodeConstants.FunBook.BOOK_NOT_FOUND, "扣款失败，传入错误的账本ID["
                    + bookId + "]");
        }       
    }

    @Override
    public void deductFundBook(String acctId, long bookId, long amount) {
        // 3.账本金额扣减
        int result = amcFundBookAtomSV.deductBalance(acctId, bookId, amount);
        if (result != 1) {
            // 更新失败
            throw new BusinessException(ExceptCodeConstants.FunBook.BALANCE_NOT_ENOUGH,
                    "账本余额不足,账本ID[" + bookId + "]");
        }
    }

    @Override
    public Map<AmcFundBook, Long> deductFundBook(String tenantId, String accountId,
            List<AmcFundBook> bookList, long totalAmount) {
        Map<AmcFundBook, Long> resultMap = new HashMap<AmcFundBook, Long>();
        // 对账本进行扣减
        for (AmcFundBook book : bookList) {
            long bookBalance = book.getBalance();
            while (bookBalance > 0 && totalAmount > 0) {// 循环尝试扣减
                long deductAmount = bookBalance < totalAmount ? bookBalance : totalAmount;
                int result = amcFundBookAtomSV.deductBalance(accountId, book.getBookId(), deductAmount);
                if (result == 1) {// 扣减成功
                    totalAmount = totalAmount - deductAmount;
                    resultMap.put(book, deductAmount);
                    break;
                } else {// 扣减失败，重新查询余额
                    bookBalance = amcFundBookAtomSV.getAmcFundBookByBookId(tenantId, accountId, book.getBookId())
                            .getBalance();
                }
            }
        }
        return resultMap;
    }

    /**
     * 保存资金交易信息并记录账本交易明细
     */
    @Override
    public String savePaySerial(DeductVo vo) throws SystemException {
        String currentMonth = DateUtil.getDateString(DateUtil.getSysDate(), DateUtil.YYYYMM);
        String paySerialCode = this.saveFundSerial(currentMonth, vo);
        this.saveFundDetail(currentMonth, vo, paySerialCode);
        return paySerialCode;
    }
    
    /**
     * 保存资金交易信息
     */
    private String saveFundSerial(String currentMonth, DeductVo vo) {
        String paySerialCode = AmcSeqUtil.createPaySerialCode().toString();
        LOG.info("开始保存资金交易信息：pay_serial_code=" + paySerialCode + "]");
        AmcFundSerialSingle funFundSerial = new AmcFundSerialSingle();
        funFundSerial.setPaySerialCode(paySerialCode.toString());
        funFundSerial.setTenantId(vo.getTenantId());
        funFundSerial.setSystemId(vo.getSystemId());
        funFundSerial.setPeerSerialCode(vo.getExternalId());
        funFundSerial.setTotalAmount(-vo.getTotalAmount());
        funFundSerial.setAcctId1(vo.getAccountId());
        funFundSerial.setCustId1(vo.getCustId());
        // funFundSerial.setAcctName1(vo.getAccountInfo().getAcctName());// TODO 是否要保存账户姓名
        funFundSerial.setRemark(vo.getBusiDesc());
        funFundSerial.setOptType(AmcConstants.FunFundSerial.OptType.DEDUCT);
        funFundSerial.setPayStatus(AmcConstants.FunFundSerial.PayStatus.SUCCESS);// TODO 成功？
        funFundSerial.setCreateTime(DateUtil.getSysDate());// TODO 不需要交易时间，只保留创建时间
        funFundSerial.setPayTime(DateUtil.getSysDate()); // FIXME 应该修改表模型，删掉字段
        funFundSerial.setLastStatusDate(DateUtil.getSysDate());
        funFundSerial.setTransSummary("无");// FIXME
        amcFundSerialAtomSV.saveAmcFundSerialSingle( funFundSerial);
        LOG.info("保存资金交易信息结束：OK");
        return paySerialCode;
    }

    /**
     * 记录资金异动 
     */
    private void saveFundDetail(String currentMonth, DeductVo deductVo, String paySerialCode) {
        LOG.info("开始保存资金异动交易明细");
        if (CollectionUtil.isEmpty(deductVo.getTransSummary())) {
            return;
        }
        for (TransSummaryVo summary : deductVo.getTransSummary()) {
            AmcFundDetailSingle funFundDetail = new AmcFundDetailSingle();
            funFundDetail.setSerialCode(AmcSeqUtil.createFundDetailSerialCode().toString());
            funFundDetail.setPaySerialCode(paySerialCode);
            funFundDetail.setAcctId(deductVo.getAccountId());
            funFundDetail.setCustId(deductVo.getCustId());
            funFundDetail.setTotalAmount(-summary.getAmount());
            funFundDetail.setBookId(summary.getBookId());
            funFundDetail.setCreateTime(DateUtil.getSysDate());
            funFundDetail.setValueDate(DateUtil.getSysDate());// FIXME 应该修改表模型，删掉字段
            funFundDetail.setBalancePre(0l);// FIXME 应该修改表模型，删掉字段
            funFundDetail.setOptType(AmcConstants.FunFundSerial.OptType.DEDUCT);
            funFundDetail.setRemark(deductVo.getBusiDesc());
            funFundDetail.setSubjectId(summary.getSubjectId());
            LOG.debug("记录资金流水异动交易明细:serial_code=" + funFundDetail.getSerialCode());
            amcFundDetailAtomSV.saveAmcFundDetailSingle(currentMonth, funFundDetail);
        }
        LOG.info("保存资金异动交易明细结束：OK");
    }

}
