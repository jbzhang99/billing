package com.ai.baas.collect.dao.interfaces;

import org.apache.ibatis.annotations.Param;

import com.ai.baas.collect.dao.bo.BlCollectFiles;

public interface BlCollectFilesMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BlCollectFiles record);

    int insertSelective(BlCollectFiles record);

    BlCollectFiles selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BlCollectFiles record);

    int updateByPrimaryKey(BlCollectFiles record);
    
    /**
     * 增加查询文件个数
     */
    int selectCountByName(@Param(value="tenantid")String tenantid,@Param(value="fileName")String fileName);
}