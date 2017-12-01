package com.ai.baas.prd.service.business.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ai.baas.prd.api.element.params.ElementDeleteVO;
import com.ai.baas.prd.api.element.params.UpdateByProductVo;
import com.ai.baas.prd.api.product.params.BaseCategoryInfo;
import com.ai.baas.prd.api.product.params.BaseRequest;
import com.ai.baas.prd.api.product.params.BranchInfo;
import com.ai.baas.prd.api.product.params.BranchRes;
import com.ai.baas.prd.api.product.params.BranchVo;
import com.ai.baas.prd.api.product.params.CategoryInfo;
import com.ai.baas.prd.api.product.params.CategoryInfoVO;
import com.ai.baas.prd.api.product.params.ChildProduct;
import com.ai.baas.prd.api.product.params.DelProductReq;
import com.ai.baas.prd.api.product.params.DimensionInfo;
import com.ai.baas.prd.api.product.params.DimensionRes;
import com.ai.baas.prd.api.product.params.DimensionVO;
import com.ai.baas.prd.api.product.params.MainProduct;
import com.ai.baas.prd.api.product.params.MainProductInfo;
import com.ai.baas.prd.api.product.params.MainProductRes;
import com.ai.baas.prd.api.product.params.ProductReq;
import com.ai.baas.prd.api.product.params.ProductRequest;
import com.ai.baas.prd.api.product.params.ProductUpdateReq;
import com.ai.baas.prd.api.product.params.QueryPmCategoryInfoReq;
import com.ai.baas.prd.api.product.params.SubQueryReq;
import com.ai.baas.prd.api.product.params.SubsProductQueryReq;
import com.ai.baas.prd.api.product.params.mainProReq;
import com.ai.baas.prd.constants.ExceptCodeConstants;
import com.ai.baas.prd.constants.PrdConstants;
import com.ai.baas.prd.dao.mapper.bo.BmcBasedataCode;
import com.ai.baas.prd.dao.mapper.bo.PmCatalogInfo;
import com.ai.baas.prd.dao.mapper.bo.PmCategoryInfo;
import com.ai.baas.prd.dao.mapper.bo.PmCategoryInfoHis;
import com.ai.baas.prd.dao.mapper.bo.PmDimensionBranch;
import com.ai.baas.prd.dao.mapper.bo.PmDimensionBranchHis;
import com.ai.baas.prd.dao.mapper.bo.PmDimensionInfo;
import com.ai.baas.prd.dao.mapper.bo.PmDimensionInfoHis;
import com.ai.baas.prd.dao.mapper.model.Member;
import com.ai.baas.prd.service.atom.interfaces.IBmcBaseCodeInfoAtomSV;
import com.ai.baas.prd.service.atom.interfaces.IPmCatalogInfoAtomSV;
import com.ai.baas.prd.service.atom.interfaces.IPmCategoryInfoAtomSV;
import com.ai.baas.prd.service.atom.interfaces.IPmCategoryInfoHisAtomSV;
import com.ai.baas.prd.service.atom.interfaces.IPmDimensionBranchAtomSV;
import com.ai.baas.prd.service.atom.interfaces.IPmDimensionBranchHisAtomSV;
import com.ai.baas.prd.service.atom.interfaces.IPmDimensionInfoAtomSV;
import com.ai.baas.prd.service.atom.interfaces.IPmDimensionInfoHisAtomSV;
import com.ai.baas.prd.service.business.interfaces.IPriceElementBusiSV;
import com.ai.baas.prd.service.business.interfaces.IProductDefineBusiSV;
import com.ai.baas.prd.util.PrdSeqUtil;
import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.vo.PageInfo;
import com.ai.opt.base.vo.ResponseHeader;
import com.ai.opt.sdk.util.BeanUtils;
import com.ai.opt.sdk.util.CollectionUtil;
import com.ai.opt.sdk.util.DateUtil;
import com.ai.paas.ipaas.util.StringUtil;
import com.alibaba.fastjson.JSON;
/**
 * 产品定义业务服务
 *
 * Date: 2016年11月9日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author gaogang
 */
