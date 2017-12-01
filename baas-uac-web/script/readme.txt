1.编译打包
#一定要先clean
gradle clean
#然后再打包
gradle build -x test

2.生成镜像
#Dockerfile 默认采用的是openjdk8，
#如果要用官方的orackejdk8 需手工将jdk-8u101-linux-x64.tar.gz 拷贝到pkg目录下。
docker build -t citic-uac-web:v1.2 .
#在本地生成带私服前缀的镜像  (每次打镜像前版本号要更新)
docker build -t 10.1.234.164:5000/citic-uac-web:v1.2 .
#将镜像推送到私服
docker push 10.1.234.164:5000/citic-uac-web:v1.2

3. 运行镜像
#--net=host  表示为主机(host)模式  去掉该配置，默认为桥接(bridge)模式
#-e 代表需要设置的环境变量
docker run -d --name citic-uac-web-v1.2 --net=host -p 14125:8080  -e "casServerLoginUrl=http://10.1.234.163:14125/login"  -e "casServerUrlPrefix=http://10.1.234.163:14125"   -e "serverName=http://10.1.234.163:14125"   -e "logOutServerUrl=http://10.1.234.163:14125/logout"   -e "logOutBackUrl=http://10.1.234.163:14125"   -e "casServerLoginUrl_Inner=http://10.1.234.163:14125/login"  -e "casServerUrlPrefix_Inner=http://10.1.234.163:14125"   -e "serverName_Inner=http://10.1.234.163:14125"   -e "logOutServerUrl_Inner=http://10.1.234.163:14125/logout"   -e "logOutBackUrl_Inner=http://10.1.234.163:14125"    -e "innerDomains=sso.citicdao.com,billingwebui.citicdao.com,billing.citicdao.com,user.citicdao.com,webui.citicdao.com,aliapt.citicdao.com,scapt.cticdao.com,service.citicdao.com" -e "ZK_ADDR=10.1.130.84:39181"   -e "DUBBO_REGISTRY=10.1.130.84:39181"   -e "DUBBO_PROTOCOL=dubbo"   -e "DUBBO_PORT=10881"  10.1.234.164:5000/citic-uac-web:v1.2  
#查看镜像启动日志
docker logs citic-uac-web-v1.2
#进入容器，查看镜像内部的情况
docker exec -it citic-uac-web-v1.2 /bin/bash
#删除运行的容器
docker rm -fv citic-uac-web-v1.2

#=============更新日志========================#
*2016-09-05 
1）更新统一session jar包，解决IE下无法登陆的问题
*2016-08-30 
1）Dockerfile 添加修改时区为上海






