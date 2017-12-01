package com.ai.baas.bmc.topology.simulator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.Charsets;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;

public class BaasClientProducer {
	private String encoding = "UTF-8";
	public final static String FIELD_SPLIT = new String(new char[] {(char) 1 });
	public final static String RECORD_SPLIT = new String(new char[] {(char) 2 });
	public final static String PACKET_HEADER_SPLIT = ",";
	private String service_id = "VOICE";//PILE
	private String tenant_id = "TR";
	private String source = "TEST";
	private String fileName = "";
	
	public void send(String path){
		InputStream in = null;
		BufferedReader reader = null;
		File file;
		try{
			file = FileUtils.getFile(path);
			fileName = file.getName();
			in = new FileInputStream(file);
			reader = new BufferedReader(new InputStreamReader(in, Charsets.toCharset(encoding)));
			List<String> lines = new ArrayList<String>();
			int i =0;
			String sLine = reader.readLine();
			while (sLine != null) {
			    do{
				lines.add(sLine);
				i++;
			    } while ((sLine =  reader.readLine()) != null&&i%10 != 0 );

    			String message = assembleMessage(lines);
    			System.out.println("message"+i+"----"+message);
    			//System.out.println(UUID.randomUUID().toString());
    			ProducerProxy.getInstance().sendMessage(message);
    			lines.clear();
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			IOUtils.closeQuietly(in);
		}
	}
	
	private String assembleMessage(List<String> lines){
		StringBuilder busData = new StringBuilder();	
		String[] fieldNames = null;
		for(int i=0;i<lines.size();i++){
			StringBuilder record = new StringBuilder();
			busData.append(tenant_id).append(FIELD_SPLIT);
			busData.append(service_id).append(FIELD_SPLIT);
			busData.append(source).append(FIELD_SPLIT);
			fieldNames = StringUtils.splitPreserveAllTokens(lines.get(i), ";");
			for(String fieldName:fieldNames){
				record.append(fieldName).append(FIELD_SPLIT);
			}
			busData.append(record.substring(0, record.length()-1)).append(RECORD_SPLIT);
		}
		return busData.substring(0, busData.length()-1).toString();
	}
	
	
	public static void main(String[] args) {
	    BaasClientProducer simulator = new BaasClientProducer();
	    String path = LoadProps.getPATH();
//		String path = "D:\\A\\B\\test01.txt";
//		String path = "D:\\A\\test01";	
		simulator.send(path);
//		simulator.send(args[0]);
	}

}
