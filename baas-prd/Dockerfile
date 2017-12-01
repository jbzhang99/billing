FROM centos:7

MAINTAINER ai-opt

RUN yum install -y java-1.8.0-openjdk

# copy service files
COPY ./build/config baas-prd/config
COPY ./build/libs baas-prd/libs

# copy shell
COPY ./script/baas-prd-service.sh baas-prd

RUN chmod 755 baas-prd/*.sh

RUN mkdir baas-prd/logs

EXPOSE 10895

ENV APP_HOME /baas-prd

RUN cd /etc && rm -f localtime && ln -s  /usr/share/zoneinfo/Asia/Shanghai /etc/localtime

CMD ["./baas-prd/baas-prd-service.sh"]


