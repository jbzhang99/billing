
FROM centos:7

MAINTAINER ai-opt

RUN yum install -y java-1.8.0-openjdk

# copy service files
COPY ./build/config baas-ccp/config
COPY ./build/libs baas-ccp/libs

# copy shell
COPY ./script/baas-ccp-service.sh baas-ccp
COPY ./script/baas-ccp-cache.sh baas-ccp
RUN chmod 755 baas-ccp/*.sh

RUN mkdir baas-ccp/logs

EXPOSE 10772

ENV APP_HOME /baas-ccp

RUN cd /etc && rm -f localtime && ln -s  /usr/share/zoneinfo/Asia/Shanghai /etc/localtime

CMD ["./baas-ccp/baas-ccp-service.sh"]


