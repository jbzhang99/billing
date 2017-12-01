package com.ai.baas.amc.service.business.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ai.baas.bmc.api.queryidinfo.interfaces.IQueryIdInfoSV;
import com.ai.baas.bmc.api.queryidinfo.params.AcctIdInfo;
import com.ai.baas.bmc.api.queryidinfo.params.BlCustinfoResponse;
import org.springframework.stereotype.Service;

import com.ai.baas.amc.api.queryinoutfundserial.params.FundSerialInfo;
import com.ai.baas.amc.api.queryinoutfundserial.params.QueryFundSerialRequest;
import com.ai.baas.amc.dao.mapper.bo.AmcFundSerial;
import com.ai.baas.amc.dao.mapper.bo.AmcFundSerialCriteria;
import com.ai.baas.amc.dao.mapper.factory.MapperFactory;
import com.ai.baas.amc.dao.mapper.interfaces.AmcFundSerialMapper;
import com.ai.baas.amc.service.business.interfaces.IqueryFundSerialBusiSV;
import com.ai.baas.bmc.api.baseInfo.interfaces.IBaseInfoSV;
import com.ai.baas.bmc.api.baseInfo.params.BaseCode;
import com.ai.baas.bmc.api.baseInfo.params.BaseCodeInfo;
import com.ai.baas.bmc.api.baseInfo.params.QueryInfoParams;
import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.PageInfo;
import com.ai.opt.sdk.dubbo.util.DubboConsumerFactory;
import com.ai.opt.sdk.util.BeanUtils;
import com.ai.opt.sdk.util.CollectionUtil;
import com.ai.opt.sdk.util.DateUtil;
import com.ai.opt.sdk.util.StringUtil;
import com.ai.opt.sys.api.citicrestcommon.interfaces.ICiticRestReqWrapperSV;
import com.ai.opt.sys.api.citicrestcommon.param.OrgQueryResponse;
import com.ai.opt.sys.api.citicrestcommon.param.OrgQueryVo;

@Service
public class QueryFundSerialBusiSVImpl implements IqueryFundSerialBusiSV {

