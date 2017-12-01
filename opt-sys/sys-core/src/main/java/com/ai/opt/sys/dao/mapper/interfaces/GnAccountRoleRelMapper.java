package com.ai.opt.sys.dao.mapper.interfaces;

import com.ai.opt.sys.dao.mapper.bo.GnAccountRoleRel;
import com.ai.opt.sys.dao.mapper.bo.GnAccountRoleRelCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GnAccountRoleRelMapper {
    int countByExample(GnAccountRoleRelCriteria example);

    int deleteByExample(GnAccountRoleRelCriteria example);

    int insert(GnAccountRoleRel record);

    int insertSelective(GnAccountRoleRel record);

    List<GnAccountRoleRel> selectByExample(GnAccountRoleRelCriteria example);

    int updateByExampleSelective(@Param("record") GnAccountRoleRel record, @Param("example") GnAccountRoleRelCriteria example);

    int updateByExample(@Param("record") GnAccountRoleRel record, @Param("example") GnAccountRoleRelCriteria example);
}