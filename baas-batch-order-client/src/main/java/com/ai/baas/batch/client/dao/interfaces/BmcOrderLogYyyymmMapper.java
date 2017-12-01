package com.ai.baas.batch.client.dao.interfaces;

import com.ai.baas.batch.client.dao.mapper.bo.BmcOrderLogYyyymm;
import com.ai.baas.batch.client.dao.mapper.bo.BmcOrderLogYyyymmCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BmcOrderLogYyyymmMapper {
	
	    int countByExample(BmcOrderLogYyyymmCriteria example);

	    int deleteByExample(BmcOrderLogYyyymmCriteria example);

	    int insert(@Param("currentMonth") String currentMonth,@Param("record")BmcOrderLogYyyymm record);

	    int insertSelective(@Param("currentMonth") String currentMonth,@Param("record")BmcOrderLogYyyymm record);

	    List<BmcOrderLogYyyymm> selectByExample(@Param("currentMonth") String currentMonth,BmcOrderLogYyyymmCriteria example);

	    int updateByExampleSelective(@Param("currentMonth") String currentMonth,@Param("record") BmcOrderLogYyyymm record, @Param("example") BmcOrderLogYyyymmCriteria example);

	    int updateByExample(@Param("currentMonth") String currentMonth,@Param("record") BmcOrderLogYyyymm record, @Param("example") BmcOrderLogYyyymmCriteria example);
}