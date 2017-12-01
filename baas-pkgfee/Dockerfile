
FROM centos:7

MAINTAINER ai-opt

RUN yum install -y java-1.8.0-openjdk

# copy service files
COPY ./build/config baas-bmc/config
COPY ./build/libs baas-bmc/libs

# copy shell
COPY ./script/baas-bmc-service.sh baas-bmc
COPY ./script/baas-bmc-cache.sh baas-bmc
RUN chmod 755 baas-bmc/*.sh

RUN mkdir baas-bmc/logs

EXPOSE 10774

ENV APP_HOME /baas-bmc

RUN cd /etc && rm -f localtime && ln -s  /usr/share/zoneinfo/Asia/Shanghai /etc/localtime

CMD ["./baas-bmc/baas-bmc-service.sh"]


