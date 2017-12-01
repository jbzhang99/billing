package com.ai.opt.sys.service.atom.interfaces;

import java.util.List;
import java.util.Map;

import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.PageInfo;
import com.ai.opt.sys.dao.mapper.bo.GnSubject;
import com.ai.opt.sys.dao.mapper.bo.GnSubjectKey;


/**
 * 科目编码 原子服务 Date: 2015年8月19日 <br>
 * Copyright (c) 2015 asiainfo.com <br>
 * 
 * @author lilg
 */
public interface IGnSubjectAtomSV {
    /**
     * 根据subjectId查询科目编码
     * 
     * @param subjectId
     * @return
     * @author lilg
     * @ApiDocMethod
     */
    GnSubject queryGnSubject(String tenantId, String industryCode, long subjectId);

    /**
     * 科目分页条件查询
     * @param tenantId
     * @param industryCode
     * @param subjectId
     * @param subjectType
     * @param subjectName
     * @param pageNo
     * @param pageSize
     * @return
     * @author lilg
     */
    PageInfo<GnSubject> queryGnSubject(String tenantId, String industryCode, Long subjectId,
            String subjectType, String subjectName, Integer pageNo, Integer pageSize);

    /**
     * 查询所有科目编码
     * 
     * @return
     * @author lilg
     * @ApiDocMethod
     */
    List<GnSubject> queryGnSubject();

    /**
     * 新增科目
     * 
     * @param vo
     * @throws BusinessException
     * @author lilg
     * @ApiDocMethod
     * @ApiCode
     */
    int addSubject(GnSubject vo);

    /**
     * 删除科目
     * 
     * @param key
     * @throws SystemException
     * @author lilg
     */
    int delSubject(GnSubjectKey key) throws SystemException;
    
    void updateByPrimaryKeySelective(GnSubject gnSubject) throws SystemException;

    GnSubject selectByPrimaryKey(GnSubjectKey key) throws SystemException;
    /**
     * 查询可以关联的详单信息列表
     * @param gnSubject
     * @return
     * @author zhangzd
     * @ApiDocMethod
     * @ApiCode
     */
    List<GnSubject> getGnSubjectListMayRelated(GnSubject gnSubject);
    /**
     * 根据租户编号和科目编号查询科目信息
     * @param tenantId
     * @param subjectId
     * @return
     * @author zhangzd
     * @ApiDocMethod
     * @ApiCode
     */
    GnSubject getGnSubject(String tenantId,long subjectId);
    /**
     * 当前租户下 不包含当前科目编号 科目名称为当前名称的信息
     * 如果为true那么就是存在 不能添加
     * @param tenantId
     * @param subjectId
     * @param subjectName
     * @return
     * @author zhangzd
     * @ApiDocMethod
     * @ApiCode
     */
    public int getGnSubjectName(String tenantId,long subjectId,String subjectName);

    List<GnSubject> getGnSubjectListRelated(String tenantId,List<Long> subjectIdList);
}
