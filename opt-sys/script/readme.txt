1.编译打包
gradle build -x test
# 生成image
docker build -t opt-sys:0.1 .
docker build -t 10.1.234.164:5000/opt-sys:1.0 .
docker push 10.1.234.164:5000/opt-sys:1.0

3. 运行镜像
docker run -d --net=host \
-e "DUBBO_REGISTRY_ADDR=10.1.130.84:39181" \
-e "DUBBO_PORT=10882" \
-e "DUBBO_PROTOCOL=dubbo" \
-e "SDK_MODE=1" \
-e "CCS_NAME=aiopt-baas-sys" \
-e "ZK_ADDRESS=10.1.130.84:39181" \
10.1.234.164:5000/opt-sys:1.0
