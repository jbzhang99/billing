# Pull base image
FROM centos:7

#FROM docker.io/mysql:5.7 
WORKDIR /
RUN yum install -y java-1.8.0-openjdk

#set timezone
RUN cd /etc && rm -f localtime && ln -s  /usr/share/zoneinfo/Asia/Shanghai /etc/localtime

# deploy user dubbo service
COPY ./build/libs /baas-bmc-service/libs/
COPY ./build/config /baas-bmc-service/config/

#mkdir logs path
RUN cd /baas-bmc-service && mkdir logs && cd /baas-bmc-service/logs && mkdir opt-baas-logs

## copy start script
COPY ./script/baas-bmc-service.sh /baas-bmc-service.sh
RUN chmod 755 /baas-bmc-service.sh

# set start parameter for dubbo service
ENV COMMON_LIB_HOME /baas-bmc-service

# Expose ports.
EXPOSE 10991

# Define default command.
CMD ["./baas-bmc-service.sh"]