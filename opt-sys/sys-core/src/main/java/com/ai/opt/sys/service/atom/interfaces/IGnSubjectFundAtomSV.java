package com.ai.opt.sys.service.atom.interfaces;

import java.util.List;

import com.ai.opt.base.exception.SystemException;
import com.ai.opt.sys.dao.mapper.bo.GnSubjectFund;
import com.ai.opt.sys.dao.mapper.bo.GnSubjectFundKey;


/**
 * 查询资金科目详细
 *
 * Date: 2016年1月4日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * 
 * @author lilg
 */
public interface IGnSubjectFundAtomSV {

    /**
     * 根据subjectId查询资金科目定义
     * 
     * @param subjectId
     * @return
     * @author lilg
     * @ApiDocMethod
     */
    GnSubjectFund queryGnSubjectFund(String tenantId,String industryCode,long subjectId);

    /**
     * 查询所有资金科目定义
     * 
     * @return
     * @author lilg
     * @ApiDocMethod
     */
    List<GnSubjectFund> queryGnSubjectFund();

    /**
     * 增加资金科目详细
     * 
     * @param vo
     * @throws SystemException
     * @author lilg
     */
    int addSubjectFund(GnSubjectFund vo) throws SystemException;

    /**
     * 删除资金科目详细
     * 
     * @param key
     * @throws SystemException
     * @author lilg
     */
    int delSubjectFund(GnSubjectFundKey key) throws SystemException;

    /**
     * 修改资金科目详细
     * 
     * @param vo
     * @param key
     * @throws SystemException
     * @author lilg
     */
    int modSubjectFund(GnSubjectFund vo, GnSubjectFundKey key) throws SystemException;

}
