# Pull base image  
FROM centos:7  
MAINTAINER gucl<gucl@asiainfo.com>  
RUN mkdir pkg

RUN yum install -y java-1.8.0-openjdk
RUN yum install -y unzip

# Install tomcat7 
ADD ./pkg/apache-tomcat-8.0.35.tar.gz /pkg/apache-tomcat-8.0.35
RUN  mv /pkg/apache-tomcat-8.0.35/apache-tomcat-8.0.35 /opt/apache-tomcat-8.0.35 && ls /opt/apache-tomcat-8.0.35
#change config parameter

RUN sed -i '/\# OS/i JAVA_OPTS="$JAVA_OPTS -server -Xms512M -Xmx512M -XX:PermSize=64M -XX:MaxPermSize=128M  -XX:+UseConcMarkSweepGC -XX:ParallelGCThreads=8 -XX:+PrintCommandLineFlags -XX:+PrintGCDetails -XX:+UseCompressedOops -XX:-UseLargePagesIndividualAllocation -XX:+HeapDumpOnOutOfMemoryError" \n if [[ "$JAVA_OPTS" != *-Djava.security.egd=* ]]; then \n   JAVA_OPTS="$JAVA_OPTS -Djava.security.egd=file:/dev/./urandom" \n  fi'  /opt/apache-tomcat-8.0.35/bin/catalina.sh
RUN rm -fr /opt/apache-tomcat-8.0.35/webapps/*

COPY ./build/libs/citic-uac.war /opt/apache-tomcat-8.0.35/webapps/sso.war
RUN cd /opt/apache-tomcat-8.0.35/webapps && unzip -oq sso.war -d sso

RUN cd /etc && rm -f localtime && ln -s  /usr/share/zoneinfo/Asia/Shanghai /etc/localtime


ENV CATALINA_HOME /opt/apache-tomcat-8.0.35  
ENV PATH $PATH:$CATALINA_HOME/bin  
ENV PATH $CATALINA_HOME/bin:$PATH


ADD ./script/citic-uac-web.sh /citic-uac-web.sh
RUN chmod 755 /*.sh

RUN mkdir /logs
# Expose ports.  
EXPOSE 8080  

RUN rm -fr /pkg
RUN rm -fr /opt/apache-tomcat-8.0.35/webapps/sso.war
  
# Define default command.  
CMD ["/citic-uac-web.sh"]