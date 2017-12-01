1.编译打包
#一定要先clean
gradle clean
#然后再打包
gradle build -x test
2.生成镜像
#在本地生成镜像 Dockerfile 默认采用的是openjdk8
docker build -t citic-uac:1.8.30 .
#在本地生成带私服前缀的镜像
docker build -t 10.1.234.164:5000/citic-uac:1.8.30 .   (每次打镜像前版本号要更新)
#将镜像推送到私服
docker push 10.1.234.164:5000/citic-uac:1.8.30

3. 运行镜像
#--net=host  表示为主机(host)模式  去掉该配置，默认为桥接(bridge)模式
#-e 代表需要设置的环境变量
docker run -d --name citic-uac-1.8.30 --net=host -p 18881:18881 -e "REST_REGISTRY_ADDR=10.1.130.84:39181" -e "REST_PORT=18881" -e "CONTEXT_PATH=citic-uac" -e "PROTOCOL=rest" -e "SDK_MODE=1" -e "CCS_NAME=aiopt-baas-citic-uac" -e "ZK_ADDRESS=10.1.130.84:39181"  10.1.234.164:5000/citic-uac:1.8.30 
#查看镜像启动日志
docker logs citic-uac-1.8.30
#进入容器，查看镜像内部的情况
docker exec -it citic-uac-1.8.30 /bin/bash
#删除运行的容器
docker rm -fv citic-uac-1.8.30

#=============更新日志========================#
*2016-08-30 
1）Dockerfile 添加修改时区为上海


