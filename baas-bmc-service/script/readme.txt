1.编译打包
gradle build -x test
2.生成镜像
在本地生成镜像
--docker build -t baas-bmc-service:1.0 .
docker build -t 10.1.234.164:5000/billing/baas-bmc-service:5.6 .
docker push 10.1.234.164:5000/billing/baas-bmc-service:5.6

3. 运行镜像
docker run -d --name baas-bmc-service-01 --net=host -p 10991:10991 -e "REST_REGISTRY_ADDR=10.1.130.84:2181" -e "REST_PORT=10991" -e "CONTEXT_PATH=baas-bmc-service" -e "PROTOCOL=rest" -e "SDK_MODE=1" -e "CCS_NAME=aiopt-baas-bmc-service" -e "ZK_ADDRESS=10.1.130.84:39181"  baas-bmc-service:1.0 
