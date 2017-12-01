程序入口:com.ai.runner.center.ctp.rtm.flow.StartServiceFlow
配置注意说明  app-properties.xml  主要配置了线程数 首次间隔  之后每次间隔
		  ctp-rtm.properties  主要配置文件，配置程序参数
		  hbase-site.xml      hbase配置
		  mysql.db.properties mysql配置,注意文件命名第一个mysql需要跟ctp-rtm.properties配置的一致
		  securityPolicy.xml  请求联通soap必须要的文件，不需要修改