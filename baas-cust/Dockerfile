
FROM centos:7

MAINTAINER ai-opt

RUN yum install -y java-1.8.0-openjdk

# copy service files
COPY ./build/config baas-cust/config
COPY ./build/libs baas-cust/libs

# copy shell
COPY ./script/baas-cust-service.sh baas-cust

# add other shell
COPY ./script/cust-deploy.sh baas-cust
COPY ./script/cust-ReplicationController.json baas-cust
COPY ./script/cust-service.json baas-cust

RUN chmod 755 baas-cust/*.sh

RUN mkdir baas-cust/logs

EXPOSE 10774

ENV APP_HOME /baas-cust

RUN cd /etc && rm -f localtime && ln -s  /usr/share/zoneinfo/Asia/Shanghai /etc/localtime

CMD ["./baas-cust/baas-cust-service.sh"]


