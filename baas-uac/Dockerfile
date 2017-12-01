# Pull base image
FROM centos:7
MAINTAINER gucl<gucl@asiainfo.com>
#FROM docker.io/mysql:5.7 
WORKDIR /
RUN yum install -y java-1.8.0-openjdk

# deploy user dubbo service
COPY ./build/libs /baas-citic-uac-service/libs/
COPY ./build/config /baas-citic-uac-service/config/

#mkdir logs path
RUN cd /baas-citic-uac-service && mkdir logs && cd /baas-citic-uac-service/logs && mkdir opt-baas-logs

## copy start script
COPY ./script/baas-citic-uac-service.sh /baas-citic-uac-service.sh
RUN chmod 755 /baas-citic-uac-service.sh

RUN cd /etc && rm -f localtime && ln -s  /usr/share/zoneinfo/Asia/Shanghai /etc/localtime

# set start parameter for dubbo service
ENV COMMON_LIB_HOME /baas-citic-uac-service

# Expose ports.
EXPOSE 18881

# Define default command.
CMD ["./baas-citic-uac-service.sh"]