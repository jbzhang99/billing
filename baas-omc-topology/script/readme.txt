## 在工程目录下
# 编译打包
gradle build -x test

# 打镜像
docker build -t baas-omc-topology .

执行
docker-compose up