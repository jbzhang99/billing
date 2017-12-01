package com.ai.baas.amc.service.atom.interfaces;

import java.util.List;
import java.util.Map;

import com.ai.baas.amc.dao.mapper.bo.AmcFundBook;
import com.ai.baas.amc.vo.DepositVo;
import com.ai.baas.amc.vo.SubjectFundVo;
import com.ai.opt.base.exception.SystemException;

/**
 * 存款通用服务
 *
 * Date: 2016年3月24日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author fanpw
 */
public interface IDepositAtomSV {
    
    /**
     * 科目校验 <br>
     * 入参，存款对象DepositVo <br>
     * 出参，Subject的JSON对象<br>
     * 
     * @param subjectIds
     * @return
     * @author lilg
     * @ApiDocMethod
     */
    Map<Long, SubjectFundVo> validateSubject(DepositVo vo);

    /**
     * 账本确认，计算交易总额，账本更新 入参，存款对象DepositVo，所用科目Map
     * 
     * @param vo
     * @param subjectMap
     * @return
     * @author lilg
     * @ApiDocMethod
     */
    List<AmcFundBook> locateFundBook(DepositVo vo, Map<Long, SubjectFundVo> subjectMap);
    
    /**
     * 保存资金交易信息并记录账本交易明细
     * @param vo
     * @return
     * @throws SystemException
     * @author fanpw
     * @ApiDocMethod
     * @ApiCode
     */
    String savePaySerial(DepositVo vo) throws SystemException;
    
}
