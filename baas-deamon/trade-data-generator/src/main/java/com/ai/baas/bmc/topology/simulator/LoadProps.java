package com.ai.baas.bmc.topology.simulator;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Properties;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoadProps implements Serializable {

	private static final long serialVersionUID = 1L;
	private static Logger logger = LoggerFactory.getLogger(LoadProps.class);
	private static String BROKER_LIST;
	private static String SERIALIZER_CLASS;
	private static String REQUIRED_ACKS;
	private static String KAFKA_TOPIC;
	private static String PATH;
	
	static {
		String path = "/config/kafka.properties";
		File configFile = new File(path);
		Properties cfg = new Properties();
		InputStream in = null;
		try {
			if (configFile.exists())
				in = new FileInputStream(configFile);
			else
				in = LoadProps.class.getResourceAsStream(path);

			if (in != null) {
				try {
					cfg.load(in);
				} finally {
					in.close();
				}
			}		
			BROKER_LIST =  cfg.getProperty("metadata.broker.list");
			SERIALIZER_CLASS= cfg.getProperty("serializer.class");
			REQUIRED_ACKS=cfg.getProperty("request.required.acks") ;
			KAFKA_TOPIC = cfg.getProperty("kafka.topic");
			PATH = cfg.getProperty("file.path");
		} catch (Exception e) {
			logger.error("kafka.properties couldn't be load ,please check!");
		}
	}

//	public static ProducerConfig getProducerConfig() {
//		Properties props = new Properties();
//		System.out.println(ZK_CONNECT);
//		props.put("zk.connect", ZK_CONNECT);
//		props.put("metadata.broker.list", METADATA_BROKER_LIST);
//		props.put("serializer.class", KAFKA_SERIALIZER);
//		props.put("zk.connectiontimeout.ms", ZK_CONNECTIONTIMEOUT);
//
//		return new ProducerConfig(props);
//	}

	public static Properties getConfig() {
		Properties props = new Properties();
	    props.put("metadata.broker.list", BROKER_LIST);
	    props.put("serializer.class", SERIALIZER_CLASS); 
	    props.put("request.required.acks",REQUIRED_ACKS);
		return props;
	}

	private LoadProps() {
	}

    public static String getBROKER_LIST() {
        return BROKER_LIST;
    }

    public static void setBROKER_LIST(String bROKER_LIST) {
        BROKER_LIST = bROKER_LIST;
    }

    public static String getSERIALIZER_CLASS() {
        return SERIALIZER_CLASS;
    }

    public static void setSERIALIZER_CLASS(String sERIALIZER_CLASS) {
        SERIALIZER_CLASS = sERIALIZER_CLASS;
    }

    public static String getREQUIRED_ACKS() {
        return REQUIRED_ACKS;
    }

    public static void setREQUIRED_ACKS(String rEQUIRED_ACKS) {
        REQUIRED_ACKS = rEQUIRED_ACKS;
    }

    public static String getKAFKA_TOPIC() {
        return KAFKA_TOPIC;
    }

    public static void setKAFKA_TOPIC(String kAFKA_TOPIC) {
        KAFKA_TOPIC = kAFKA_TOPIC;
    }

    public static String getPATH() {
        return PATH;
    }

    public static void setPATH(String pATH) {
        PATH = pATH;
    }



	
}