@Service
@Transactional
public class ProductDefineBusiSVImpl implements IProductDefineBusiSV{
	private Logger logger = Logger.getLogger(ProductDefineBusiSVImpl.class);
	@Autowired
	IPmDimensionInfoAtomSV pmDimensionInfoAtomSV;
	@Autowired
	IPmDimensionBranchAtomSV pmDimensionBranchAtomSV;
	@Autowired
	IPmCategoryInfoAtomSV pmCategoryInfoAtomSV;
	@Autowired
	IPmDimensionInfoHisAtomSV pmDimensionInfoHisAtomSV;
	@Autowired
	IPmDimensionBranchHisAtomSV pmDimensionBranchHisAtomSV;
	@Autowired
	IPmCategoryInfoHisAtomSV pmCategoryInfoHisAtomSV;
	@Autowired
    IPmCatalogInfoAtomSV iPmCatalogInfoAtomSv;
	@Autowired
	IPriceElementBusiSV iPriceElementBusiSV;
	@Autowired
	IBmcBaseCodeInfoAtomSV iBasedataAtomSv;
	private String mainProductCode;
	private String mainProductName;
	private String tenantId;
	List<String> reqIds;//=new ArrayList<String>();//用来封装请求的类目Id
	List<String> resIds;//=new ArrayList<String>();//用来封装从表里获取的产品类目信息表的类目Id
	@Override
	public void addProduct(ProductRequest req) {
		
		 //要复制的对象
		ProductReq pr=new ProductReq();
		List<DimensionInfo> dims=new ArrayList<DimensionInfo>();
		
		
		List<DimensionVO> dimensionVos=req.getDimensions();
		mainProductCode=req.getMainProductCode();
		mainProductName=req.getMainProductName();
		tenantId=req.getTenantId();
		String tradeCode=req.getTradeCode();
		String tradeName=req.getTradeName();
		//先去表里找重复的主产品，如果当前租户已经有这个主产品则需要进行重复的判断
		
		if(pmDimensionInfoAtomSV.getPmDimensionInfos(tenantId, mainProductCode).size()>0){
			throw new BusinessException("999999", "当前主产品已经存在，不能重复添加"); 
		}
		
		//copy
		pr.setMainProductCode(mainProductCode);
		pr.setMainProductName(mainProductName);
		pr.setTenantId(tenantId);
		pr.setTradeCode(tradeCode);
		pr.setTradeName(tradeName);
		for(DimensionVO vo: dimensionVos){  //维度表的维护
			PmDimensionInfo pdi=new PmDimensionInfo();
			pdi.setMainProductCode(mainProductCode);
			pdi.setMainProductName(mainProductName);
			pdi.setTenantId(tenantId);
			pdi.setTradeCode(tradeCode);
			pdi.setTradeName(tradeName);
			pdi.setComments("");
			pdi.setDimensionCode(vo.getDimensionCode());
			pdi.setDimensionName(vo.getDimensionName());
			pdi.setDimensionSeq(Integer.valueOf(vo.getDimensionSeq()));
			Long did=PrdSeqUtil.getDimensionId();
			pdi.setId(did);
			pmDimensionInfoAtomSV.addDimension(pdi);
			
			//copy
			DimensionInfo di=new DimensionInfo();
			di.setDimensionCode(vo.getDimensionCode());
			di.setDimensionId(String.valueOf(did));
			di.setDimensionName(vo.getDimensionName());
			di.setDimensionSeq(vo.getDimensionSeq());
			List<BranchInfo> brans=new ArrayList<BranchInfo>();
			
			
			
			
			//分支表的维护
			List<BranchVo> branchs=vo.getBranchs();
			for(BranchVo bvo:branchs){
				PmDimensionBranch branch=new PmDimensionBranch();
				branch.setBranchCode(bvo.getBranchCode());
				branch.setBranchName(bvo.getBranchName());
				branch.setComments("");
				branch.setDimensionCode(vo.getDimensionCode());
				Long bid=PrdSeqUtil.getBranchnId();
				branch.setId(bid);
				branch.setTenantId(tenantId);
				branch.setMainProductCode(mainProductCode);
				pmDimensionBranchAtomSV.addBranch(branch);
				
				
				
				//copy
				BranchInfo bi=new BranchInfo();
				bi.setBranchCode(bvo.getBranchCode());
				bi.setBranchId(String.valueOf(bid));
				bi.setBranchName(bvo.getBranchName());
				brans.add(bi);
			
			}
			di.setBranchs(brans);
			dims.add(di);
		}
		pr.setDimensions(dims);
		System.out.println(JSON.toJSONString(pr));
		//开始维护产品类目信息表
		//首先维护助产品名称
		PmCategoryInfo pci=new PmCategoryInfo();
		pci.setId(PrdSeqUtil.getCategoryInfoId());
		pci.setCategoryLevel(PrdConstants.PRODUCT.LEVEL1);
		pci.setCategoryName(mainProductName);
		pci.setCategoryType(PrdConstants.PRODUCT.CATEGORY_TYPE);
		pci.setCategoryId(mainProductCode);
		pci.setParentId(null);
		pci.setTenantId(tenantId);
		pci.setMembers("");
		pmCategoryInfoAtomSV.addPmCategoryInfo(pci);
		//拼装子产品类目
		//addCategory(0, req, new StringBuilder(mainProductName),  new StringBuilder(mainProductCode),new ArrayList<Member>());
		addCategorys(0, pr, new StringBuilder(mainProductName),  new StringBuilder(mainProductCode),new ArrayList<Member>());
		
	}
	
	
	/**
	 * 全排列算法，维护产品类目信息表
	 * @param i  维度长度
	 * @param req  请求的参数
	 * @param pname 产品名称
	 * @param pid 产品编码
	 * @author gaogang
	 */
	private void addCategory(int i,ProductRequest req,StringBuilder pname,StringBuilder pid,List<Member> list){
		if(i<req.getDimensions().size()){
			List<BranchVo> branchs=req.getDimensions().get(i).getBranchs();
			for(BranchVo vo:branchs){
				StringBuilder pname1=new StringBuilder();
				StringBuilder pid1=new StringBuilder();
				List<Member> list1=new ArrayList<Member>();
				list1.addAll(list);
				pname1.append(pname.toString());   //拼接分支的名字
				pname1.append(vo.getBranchName());   //拼接分支的名字
				pid1.append(pid.toString());
				pid1.append(vo.getBranchCode());
				Member mem=new Member();
				mem.setBranch(vo.getBranchCode());
				mem.setDimension(req.getDimensions().get(i).getDimensionCode());
				list1.add(mem);
				if(i<req.getDimensions().size()&&i!=(req.getDimensions().size()-1)){//如果不是到达维度的深度则继续去循环
					//System.out.println("pname="+pname1);
					addCategory(i+1,req,pname1,pid1,list1);
				}
				
				
				
				if(i==(req.getDimensions().size()-1)){//当已经循环到最底层的维度的时候就需要分别打印最后维度的各个分支的数据
					//System.out.println();
					StringBuilder sb=new StringBuilder();
					StringBuilder sd=new StringBuilder();
					sb.append(pname.toString());
					sb.append(vo.getBranchName());
					sd.append(pid.toString());
					sd.append(vo.getBranchCode());
					/*System.out.println("子目录的名字="+sb.toString());
					System.out.println("子类目id="+sd.toString());*/
					PmCategoryInfo pci=new PmCategoryInfo();
					pci.setId(PrdSeqUtil.getCategoryInfoId());
					pci.setCategoryLevel(PrdConstants.PRODUCT.LEVEL2);
					pci.setCategoryName(sb.toString());
					pci.setCategoryType(PrdConstants.PRODUCT.CATEGORY_TYPE);
					pci.setCategoryId(sd.toString());
					pci.setParentId(mainProductCode);
					pci.setTenantId(tenantId);
					//拼装数据构成明细
					
					Map<String,List<Member>> map=new HashMap<String,List<Member>>();
					map.put("members", list1);
					pci.setMembers(JSON.toJSONString(map));
					pmCategoryInfoAtomSV.addPmCategoryInfo(pci);
					
				}
				
				
			}
		}
	}
	/**
	 * 全排列算法，维护产品类目信息表
	 * @param i  维度长度
	 * @param req  请求的参数
	 * @param pname 产品名称
	 * @param pid 产品编码
	 * @author gaogang
	 */
	private void addCategorys(int i,ProductReq req,StringBuilder pname,StringBuilder pid,List<Member> list){
		if(i<req.getDimensions().size()){
			List<BranchInfo> branchs=req.getDimensions().get(i).getBranchs();
			for(BranchInfo vo:branchs){
				StringBuilder pname1=new StringBuilder();
				StringBuilder pid1=new StringBuilder();
				List<Member> list1=new ArrayList<Member>();
				list1.addAll(list);
				pname1.append(pname.toString());   //拼接分支的名字
				pname1.append(vo.getBranchName());   //拼接分支的名字
				pid1.append(pid.toString());
				pid1.append(vo.getBranchId());
				Member mem=new Member();
				mem.setBranch(vo.getBranchCode());
				mem.setDimension(req.getDimensions().get(i).getDimensionCode());
				list1.add(mem);
				if(i<req.getDimensions().size()&&i!=(req.getDimensions().size()-1)){//如果不是到达维度的深度则继续去循环
					//System.out.println("pname="+pname1);
					addCategorys(i+1,req,pname1,pid1,list1);
				}
				
				
				
				if(i==(req.getDimensions().size()-1)){//当已经循环到最底层的维度的时候就需要分别打印最后维度的各个分支的数据
					//System.out.println();
					StringBuilder sb=new StringBuilder();
					StringBuilder sd=new StringBuilder();
					sb.append(pname.toString());
					sb.append(vo.getBranchName());
					sd.append(pid.toString());
					sd.append(vo.getBranchId());
					/*System.out.println("子目录的名字="+sb.toString());
					System.out.println("子类目id="+sd.toString());*/
					PmCategoryInfo pci=new PmCategoryInfo();
					pci.setId(PrdSeqUtil.getCategoryInfoId());
					pci.setCategoryLevel(PrdConstants.PRODUCT.LEVEL2);
					pci.setCategoryName(sb.toString());
					pci.setCategoryType(PrdConstants.PRODUCT.CATEGORY_TYPE);
					pci.setCategoryId(sd.toString());
					pci.setParentId(mainProductCode);
					pci.setTenantId(tenantId);
					//拼装数据构成明细
					
					Map<String,List<Member>> map=new HashMap<String,List<Member>>();
					map.put("members", list1);
					pci.setMembers(JSON.toJSONString(map));
					pmCategoryInfoAtomSV.addPmCategoryInfo(pci);
					
				}
				
				
			}
		}
	}


