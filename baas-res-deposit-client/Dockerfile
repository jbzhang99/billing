FROM centos:7

MAINTAINER ai-opt

RUN yum install -y java-1.8.0-openjdk

# copy service files
COPY ./build/config baas-res-deposit-client/config
COPY ./build/libs baas-res-deposit-client/libs

# copy shell
COPY ./script/resdeposit-easy.sh baas-res-deposit-client
RUN chmod 755 baas-res-deposit-client/resdeposit-easy.sh

RUN mkdir baas-res-deposit-client/logs

EXPOSE 10774

ENV APP_HOME /baas-res-deposit-client

RUN cd /etc && rm -f localtime && ln -s  /usr/share/zoneinfo/Asia/Shanghai /etc/localtime

CMD ["./baas-res-deposit-client/resdeposit-easy.sh"]



