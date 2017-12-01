1.编译打包
gradle build -x test
2.生成镜像
在本地生成镜像
docker build -t rtm:1.0 .
docker build -t 10.1.234.164:5000/billing/rtm:1.0 .
docker push 10.1.234.164:5000/billing/rtm:1.0