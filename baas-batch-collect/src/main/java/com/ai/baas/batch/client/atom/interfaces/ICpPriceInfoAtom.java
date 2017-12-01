package com.ai.baas.batch.client.atom.interfaces;
//package com.ai.baas.batch.client.service;
//
//import java.util.List;
//
//import com.ai.baas.batch.client.dao.mapper.bo.CpPriceInfo;
//
//
///**
// * 信息资费
// *
// * Date: 2016年4月1日 <br>
// * Copyright (c) 2016 asiainfo.com <br>
// * @author gaogang
// */
//public interface ICpPriceInfoAtom {
//	
//	Long addCpPriceInfo(CpPriceInfo info);
//	
//	int delCpRpriceInfo(CpPriceInfo info);
//	
//	List<CpPriceInfo> getCpPriceInfo(ProductQueryVO vo);
//	
//	CpPriceInfo getCpPriceInfo(ProductQueryParam param);
//	
//	CpPriceInfo getCpPriceInfo(RelatedVO vo);
//	
//	void updatePriceInfo(CpPriceInfo info);
//	/**
//	 * 通过priceCode修改信息
//	 * @param info
//	 * @author zhangzd
//	 * @ApiDocMethod
//	 * @ApiCode
//	 */
//	void updatePriceInfoByPriceCode(CpPriceInfo info);
//	/**
//	 * 通过priceCode删除信息
//	 * @param info
//	 * @author zhangzd
//	 * @ApiDocMethod
//	 * @ApiCode
//	 */
//	void deletePriceInfoByPriceCode(CpPriceInfo info);
//	/**
//	 * 根据priceCode查询信息
//	 * @param info
//	 * @return
//	 * @author zhangzd
//	 * @ApiDocMethod
//	 * @ApiCode
//	 */
//	CpPriceInfo getCpPriceInfoByPriceCode(CpPriceInfo info);
//	/**
//	 * 修改产品状态根据priceCode
//	 * @param info
//	 * @author zhangzd
//	 * @ApiDocMethod
//	 * @ApiCode
//	 */
//	void updateProductStatus(CpPriceInfo info);
//}
