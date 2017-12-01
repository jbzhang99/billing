package com.ai.opt.sys.dao.mapper.interfaces;

import com.ai.opt.sys.dao.mapper.bo.GnSubjectFund;
import com.ai.opt.sys.dao.mapper.bo.GnSubjectFundCriteria;
import com.ai.opt.sys.dao.mapper.bo.GnSubjectFundKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GnSubjectFundMapper {
    int countByExample(GnSubjectFundCriteria example);

    int deleteByExample(GnSubjectFundCriteria example);

    int deleteByPrimaryKey(GnSubjectFundKey key);

    int insert(GnSubjectFund record);

    int insertSelective(GnSubjectFund record);

    List<GnSubjectFund> selectByExample(GnSubjectFundCriteria example);

    GnSubjectFund selectByPrimaryKey(GnSubjectFundKey key);

    int updateByExampleSelective(@Param("record") GnSubjectFund record, @Param("example") GnSubjectFundCriteria example);

    int updateByExample(@Param("record") GnSubjectFund record, @Param("example") GnSubjectFundCriteria example);

    int updateByPrimaryKeySelective(GnSubjectFund record);

    int updateByPrimaryKey(GnSubjectFund record);
}