	@Override
	public PageInfo<MainProduct> getCategoryInfos(QueryPmCategoryInfoReq req) {
		
		PageInfo<MainProduct> pageInfo=new PageInfo<MainProduct>();
		List<PmCategoryInfo> list=  pmCategoryInfoAtomSV.getPmCategoryInfos(req);
		List<MainProduct> mainList=new ArrayList<MainProduct>();
		for(int i=0;i<list.size();i++){//查询子产品
			PmCategoryInfo info=list.get(i);
			MainProduct mainp=new MainProduct();
			mainp.setCategoryLevel(info.getCategoryLevel());
			mainp.setMainProductCode(info.getCategoryId());
			mainp.setMainProductName(info.getCategoryName());
			mainp.setIndex(String.valueOf((req.getPageNO()-1)*req.getPageSize()+1+i));
			mainp.setTenantId(info.getTenantId());
			List<PmCategoryInfo> li=pmCategoryInfoAtomSV.getPmCategoryByPId(info.getTenantId(), info.getCategoryId());
			List<ChildProduct> clist=new ArrayList<ChildProduct>();
			for(PmCategoryInfo pci:li){
				ChildProduct cp=new ChildProduct();
				cp.setCategoryId(pci.getCategoryId());
				cp.setCategoryLevel(pci.getCategoryLevel());
				cp.setCategoryName(pci.getCategoryName());
				clist.add(cp);
			}
			mainp.setChildProducts(clist);
			mainList.add(mainp);
		}
		
		pageInfo.setCount(pmCategoryInfoAtomSV.getPmCategoryInfosCount(req));
		pageInfo.setPageNo(req.getPageNO());
		pageInfo.setPageSize(req.getPageSize());
		pageInfo.setResult(mainList);
		return pageInfo;
	}


