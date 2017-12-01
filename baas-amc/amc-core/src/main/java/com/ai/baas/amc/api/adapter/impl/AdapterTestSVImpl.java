package com.ai.baas.amc.api.adapter.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ai.baas.amc.api.adapter.interfaces.IAdapterTestSV;
import com.ai.baas.bmc.api.baseInfo.interfaces.IBaseInfoSV;
import com.ai.baas.bmc.api.baseInfo.params.BaseCode;
import com.ai.baas.bmc.api.baseInfo.params.BaseCodeInfo;
import com.ai.baas.bmc.api.baseInfo.params.QueryInfoParams;
import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.sdk.dubbo.util.DubboConsumerFactory;
import com.alibaba.dubbo.config.annotation.Service;
@Service
@Component
public class AdapterTestSVImpl implements IAdapterTestSV {

    @Override
    public String orders(String vo) throws BusinessException, SystemException {
        String data = "{\"orders\":[{\"id\":\"6ba76566-0251-4e1a-a188-64e1b2d26be2\",\"state_id\":\"1\",\"user_id\":\"fd634834-59a1-416d-a124-f74fb99069d9\",\"shopping_lists\":[{\"list_id\":\"3\",\"service_id\":\"57721cb62fa45f06e1c013d6\",\"parameters\":{\"RegionId\":\"cn-hangzhou\",\"Capacity\":\"2\"},\"instances\":[{\"Instance_id\":\"fbf20ccc-ef9e-4377-bbe1-7c56bd6b996c\",\"use\":\"vm\",\"app_base_line\":\"001\",\"is_deleted\":\"0\",}]}]}]}";        
        return data;
    }

    @Override
    public String costs(String vo) throws BusinessException, SystemException {
    	return "{\"costs\":[{\"cost_detail\":[{\"cost_center_id\":\"22\",\"ratio\":30},{\"cost_center_id\":\"33\",\"ratio\":70}],\"Instance_id\":\"fbf20ccc-ef9e-4377-bbe1-7c56bd6b996c\"}]}";
    }

    @Override
    public String totalBill(String vo) throws BusinessException, SystemException {
    	return "{\"bill\":[{\"date\" : \"201603\",      \"amount\": \"3000.00\"},{  \"date\" : \"201604\",      \"amount\": \"3000.00\"},{  \"date\" : \"201605\",      \"amount\": \"3000.00\"},{  \"date\" : \"201606\",      \"amount\": \"3000.00\"    }]    } ";
    }

    @Override
    public String detail(String vo) throws BusinessException, SystemException {
    	return "{\"balance\":50000}";
    }

    @Override
    public String org(String vo) throws BusinessException, SystemException {
        String ORG_QUERY_PATH_VAL = "http://10.1.130.84:10883/";
        IBaseInfoSV baseinfoSV = DubboConsumerFactory.getService(IBaseInfoSV.class);
        QueryInfoParams param = new QueryInfoParams();
        param.setParamType("ADAPTER_PARAM");
        param.setTradeSeq(System.currentTimeMillis()+"");
        BaseCodeInfo info = baseinfoSV.getBaseInfo(param);
        List<BaseCode> baseCodes = info.getParamList();
        if(baseCodes!=null){
            for(BaseCode baseCode : baseCodes){
                if("ORG_QUERY_PATH_VAL".equals(baseCode.getParamCode())){
                    ORG_QUERY_PATH_VAL = baseCode.getDefaultValue();
                }
                
            }
        }
        return "{orgs:[{\"org_id\":\"6ba76566-0251-4e1a-a188-64e1b2d26be2\",\"uri\":\""+ORG_QUERY_PATH_VAL+"\"}]}";
    }

    @Override
    public String usage(String vo) throws BusinessException, SystemException {
    	return "{\"usage_and_expenses_format\": [{      \"name\": \"tradetype3\",      \"display_name\": \"交易类型\",      \"in_list\": true      },{      \"name\": \"date3\",      \"display_name\": \"日期\",      \"in_list\": true      },{      \"name\": \"instance_id\",      \"display_name\": \"服务实例ID\",      \"in_list\": false    }],   \"usage_and_expenses_data\": [{             \"ProviderId\": \"1255207400969322\",       \"EndTime\": \"2016-07-10T09:00:00Z\",       \"InstanceId\": \"i-25m6aeon2\",       \"ImageSize\": \"21474836480\",       \"Memory\": \"1073741824\",       \"StartTime\": \"2016-07-10T08:00:00Z\",       \"TmpDataDiskSize\": \"0\",       \"SsdDataDiskSize\": \"0\",       \"NetworkOut\": \"0\",       \"ImageOS\": \"CENTOS5\",       \"CPUs\": \"1\",       \"NetworkIn\": \"0\",       \"Bandwidth\": \"0\",       \"Region\": \"cn-beijing-btc-a01\",       \"DataDiskSize\": \"0\",       \"CloudDataDiskSize\": \"0\",       \"Disk\": \"0\"   }, {\"ProviderId\": \"1255207400969322\",       \"EndTime\": \"2016-07-10T10:00:00Z\",       \"InstanceId\": \"i-25m6aeon2\",       \"ImageSize\": \"21474836480\",       \"Memory\": \"1073741824\",       \"StartTime\": \"2016-07-10T09:00:00Z\",       \"TmpDataDiskSize\": \"0\",       \"SsdDataDiskSize\": \"0\",       \"NetworkOut\": \"12\",       \"ImageOS\": \"CENTOS5\",       \"CPUs\": \"1\",       \"NetworkIn\": \"34\",       \"Bandwidth\": \"0\",       \"Region\": \"cn-beijing-btc-a01\",       \"DataDiskSize\": \"0\",       \"CloudDataDiskSize\": \"0\",       \"Disk\": \"0\"}       ]  }";
    }

    @Override
    public String catalog(String vo) throws BusinessException, SystemException {
    	return "{\"suppliers\": [{      \"id\": \"6ba76566-0251-4e1a-a188-64e1b2d26be2\",      \"name\": \"aliyun\",      \"services\": [{        \"id\": \"576206bb6ae6ca04e145958d\",        \"name\": \"aliyunecs\",        \"score\": \"85\",        \"config_options\": [{          \"name\": \"RegionID\",          \"display_name\": \"地域\",          \"default\": \"huabei1\",         }]     }]  }] }";
    }

}
