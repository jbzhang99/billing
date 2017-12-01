package com.ai.opt.sys.dao.mapper.interfaces;

import com.ai.opt.sys.dao.mapper.bo.GnRoleFuncRel;
import com.ai.opt.sys.dao.mapper.bo.GnRoleFuncRelCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GnRoleFuncRelMapper {
    int countByExample(GnRoleFuncRelCriteria example);

    int deleteByExample(GnRoleFuncRelCriteria example);

    int deleteByPrimaryKey(long roleFuncRelId);

    int insert(GnRoleFuncRel record);

    int insertSelective(GnRoleFuncRel record);

    List<GnRoleFuncRel> selectByExample(GnRoleFuncRelCriteria example);

    GnRoleFuncRel selectByPrimaryKey(long roleFuncRelId);

    int updateByExampleSelective(@Param("record") GnRoleFuncRel record, @Param("example") GnRoleFuncRelCriteria example);

    int updateByExample(@Param("record") GnRoleFuncRel record, @Param("example") GnRoleFuncRelCriteria example);

    int updateByPrimaryKeySelective(GnRoleFuncRel record);

    int updateByPrimaryKey(GnRoleFuncRel record);
}