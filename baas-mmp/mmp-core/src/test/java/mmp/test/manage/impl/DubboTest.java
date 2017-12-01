package mmp.test.manage.impl;


import org.springframework.beans.factory.annotation.Autowired;

import com.ai.runner.center.mmp.api.manager.interfaces.SMSServices;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration({ "/dubbo/consumer/mmp-consumer.xml" })
public class DubboTest 
{
	//@Reference
//	@Autowired
//	private MMPManager mmpManager;

	@Autowired
	private SMSServices sMSServices;

//	@org.junit.Test
//	public void saveUmsMsgService(){
//		UmsMsgService ums=new UmsMsgService();
////		ums.setServiceId(1003l);
//		ums.setInsertTime(new Timestamp(System.currentTimeMillis()));
//		ums.setPriorityLevel(3);
//		ums.setServicename("llll");
//		ums.setServicespecification("main");
//		ums.setStatus(1);
//		ums.setSystemId("3");
//		ums.setTenantId("3");
//		ums.setUpdateTime(new Timestamp(System.currentTimeMillis()));
//		
//		Gson gson = new Gson();
//		String param=gson.toJson(ums);
//		
//		String resultCode=mmpManager.saveUmsMsgService(param);
//		System.out.println("param:"+param+"resultCode:"+resultCode);
//		
//	}
	
//	@org.junit.Test
//	public void delUmsMsgService(){
//		UmsMsgService ums=new UmsMsgService();
//		ums.setServiceId(1000l);
//		
//		Gson gson=new Gson();
//		String param=gson.toJson(ums);
//		
//		String resultCode=mmpManager.delUmsMsgService(param);
//		System.out.println("param:"+param+"resultCode:"+resultCode);
//		
//	}
//	
//	@org.junit.Test
//	public void getUmsMsgService(){
//		String servicename="hh";
//		int pageNow=1;
//		int pageCount=2;
//		Map map=new HashMap();
//		map.put("servicename", servicename);
//		map.put("pageNow",pageNow);
//		map.put("pageCount", pageCount);
//		
//		Gson gson=new Gson();
//		String param=gson.toJson(map);
//		String returnJson=mmpManager.getUmsMsgService(param);
//		System.out.println(returnJson);
//		List<UmsMsgService> umsList=gson.fromJson(returnJson, new TypeToken<List<UmsMsgService>>(){}.getType());
//		for(int i=0;i<umsList.size();i++){
//			System.out.println(umsList.get(i).getServiceId()+":"+umsList.get(i).getServicename());
//		}
//		
//	}
//	
////	@org.junit.Test
//	public void saveUmsMsgTemplate(){
//		UmsMsgTemplate umt=new UmsMsgTemplate();
//		umt.setInsertTime(new Timestamp(System.currentTimeMillis()));
//		umt.setSequenceId(1002l);
//		umt.setTemplateName("kkss");
//		umt.setRetryTimes(3);
//		umt.setSbeginTime("hh:hh:hh");
//		umt.setScloseTime("ss:ss:ss");
//		umt.setServiceId(1000l);
//		umt.setSystemId("uuuu");
//		umt.setTemplateId(199l);
//		umt.setTemplateText("text");
//		umt.setTenantId("lll");
//		umt.setUpdateTime(null);
////		umt.setUpdateTime(new Timestamp(System.currentTimeMillis()));
//		
//		Gson gson = new Gson();
//		String param=gson.toJson(umt);
//		
//		String resultCode=mmpManager.saveUmsMsgTemplate(param);
//		System.out.println("param:"+param+"resultCode:"+resultCode);
//	}
//	
////	@org.junit.Test
//	public void delUmsMsgTemplate(){
//		UmsMsgTemplate umt=new UmsMsgTemplate();
//		umt.setSequenceId(1000l);
//		
//		
//		Gson gson=new Gson();
//		String param=gson.toJson(umt);
//		
//		String resultCode=mmpManager.delUmsMsgTemplate(param);
//		System.out.println("param:"+param+"resultCode:"+resultCode);
//	}
//	
//	//@org.junit.Test
//	public void getUmsMsgTemplate(){
//		String templateName="1";
//		int pageNow=1;
//		int pageCount=3;
////		Map map=new HashMap();
////		map.put("templateName", templateName);
////		map.put("pageNow",pageNow);
////		map.put("pageCount", pageCount);
//		PageEntity pe=new PageEntity();
//		pe.setCurrentPage(pageNow);
//		pe.setPageSize(pageCount);
//		pe.setName(templateName);
//		Map<String ,String > params=new HashMap<>();
//		params.put("serviceId", "1012");
//		pe.setParams(params);
//	
//		
//		Gson gson=new Gson();
//		String param=gson.toJson(pe);
//		System.out.println(param);
//		System.out.println("-----------");
//		String returnJson=mmpManager.getUmsMsgTemplate(param);
//		System.out.println(returnJson);
//		PageResult<UmsMsgTemplate> pr=gson.fromJson(returnJson, PageResult.class);
//		List<UmsMsgTemplate> umtList=pr.getResultList();
//		for(int i=0;i<umtList.size();i++){
//			System.out.println(umtList.get(i).getSequenceId()+":"+umtList.get(i).getTemplateName());
//		}
//		
//	}
//	
////	@org.junit.Test
//	public void getOneServiceByServiceId(){
//		Gson gson=new Gson();
//		Long serviceId=1000l;
//		UmsMsgService ums=new UmsMsgService();
//		ums.setServiceId(serviceId);
//		String params=gson.toJson(ums);
//		System.out.println(params);
//		String param=mmpManager.getOneServiceByServiceId(params);
//		UmsMsgService ums1=gson.fromJson(param, UmsMsgService.class);
//		System.out.println(ums1);
//	}
//	
//////	@org.junit.Test
////	public void getOneTemplateByTemplateId(){
////		Gson gson=new Gson();
////		Long templateId=3l;
////		UmsMsgTemplate umt=new UmsMsgTemplate();
////		umt.setTemplateId(templateId);
////		String params=gson.toJson(umt);
////		System.out.println(params);
////		String param=mmpManager.getOneTemplateByTemplateId(params);
////		UmsMsgTemplate umt1=gson.fromJson(param, UmsMsgTemplate.class);
////		System.out.println(umt1);
////	}
//	
////	@org.junit.Test
////	public void existTemplateByServiceId(){
////		Long serviceId=2000000l;
////		boolean flag=mmpManager.existTemplateByServiceId(serviceId);
////		System.out.println(flag);
////	}
//	@org.junit.Test
//	public void saveSgipSrcGsm(){
//	    SgipSrcGsm ssg=new SgipSrcGsm();
//	    ssg.setCreateTime(new Timestamp(System.currentTimeMillis()));
//	    ssg.setFlag(1);
//	    ssg.setGsmcontent("123");
//	    ssg.setPhone("123456");
//	    ssg.setPriority(9);
//	    ssg.setServicetype("1");
//	    ssg.setSrcName("hhh");
//	    ssg.setTemplateId(123l);
//	    ssg.setVerifyid(123l);
//		sMSServices = new SMSServiceImple();
//
//		SMDataInfoNotify sMDataInfoNotify=new SMDataInfoNotify();
//	    sMDataInfoNotify.setTenantPwd("99");
//	    sMDataInfoNotify.setSystemId("99");
//	    sMDataInfoNotify.setTenantId("99");
//	    sMDataInfoNotify.setMsgSeq("99");
//	    List<SMData> sMDataList=new ArrayList<SMData>();
//	    SMData sMData=new SMData();
//	    sMData.setGsmContent("99");
//	    sMData.setPhone("99");
//	    sMData.setServiceType("99");
//	    sMData.setTemplateId("99");
//	    sMDataList.add(sMData);
//	    sMDataInfoNotify.setDataList(sMDataList);
//	    try {
//	    	String resultCode=sMSServices.dataInput(sMDataInfoNotify);
//	    	System.out.println("resultCode:"+resultCode);
//	    } catch (CallerException e) {
//	    	e.getStackTrace();
//	    }
//        
//        
//	}
}