1.编译打包
gradle build -x test
2.生成镜像
2.1本地测试镜像
docker build -t baas-job .
docker build -t 10.248.4.13:5000/billing/baas-job:T1.0 .
docker push 10.248.4.13:5000/billing/baas-job:T1.0

3. 运行镜像
3.1运行测试镜像
docker rm baas-job;
docker run -d --name baas-job -e "REGISTRY_ADDRESS=10.1.130.84:39181" -e "SDK_MODE=1" -e "CCS_NAME=aiopt-baas-job" -e "ZK_ADDRESS=10.1.130.84:39181" baas-job