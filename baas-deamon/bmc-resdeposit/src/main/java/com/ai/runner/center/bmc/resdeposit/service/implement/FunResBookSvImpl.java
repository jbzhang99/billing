package com.ai.runner.center.bmc.resdeposit.service.implement;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.stereotype.Service;

import com.ai.runner.center.bmc.resdeposit.constants.TableConstants;
import com.ai.runner.center.bmc.resdeposit.constants.TableConstants.PackageInfo;
import com.ai.runner.center.bmc.resdeposit.constants.TableConstants.PriceDetail;
import com.ai.runner.center.bmc.resdeposit.constants.TableConstants.PriceInfo;
import com.ai.runner.center.bmc.resdeposit.constants.TableConstants.SubsComm;
import com.ai.runner.center.bmc.resdeposit.service.interfaces.IFunResBookSv;
import com.ai.runner.center.bmc.resdeposit.util.DshmUtil;
import com.ai.runner.center.bmc.resdeposit.vo.CommMsg;
import com.ai.runner.center.bmc.resdeposit.vo.FunResBook;
import com.ai.runner.center.bmc.resdeposit.vo.UserMsg;
import com.ai.runner.center.dshm.api.dshmservice.interfaces.IdshmreadSV;
import com.ai.runner.utils.util.StringUtil;

@Service
public class FunResBookSvImpl implements IFunResBookSv {

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
        IdshmreadSV dshmread = DshmUtil.getDshmread();
        List<FunResBook> bookList = new ArrayList<>();
        Map<String, String> params = new TreeMap<String, String>();
        params.put(SubsComm.SUBS_ID, user.getSUBS_ID());
        params.put(SubsComm.TENANT_ID, user.getTENANT_ID());
        List<Map<String, String>> result = dshmread.list(TableConstants.SUBS_COMM).where(params)
                .executeQuery();
        for (Map<String, String> r : result) {
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
        IdshmreadSV dshmread = DshmUtil.getDshmread();
        List<FunResBook> bookList = new ArrayList<>();
        Map<String, String> params = new TreeMap<String, String>();
        params.put(SubsComm.SUBS_ID, comm.getSUBS_ID());
        params.put(SubsComm.TENANT_ID, comm.getTENANT_ID());
        params.put(SubsComm.PRODUCT_ID, comm.getPRODUCT_ID());
        params.put(SubsComm.SUBS_PRODUCT_ID, comm.getSUBS_PRODUCT_ID());
        List<Map<String, String>> result = dshmread.list(TableConstants.SUBS_COMM).where(params)
                .executeQuery();
        for (Map<String, String> r : result) {
            bookList.addAll(packFunResBook(r));
        }
        return bookList;
    }

    // 将map封装成FunResBook对象
    private List<FunResBook> packFunResBook(Map<String, String> result) {
        List<FunResBook> bookList = new ArrayList<>();
        FunResBook book = null;
        String[] systemIds = result.get(SubsComm.SYSTEM_ID).split("#");
        String[] subsIds = result.get(SubsComm.SUBS_ID).split("#");
        String[] tenantIds = result.get(SubsComm.TENANT_ID).split("#");
        String[] productIds = result.get(SubsComm.PRODUCT_ID).split("#");
        String[] subsProductIds = result.get(SubsComm.SUBS_PRODUCT_ID).split("#");
        String[] activeTimes = result.get(SubsComm.ACTIVE_TIME).split("#");
        String[] inactiveTimes = result.get(SubsComm.INACTIVE_TIME).split("#");
        String[] resClearFlag = result.get(SubsComm.RES_CLEAR_FLAG).split("#");
        String[] resBonusFlag = result.get(SubsComm.RES_BONUS_FLAG).split("#");
        // 获得所有字段最大长度size(只需比较subsIds、productIds和subsProductIds的长度)
        int size = subsIds.length > productIds.length ? subsIds.length : productIds.length;
        size = size > subsProductIds.length ? size : subsProductIds.length;
        for (int i = 0; i < size; i++) {
            // 将结果包装成CommMsg类，并加入集合当中
            book = new FunResBook();
            book.setSYSTEM_ID(getValue(systemIds, i));
            book.setSUBS_ID(getValue(subsIds, i));
            book.setTENANT_ID(getValue(tenantIds, i));
            book.setPRODUCT_ID(getValue(productIds, i));
            book.setSUBS_PRODUCT_ID(getValue(subsProductIds, i));
            book.setACTIVE_TIME(getValue(activeTimes, i));
            book.setINACTIVE_TIME(getValue(inactiveTimes, i));
            book.setRES_CLEAR_FLAG(getValue(resClearFlag, i));
            book.setRES_BONUS_FLAG(getValue(resBonusFlag, i));
            bookList.add(book);
        }
        return bookList;
    }

    // 获得item数组的第i个元素，如果超出，则选出最后一个
    private String getValue(String[] item, int index) {
        return item[index >= item.length ? item.length - 1 : index];
    }

    @Override
    public String getAmount(String product_id, String charge_type) {
        // -----------------死数据----------------------
        // if(!StringUtil.isBlank(charge_type)){
        // return "1";
        // }
        // -------------------end---------------------
        IdshmreadSV dshmread = DshmUtil.getDshmread();
        Map<String, String> params = new TreeMap<String, String>();
        params.put(PriceDetail.PRICE_CODE, product_id);
        params.put(PriceDetail.CHARGE_TYPE, charge_type);
        List<Map<String, String>> result = dshmread.list(TableConstants.PRICE_DETAIL).where(params)
                .executeQuery();
//        System.out.println(result);
        if (result.isEmpty()) {
            return null;
        }
        // String detail_code = result.get(0).get(PriceDetail.DETAIL_CODE);
        String[] temp = result.get(0).get(PriceDetail.DETAIL_CODE).split("#");
        String detail_code = temp[temp.length - 1];
        result.clear();
        if (StringUtil.isBlank(detail_code)) {
            return null;
        }
        params.clear();
        params.put(PackageInfo.DETAIL_CODE, detail_code);
        result = dshmread.list(TableConstants.PACKAGE_INFO).where(params).executeQuery();
        return result.isEmpty() ? null : result.get(0).get(PackageInfo.AMOUNT);
    }

    @Override
    public String getProductType(String product_id,String tenant_id) {
        IdshmreadSV dshmread = DshmUtil.getDshmread();
        Map<String, String> params = new TreeMap<String, String>();
        params.put(PriceInfo.PRICE_CODE, product_id);
        params.put(PriceInfo.TENANT_ID, tenant_id);
        List<Map<String, String>> result = dshmread.list(TableConstants.PRICE_INFO).where(params)
                .executeQuery();
//        System.out.println(result);
        String[] temp = result.get(0).get(PriceInfo.PRODUCT_TYPE).split("#");
        String priceType = temp[temp.length - 1];
        return priceType;
    }
}
