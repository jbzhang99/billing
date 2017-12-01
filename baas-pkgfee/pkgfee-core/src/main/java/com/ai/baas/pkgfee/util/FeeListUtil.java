package com.ai.baas.pkgfee.util;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.apache.commons.lang.StringUtils;

import com.ai.baas.pkgfee.constants.CpPkgfeeConstants;
import com.google.common.collect.Maps;

public class FeeListUtil {
	
	private Map<String,String> feeMap = Maps.newTreeMap();
	private Map<String,String> preferentialMap = Maps.newTreeMap();
	private String preferentialSubjectCode = "";

	public Map<String, String> getFeeMap() {
		return feeMap;
	}

	public void addFee(String key,String value) {
		feeMap.put(key, value);
	}
	
	public Map<String, String> getPreferentialMap() {
		return preferentialMap;
	}

	public void addPreferential(String key,String value){
		preferentialMap.put(key, value);
	}

	public String getPreferentialSubjectCode() {
		return preferentialSubjectCode;
	}

	public void setPreferentialSubjectCode(String preferentialSubjectCode) {
		this.preferentialSubjectCode = preferentialSubjectCode;
	}

	public void parser(String feeList){
		String[] records = StringUtils.splitPreserveAllTokens(feeList,CpPkgfeeConstants.FEE_LIST_RECORD);
		String[] feeField = StringUtils.splitPreserveAllTokens(records[0],CpPkgfeeConstants.FEE_LIST_FIELD);
		String[] datas = StringUtils.splitPreserveAllTokens(feeField[1],CpPkgfeeConstants.COMMON_SPLIT);
		for (String data : datas) {
			String[] split = data.split(":");
			addFee(split[0], split[1]);
		}
		if (records.length > 1) {
			String[] preferentialField = StringUtils.splitPreserveAllTokens(records[1],CpPkgfeeConstants.FEE_LIST_FIELD);
			preferentialSubjectCode = preferentialField[2];
			datas = StringUtils.splitPreserveAllTokens(preferentialField[1],CpPkgfeeConstants.COMMON_SPLIT);
			for (String data : datas) {
				String[] split = data.split(":");
				addPreferential(split[0], split[1]);
			}
		}
		
	}
	
	public String toPlainString(){
		StringBuilder str = new StringBuilder("FEE$");
		for (Entry<String, String> entry:feeMap.entrySet()) {
			str.append(entry.getKey());
			str.append(":");
			str.append(entry.getValue());
			str.append(CpPkgfeeConstants.COMMON_SPLIT);
		}
		str.delete(str.length()-1, str.length());
		if (preferentialMap.size() > 0) {
			str.append("#PREFERENTIAL$");
			for (Entry<String, String> entry:preferentialMap.entrySet()) {
				str.append(entry.getKey());
				str.append(":");
				str.append(entry.getValue());
				str.append(CpPkgfeeConstants.COMMON_SPLIT);
			}
			str.delete(str.length()-1, str.length());
			str.append("$");
			str.append(preferentialSubjectCode);
		}
		return str.toString();
	}
	
	public BigDecimal calFeeListTotal(){
		BigDecimal total = new BigDecimal("0.00");
		BigDecimal addValue = null;
		for(Entry<String,String> entry:feeMap.entrySet()){
			addValue = new BigDecimal(entry.getValue());
			total = total.add(addValue);
		}
		return total;
	}
	
	public BigDecimal calPreferentialTotal(){
		BigDecimal total = new BigDecimal("0.00");
		BigDecimal addValue = null;
		for(Entry<String,String> entry:preferentialMap.entrySet()){
			addValue = new BigDecimal(entry.getValue());
			total = total.add(addValue);
		}
		return total;
	}
	
	public static void main(String[] args) {
		FeeListUtil feeUtil = new FeeListUtil();
		feeUtil.addFee("201612", "50");
		feeUtil.addFee("201701", "100");
		feeUtil.addFee("201702", "100");
		feeUtil.addFee("201703", "80");
		
//		feeUtil.addPreferential("201702", "-100");
//		feeUtil.addPreferential("201703", "-100");
//		feeUtil.setPreferentialSubjectCode("YHJ");
		
		System.out.println(feeUtil.toPlainString());
		
//		feeUtil.parser("FEE$201612:38.71,201701:100,201702:100,201703:100#PREFERENTIAL$201702:-100,201703:-100$YHJ");
//		Map<String,String> feeMap = feeUtil.getFeeMap();
//		System.out.println(feeMap.toString());
//		Map<String,String> preferentialMap = feeUtil.getPreferentialMap();
//		System.out.println(preferentialMap.toString());
		
		
		Map<String,String> treeMap = Maps.newTreeMap();
		treeMap.put("201612", "50");
		treeMap.put("201701", "100");
		treeMap.put("201702", "100");
		treeMap.put("201703", "80");
		
		TreeMap<String,String> treeMap2 = Maps.newTreeMap();
		treeMap2.putAll(treeMap);
		
		for(Entry<String, String> entry:treeMap2.descendingMap().entrySet()){
			System.out.println(entry.getKey()+":"+entry.getValue());
		}
		
		System.out.println("-------------------------------");
		treeMap.put("201704", "90");
		for(Entry<String, String> entry:treeMap.entrySet()){
			System.out.println(entry.getKey()+":"+entry.getValue());
		}
		
		System.out.println("-------------------------------");
		BigDecimal decimal = new BigDecimal("0");
		decimal = decimal.subtract(new BigDecimal(100));
		System.out.println(decimal);
		
		String time = "20170320100000";
		String beginOfDay = StringUtils.substring(time, 6, 8);
		System.out.println("day="+beginOfDay);
		
		System.out.println("-------------------------------");
		String str = "FEE$201601:100,201602:100,201603:100,201604:100,201605:100,201606:100,201607:100,201608:100,201609:100,201610:100,201611:100,201612:100,201701:100";
		FeeListUtil feeUtilObj = new FeeListUtil();
		feeUtilObj.parser(str);
		System.out.println("000000000");
		
	}
	
}
