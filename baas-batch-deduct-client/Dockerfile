FROM 10.1.234.164:5000/crontab:test2

MAINTAINER ai-opt

#RUN yum install -y java-1.8.0-openjdk
WORKDIR /
# copy service files
COPY ./build/config baas-batch-deduct-client/config
COPY ./build/libs baas-batch-deduct-client/libs

# copy shell
COPY ./script/batch-deduct-client.sh baas-batch-deduct-client
RUN chmod 755 baas-batch-deduct-client/batch-deduct-client.sh

RUN mkdir /logs

EXPOSE 10774

ENV APP_HOME /baas-batch-deduct-client

RUN cd /etc && rm -f localtime && ln -s  /usr/share/zoneinfo/Asia/Shanghai /etc/localtime

CMD service cron restart && /opt/zookeeper/bin/zkServer.sh start-foreground

