1.编译打包
gradle build -x test

2.生成镜像
docker build -t biu-bj-baas-app3:5000/baas-mt-web:1.0 ./

3. 运行镜像
docker run -d --name=baas-mt-web-1.0 \
--net=host -p 30771:8080 \
-e "ZK_ADDR=10.1.234.160:28381" \
-e "DUBBO_REGISTRY=10.1.234.160:28381" \
-e "DUBBO_PROTOCOL=dubbo" \
-e "DUBBO_PORT=10880" \
biu-bj-baas-app3:5000/baas-mt-web:1.0