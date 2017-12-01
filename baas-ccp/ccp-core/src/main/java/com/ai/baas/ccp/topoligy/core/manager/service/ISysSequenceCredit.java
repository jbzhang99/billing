package com.ai.baas.ccp.topoligy.core.manager.service;

import com.ai.baas.ccp.topoligy.core.exception.OmcException;

import java.util.List;


/**
 * Created by lvsj on 2015/9/27.
 */
public interface ISysSequenceCredit {
    List<Long> getSequence(String name, int nCount)  throws OmcException;
    Long getSequence(String name)  throws OmcException;
}
