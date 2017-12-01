package com.ai.baas.bmc.service.business.impl;


import com.ai.baas.bmc.api.detailsquery.params.DetailVo;
import com.ai.baas.bmc.api.detailsquery.params.DetailsQueryRequest;
import com.ai.baas.bmc.constants.BmcConstants;
import com.ai.baas.bmc.dao.mapper.bo.BmcOutputInfo;
import com.ai.baas.bmc.dao.mapper.bo.CiticDrRecord;
import com.ai.baas.bmc.service.atom.interfaces.IBmcOutputInfoAtomSV;
import com.ai.baas.bmc.service.atom.interfaces.IDetailsQueryAtomSV;
import com.ai.baas.bmc.service.business.interfaces.IDetailsQueryBusiness;
import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.sdk.util.BeanUtils;
import com.ai.opt.sdk.util.CollectionUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class DetailsQueryBusinessImpl implements IDetailsQueryBusiness{
    private static final Logger LOGGER = Logger.getLogger(DetailsQueryBusinessImpl.class);
    @Autowired
    private IBmcOutputInfoAtomSV iBmcOutputInfoAtomSV;

    @Autowired
    private IDetailsQueryAtomSV detailsQueryAtomSV;

    @Override
    public List<DetailVo>  getDetails(DetailsQueryRequest request) {
        List<DetailVo> details = null;
        List<BmcOutputInfo> list=iBmcOutputInfoAtomSV.getOutputInfo(request.getTenantId(), "DR");
        if(!CollectionUtil.isEmpty(list)){
            String searchTime = request.getStartDate().substring(0,6);
            for (BmcOutputInfo info:list){
                if(info.getServiceType().equalsIgnoreCase(request.getServiceType())){
                    String tableName=request.getTenantId()+"_"+info.getServiceType()+"_"+"DR"+"_"+searchTime;
                    if(detailsQueryAtomSV.getTableNum(tableName.toLowerCase())>0){
                        if(BmcConstants.OutputType.OUTPUT_TYPE_MYSQL.equals(info.getOutputType())){
                            List<CiticDrRecord> drDetails = detailsQueryAtomSV.getDrDetails(tableName.toLowerCase(), request);
                            if (!CollectionUtil.isEmpty(drDetails)){
                                details = new ArrayList<>();
                                for(CiticDrRecord citicDrRecord:drDetails){
                                    DetailVo vo = new DetailVo();
                                    BeanUtils.copyProperties(vo,citicDrRecord);
                                    Double v = Double.parseDouble(citicDrRecord.getFee1());
                                    vo.setFee(v.longValue());
                                    vo.setSubject(citicDrRecord.getSubject1());
                                    details.add(vo);
                                }
                                return details;
                            }
                        }else if(BmcConstants.OutputType.OUTPUT_TYPE_HBASE.equals(info.getOutputType())){
                            LOGGER.error("暂不支持存储至"+request.getServiceType()+"的详单查询");
                            throw new BusinessException("暂不支持存储至"+request.getServiceType()+"的详单查询");
                        }else{
                            LOGGER.error("不支持的详单存储类型查询："+request.getServiceType());
                            throw new BusinessException("不支持的详单存储类型查询："+request.getServiceType());
                        }
                    }else{
                        LOGGER.error("表["+tableName+"]不存在，无法查询");
                        throw new BusinessException("表["+tableName+"]不存在，无法查询");
                    }
                }
            }
        }
        return details;
    }

}
