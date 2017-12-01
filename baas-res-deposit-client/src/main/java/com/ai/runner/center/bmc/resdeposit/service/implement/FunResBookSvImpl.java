package com.ai.runner.center.bmc.resdeposit.service.implement;

import com.ai.baas.dshm.client.CacheFactoryUtil;
import com.ai.baas.dshm.client.impl.CacheBLMapper;
import com.ai.baas.dshm.client.impl.DshmClient;
import com.ai.baas.dshm.client.interfaces.IDshmClient;
import com.ai.opt.sdk.util.StringUtil;
import com.ai.paas.ipaas.mcs.interfaces.ICacheClient;
import com.ai.runner.center.bmc.resdeposit.constants.TableConstants;
import com.ai.runner.center.bmc.resdeposit.constants.TableConstants.PackageInfo;
import com.ai.runner.center.bmc.resdeposit.constants.TableConstants.PriceDetail;
import com.ai.runner.center.bmc.resdeposit.constants.TableConstants.PriceInfo;
import com.ai.runner.center.bmc.resdeposit.constants.TableConstants.SubsComm;
import com.ai.runner.center.bmc.resdeposit.service.interfaces.IBlUserinfoSV;
import com.ai.runner.center.bmc.resdeposit.service.interfaces.IFunResBookSv;
import com.ai.runner.center.bmc.resdeposit.vo.CommMsg;
import com.ai.runner.center.bmc.resdeposit.vo.FunResBook;
import com.ai.runner.center.bmc.resdeposit.vo.UserMsg;
import net.sf.json.JSONArray;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class FunResBookSvImpl implements IFunResBookSv {
	
	@Autowired
    private transient IBlUserinfoSV blUserinfoSV;
	
	public static Logger log = LogManager.getLogger(FunResBookSvImpl.class);

    @Override
    public List<FunResBook> getFunResBook(UserMsg user) {
        // ----------------死数据------------------------
        // List<FunResBook> testList = new ArrayList<>();
        // FunResBook f = new FunResBook();
        // f.setSYSTEM_ID("1");
        // f.setSUBS_ID(user.getSUBS_ID());
        // f.setTENANT_ID(user.getTENANT_ID());
        // f.setPRODUCT_ID("123");
        // f.setSUBS_PRODUCT_ID("233");
        // f.setACTIVE_TIME("2016-01-02 11:22:33");
        // f.setINACTIVE_TIME("2022-01-02 11:22:33");
        // f.setRES_CLEAR_FLAG("y");
        // f.setRES_BONUS_FLAG("y");
        // testList.add(f);
        // if(!testList.isEmpty()){
        // return testList;
        // }
        // ----------------end--------------------------
        
//        IdshmreadSV dshmread = DshmUtil.getDshmread();
        IDshmClient client=new DshmClient();
        
        List<FunResBook> bookList = new ArrayList<>();
        Map<String, String> params = new TreeMap<String, String>();
        params.put(SubsComm.SUBS_ID, user.getSUBS_ID());
        params.put(SubsComm.TENANT_ID, user.getTENANT_ID());
        
//        List<Map<String, String>> result = dshmread.list(TableConstants.SUBS_COMM).where(params)
//                .executeQuery();
        
        ICacheClient cacheClient =  CacheFactoryUtil
                .getCacheClient(CacheBLMapper.CACHE_BL_CAL_PARAM);
        List<Map<String, String>> results=client.list(TableConstants.SUBS_COMM)
                .where(params)
                .executeQuery(cacheClient);
        
        for (Map<String, String> r : results) {
            bookList.addAll(packFunResBook(r));
        }
        return bookList;
    }

    @Override
    public List<FunResBook> getFunResBook(CommMsg comm) {
        // ----------------死数据------------------------
        // List<FunResBook> testList = new ArrayList<>();
        // FunResBook f = new FunResBook();
        // f.setSYSTEM_ID("1");
        // f.setSUBS_ID(comm.getSUBS_ID());
        // f.setTENANT_ID(comm.getTENANT_ID());
        // f.setPRODUCT_ID(comm.getPRODUCT_ID());
        // f.setSUBS_PRODUCT_ID(comm.getSUBS_PRODUCT_ID());
        // f.setACTIVE_TIME(comm.getACTIVE_TIME());
        // f.setINACTIVE_TIME(comm.getINACTIVE_TIME());
        // f.setRES_CLEAR_FLAG("y");
        // f.setRES_BONUS_FLAG("y");
        // testList.add(f);
        // if(!testList.isEmpty()){
        // return testList;
        // }
        // ----------------end--------------------------
        IDshmClient client=new DshmClient();
        
        List<FunResBook> bookList = new ArrayList<>();
        Map<String, String> params = new TreeMap<String, String>();
        params.put(SubsComm.SUBS_ID, comm.getSUBS_ID());
        params.put(SubsComm.TENANT_ID, comm.getTENANT_ID());
        params.put(SubsComm.PRODUCT_ID, comm.getPRODUCT_ID());
        params.put(SubsComm.SUBS_PRODUCT_ID, comm.getSUBS_PRODUCT_ID());
//        List<Map<String, String>> result = dshmread.list(TableConstants.SUBS_COMM).where(params)
//                .executeQuery();
        ICacheClient cacheClient =  CacheFactoryUtil
                .getCacheClient(CacheBLMapper.CACHE_BL_CAL_PARAM);
        List<Map<String, String>> results=client.list(TableConstants.SUBS_COMM)
                .where(params)
                .executeQuery(cacheClient);
        
        log.debug("缓存中订购信息【{}】",JSONArray.fromObject(results).toString());
        for (Map<String, String> r : results) {
            bookList.addAll(packFunResBook(r));
        }
        return bookList;
    }

    // 将map封装成FunResBook对象
    private List<FunResBook> packFunResBook(Map<String, String> result) {
        List<FunResBook> bookList = new ArrayList<>();
        FunResBook book = null;
        String[] subsIds = !StringUtil.isBlank(result.get(SubsComm.SUBS_ID))?result.get(SubsComm.SUBS_ID).split("#"):new String[0];
        String[] tenantIds = !StringUtil.isBlank(result.get(SubsComm.TENANT_ID))?result.get(SubsComm.TENANT_ID).split("#"):new String[0];
        String[] productIds = !StringUtil.isBlank(result.get(SubsComm.PRODUCT_ID))?result.get(SubsComm.PRODUCT_ID).split("#"):new String[0];
        String[] subsProductIds = !StringUtil.isBlank(result.get(SubsComm.SUBS_PRODUCT_ID))?result.get(SubsComm.SUBS_PRODUCT_ID).split("#"):new String[0];
        String[] activeTimes = !StringUtil.isBlank(result.get(SubsComm.ACTIVE_TIME))?result.get(SubsComm.ACTIVE_TIME).split("#"):new String[0];
        String[] inactiveTimes = !StringUtil.isBlank(result.get(SubsComm.INACTIVE_TIME))?result.get(SubsComm.INACTIVE_TIME).split("#"):new String[0];
        String[] resBonusFlag = !StringUtil.isBlank(result.get(SubsComm.RES_BONUS_FLAG))?result.get(SubsComm.RES_BONUS_FLAG).split("#"):new String[0];
        String[] resClearFlag = null;
     // 获得所有字段最大长度size(只需比较subsIds、productIds和subsProductIds的长度)
        int size = subsIds.length > productIds.length ? subsIds.length : productIds.length;
        size = size > subsProductIds.length ? size : subsProductIds.length;
        
        //清零标识要从扩展表里获取     【有问题】
        
        IDshmClient client=new DshmClient();
        Map<String, String> params = new TreeMap<String, String>();
//        params.put(SubsComm.SUBS_ID, subsIds[0]);
        params.put(SubsComm.PRODUCT_ID, productIds[0]);
        params.put(SubsComm.SUBS_PRODUCT_ID, subsProductIds[0]);
        params.put(SubsComm.EXT_NAME, "res_clear_flag");
        ICacheClient cacheClient =  CacheFactoryUtil
                .getCacheClient(CacheBLMapper.CACHE_BL_CAL_PARAM);
        List<Map<String, String>> exts=client.list("bl_subscomm_ext")
                .where(params)
                .executeQuery(cacheClient);
        if(exts.size()!=0){
            System.err.println(com.alibaba.fastjson.JSONObject.toJSONString(exts));
            resClearFlag = exts.get(0).get("ext_value").split("#");
        }
        
       
        //根据用户查询出对应的运营商标识      【修改】
        List<String> ids = new ArrayList<String>();
        Collections.addAll(ids, subsIds);
        //用户信息在下边并没有用到暂时
//        List<BlUserinfo> infos = blUserinfoSV.querUserinfos(tenantIds[0],ids);
//        log.debug("size【{}】,用户信息【{}】。",size,JSONArray.fromObject(infos).toString());
//        Map<String, BlUserinfo> infoMap = new HashMap<String, BlUserinfo>();
//        if (!CollectionUtil.isEmpty(infos)) {
//			infoMap = index(infos, on(BlUserinfo.class).getSubsId());
//		}
        for (int i = 0; i < size; i++) {
            // 将结果包装成CommMsg类，并加入集合当中
            book = new FunResBook();
//            book.setSYSTEM_ID(getValue(systemIds, i));
            String subsId = getValue(subsIds, i);
            log.debug("subsid=【{}】",subsId);
            book.setSUBS_ID(subsId);
            book.setTENANT_ID(getValue(tenantIds, i));
            book.setPRODUCT_ID(getValue(productIds, i));
            book.setSUBS_PRODUCT_ID(getValue(subsProductIds, i));
            book.setACTIVE_TIME(getValue(activeTimes, i));
            book.setINACTIVE_TIME(getValue(inactiveTimes, i));
            if(resClearFlag!=null&&resClearFlag.length>0){
                book.setRES_CLEAR_FLAG(getValue(resClearFlag, i));
            }
            if(resBonusFlag!=null&&resBonusFlag.length>0){
                book.setRES_BONUS_FLAG(getValue(resBonusFlag, i));
            }

            String basicOrgId = getBasicOrgIdValue(subsId, productIds,subsProductIds);
            log.debug("用户【{}】运营商为【{}】",subsId,basicOrgId);
            book.setBasicOrgId(basicOrgId);
            
            bookList.add(book);
        }
        return bookList;
    }

    // 获得item数组的第i个元素，如果超出，则选出最后一个
    private String getValue(String[] item, int index) {
        return item[index >= item.length ? item.length - 1 : index];
    }
    
 // 获得item数组的第i个元素，如果超出，则选出最后一个
    private String getBasicOrgIdValue(String subsId, String [] ProductIds,String [] subsProductIds) {
//        if (!CollectionUtil.isEmpty(infos)&&!StringUtil.isBlank(subsId)) {
//        	for (int i = 0; i < infos.size(); i++) {
//        		BlUserinfo info = infos.get(i);
//        		if (info!=null) {
//        			if ((!StringUtil.isBlank(info.getSubsId()))&&(info.getSubsId().equals(subsId))) {
        			    
        		    IDshmClient client=new DshmClient();
        		    Map<String, String> params = new TreeMap<String, String>();
        	        params.put("ext_name","basic_org_id" );
        	        params.put("subs_Id", subsId);

        	        ICacheClient cacheClient =  CacheFactoryUtil
        	                .getCacheClient(CacheBLMapper.CACHE_BL_CAL_PARAM);
        	        List<Map<String, String>> exts=client.list("bl_userinfo_ext")
        	                .where(params)
        	                .executeQuery(cacheClient);
        		    if(exts.size()!=0){
        		        return exts.get(0).get("ext_value");
        		    }
        			return "";
//					}
//    				continue;
//    			}
//			}
//		}
//        return "";
    }

    @Override
    public String getAmount(String product_id, String charge_type, String tenant_id) {
        // -----------------死数据----------------------
        // if(!StringUtil.isBlank(charge_type)){
        // return "1";
        // }
        // -------------------end---------------------
//        IdshmreadSV dshmread = DshmUtil.getDshmread();
        
        IDshmClient client=new DshmClient();
        
        Map<String, String> params = new TreeMap<String, String>();
        params.put(PriceDetail.PRICE_CODE, product_id);
        params.put(PriceDetail.CHARGE_TYPE, charge_type);       
//        List<Map<String, String>> result = dshmread.list(TableConstants.PRICE_DETAIL).where(params)
//                .executeQuery();
        
        ICacheClient cacheClient =  CacheFactoryUtil
                .getCacheClient(CacheBLMapper.CACHE_BL_CAL_PARAM);
        List<Map<String, String>> results=client.list(TableConstants.PRICE_DETAIL)
                .where(params)
                .executeQuery(cacheClient);
//        System.out.println(result);
        if (results.isEmpty()) {
            return null;
        }
        // String detail_code = result.get(0).get(PriceDetail.DETAIL_CODE);
        String[] temp = results.get(0).get(PriceDetail.DETAIL_CODE).split("#");
        String detail_code = temp[temp.length - 1];
        results.clear();
        if (StringUtil.isBlank(detail_code)) {
            return null;
        }
        params.clear();
        params.put(PackageInfo.DETAIL_CODE, detail_code);
        // 【车辆网 流量】
        params.put(PackageInfo.SERVICE_TYPE, "STREAM");
        results = client.list(TableConstants.PACKAGE_INFO).where(params).executeQuery(cacheClient);
        
        return results.isEmpty() ? null : results.get(0).get(PackageInfo.AMOUNT);
    }

    @Override
    public String getProductType(String product_id,String tenant_id) {
//        IdshmreadSV dshmread = DshmUtil.getDshmread();
        IDshmClient client=new DshmClient();
        
        Map<String, String> params = new TreeMap<String, String>();
        params.put(PriceInfo.PRICE_CODE, product_id);
        params.put(PriceInfo.TENANT_ID, tenant_id);
//        List<Map<String, String>> result = dshmread.list(TableConstants.PRICE_INFO).where(params)
//                .executeQuery();
        
        ICacheClient cacheClient =  CacheFactoryUtil
                .getCacheClient(CacheBLMapper.CACHE_BL_CAL_PARAM);
        List<Map<String, String>> results=client.list(TableConstants.PRICE_INFO)
                .where(params)
                .executeQuery(cacheClient);
//        System.out.println(result);
        String[] temp = results.get(0).get(PriceInfo.PRODUCT_TYPE).split("#");
        String priceType = temp[temp.length - 1];
        return priceType;
    }
}
