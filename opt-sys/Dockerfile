FROM centos:7

MAINTAINER ai-opt

RUN yum install -y java-1.8.0-openjdk

#modify system time
RUN cd /etc && rm -f localtime && ln -s  /usr/share/zoneinfo/Asia/Shanghai /etc/localtime

# copy service files
COPY ./build/config opt-sys-service/config
COPY ./build/libs opt-sys-service/libs

# copy shell
COPY ./script/opt-sys-service.sh opt-sys-service
COPY ./script/opt-sys-cache.sh opt-sys-service
RUN chmod 755 opt-sys-service/*.sh

RUN mkdir opt-sys-service/logs

#EXPOSE 10882

ENV APP_HOME /opt-sys-service

CMD ["./opt-sys-service/opt-sys-service.sh"]