	@Override
	public int delProduct(DelProductReq req) {

		Timestamp insertTime=DateUtil.getSysDate();
		List<PmDimensionInfo> pdiList=pmDimensionInfoAtomSV.getPmDimensionInfos(req.getTenantId(), req.getMainProCode());
		for(PmDimensionInfo pdinfo:pdiList){
			PmDimensionInfoHis his=new PmDimensionInfoHis();
			BeanUtils.copyProperties(his, pdinfo);
			his.setInsertTime(insertTime);
			pmDimensionInfoHisAtomSV.addDimensionInfoHis(his);
		}
		List<PmDimensionBranch> pdbList=pmDimensionBranchAtomSV.getDimensionBranch(req.getTenantId(), req.getMainProCode());
		for(PmDimensionBranch br:pdbList){
			PmDimensionBranchHis his=new PmDimensionBranchHis();
			BeanUtils.copyProperties(his, br);
			his.setInsertTime(insertTime);
			pmDimensionBranchHisAtomSV.addDimensionBranchHis(his);
		}
	   List<PmCategoryInfo> pciList=pmCategoryInfoAtomSV.getPmCategoryInfo(req.getTenantId(), req.getMainProCode());
	   List<PmCategoryInfo> pciList1=pmCategoryInfoAtomSV.getPmCategoryByPId(req.getTenantId(), req.getMainProCode());
	   pciList.addAll(pciList1);
	   List<String> categoryIds=new ArrayList<String>();
	   for(PmCategoryInfo pcInfo:pciList){
		   categoryIds.add(pcInfo.getCategoryId());
		    PmCategoryInfoHis his=new PmCategoryInfoHis();
			BeanUtils.copyProperties(his, pcInfo);
			his.setInsertTime(insertTime);
			pmCategoryInfoHisAtomSV.addPmCategoryInfoHis(his);
		}
	   
	   
		int pdi=pmDimensionInfoAtomSV.delPmDimensionInfo(req.getTenantId(), req.getMainProCode());
		int pdb=pmDimensionBranchAtomSV.delDimensionBranch(req.getTenantId(),req.getMainProCode());
	    int pci=pmCategoryInfoAtomSV.delPmCategoryInfo(req.getTenantId(), req.getMainProCode());
	    
	    if(pdi>0&&pdb>0&&pci>0){
	    	ElementDeleteVO edvo=new ElementDeleteVO();
		    edvo.setTenantId(req.getTenantId());
		    edvo.setMainProductId(req.getMainProCode());
		    edvo.setCategoryIds(categoryIds);
		    iPriceElementBusiSV.deleteElement(edvo);
	    	return 1;
	    }
		return 0;
	}


