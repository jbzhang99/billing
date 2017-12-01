package com.ai.runner.center.bmc.resdeposit.service.implement;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.ai.baas.dshm.client.CacheFactoryUtil;
import com.ai.baas.dshm.client.impl.CacheBLMapper;
import com.ai.baas.dshm.client.impl.DshmClient;
import com.ai.baas.dshm.client.interfaces.IDshmClient;
import com.ai.opt.sdk.util.CollectionUtil;
import com.ai.paas.ipaas.mcs.interfaces.ICacheClient;
import com.ai.runner.center.bmc.resdeposit.constants.TableConstants;
import com.ai.runner.center.bmc.resdeposit.dao.factory.MapperFactory;
import com.ai.runner.center.bmc.resdeposit.dao.mapper.bo.BlUserinfo;
import com.ai.runner.center.bmc.resdeposit.dao.mapper.bo.BlUserinfoCriteria;
import com.ai.runner.center.bmc.resdeposit.dao.mapper.bo.BlUserinfoCriteria.Criteria;
import com.ai.runner.center.bmc.resdeposit.service.interfaces.IBlUserinfoSV;
import com.ai.runner.center.bmc.resdeposit.util.MyJsonUtil;
import com.ai.runner.center.bmc.resdeposit.vo.UserMsg;
import com.alibaba.fastjson.JSONObject;

import org.springframework.stereotype.Service;

/**
 * Date: 2016年5月4日 <br>
 * 
 * @author zhoushanbin Copyright (c) 2016 asiainfo.com <br>
 */
@Service
public class BlUserinfoSVImpl implements IBlUserinfoSV {

	@SuppressWarnings("unchecked")
	@Override
	public List<BlUserinfo> querUserinfos(String tenantId , List<String> subsIds) {
	    
//		if (!CollectionUtil.isEmpty(subsIds)) {
//			BlUserinfoCriteria blUserinfoCriteria = new BlUserinfoCriteria();
//			Criteria criteria = blUserinfoCriteria.createCriteria();
//			criteria.andTenantIdEqualTo(tenantId);
//			criteria.andSubsIdEqualTo(subsIds.get(0));
//			Timestamp now = DateUtil.getSysDate();
//			//生效的用户
//			criteria.andActiveTimeLessThanOrEqualTo(now);
//			criteria.andInactiveTimeGreaterThan(now);
//			return MapperFactory.getBlUserinfoMapper().selectByExample(
//					blUserinfoCriteria);
//		}
		
		if (!CollectionUtil.isEmpty(subsIds)) {
    		DshmClient client=new DshmClient();
            Map<String, String> params = new TreeMap<String, String>();
            params.put("tenant_id",tenantId );
            params.put("subs_Id", subsIds.get(0));
            ICacheClient cacheClient =  CacheFactoryUtil
                    .getCacheClient(CacheBLMapper.CACHE_BL_CAL_PARAM);
            List<Map<String, String>> exts=client.list("bl_userinfo")
                    .where(params)
                    .executeQuery(cacheClient);
            if(exts != null){
                System.err.println("格式转换"); 
                BlUserinfo bl = MyJsonUtil.toBean(exts.get(0).get("ext_value"),BlUserinfo.class);
                System.err.println(JSONObject.toJSONString(bl));
                List<BlUserinfo> blList = new ArrayList<>();
                blList.add(bl);
                return blList;
            }
		}
        return Collections.EMPTY_LIST;
	}




}
