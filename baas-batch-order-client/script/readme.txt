1.编译打包
gradle build -x test
2.生成镜像
在本地生成镜像
docker build -t citic-uac:1.0 .
docker build -t 10.1.245.4:5000/citic-uac:1.0 .
docker push 10.1.245.4:5000/citic-uac:1.0

3. 运行镜像
docker run -d --name citic-uac-01 --net=host -p 18881:18881 -e "REST_REGISTRY_ADDR=10.1.130.84:2181" -e "REST_PORT=18881" -e "CONTEXT_PATH=citic-uac" -e "PROTOCOL=rest" -e "SDK_MODE=1" -e "CCS_NAME=aiopt-baas-citic-uac" -e "ZK_ADDRESS=10.1.130.84:2181" -e "RTM_ADDRESS=http://10.1.130.84:10771/baasrtm/dataService/transResource" citic-uac:1.0 