	@Override
	public MainProductRes getProductById(BaseRequest req) {
		MainProductRes res=new MainProductRes();
		
		List<PmDimensionInfo> list=  pmDimensionInfoAtomSV.getPmDimensionInfos(req.getTenantId(), req.getMainProductCode());
		if(CollectionUtil.isEmpty(list)){
			throw new BusinessException("999999", "没有查询到该产品");
		}
		List<DimensionRes> dimensions=new ArrayList<DimensionRes>();
		for(PmDimensionInfo info:list){
			res.setMainProductCode(info.getMainProductCode());
			res.setMainProductName(info.getMainProductName());
			res.setTradeCode(info.getTradeCode());
			res.setTradeName(info.getTradeName());
			
			DimensionRes dr=new DimensionRes();
			dr.setDimensionCode(info.getDimensionCode());
			dr.setDimensionId(String.valueOf(info.getId()));
			dr.setDimensionName(info.getDimensionName());
			dr.setDimensionSeq(String.valueOf(info.getDimensionSeq()));
			List<BranchRes> bilist=new ArrayList<BranchRes>();
			List<PmDimensionBranch> blist = pmDimensionBranchAtomSV.getDimensionBranch(info.getTenantId(), info.getMainProductCode(), info.getDimensionCode());
			for(PmDimensionBranch pdb:blist){
				BranchRes br=new BranchRes();
				br.setBranchCode(pdb.getBranchCode());
				br.setBranchId(String.valueOf(pdb.getId()));
				br.setBranchName(pdb.getBranchName());
				bilist.add(br);
			}
			dr.setBranchs(bilist);
			dimensions.add(dr);
		}
		
		res.setDimensions(dimensions);
		return res;
	}


