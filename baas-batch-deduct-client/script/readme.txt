1.编译打包
gradle build -x test
2.生成镜像
docker build -t baas--batch-deduct-client:v1.0 .
3. 运行镜像
docker run -d --name=baas--batch-deduct-client --net=host -p 14111:8080
-e "SDK_MODE=1" 
-e "ZK_ADDRESS=10.1.130.84:39181" 
-e "CCS_NAME=aiopt-baas--batch-deduct-client"
baas--batch-deduct-client:v1.0