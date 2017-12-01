package com.ai.prd.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ai.baas.prd.api.product.interfaces.IProductDefineSV;
import com.ai.baas.prd.api.product.params.BranchVo;
import com.ai.baas.prd.api.product.params.ProductRequest;
import com.ai.baas.prd.dao.mapper.model.Member;
import com.ai.baas.prd.service.business.interfaces.IProductDefineBusiSV;
import com.ai.baas.prd.util.ReadJsonUtil;
import com.alibaba.fastjson.JSON;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "/context/core-context.xml" })
public class JsonTest {
	
	//主要目的就是找到维度的个数，即深度；另外就是每个维度的分支的深度
	
	/*for(第一个维度的分支){
		//append name
		for(第二个维度的分支){
			//append name
			for(第n个维度的分支){
				//append name
			}
		}
	}*/
	
	//List<Member> list=new ArrayList<Member>();
	
	/**
	 * 全排列算法
	 * @param i  //维度长度
	 * @param req  //请求的参数
	 * @param pname 产品名称
	 * @param pid 产品编码
	 * @author gaogang
	 */
	public void forfun(int i,ProductRequest req,StringBuilder pname,StringBuilder pid,List<Member> list){
		if(i<req.getDimensions().size()){
			List<BranchVo> branchs=req.getDimensions().get(i).getBranchs();
			//System.out.println("获取维度的编码："+req.getDimensions().get(i).getDimensionCode());
			int bsize=branchs.size();
			int j=0;
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
					/*System.out.println("当前维度的编码："+req.getDimensions().get(i).getDimensionCode());
					System.out.println("当前分支编码："+vo.getBranchCode());*/
					
					//System.out.println("member成员="+JSON.toJSONString(list));
					forfun(i+1,req,pname1,pid1,list1);
				}
				
				
				
				
				if(i==(req.getDimensions().size()-1)){//当已经循环到最底层的维度的时候就需要分别打印最后维度的各个分支的数据
					/*System.out.println("当前维度的编码1："+req.getDimensions().get(i).getDimensionCode());
					System.out.println("当前分支编码1："+vo.getBranchCode());*/
					//System.out.println();
					
					j=j+1;
					StringBuilder sb=new StringBuilder();
					StringBuilder sd=new StringBuilder();
					sb.append(pname.toString());
					sb.append(vo.getBranchName());
					sd.append(pid.toString());
					sd.append(vo.getBranchCode());
					/*System.out.println("member list:"+JSON.toJSONString(list));
					list1.addAll(list);*/
					System.out.println("member list1:"+JSON.toJSONString(list1));
					Map<String,List<Member>> map=new HashMap<String,List<Member>>();
					map.put("members", list1);
					System.out.println(JSON.toJSONString(map));
					//list.remove(i);
					//list.clear();
					System.out.println("子目录的名字="+sb.toString());
					System.out.println("子类目id="+sd.toString());
					/*if(j==(bsize-1)){
						list.clear();
					}*/
					
				}
				
				
			}
		}
		
	}
	public void fun(ProductRequest req){
		
		int dimsize=req.getDimensions().size();
		
		
		int i=0;  //记录维度
		int j=0;  //记录分支
		List<BranchVo> branchs=req.getDimensions().get(i).getBranchs();
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		//j=branchs.size();//j表示分支的個數
		for(BranchVo vo:branchs){
			
			int branchSize=branchs.size();
			
			StringBuilder pname=new StringBuilder();
			
			if(i<dimsize){
				pname.append(vo.getBranchName());
				i=i+1; //维度+1
				j++;  //分支+1
				
				
				//System.out.println("-2-->"+pname.toString());	
				forfun(req, i, dimsize, pname);
				
			
					
					
//					System.out.println(i);
//					fun(req,pname,j);
				}
			}
			
		}


	private void forfun(ProductRequest req, int i, int dimsize, StringBuilder pname) {
		System.out.println("dimsize="+dimsize);
		System.out.println("xxxx="+i);
		int j=0;
	    
		if(i<dimsize){
			System.out.println("yyyyy="+i);
			//fun(req,pname,i);
			List<BranchVo> branchs1=req.getDimensions().get(i).getBranchs();
			
		//	j=branchs1.size();
			for(BranchVo vo1:branchs1){
				StringBuilder sb=new StringBuilder();
				sb.append(pname.toString());
				sb.append(vo1.getBranchName());
				
				
				j=j+1;
				if(i==dimsize){
					System.out.println("真正的="+sb.toString());
				}
				//System.out.println("-2-->"+sb.toString());
				if(i<dimsize){
					i=i+1;
					
					System.out.println("ininini="+sb.toString());
					forfun(req,i,dimsize,sb);
					
					
				}/*else if(i==dimsize){
					System.out.println("zzzzout="+i);
					System.out.println("-3-->"+sb.toString());
				}*/
				
			}
			
			
		}else{
			
			System.out.println("-----else------"+pname.toString());
		}
	}
	
	
	
	
	@Autowired
	IProductDefineBusiSV iProductDefineSV;
	@Test
	public void add(){
		String json=new ReadJsonUtil().ReadFile("C:\\Users\\scorpion\\Desktop\\dim.json");
		ProductRequest req=JSON.parseObject(json, ProductRequest.class);
		iProductDefineSV.addProduct(req);
	}
public static void main(String[] args) {
	String json=new ReadJsonUtil().ReadFile("C:\\Users\\scorpion\\Desktop\\dim.json");
	ProductRequest req=JSON.parseObject(json, ProductRequest.class);
	//System.out.println(JSON.toJSONString(req));
	int i=0;
	int j=0;
	StringBuilder pname=new StringBuilder();
	/*int dimsize=req.getDimensions().size();
	if(i==0){
		List<BranchVo> branchs=req.getDimensions().get(i).getBranchs();
		for(BranchVo vo:branchs){
			if(i!=dimsize){
				pname.append(vo.getBranchName());
				i++;
			}
			
		}
	}*/
	//new JsonTest().fun(req);
	//new JsonTest().forfun(0,req,new StringBuilder(),new StringBuilder(),new ArrayList<Member>());
	
	//IProductDefineSV.
}


}