	@Override
	public int updatePmCategoryInfo(ProductUpdateReq req) {//修改产品(目前不支持修改主产品的code)
		try{
		//首先解析字符串
		
		//首先构造维度相关
		List<DimensionRes> dimList=req.getDimensions();
		
		//获取对应主产品下的维度信息
		//List<PmDimensionInfo>  listdim=pmDimensionInfoAtomSV.getPmDimensionInfos(req.getTenantId(), req.getMainProductCode());
		//需要学习添加的方法，将修改的请求参数进行复制，然后再进行编码分支全排列，然后再去产品类目信息表中对比
		
		//修改维度信息表
		List<PmDimensionBranch> pdbas=pmDimensionBranchAtomSV.getDimensionBranch(req.getTenantId(), req.getMainProductCode());
		List<String> pdblist=new ArrayList<String>();
		for(PmDimensionBranch pd:pdbas){
			pdblist.add(String.valueOf(pd.getId()));
		}
		for(DimensionRes dr:dimList){//更新维度表
			PmDimensionInfo pdi=new PmDimensionInfo();
			pdi.setDimensionCode(dr.getDimensionCode());
			pdi.setDimensionName(dr.getDimensionName());
			pdi.setMainProductName(req.getMainProductName());
			pdi.setTradeCode(req.getTradeCode());
			pdi.setTradeName(req.getTradeName());
			pmDimensionInfoAtomSV.updatePmDimensionInfoById(req.getTenantId(), Long.valueOf(dr.getDimensionId()), pdi);
			List<BranchRes> branchs=dr.getBranchs();
			for(BranchRes br:branchs){//修改维度分支信息表
				if(StringUtil.isBlank(br.getBranchId())){//  //先去添加没有分支id的数据insert
					PmDimensionBranch pdb=new PmDimensionBranch();
					pdb.setBranchCode(br.getBranchCode());
					pdb.setBranchName(br.getBranchName());
					Long id=PrdSeqUtil.getBranchnId();
					pdb.setId(id);//这个id需要同步到类目信息表的类目编码上去
					br.setBranchId(String.valueOf(id));    //并且给set进去
					pdb.setComments("");
					pdb.setDimensionCode(dr.getDimensionCode());
					pdb.setMainProductCode(req.getMainProductCode());
					pdb.setTenantId(req.getTenantId());
					pmDimensionBranchAtomSV.addBranch(pdb);
					
				}else{//update or del
					pdblist.remove(br.getBranchId());
					PmDimensionBranch pdb=new PmDimensionBranch();
					pdb.setBranchCode(br.getBranchCode());
					pdb.setBranchName(br.getBranchName());
					pdb.setDimensionCode(dr.getDimensionCode());
					pmDimensionBranchAtomSV.updatePmDimensionBranch(req.getTenantId(), Long.valueOf(br.getBranchId()), pdb);
					
				}
			}
		
			
		}
		for(String tmp:pdblist){
			pmDimensionBranchAtomSV.delDimensionBranchById(req.getTenantId(),Long.valueOf(tmp));
		}
	
		//修改产品类目信息表
    //	从原来的表中获取list
		List<PmCategoryInfo>  cateIds=pmCategoryInfoAtomSV.getPmCategoryByPId(req.getTenantId(), req.getMainProductCode());	
		resIds=new ArrayList<String>();
		for(PmCategoryInfo p:cateIds){
			resIds.add(p.getCategoryId());
		}
		reqIds=new ArrayList<String>();
		updateCategorys(0,req, new StringBuilder(req.getMainProductName()), new StringBuilder(req.getMainProductCode()),new ArrayList<Member>());
		for(String resId:resIds){
			if(!reqIds.contains(resId)){
				logger.info("删除类目id"+resId);
				//添加方法去删除，需要传递租户id，主产品id，类目Id
				pmCategoryInfoAtomSV.delPmcategoryInfo(req.getTenantId(), req.getMainProductCode(), resId);
			}
		}
		logger.info("关联定价元素。。。");
		UpdateByProductVo uvo=new UpdateByProductVo();
		uvo.setTenantId(req.getTenantId());
		uvo.setCategoryId(req.getMainProductCode());
		iPriceElementBusiSV.alterElementByProduct(uvo);
		//alterElementByProduct(uvo);
       }catch(Exception e){
    	   logger.error(e.getMessage());
			return 0;
		}
		return 1;
	}
	
