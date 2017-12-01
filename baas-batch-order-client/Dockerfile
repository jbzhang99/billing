# Pull base image
FROM 10.1.234.164:5000/crontab:test2
#FROM centos:7
MAINTAINER gucl<gucl@asiainfo.com>
#RUN yum install -y java-1.8.0-openjdk
# deploy user dubbo service

##set local time
RUN cd /etc && rm -f localtime && ln -s  /usr/share/zoneinfo/Asia/Shanghai /etc/localtime
RUN echo "UTC/GMT+08:00" >/etc/timezone


## copy amount script
COPY ./script/baas-batch-amount-client.sh /baas-batch-amount-client.sh
RUN chmod 755 /baas-batch-amount-client.sh

COPY ./script/setEnv.sh /setEnv.sh
RUN chmod 755 /setEnv.sh

## copy batch script
COPY ./script/startService.sh /startService.sh
RUN chmod 755 /startService.sh

# set start parameter for dubbo service
ENV COMMON_LIB_HOME /baas-batch-order-client

COPY ./build/config /baas-batch-order-client/config/
#COPY ./TestDate.class /
COPY ./clock /
#mkdir logs path
RUN cd /baas-batch-order-client && mkdir logs && cd /baas-batch-order-client/logs && mkdir opt-baas-logs
RUN cd / && mkdir logs
# RUN  echo "0 2 * * *  /baas-batch-amount-client.sh >> /logs/crontab.log" >> /var/spool/cron/crontabs/root
# Expose ports.
EXPOSE 11221

# Define default command.
WORKDIR /

COPY ./build/libs /baas-batch-order-client/libs/
CMD service cron restart && /startService.sh
# /opt/zookeeper/bin/zkServer.sh start-foreground 