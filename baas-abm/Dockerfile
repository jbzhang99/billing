FROM centos:7

MAINTAINER ai-opt

RUN yum install -y java-1.8.0-openjdk

# copy service files
COPY ./build/config baas-abm-service/config
COPY ./build/libs baas-abm-service/libs

# copy shell
COPY ./script/baas-abm-service.sh baas-abm-service
RUN chmod 755 baas-abm-service/baas-abm-service.sh

COPY ./script/baas-abm-totalbill-task.sh baas-abm-service
RUN chmod 755 baas-abm-service/baas-abm-totalbill-task.sh

RUN mkdir baas-abm-service/logs

EXPOSE 10774

ENV APP_HOME /baas-abm-service

RUN cd /etc && rm -f localtime && ln -s  /usr/share/zoneinfo/Asia/Shanghai /etc/localtime

CMD ["./baas-abm-service/baas-abm-service.sh"]