	/**
	 * 全排列算法，维护产品类目信息表
	 * @param i  维度长度
	 * @param req  请求的参数
	 * @param pname 产品名称
	 * @param pid 产品编码
	 * @author gaogang
	 */
	private void updateCategorys(int i,ProductUpdateReq req,StringBuilder pname,StringBuilder pid,List<Member> list){
		if(i<req.getDimensions().size()){
			List<BranchRes> branchs=req.getDimensions().get(i).getBranchs();
			for(BranchRes vo:branchs){
				StringBuilder pname1=new StringBuilder();
				StringBuilder pid1=new StringBuilder();
				List<Member> list1=new ArrayList<Member>();
				list1.addAll(list);
				pname1.append(pname.toString());   //拼接分支的名字
				pname1.append(vo.getBranchName());   //拼接分支的名字
				pid1.append(pid.toString());
				pid1.append(vo.getBranchId());
				Member mem=new Member();
				mem.setBranch(vo.getBranchCode());
				mem.setDimension(req.getDimensions().get(i).getDimensionCode());
				list1.add(mem);
				if(i<req.getDimensions().size()&&i!=(req.getDimensions().size()-1)){//如果不是到达维度的深度则继续去循环
					//System.out.println("pname="+pname1);
					updateCategorys(i+1,req,pname1,pid1,list1);
				}
				
				
				
				if(i==(req.getDimensions().size()-1)){//当已经循环到最底层的维度的时候就需要分别打印最后维度的各个分支的数据
					//System.out.println();
					StringBuilder sb=new StringBuilder();
					StringBuilder sd=new StringBuilder();
					sb.append(pname.toString());
					sb.append(vo.getBranchName());
					sd.append(pid.toString());
					sd.append(vo.getBranchId());
					/*System.out.println("子目录的名字="+sb.toString());
					System.out.println("子类目id="+sd.toString());*/
					PmCategoryInfo pci=new PmCategoryInfo();
				
					pci.setCategoryLevel(PrdConstants.PRODUCT.LEVEL2);
					pci.setCategoryName(sb.toString());
					pci.setCategoryType(PrdConstants.PRODUCT.CATEGORY_TYPE);
					pci.setCategoryId(sd.toString());
					pci.setParentId(req.getMainProductCode());
					pci.setTenantId(req.getTenantId());
					
					reqIds.add(sd.toString());
					//拼装数据构成明细
					
					Map<String,List<Member>> map=new HashMap<String,List<Member>>();
					map.put("members", list1);
					pci.setMembers(JSON.toJSONString(map));
					
					if(resIds.contains(sd.toString())){   //如果存在则update
						pmCategoryInfoAtomSV.updatePmcategoryInfo(req.getTenantId(), req.getMainProductCode(), sd.toString(), pci);
					}else{
						pci.setId(PrdSeqUtil.getCategoryInfoId());  //有点问题如果是更新的话
						pmCategoryInfoAtomSV.addPmCategoryInfo(pci);
					}
					
				}
				
				
			}
		}
	}


