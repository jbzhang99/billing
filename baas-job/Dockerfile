FROM 10.1.234.164:5000/crontab:test2

# copy service files
COPY build/config /baas-job/config
COPY build/libs /baas-job/libs

# copy shell
COPY script/baas-job-citicbank-charge-autotask.sh /
COPY script/package-scan-autotask.sh /
COPY script/loadConfig.sh /
RUN chmod +x /baas-job-citicbank-charge-autotask.sh
RUN chmod +x /package-scan-autotask.sh
RUN chmod +x /loadConfig.sh
ENV COMMON_LIB_HOME /baas-job

# add crontab task
RUN cd / && mkdir logs
RUN  echo "30 0 * * *  /baas-job-citicbank-charge-autotask.sh >> /logs/citicbank-charge-autotask.log" >> /var/spool/cron/crontabs/root
RUN  echo "30 0 * * *  /package-scan-autotask.sh >> /logs/package-scan-autotask.log" >> /var/spool/cron/crontabs/root

RUN cd /etc && rm -f localtime && ln -s  /usr/share/zoneinfo/Asia/Shanghai /etc/localtime
RUN echo "UTC/GMT+08:00" >/etc/timezone

## copy start script
WORKDIR /
#CMD ["service cron start && ./opt/run.sh"]
CMD service cron restart &&/loadConfig.sh && /opt/zookeeper/bin/zkServer.sh start-foreground 