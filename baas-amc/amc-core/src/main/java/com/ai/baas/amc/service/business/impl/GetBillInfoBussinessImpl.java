//package com.ai.baas.amc.service.business.impl;
//
//import java.math.BigDecimal;
//import java.math.RoundingMode;
//import java.util.ArrayList;
//import java.util.List;
//
//import org.apache.log4j.Logger;
//import org.mybatis.spring.SqlSessionTemplate;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.ai.baas.amc.api.billQuery.params.Bill;
//
//import com.ai.baas.amc.api.billQuery.params.SubjectDetail;
//import com.ai.baas.amc.dao.mapper.bo.AmcChargeYyyydd;
//import com.ai.baas.amc.dao.mapper.bo.AmcChargeYyyyddCriteria;
//import com.ai.baas.amc.dao.mapper.bo.AmcInvoiceYyyydd;
//import com.ai.baas.amc.dao.mapper.bo.AmcInvoiceYyyyddCriteria;
//import com.ai.baas.amc.dao.mapper.interfaces.AmcChargeYyyyddMapper;
//import com.ai.baas.amc.dao.mapper.interfaces.AmcInvoiceYyyyddMapper;
//import com.ai.baas.amc.service.business.interfaces.IGetBillInfoBussinessSV;
//import com.ai.opt.sdk.util.StringUtil;
//import com.alibaba.dubbo.common.utils.CollectionUtils;
//
//@Service
//@Transactional
//public class GetBillInfoBussinessImpl implements IGetBillInfoBussinessSV{
//
//    private Logger logger = Logger.getLogger(GetBillInfoBussinessImpl.class);
//    
//    @Autowired
//    private SqlSessionTemplate sqlSessionTemplate;
//    
//    private static String tenantId = null;
//    private static Long extCustId = null;
//    private static String serviceId = null;
//    private static String paging = null;
//    private static Integer pageNumber = null;
//    private static Integer pagecountNumber = null;
//    private static String queryStartTime = null;
//    private static String queryEndTime = null;
//
//    @Override
//    public BillInfo getBillInfo(BillQueryParams billQueryParams) {
//        
//        BillInfo billInfo = new BillInfo();
//        
//        tenantId = billQueryParams.getTenantId();
//        
//        if(!StringUtil.isBlank(billQueryParams.getExtCustId())){
//        extCustId = Long.parseLong(billQueryParams.getExtCustId());
//        }
//        serviceId = billQueryParams.getServiceId();
//        paging = billQueryParams.getPaging();
//        pageNumber = billQueryParams.getPageNumber();
//        pagecountNumber = billQueryParams.getPagecountNumber();
//        queryStartTime = billQueryParams.getQueryStartTime();
//        queryEndTime = billQueryParams.getQueryEndTime();
//        
//        if(queryStartTime.length() < 6 || queryEndTime.length()<6){
//            System.err.println("时间格式错误");
//            return billInfo;
//        }
//        String startTime = queryStartTime.substring(0,6);// 开始时间只取年月
//        String endTime = queryEndTime.substring(0,6);// 结束时间只取年月
//        //分页用
//        int startIndex =0;
//        int endIndex=0;
//        
//        //判断是否支持分页查询
//        if(billQueryParams.getPaging().equals("YES")){
//        startIndex = (pageNumber - 1) * pagecountNumber;
//        endIndex = (pageNumber - 1) * pagecountNumber + pagecountNumber;
//        }
//        
//        AmcInvoiceYyyyddMapper amcInvoiceYyyyddMapper = sqlSessionTemplate.getMapper(AmcInvoiceYyyyddMapper.class);
//        AmcInvoiceYyyyddCriteria amcInvoiceYyyyddCriteria = new AmcInvoiceYyyyddCriteria();
//        AmcInvoiceYyyyddCriteria.Criteria criteria = amcInvoiceYyyyddCriteria.createCriteria()
//                .andTenantIdEqualTo(tenantId)
//                
//                
//                //.andServiceIdEqualTo(serviceId);
//                
//                .andCustIdEqualTo(extCustId);
//        if (!StringUtil.isBlank(serviceId)) {
//            criteria.andServiceIdEqualTo(serviceId);    
//        }
//        //(billList的总条数)
//        int totalCount = 0;
//        List<ServiceId> serviceIdList = new ArrayList<ServiceId>();
//        billInfo.setServiceIdList(serviceIdList);//先将链表存入返回的对象中
//        billInfo.setTotalCount(totalCount+"");
//        
//        //根据开始时间和结束时间查询所需月份表
//        List<String> yyyyddList = this.getDateList(startTime, endTime);
//        if(CollectionUtils.isEmpty(yyyyddList)){
//            System.err.println("没有合法的月份");
//            return billInfo;
//        }
//        
//        //查询月份遍历
//        for(int i = 0; i < yyyyddList.size(); i++){
//            String currentMonth = yyyyddList.get(i);
//            try{
//                //循环查找每个月份的表,获取总条数
//                int p = amcInvoiceYyyyddMapper.countByExample(currentMonth, amcInvoiceYyyyddCriteria);
//                totalCount += p;
//                System.out.println("日期："+currentMonth+"条数："+p);
//            }catch (Exception e){
//                logger.error("amcInvoiceYyyyddMapper.countByExample"+e);
//            }
//        }
//        
//        billInfo.setTotalCount(totalCount+"");
//      
//        //判断是否需要第n页，n取决于startIndex，如超出totalCount，直接返回
//        if(startIndex > (totalCount)){
//            System.err.println("startIndex > totalCount，当前页没有数据");
//            return billInfo;
//        }
//        //当前页面条数
//        int offsetBL = 0;
//        int endFlag=0;
//        
//        
//        //月份遍历
//        for (int i = 0; i < yyyyddList.size(); i++) {
//            //从yyyyddList遍历每个月份
//            String currentMonth = yyyyddList.get(i);
//            List<AmcInvoiceYyyydd> amcInvoiceYyyyddList = null;
//            try {
//                amcInvoiceYyyyddList = amcInvoiceYyyyddMapper.selectByExample(currentMonth,amcInvoiceYyyyddCriteria);
//            } catch (Exception e) {
//                logger.error(
//                        "accInvoiceYYYYMMMapper.selectByExample Exception []",
//                        e);
//            }
//            //System.out.println("accInvoiceYYYYMMList size:"+amcInvoiceYyyyddList.size());
//            if (CollectionUtils.isEmpty(amcInvoiceYyyyddList)) {
//                // 如果当前月份的账单汇总信息没有，则继续获取下个月份的
//                System.out.println(currentMonth+" 没有账单");
//                continue;
//            }
//            // 遍历当前月份的List
//            for (AmcInvoiceYyyydd aInvoice : amcInvoiceYyyyddList) {  
//                if (billQueryParams.getPaging().equals("YES")) {
//                    //若分页，则取当前页第一条
//                    if (offsetBL < startIndex) {
//                        // 循环直到,到当前页的第一条记录
//                        offsetBL++;
//                        continue;
//                    }
//                }
//    
//                Bill bill = setBill(currentMonth,aInvoice);      
//                boolean serviceIdExist = false;//先将serviceId至为false
//
//                // 在list中查找serviceId，找到此id对应的list，则加入当前账单，否则，新建一个账单列表对象
//                for (int k = 0; k < billInfo.getServiceIdList().size(); k++) {
//                    ServiceId serviceId = billInfo.getServiceIdList().get(k);//遍历list，若billInfo中已有的该id对应的账单，对serviceId置为true，跳过新增id步骤
//                    if (serviceId.getServiceId().compareTo(aInvoice.getServiceId()) == 0) {
//                        // 找到相同的serviceId
//                        List<Bill> BillList = serviceId.getBillList();//该serviceId下不一定有bill
//                        //若为空，新建billList
//                        if (CollectionUtils.isEmpty(BillList)) {   
//                            BillList = new ArrayList<Bill>();
//                        } 
//                        BillList.add(bill);
//                        //list中存在当前serviceId
//                        serviceIdExist = true;
//                    }
//
//                }
//                if (!serviceIdExist) {
//                    // 没有找到当前用户信息,则新增一个用户
//                    ServiceId serviceId = new ServiceId();
//                    serviceId.setServiceId(aInvoice.getServiceId());
//    
//                    List<Bill> billList = new ArrayList<Bill>();
//                    billList.add(bill);//bill可能为空
//                    serviceId.setBillList(billList);
//    
//                    billInfo.getServiceIdList().add(serviceId);
//                }
//                //若分页，当前页条数+1
//                if (billQueryParams.getPaging().equals("YES")) {
//                    offsetBL++;
//                    if(offsetBL == endIndex){
//                        endFlag = 1;//当前页结束
//                        break;
//                    }
//                }
//         }
//         //若分页，判断当前页是否结束
//         if(billQueryParams.getPaging().equals("YES") &&endFlag==1){
//             break;
//         }
//       }
//        return billInfo;
//    }
//    
////向bill和subjectDetail类中插入信息
//    private Bill setBill(String currentMonth, AmcInvoiceYyyydd aInvoice) {
//        Bill bill = new Bill();
//        bill.setBillDuration(currentMonth);//当月账期
//        
//        bill.setOrgFee(aInvoice.getTotalAmount());
//        bill.setAdjustFee(aInvoice.getAdjustAfterwards());
//        bill.setDisFee(aInvoice.getDiscTotalAmount());
//        bill.setTotalfee(aInvoice.getBalance());
//        
//        AmcChargeYyyyddCriteria amcChargeYyyyddCriteria = new AmcChargeYyyyddCriteria();
//        AmcChargeYyyyddMapper amcChargeYyyyddMapper = sqlSessionTemplate.getMapper(AmcChargeYyyyddMapper.class);
//        amcChargeYyyyddCriteria.createCriteria()
//         .andAcctIdEqualTo(aInvoice.getAcctId())
//         .andSubsIdEqualTo(aInvoice.getSubsId())
//         .andCustIdEqualTo(aInvoice.getCustId());
//        List<AmcChargeYyyydd> amcChargeYyyyddList = new ArrayList<>();
//        try {
//            amcChargeYyyyddList = amcChargeYyyyddMapper.selectByExample(
//                    currentMonth, amcChargeYyyyddCriteria);
//        } catch (Exception e) {
//            logger.error("amcChargeYyyyddMapper.selectByExample Exception " + e);
//        }
//
//        //amcChargeYyyyddList判空
//        if (CollectionUtils.isEmpty(amcChargeYyyyddList)) {
//            // 如果明细信息列表为空，则返回空列表
//            return bill;
//        }
//        List<SubjectDetail>subjectDetailList = new ArrayList<>();
//         // 遍历账单明细的List
//        for ( AmcChargeYyyydd amcChargeyyyydd : amcChargeYyyyddList) {
//
//            SubjectDetail subjectDetail = new SubjectDetail();
//
//            subjectDetail.setSubjectId(amcChargeyyyydd.getSubjectId().toString());
//            subjectDetail.setSubjectAdjustFee(amcChargeyyyydd.getAdjustAfterwards());
//            subjectDetail.setSubjectDisFee(amcChargeyyyydd.getDiscTotalAmount());
//            subjectDetail.setSubjectOrgFee(amcChargeyyyydd.getTotalAmount());
//            subjectDetail.setSubjectFee(amcChargeyyyydd.getBalance());
//
//            subjectDetailList.add(subjectDetail);
//        }
//
//        bill.setSubjectDetailList(subjectDetailList);
//        return bill;
//    }
//
//
//    public List<String> getDateList(String startTime, String endTime) {
//        List<String> yyyyddList = new ArrayList<String>();
//        //取年份
//        String startYYYY = startTime.substring(0, 4);
//        String endYYYY = endTime.substring(0, 4);
//        //取月份
//        String startMM = startTime.substring(4, 6);
//        String endMM = endTime.substring(4, 6);
//
//        System.out.println("startYYYY:"+startYYYY+" startMM:"+startMM+" endYYYY:"+endYYYY+" endMM:"+endMM);
//        if (startYYYY.equalsIgnoreCase(endYYYY)) {
//        // 一年内的
//            int startTimeI = Integer.parseInt(startMM);//开始月份
//            int endTimeI = Integer.parseInt(endMM);//结束月份
//            for (int i = startTimeI; i <= endTimeI; i++) {
//                yyyyddList.add(startYYYY + String.format("%02d", i));
//            }
//        } else {
//        // 跨年的
//            int startYearI = Integer.parseInt(startYYYY);
//            int endYearI = Integer.parseInt(endYYYY);
//            int startMonthI = Integer.parseInt(startMM);
//            int endMonthI = Integer.parseInt(endMM);
//
//            // 第一年
//            for (int m = startMonthI; m <= 12; m++) {
//                yyyyddList.add(startYYYY + String.format("%02d", m));
//            }
//            // 第二年到倒数第二年
//            startYearI++;
//            for (int y = startYearI; y <= endYearI - 1; y++) {
//                for (int m = 1; m <= 12; m++) {
//                    yyyyddList.add(y + String.format("%02d", m));
//                }
//            }
//
//            // 最后一年
//            for (int m = 1; m <= endMonthI; m++) {
//                yyyyddList.add(endYYYY + String.format("%02d", m));
//            }
//        }
//        return yyyyddList;
//    }
//
//}
