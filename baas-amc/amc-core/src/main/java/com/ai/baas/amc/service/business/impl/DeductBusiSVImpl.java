package com.ai.baas.amc.service.business.impl;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ai.baas.amc.api.deduct.param.DeductRequest;
import com.ai.baas.amc.api.deduct.param.TransSummary;
import com.ai.baas.amc.constants.AmcConstants;
import com.ai.baas.amc.constants.ExceptCodeConstants;
import com.ai.baas.amc.dao.mapper.bo.AmcFundBook;
import com.ai.baas.amc.service.atom.impl.CommonBalanceHandler;
import com.ai.baas.amc.service.atom.interfaces.IAmcFundBookAtomSV;
import com.ai.baas.amc.service.atom.interfaces.IDeductAtomSV;
import com.ai.baas.amc.service.business.interfaces.IDeductBusiSV;
import com.ai.baas.amc.util.FunSubjectUtil;
import com.ai.baas.amc.vo.DeductVo;
import com.ai.baas.amc.vo.DeductVo.TransSummaryVo;
import com.ai.baas.amc.vo.SubjectFundVo;
import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.sdk.util.BeanUtils;
import com.ai.opt.sdk.util.CollectionUtil;
import com.ai.opt.sdk.util.StringUtil;

/**
 * 扣款业务具体实现类.<br>
 *
 * Date: 2016年3月29日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author fanpw
 */
@Service
@Transactional
public class DeductBusiSVImpl extends CommonBalanceHandler implements IDeductBusiSV {
    
    private static final Logger LOG = LogManager.getLogger(DeductBusiSVImpl.class);
    
    @Autowired
    private IDeductAtomSV deductAtomSV;

    @Autowired
    private IAmcFundBookAtomSV funFundBookSV;

    /**
     * 单次扣款
     */
    @Override
    public String deductFund(DeductRequest request) throws BusinessException {
        LOG.info("普通扣款开始");
        /* 参数转化 */
        DeductVo deductVo = new DeductVo();
        BeanUtils.copyProperties(deductVo, request);
        if (!CollectionUtil.isEmpty(request.getTransSummary())) {
            for (TransSummary summary : request.getTransSummary()) {
                BeanUtils.copyProperties(deductVo.createTransSummary(), summary);
            }
        }
        
        /* 1.合法性校验 */
        /* 1.1 账户校验 */
        this.validateAccountInfo(deductVo.getAccountId(), deductVo.getTenantId());
        /* 1.2 幂等性验证 */
        String paySerialCode = this.validateDuplication(deductVo.getTenantId(),
                deductVo.getSystemId(), deductVo.getExternalId());
        if(!StringUtil.isBlank(paySerialCode)) {
            LOG.info("幂等性校验，该订单已经扣款，返回扣款流水号: " + paySerialCode);
            return paySerialCode;
        }
        
        /* 科目校验 */
        // 资金类型只能是现金流 
        deductVo.addFundType(AmcConstants.FunSubject.FundType.CASH);
        deductVo.addFundType(AmcConstants.FunSubject.FundType.GRANT);
        
        final Map<Long, SubjectFundVo> subjectFundVoMap = deductAtomSV.validateSubject(deductVo);
        /* 2.账本扣款 */
        DeductVo destDeductVo = new DeductVo();// 存储确定账本后的数据
        BeanUtils.copyProperties(destDeductVo, deductVo);
        long alreadyDeduct = 0;
        if (!CollectionUtil.isEmpty(request.getTransSummary())) {
            for (TransSummaryVo summary : deductVo.getTransSummary()) {
                // 统计扣减的金额
                alreadyDeduct = alreadyDeduct + summary.getAmount();
                // 2.1 账本明确
                if (summary.getBookId() != 0) {
                    // 校验账本
                    deductAtomSV.validateFundBook(deductVo.getAccountId(), deductVo.getTenantId(),
                            summary.getBookId());
                    // 单账本扣款
                    deductAtomSV.deductFundBook(deductVo.getAccountId(), summary.getBookId(),
                            summary.getAmount());
                    // copy结果到新对象
                    destDeductVo.addTransSummary(summary.getBookId(), summary.getSubjectId(),
                            summary.getAmount());
                }
                // 2.2 科目明确，账本不明确
                else if (summary.getSubjectId() != 0l) {
                    long subjectId = summary.getSubjectId();
                    long amount = summary.getAmount();
                    // 按照科目扣减，扣减结果保存在destDeductVo中
                    this.subjectDecude(deductVo, destDeductVo, subjectId, amount);
                }
            }
        }
        // 2.3账本和科目均不明确的扣减
        if (deductVo.getTotalAmount() > alreadyDeduct) {
            // 需要按照系统规则扣减的金额
            long ruleAmount = deductVo.getTotalAmount() - alreadyDeduct;
            // 按照系统默认自然扣减，扣减结果保存在destDeductVo中
            this.naturalDecude(deductVo, destDeductVo, subjectFundVoMap, ruleAmount);
        }
        
        // 3.记录交易订单
        paySerialCode = deductAtomSV.savePaySerial(destDeductVo);
        LOG.info("扣款成功，返回扣款流水号：" + paySerialCode);
        return paySerialCode;
    }
    
