1.编译打包
#一定要先clean
gradle clean
#然后再打包
gradle build -x test -x check

2.生成镜像
#在本地生成带私服前缀的镜像
docker build -t biu-bj-baas-app3:5000/baas-omc:1.0 ./   (每次打镜像前版本号要更新)

#将镜像推送到私服
docker push biu-bj-baas-app3:5000/baas-omc:1.0

3. 运行镜像
#--net=host  表示为主机(host)模式  去掉该配置，默认为桥接(bridge)模式
#-e 代表需要设置的环境变量
docker run -d --name baas-omc -p 30773:10773 \
-e "REST_REGISTRY_ADDR=10.1.130.84:39181" \
-e "REST_PORT=30773" \
-e "CONTEXT_PATH=baas-omc" \
-e "SDK_MODE=1" \
-e "CCS_NAME=aiopt-baas-omc" \
-e "ZK_ADDR=10.1.130.84:39181" \
10.1.234.164:5000/baas-omc:1.0

#查看镜像启动日志
docker logs baas-omc

#进入容器，查看镜像内部的情况
docker exec -it baas-omc /bin/bash

#删除运行的容器
docker rm -fv baas-omc

#=============配置中心配置项说明========================#



#=============更新日志========================#
*2016-09-23
1）初始版本