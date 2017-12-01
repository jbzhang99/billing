# Pull base image
FROM centos:7

RUN mkdir pkg

RUN yum install -y java-1.8.0-openjdk
RUN yum install -y unzip

#modify system time
RUN cd /etc && rm -f localtime && ln -s  /usr/share/zoneinfo/Asia/Shanghai /etc/localtime

# Install tomcat7
ADD ./pkg/apache-tomcat-8.0.35.tar.gz /pkg/apache-tomcat-8.0.35
RUN  mv /pkg/apache-tomcat-8.0.35/apache-tomcat-8.0.35 /opt/apache-tomcat-8.0.35 && ls /opt/apache-tomcat-8.0.35

#change config parameter
RUN sed -i '/\# OS/i JAVA_OPTS="$JAVA_OPTS -server -Xms512M -Xmx512M -XX:PermSize=64M -XX:MaxPermSize=128M  -XX:+UseConcMarkSweepGC -XX:ParallelGCThreads=8 -XX:+PrintCommandLineFlags -XX:+PrintGCDetails -XX:+UseCompressedOops -XX:-UseLargePagesIndividualAllocation -XX:+HeapDumpOnOutOfMemoryError" \n if [[ "$JAVA_OPTS" != *-Djava.security.egd=* ]]; then \n   JAVA_OPTS="$JAVA_OPTS -Djava.security.egd=file:/dev/./urandom" \n  fi'  /opt/apache-tomcat-8.0.35/bin/catalina.sh
RUN rm -fr /opt/apache-tomcat-8.0.35/webapps/*

COPY ./build/libs/baas-mt.war /opt/apache-tomcat-8.0.35/webapps/ROOT.war
RUN cd /opt/apache-tomcat-8.0.35/webapps && unzip -oq ROOT.war -d ROOT


ENV CATALINA_HOME /opt/apache-tomcat-8.0.35
ENV PATH $PATH:$CATALINA_HOME/bin
ENV PATH $CATALINA_HOME/bin:$PATH


#ADD ./script/tomcat8.sh /etc/init.d/tomcat8
#RUN chmod 755 /etc/init.d/tomcat8
ADD ./script/baas-mt-web.sh /baas-mt-web.sh
RUN chmod 755 /*.sh

RUN mkdir logs
# Expose ports.
#EXPOSE 10771

RUN rm -fr /pkg

# Define default command.
CMD ["/baas-mt-web.sh"]