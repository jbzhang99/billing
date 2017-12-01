# Pull base image
FROM biu-bj-baas-app3:5000/jdk:7 
MAINTAINER yanggz<yanggz@asiainfo.com>

# deploy user dubbo service
COPY ./build/libs /dubbo-service/libs/
COPY ./build/config /dubbo-service/config/

#mkdir logs path
RUN cd /dubbo-service && mkdir logs && cd /dubbo-service/logs

## copy start script
COPY ./script/start-dubbo-service.sh /start-dubbo-service.sh
COPY ./script/start-cache-service.sh /start-cache-service.sh
RUN chmod 755 /*.sh

# Expose dubbo/rest ports. 根据各自的dubbo端口进行调整
EXPOSE 10773

# Define default command.
CMD ["./start-dubbo-service.sh"] 