	@Override
	public BaseCategoryInfo querySubsProduct(SubsProductQueryReq req) {
		// TODO Auto-generated method stub
		
		List<PmCategoryInfo> infos = pmCategoryInfoAtomSV.getPmCategoryByPId(req.getTenantId(), req.getMainProCode());
		List<CategoryInfoVO> categoryInfoVOs = new ArrayList<>();
		if(infos!=null){
			for(PmCategoryInfo info : infos){
				CategoryInfoVO vo = new CategoryInfoVO();
				BeanUtils.copyProperties(vo, info);
				categoryInfoVOs.add(vo);
			}
		}
		BaseCategoryInfo response = new BaseCategoryInfo();
		response.setCategoryInfos(categoryInfoVOs);
        response.setTenantId(req.getTenantId());
        response.setResponseHeader(new ResponseHeader(true, ExceptCodeConstants.SUCCESS, "成功"));
		return response;
	}

	private void alterElementByProduct(UpdateByProductVo vo) {
        String tenantId = vo.getTenantId();
        String mainProCode = vo.getCategoryId();
                       
        List<PmCategoryInfo>pmCategoryInfos =  pmCategoryInfoAtomSV.getPmCategoryByPId(tenantId, mainProCode);     
        Set<String> idinCategoryInfo = new HashSet<>();  
        for(PmCategoryInfo cg: pmCategoryInfos){
            idinCategoryInfo.add(cg.getCategoryId());
        }
        
        List<PmCatalogInfo> pmCatalogInfos = iPmCatalogInfoAtomSv.queryElementByMainProductId(tenantId,mainProCode);
        Set<String> idinCatalogInfo = new HashSet<>();
        for(PmCatalogInfo cl: pmCatalogInfos){
            idinCatalogInfo.add(cl.getCategoryId());
        }
        
        for(String categoryId : idinCatalogInfo){
                boolean notExistFlag = true;
                for(String IdinCategory :idinCategoryInfo){
                    if(categoryId.equals(IdinCategory)){
                        notExistFlag = false;
                        break;
                    }   
                }
                if(notExistFlag){
                	logger.info("删除类目ID为: "+categoryId+" 的子产品");
                    iPmCatalogInfoAtomSv.deleteElementByCategoryId(tenantId,categoryId);
                }
        }
    }


	@Override
	public PageInfo<CategoryInfo> getCategorys(SubQueryReq req) {
		List<PmCategoryInfo> list=pmCategoryInfoAtomSV.getCategoryInfo(req);
		List<CategoryInfo> clist=new ArrayList<CategoryInfo>();
		CategoryInfo info=null;
		if(!CollectionUtil.isEmpty(list)){
			for(int i=0;i<list.size();i++){
				info=new CategoryInfo();
				BeanUtils.copyProperties(info, list.get(i));
				info.setIndex(String.valueOf((req.getPageNO()-1)*req.getPageSize()+1+i));
				clist.add(info);
			}
		}
		PageInfo<CategoryInfo> pageInfo=new PageInfo<CategoryInfo>();
		pageInfo.setCount(pmCategoryInfoAtomSV.getCategoryInfoCount(req));
		pageInfo.setResult(clist);
		pageInfo.setPageNo(req.getPageNO());
		pageInfo.setPageSize(req.getPageSize());
	    return pageInfo;
	}


	@Override
	public PageInfo<MainProductInfo> getMainProduct(mainProReq req) {

		List<BmcBasedataCode> list=iBasedataAtomSv.getBasedata(req);
		MainProductInfo mpi=null;
		List<MainProductInfo> mlist=new ArrayList<MainProductInfo>();
		if(!CollectionUtil.isEmpty(list)){
			for(int i=0;i<list.size();i++){
				mpi=new MainProductInfo();
				BeanUtils.copyProperties(mpi, list.get(i));
				mpi.setIndex(String.valueOf((req.getPageNO()-1)*req.getPageSize()+1+i));
				mlist.add(mpi);
			}
		}
		
		
		PageInfo<MainProductInfo> pageInfo=new PageInfo<MainProductInfo>();
		pageInfo.setCount(iBasedataAtomSv.getBaseDataCount(req));
		pageInfo.setResult(mlist);
		pageInfo.setPageNo(req.getPageNO());
		pageInfo.setPageSize(req.getPageSize());
		
		return pageInfo;
	}
}
