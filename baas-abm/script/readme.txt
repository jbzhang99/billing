1.编译打包
gradle build -x test
# 生成image
docker build -t baas-abm:0.1 .
docker build -t 10.1.245.4:5000/baas-abm:1.0 .
docker push 10.1.245.4:5000/baas-abm:1.0

3. 运行镜像
docker run -d --net=host -e "DUBBO_REGISTRY_ADDR=10.1.130.84:39181" -e "DUBBO_PORT=10776" -e "CONTEXT_PATH=baas-abm" -e "DUBBO_PROTOCOL=rest" -e "SDK_MODE=1" -e "CCS_NAME=aiopt-baas-abm" -e "ZK_ADDRESS=10.1.130.84:39181" baas-abm:0.1


4.  10.28版本数据库变更
INSERT INTO `sys_sequences` (`sequence_name`, `start_by`, `increment_by`, `last_number`, `jvm_step_by`) VALUES ('AMC_RES_BOOK$BOOK_ID$SEQ', 1, 1, 0, 20);
