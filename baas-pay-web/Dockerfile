# Pull base image tomcat:8.0, port is 8080,
#
#default Tomcat environment
#   CATALINA_BASE:   /usr/local/tomcat
#   CATALINA_HOME:   /usr/local/tomcat
#   CATALINA_TMPDIR: /usr/local/tomcat/temp
#   JRE_HOME:        /usr
#   CLASSPATH:       /usr/local/tomcat/bin/bootstrap.jar:/usr/local/tomcat/bin/tomcat-juli.jar
#  /usr/local/tomcat/webapps:
#        docs  examples  host-manager  manager

FROM 10.1.234.164:5000/tomcat:8.x
#FROM tomcat:8.0

MAINTAINER mayt<mayt@asiainfo.com>

# remove exisited info
RUN rm -rf /opt/tomcat/webapps/* && mkdir /opt/tomcat/webapps/ROOT
#RUN rm -rf /usr/local/tomcat/webapps/* && mkdir /usr/local/tomcat/webapps/ROOT

# copy to the war package
COPY ./build/libs/baas-pay.war /opt/tomcat/webapps/baas-pay/baas-pay.war
#COPY ./build/libs/baas-pay.war /usr/local/tomcat/webapps/baas-pay/baas-pay.war

RUN cd /opt/tomcat/webapps/baas-pay && jar -xf baas-pay.war && rm -rf /opt/tomcat/webapps/baas-pay/baas-pay.war
#RUN cd /usr/local/tomcat/webapps/baas-pay && jar -xf baas-pay.war && rm -rf /usr/local/tomcat/webapps/baas-pay/baas-pay.war

ADD ./script/start-web.sh /start-web.sh
RUN chmod 755 /*.sh

#拷贝证书
#COPY ./assets /assets

# Define default command.  CMD ["catalina.sh", "run"]
CMD ["/start-web.sh"]
