# Pull base image
FROM 10.1.234.164:5000/jdk:7 
MAINTAINER mayt<mayt@asiainfo.com>

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
EXPOSE 10886

# Define default command.
CMD ["./start-dubbo-service.sh"] 