package com.ai.opt.sys.dao.mapper.interfaces;

import com.ai.opt.sys.dao.mapper.bo.GnRole;
import com.ai.opt.sys.dao.mapper.bo.GnRoleCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GnRoleMapper {
    int countByExample(GnRoleCriteria example);

    int deleteByExample(GnRoleCriteria example);

    int deleteByPrimaryKey(long roleId);

    int insert(GnRole record);

    int insertSelective(GnRole record);

    List<GnRole> selectByExample(GnRoleCriteria example);

    GnRole selectByPrimaryKey(long roleId);

    int updateByExampleSelective(@Param("record") GnRole record, @Param("example") GnRoleCriteria example);

    int updateByExample(@Param("record") GnRole record, @Param("example") GnRoleCriteria example);

    int updateByPrimaryKeySelective(GnRole record);

    int updateByPrimaryKey(GnRole record);
}