    @Override
    public PageInfo<FundSerialInfo> queryFundSerialList(
            QueryFundSerialRequest queryFundSerialRequest) throws BusinessException,
            SystemException {
        ICiticRestReqWrapperSV iCiticRestReqWrapperSV = DubboConsumerFactory
                .getService(ICiticRestReqWrapperSV.class);
        List<FundSerialInfo> fundSerialInfos = null;
        Map<String, String> translate = null;
        AmcFundSerialCriteria amcFundSerialCriteria = new AmcFundSerialCriteria();
        AmcFundSerialCriteria.Criteria criteria = amcFundSerialCriteria.createCriteria();
        String orderByClause = "pay_time desc";
        amcFundSerialCriteria.setOrderByClause(orderByClause);
        criteria.andTenantIdEqualTo(queryFundSerialRequest.getTenantId());
        if (!CollectionUtil.isEmpty(queryFundSerialRequest.getAcctIds())) {
            criteria.andAcctId1In(queryFundSerialRequest.getAcctIds());
        }
        if (!StringUtil.isBlank(queryFundSerialRequest.getOptType())) {
            criteria.andOptTypeEqualTo(queryFundSerialRequest.getOptType());
        }
        if (!StringUtil.isBlank(queryFundSerialRequest.getBeginTime())) {
            criteria.andPayTimeGreaterThanOrEqualTo(Timestamp.valueOf(queryFundSerialRequest.getBeginTime()));
        }
        if (!StringUtil.isBlank(queryFundSerialRequest.getEndTime())) {
            criteria.andPayTimeLessThanOrEqualTo(Timestamp.valueOf(queryFundSerialRequest.getEndTime()));
        }
        AmcFundSerialMapper mapper = MapperFactory.getAmcFundSerialMapper();
        PageInfo<FundSerialInfo> pageInfo = new PageInfo<FundSerialInfo>();
        pageInfo.setCount(mapper.countByExample(amcFundSerialCriteria));
        if (queryFundSerialRequest.getPageInfo() == null) {
            pageInfo.setPageNo(1);
            pageInfo.setPageSize(pageInfo.getPageSize() == null ? 10 : pageInfo.getPageSize());
        } else {
            pageInfo.setPageNo(queryFundSerialRequest.getPageInfo().getPageNo());
            pageInfo.setPageSize(queryFundSerialRequest.getPageInfo().getPageSize() == null ? 10
                    : queryFundSerialRequest.getPageInfo().getPageSize());
            amcFundSerialCriteria.setLimitStart(queryFundSerialRequest.getPageInfo()
                    .getStartRowIndex());
            amcFundSerialCriteria.setLimitEnd(queryFundSerialRequest.getPageInfo().getPageSize());

        }
        List<AmcFundSerial> amcFundSerials = mapper.selectByExample(amcFundSerialCriteria);
        if (!CollectionUtil.isEmpty(amcFundSerials)) {
            translate = getTranslate("BAAS-CITIC", "OPT_TYPE");
            fundSerialInfos = new ArrayList<FundSerialInfo>();
            if (!StringUtil.isBlank(queryFundSerialRequest.getExtCustId())) {
                OrgQueryVo queryVo = new OrgQueryVo();
                queryVo.setSelectId(queryFundSerialRequest.getExtCustId());
                queryVo.setSelectType("3");
                OrgQueryResponse searchOrg = iCiticRestReqWrapperSV.searchOrg(queryVo);
                for (int i = 0; i < amcFundSerials.size(); i++) {
                    FundSerialInfo fundSerialInfo = new FundSerialInfo();
                    BeanUtils.copyVO(fundSerialInfo, amcFundSerials.get(i));
                    fundSerialInfo.setOptType(translate.get(amcFundSerials.get(i).getOptType()));
                    if (searchOrg != null&&!CollectionUtil.isEmpty(searchOrg.getOrgs())) {
                        fundSerialInfo.setTenantName(searchOrg.getOrgs().get(0).getName());
                        fundSerialInfo.setBandSerialCode(searchOrg.getOrgs().get(0).getBankAccount());
                    }
                    fundSerialInfos.add(fundSerialInfo);
                }
            }else{
                IQueryIdInfoSV iQueryIdInfoSV = DubboConsumerFactory.getService(IQueryIdInfoSV.class);
                for (int i = 0; i < amcFundSerials.size(); i++) {
                    FundSerialInfo fundSerialInfo = new FundSerialInfo();
                    BeanUtils.copyVO(fundSerialInfo, amcFundSerials.get(i));
                    fundSerialInfo.setOptType(translate.get(amcFundSerials.get(i).getOptType()));
                    // fundSerialInfo.setOptType(amcFundSerials.get(i).getOptType().equals(1) ? "存入"
                    // : "扣款");
                    if (!StringUtil.isBlank(amcFundSerials.get(i).getAcctId1())) {
                        AcctIdInfo acctIdInfo = new AcctIdInfo();
                        acctIdInfo.setTenantId(queryFundSerialRequest.getTenantId());
                        acctIdInfo.setAcctId(amcFundSerials.get(i).getAcctId1());
                        BlCustinfoResponse blCustinfoResponse = iQueryIdInfoSV.queryExtCustIdByAcctId(acctIdInfo);
                        // 调用中信rest根据id查询租户名称和银行流水号
                        if (!CollectionUtil.isEmpty(blCustinfoResponse.getBlCustinfoInfos())) {
                            fundSerialInfo.setExtCustId(blCustinfoResponse.getBlCustinfoInfos().get(0)
                                    .getExtCustId());
                            OrgQueryVo queryVo = new OrgQueryVo();
                            queryVo.setSelectId(fundSerialInfo.getExtCustId());
                            queryVo.setSelectType("3");
                            OrgQueryResponse searchOrg = iCiticRestReqWrapperSV.searchOrg(queryVo);
                            if (searchOrg != null&&!CollectionUtil.isEmpty(searchOrg.getOrgs())) {
                                fundSerialInfo.setTenantName(searchOrg.getOrgs().get(0).getName());
                                fundSerialInfo.setBandSerialCode(searchOrg.getOrgs().get(0).getBankAccount());
                            }
                        }
                    }
                    fundSerialInfos.add(fundSerialInfo);
                }
            }
            if(!CollectionUtil.isEmpty(fundSerialInfos)){
                pageInfo.setPageNo(pageInfo.getPageNo() == null?1:pageInfo.getPageNo());
                pageInfo.setPageSize(pageInfo.getPageSize() == null ? 10 : pageInfo.getPageSize());
                pageInfo.setPageCount((pageInfo.getCount()+pageInfo.getPageSize()-1)/pageInfo.getPageSize());
                pageInfo.setResult(fundSerialInfos);
            }else {
                return null;
            }
        } else {
            return null;
        }

        return pageInfo;
    }

    Map<String, String> getTranslate(String tenantId, String paramType) {
        Map<String, String> result = new HashMap<String, String>();
        IBaseInfoSV iBaseInfoSV = DubboConsumerFactory.getService("iBaseInfoSV");

        QueryInfoParams queryInfo = new QueryInfoParams();
        queryInfo.setTenantId(tenantId);
        queryInfo.setParamType(paramType); // yyyyMMddHHmmssS
        String date = DateUtil.getDateString(new Date(), "YYYYMMDDHHmmSS");
        queryInfo.setTradeSeq("BAAS-CITIC" + date);
        queryInfo.setTradeSeq(tenantId + date);
        BaseCodeInfo resultInfo = iBaseInfoSV.getBaseInfo(queryInfo);
        if (resultInfo == null) {
            throw new BusinessException("租户id：" + tenantId + "paramType:" + paramType
                    + "在缓存表中对应的数据为空，请配置缓存表并刷新缓存");
        }
        List<BaseCode> baseList = resultInfo.getParamList();
        for (int i = 0; i < baseList.size(); i++) {
            result.put(baseList.get(i).getParamCode(), baseList.get(i).getParamName());
        }
        return result;
    }
}