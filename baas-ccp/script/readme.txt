在主工程目录下执行以下命令
1.编译打包
gradle build -x test

# 生成image
docker build -t ccp:1.0 .
docker build -t 10.1.234.164:5000/ccp:12.1 .   (每次打镜像版本要+1)

docker build -t biu-bj-baas-app3:5000/baas-ccp:1.0 ./

docker push 10.1.234.164:5000/ccp:12.1

3. 运行镜像
docker run -d --net=host \
-e "REST_REGISTRY_ADDR=10.1.130.84:30772" \
-e "REST_PORT=10772" \
-e "CONTEXT_PATH=baas-ccp" \
-e "PROTOCOL=rest" \
-e "SDK_MODE=1" \
-e "CCS_NAME=aiopt-baas-ccp" \
-e "ZK_ADDRESS=10.1.130.84:39181" ccp:1.0

docker run -it --net=host \
-e "REST_REGISTRY_ADDR=10.1.130.84:30772" \
-e "REST_PORT=10772" \
-e "PROTOCOL=rest" \
-e "SDK_MODE=1" \
-e "CCS_NAME=aiopt-baas-ccp" \
-e "ZK_ADDRESS=10.1.130.84:39181" baas-ccp

