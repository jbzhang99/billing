package com.ai.baas.amc.service.atom.interfaces;

import com.ai.baas.amc.vo.SubjectFeeVo;
import com.ai.baas.amc.vo.SubjectFundVo;
import com.ai.baas.amc.vo.SubjectVo;

/**
 * 消费科目定义操作
 * Created by jackieliu on 16/4/1.
 */
public interface ISubjectAtomSV {

    /**
     * 根据租户id和科目id获取资金科目定义
     *
     * @param tenantId
     * @param subjectId
     * @return
     */
    public SubjectVo getSubjectByTenantAndSubject(String tenantId,Long subjectId);

    /**
     * 根据科目id获取消费科目定义信息
     *
     * @param subjectId
     * @return
     */
    public SubjectFeeVo getFeeBySubjectId(Long subjectId);

    /**
     * 根据租户id和资金id获取资金科目定义
     *
     * @param tenantId
     * @param subjectId
     * @return
     */
    public SubjectFundVo getFundByTenantAndSubject(String tenantId,Long subjectId);
}
