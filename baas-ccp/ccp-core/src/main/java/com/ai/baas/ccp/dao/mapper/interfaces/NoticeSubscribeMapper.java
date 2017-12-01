package com.ai.baas.ccp.dao.mapper.interfaces;

import com.ai.baas.ccp.dao.mapper.bo.NoticeSubscribe;
import com.ai.baas.ccp.dao.mapper.bo.NoticeSubscribeCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface NoticeSubscribeMapper {
    int countByExample(NoticeSubscribeCriteria example);

    int deleteByExample(NoticeSubscribeCriteria example);

    int deleteByPrimaryKey(String subscribeId);

    int insert(NoticeSubscribe record);

    int insertSelective(NoticeSubscribe record);

    List<NoticeSubscribe> selectByExample(NoticeSubscribeCriteria example);

    NoticeSubscribe selectByPrimaryKey(String subscribeId);

    int updateByExampleSelective(@Param("record") NoticeSubscribe record, @Param("example") NoticeSubscribeCriteria example);

    int updateByExample(@Param("record") NoticeSubscribe record, @Param("example") NoticeSubscribeCriteria example);

    int updateByPrimaryKeySelective(NoticeSubscribe record);

    int updateByPrimaryKey(NoticeSubscribe record);
}