    /**
     * 按照科目扣减，规则：失效时间越早越优先
     * 
     * @param deductVo
     *            请求对象
     * @param destDeductVo
     *            扣减结果对象
     * @param subjectId
     *            科目
     * @param amount
     *            该科目下扣减金额
     * @throws BusinessException
     * @author lilg
     */
    private void subjectDecude(DeductVo deductVo, DeductVo destDeductVo, long subjectId, long amount)
            throws BusinessException {
        // 查询可扣减的账本，subsId区分是否从专款上扣减
        List<AmcFundBook> fundBookList = funFundBookSV.getSubsValidFundBooksBySubjectId(
                deductVo.getTenantId(), deductVo.getAccountId(), subjectId, deductVo.getSubsId());
        // TODO 是否需要预判科目金额是否充足
        if (CollectionUtil.isEmpty(fundBookList) && amount > 0) {
            throw new BusinessException(ExceptCodeConstants.FunBook.BOOK_NOT_FOUND,
                    "该科目下没有可供扣减的账本，科目ID[" + subjectId + "]");
        }
        // 按照失效时间排序
        Collections.sort(fundBookList, new Comparator<AmcFundBook>() {
            public int compare(AmcFundBook book0, AmcFundBook book1) {
                return book0.getExpireDate().compareTo(book1.getExpireDate());
            }
        });
        // 多账本扣减
        Map<AmcFundBook, Long> resultMap = deductAtomSV.deductFundBook(deductVo.getTenantId(),
                deductVo.getAccountId(), fundBookList, amount);
        long deductAmount = 0;
        for (Map.Entry<AmcFundBook, Long> result : resultMap.entrySet()) {
            // copy结果到新对象
            destDeductVo.addTransSummary(result.getKey().getBookId(), subjectId, result.getValue());
            // 统计总扣减金额
            deductAmount = deductAmount + result.getValue();
        }
        if (deductAmount < amount) {
            throw new BusinessException(ExceptCodeConstants.FunBook.BALANCE_NOT_ENOUGH,
                    "该科目下账本余额不足，科目ID[" + subjectId + "]");
        }

    }

    /**
     * 系统自然扣减，规则：科目优先级,失效时间越早越优先
     * 
     * @param deductVo
     *            请求对象
     * @param destDeductVo
     *            扣减结果对象
     * @param subjectList
     *            科目缓存列表
     * @param amount
     *            扣减金额
     * @throws BusinessException
     * @author lilg
     */
    private void naturalDecude(DeductVo deductVo, DeductVo destDeductVo,
            final Map<Long, SubjectFundVo> subjectList, long amount) throws BusinessException {
        // 查询可扣减的账本,subsId区分是否从专款上扣减
        List<AmcFundBook> fundBookList = funFundBookSV.getSubsValidFundBooksByFundType(
                deductVo.getTenantId(), deductVo.getAccountId(), deductVo.getFundTypes(),
                deductVo.getSubsId());
        // TODO 是否需要预判科目金额是否充足
        if (CollectionUtil.isEmpty(fundBookList) && amount > 0) {
            throw new BusinessException(ExceptCodeConstants.FunBook.BOOK_NOT_FOUND,
                    "该账户下没有可供扣减的账本，账户ID[" + deductVo.getAccountId() + "]");
        }
        // 按照科目优先级,失效时间排序
        Collections.sort(fundBookList, new Comparator<AmcFundBook>() {
            public int compare(AmcFundBook book0, AmcFundBook book1) {
                // 优先从内存List中读取，如果不存在，调用Common服务读取并放入List
                if (!subjectList.containsKey(book0.getSubjectId())) {
                    subjectList.put(book0.getSubjectId(),
                            FunSubjectUtil.getSubjectFund(book0.getTenantId(), book0.getSubjectId()));
                }
                if (!subjectList.containsKey(book1.getSubjectId())) {
                    subjectList.put(book1.getSubjectId(),
                            FunSubjectUtil.getSubjectFund(book1.getTenantId(), book1.getSubjectId()));
                }
                SubjectFundVo subject0 = subjectList.get(book0.getSubjectId());
                SubjectFundVo subject1 = subjectList.get(book1.getSubjectId());
                if (subject0 == null) {
                    throw new BusinessException(ExceptCodeConstants.Subject.SUBJECT_NOT_FOUND,
                            "资金科目未配置：科目ID=" + book0.getSubjectId());
                }
                if (subject1 == null) {
                    throw new BusinessException(ExceptCodeConstants.Subject.SUBJECT_NOT_FOUND,
                            "资金科目未配置：科目ID=" + book1.getSubjectId());
                }
                Long usePri0 = subject0.getUsePri();
                Long usePri1 = subject1.getUsePri();
                int result = usePri0.compareTo(usePri1);
                return result == 0 ? book0.getExpireDate().compareTo(book1.getExpireDate()) : result;
            }
        });
        // 多账本扣减
        Map<AmcFundBook, Long> resultMap = deductAtomSV.deductFundBook(deductVo.getTenantId(),
                deductVo.getAccountId(), fundBookList, amount);
        long deductAmount = 0;
        for (Map.Entry<AmcFundBook, Long> result : resultMap.entrySet()) {
            // copy结果到新对象
            destDeductVo.addTransSummary(result.getKey().getBookId(), result.getKey()
                    .getSubjectId(), result.getValue());
            // 统计总扣减金额
            deductAmount = deductAmount + result.getValue();
        }
        if (deductAmount < amount) {
            throw new BusinessException(ExceptCodeConstants.FunBook.BALANCE_NOT_ENOUGH,
                    "该账户下账本余额不足，账户ID[" + deductVo.getAccountId() + "]");
        }
    }

}
