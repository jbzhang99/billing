package com.ai.baas.bmc.service.business.impl;

import com.ai.baas.bmc.api.failedbills.params.BillIdList;
import com.ai.baas.bmc.api.failedbills.params.FailedBillQueryVo;
import com.ai.baas.bmc.api.failedbills.params.FailedBillVo;
import com.ai.baas.bmc.constants.BaasConstants;
import com.ai.baas.bmc.dao.interfaces.BmcRecordFmtMapper;
import com.ai.baas.bmc.dao.mapper.bo.BmcFailureBillWithBLOBs;
import com.ai.baas.bmc.dao.mapper.bo.BmcRecordFmt;
import com.ai.baas.bmc.dao.mapper.bo.BmcRecordFmtCriteria;
import com.ai.baas.bmc.service.atom.interfaces.IBmcFailureBillAtomSV;
import com.ai.baas.bmc.service.business.interfaces.IFailedBillsBusiSV;
import com.ai.baas.bmc.util.MDSUtil;
import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.sdk.util.BeanUtils;
import com.ai.opt.sdk.util.CollectionUtil;
import com.ai.opt.sdk.util.StringUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Transactional
public class FailedBillsBusiSVImpl implements IFailedBillsBusiSV{
    private static final Logger LOG = LogManager.getLogger(FailedBillsBusiSVImpl.class);
    private static final String[] PRICING_HEADER_KEY = new String[] { BaasConstants.TENANT_ID,
            BaasConstants.SERVICE_TYPE, BaasConstants.SOURCE,
            BaasConstants.BATCH_SERIAL_NUMBER, BaasConstants.SERIAL_NUMBER,
            BaasConstants.ARRIVAL_TIME, BaasConstants.ACCOUNT_PERIOD };
    public static final String FIELD_SPLIT = new String(new char[] { (char) 1 });
    public static final String PRICING_MDS_TOPIC = "baas_bmc_service_in_queue";

    @Resource
    private IBmcFailureBillAtomSV bmcFailureBillAtomSV;
    @Resource
    private BmcRecordFmtMapper bmcRecordFmtMapper;
    @Override
    public List<FailedBillVo> queryFailedBills(FailedBillQueryVo queryVo) {
        List<BmcFailureBillWithBLOBs> bmcFailureBillWithBLOBss = bmcFailureBillAtomSV.queryFailedBills(queryVo);
        List<FailedBillVo> failedBillVos = null;
        if(!CollectionUtil.isEmpty(bmcFailureBillWithBLOBss)){
            failedBillVos = new ArrayList<>(bmcFailureBillWithBLOBss.size());
            for(BmcFailureBillWithBLOBs billWithBLOBs:bmcFailureBillWithBLOBss){
                FailedBillVo billVo = new FailedBillVo();
                BeanUtils.copyProperties(billVo,billWithBLOBs);
                failedBillVos.add(billVo);
            }
        }
        return failedBillVos;
    }

    @Override
    public Integer countFailedBills(FailedBillQueryVo queryVo) {
        return bmcFailureBillAtomSV.countFailedBills(queryVo);
    }

    @Override
    public FailedBillVo getFailedBillById(Long id) {
        FailedBillVo billVo = null;
        BmcFailureBillWithBLOBs failureBill = bmcFailureBillAtomSV.getFailedBillById(id);
        if(failureBill!=null){
            billVo = new FailedBillVo();
            BeanUtils.copyProperties(billVo,failureBill);
        }
        return billVo;
    }

    @Override
    public void resendBill(FailedBillVo billVo) throws IOException {
        BmcFailureBillWithBLOBs failureBill = bmcFailureBillAtomSV.getFailedBillById(billVo.getId());
        if(failureBill!=null){
            failureBill.setFailPacket(billVo.getFailPacket());
            MDSUtil.sendMessage(PRICING_MDS_TOPIC,generateMessage(failureBill));
            bmcFailureBillAtomSV.delteFailedBillById(billVo.getId());
        }else {
            throw new BusinessException("重发编辑错单失败，原错单【"+billVo.getId()+"】不存在");
        }
    }

    @Override
    public void batchResendBill(BillIdList idList) throws IOException {
        for(Long id:idList.getBillIds()){
            BmcFailureBillWithBLOBs failureBill = bmcFailureBillAtomSV.getFailedBillById(id);
            if(failureBill!=null){
                MDSUtil.sendMessage(PRICING_MDS_TOPIC,generateMessage(failureBill));
                bmcFailureBillAtomSV.delteFailedBillById(id);
            }else {
                LOG.info("错单【"+id+"】不存在，无法重发");
            }
        }
    }

    @Override
    public void batchDeleteBill(BillIdList idList) {
        for(Long id:idList.getBillIds()){
            bmcFailureBillAtomSV.delteFailedBillById(id);
        }
    }

    /**
     * 重新生成报文信息
     * @param billVo
     * @return
     */
    private String generateMessage(BmcFailureBillWithBLOBs billVo) {
        StringBuilder message = new StringBuilder();
        BmcRecordFmtCriteria criteria = new BmcRecordFmtCriteria();
        criteria.createCriteria().andTenantIdEqualTo(billVo.getTenantId())
                .andServiceTypeEqualTo(billVo.getServiceType()).andSourceEqualTo(billVo.getSource());
        criteria.setOrderByClause(" field_serial ");
        List<BmcRecordFmt> recordFmts = bmcRecordFmtMapper.selectByExample(criteria);
        if(!CollectionUtil.isEmpty(recordFmts)){
            List<Field> fieldList = new ArrayList<>();
            Class clazz = billVo.getClass();
            while (clazz!=null&&!clazz.getName().toLowerCase().equals("java.lang.object")){
                fieldList.addAll(Arrays.asList(clazz.getDeclaredFields()));
                clazz = clazz.getSuperclass();
            }
            for(String key:PRICING_HEADER_KEY){
                for(Field field:fieldList){
                    field.setAccessible(true);
                    if(field.getName().equals(toCamelCase(key))){
                        try {
                            message.append(field.get(billVo)).append(FIELD_SPLIT);
                        } catch (IllegalAccessException e) {
                            throw new BusinessException(e);
                        }
                    }
                }
            }
            JSONObject packet = JSON.parseObject(billVo.getFailPacket());
            if(packet!=null){
                for (BmcRecordFmt recordFmt:recordFmts){
                    message.append(packet.getString(recordFmt.getFieldCode())).append(FIELD_SPLIT);
                }
            }
        }else{
            throw new BusinessException("["+billVo.getTenantId()+","+billVo.getServiceType()+","+billVo.getSource()+"]在bmc_record_fmt中未配置对应报文格式");
        }
        return message.toString().substring(0,message.length()-1);
    }

    /**
     * 下划线分割字符串转驼峰
     * @param source
     * @return
     */
    private String toCamelCase(String source){
        if(!StringUtil.isBlank(source)){
            if(!source.contains("_")){
                return source;
            }
            StringBuffer target = new StringBuffer();
            Matcher matcher = Pattern.compile("_[a-z]").matcher(source);
            while (matcher.find()){
                matcher.appendReplacement(target,matcher.group().replace("_","").toUpperCase());
            }
            matcher.appendTail(target);
            return target.toString();
        }
        return "";
    }
}
