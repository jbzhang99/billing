package com.ai.baas.pt.web.controller.business;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * 功能页
 * @author gucl
 */
@RestController
@RequestMapping("/guide")
public class GuideController {
	
	private static final Logger LOG = LoggerFactory.getLogger(GuideController.class);
	
	@RequestMapping("/list")
    public ModelAndView toList(HttpServletRequest request) {

        ModelAndView view = new ModelAndView("jsp/guide/guidelist");
        return view;
    }
	//客户信息同步接口
	@RequestMapping("/custinfo")
	public ModelAndView toCustInfo(HttpServletRequest request) {
		
		ModelAndView view = new ModelAndView("jsp/guide/custinfo");
		return view;
	}
	//订购信息同步接口
	@RequestMapping("/custordinfo")
	public ModelAndView toCustOrderInfo(HttpServletRequest request) {
		
		ModelAndView view = new ModelAndView("jsp/guide/custordinfo");
		return view;
	}
	//账单查询接口
	@RequestMapping("/billinfo")
	public ModelAndView toBillInfo(HttpServletRequest request) {
		
		ModelAndView view = new ModelAndView("jsp/guide/billinfo");
		return view;
	}
	//使用量查询接口
	@RequestMapping("/useageinfo")
	public ModelAndView toCustBillSv(HttpServletRequest request) {
		
		ModelAndView view = new ModelAndView("jsp/guide/useageinfo");
		return view;
	}
	//欠费查询接口
	@RequestMapping("/feeowed")
	public ModelAndView toFeeOwedSv(HttpServletRequest request) {
		
		ModelAndView view = new ModelAndView("jsp/guide/feeowed");
		return view;
	}
	
	@RequestMapping("/download/{fileName}")
	public void downloadFile(HttpServletRequest request, HttpServletResponse response,@PathVariable("fileName") String fileName) {
		OutputStream os = null;
		/*Properties prop=new Properties();
		prop.setProperty("opt-billing", "运营家·billing能力开发文档");
		prop.setProperty("custinfo", "客户信息同步接口说明");
		prop.setProperty("billinfo", "账单查询接口说明");
		prop.setProperty("custordinfo", "订购信息同步接口说明");
		prop.setProperty("useageinfo", "使用量查询接口说明");
		prop.setProperty("feeowed", "欠费查询接口说明");*/
		try {
			os = response.getOutputStream();// 取得输出流
//			String exportFileName = prop.getProperty(fileName, fileName)+".pdf";
			String exportFileName = fileName+".pdf";
			response.reset();// 清空输出流
			response.setContentType("application/pdf");// 定义输出类型
			response.setHeader("Content-disposition", "attachment; filename=" + exportFileName);// 设定输出文件头
			String filePath="/docs/"+fileName+".pdf";
			String realPath=request.getRealPath(filePath);
			FileInputStream fis =  new FileInputStream(realPath); 
	         byte[] b = new byte[1024]; 
	         int i = 0; 
	         while((i = fis.read(b)) > 0) 
	         { 
	        	 os.write(b, 0, i); 
	         } 
	         os.flush(); 
			
			os.close();
		} catch (Exception e) {
			LOG.error("下载文件失败",e);
			if(os!=null){
				try {
					os.close();
				} catch (IOException e1) {
					LOG.error("操作异常",e1);
				}
			}
		}

	}//end of download
	
	@RequestMapping("/download/chk/{fileName}")
	public ModelAndView downloadCheckLogin(HttpServletRequest request, HttpServletResponse response,@PathVariable("fileName") String fileName) {
		return new ModelAndView("redirect:../../"+fileName);

	}//end of download
	
}
