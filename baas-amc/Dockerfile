FROM centos:7

MAINTAINER ai-opt

RUN yum install -y java-1.8.0-openjdk

# copy service files
COPY ./build/config baas-amc-service/config
COPY ./build/libs baas-amc-service/libs

# copy shell
COPY ./script/baas-amc-service.sh baas-amc-service
RUN chmod 755 baas-amc-service/baas-amc-service.sh

COPY ./script/baas-amc-totalbill-task.sh baas-amc-service
RUN chmod 755 baas-amc-service/baas-amc-totalbill-task.sh

RUN mkdir baas-amc-service/logs

#EXPOSE 10774

ENV APP_HOME /baas-amc-service

RUN cd /etc && rm -f localtime && ln -s  /usr/share/zoneinfo/Asia/Shanghai /etc/localtime

CMD ["./baas-amc-service/baas-amc-service.sh"]

