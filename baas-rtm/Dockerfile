# Pull base image
FROM centos:7
#FROM docker.io/mysql:5.7 
#WORKDIR /
RUN yum install -y java-1.8.0-openjdk

# deploy user dubbo service
RUN mkdir baas-rtm-service && cd /baas-rtm-service && mkdir libs config property

COPY ./libs/3rd-libs/*.jar /baas-rtm-service/libs/3rd-libs/
COPY ./libs/core-libs/*.jar /baas-rtm-service/libs/core-libs/
COPY ./config /baas-rtm-service/config/
COPY ./*.properties /baas-rtm-service/property

# remove some libs
#RUN rm -rf /iPaaS-Service-Dubbo/3rd-libs/httpcore-4.2.5.jar /iPaaS-Service-Dubbo/3rd-libs/httpclient-4.2.6.jar

## copy config files
RUN cd /baas-rtm-service/config 
#&& mkdir context paas dubbo

COPY ./config/context/* /baas-rtm-service/config/context/
COPY ./config/paas/* /baas-rtm-service/config/paas/
COPY ./config/dubbo/* /baas-rtm-service/config/dubbo/

#copy dubbo files
RUN cd /baas-rtm-service/config/dubbo
# && mkdir provider consumer
COPY ./config/dubbo/provider/* /baas-rtm-service/config/dubbo/provider
#COPY ./config/dubbo/consumer/* /baas-rtm-service/config/dubbo/consumer 
#mkdir logs path
RUN cd /baas-rtm-service && mkdir logs && cd /baas-rtm-service/logs && mkdir opt-baas-logs

## copy start script
COPY ./baas-rtm-service.sh /baas-rtm-service.sh
RUN chmod 755 /baas-rtm-service.sh

RUN cd /etc && rm -f localtime && ln -s  /usr/share/zoneinfo/Asia/Shanghai /etc/localtime
# set start parameter for dubbo service
ENV COMMON_LIB_HOME /baas-rtm-service
ENV PATH $CATALINA_HOME/bin:$PATH

# Expose ports.
EXPOSE 10771

# Define default command.
CMD ["./baas-rtm-service.sh"]
