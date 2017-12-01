package com.ai.opt.sys.dao.mapper.interfaces;

import com.ai.opt.sys.dao.mapper.bo.GnFunc;
import com.ai.opt.sys.dao.mapper.bo.GnFuncCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GnFuncMapper {
    int countByExample(GnFuncCriteria example);

    int deleteByExample(GnFuncCriteria example);

    int deleteByPrimaryKey(long funcId);

    int insert(GnFunc record);

    int insertSelective(GnFunc record);

    List<GnFunc> selectByExample(GnFuncCriteria example);

    GnFunc selectByPrimaryKey(long funcId);

    int updateByExampleSelective(@Param("record") GnFunc record, @Param("example") GnFuncCriteria example);

    int updateByExample(@Param("record") GnFunc record, @Param("example") GnFuncCriteria example);

    int updateByPrimaryKeySelective(GnFunc record);

    int updateByPrimaryKey(GnFunc record);
}