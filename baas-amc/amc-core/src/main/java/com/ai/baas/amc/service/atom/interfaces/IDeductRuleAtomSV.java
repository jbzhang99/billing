package com.ai.baas.amc.service.atom.interfaces;

import com.ai.baas.amc.vo.DeductRuleVo;

import java.util.List;

/**
 * 销账规则操作
 * Created by jackieliu on 16/3/31.
 */
public interface IDeductRuleAtomSV {

    /**
     * 获取指定租户的指定科目的销账规则
     *
     * @param tenantId 租户id
     * @param subjectId 科目id
     * @return
     */
    public List<DeductRuleVo> query(String tenantId,Long subjectId);
}
