1.编译打包
gradle build -x test
2.生成镜像
docker build -t citic-billing-web:1.0 .

docker build -t 10.1.234.164:5000/citic-billing-web:14.3 .
docker push 10.1.234.164:5000/citic-billing-web:14.3

3. 运行镜像
#host模式
docker run -d --name=citic-billing-web-1.0 --net=host -p 14131:8080 -e "casServerLoginUrl=http://10.1.234.163:8080/login" -e "casServerUrlPrefix=http://10.1.234.163:8080" -e "serverName=http://10.1.234.163:14131" -e "logOutServerUrl=http://10.1.234.163:8080/logout" -e "logOutBackUrl=http://10.1.234.163:14131" -e "casServerLoginUrl_Inner=http://10.1.234.163:8080/login" -e "casServerUrlPrefix_Inner=http://10.1.234.163:8080" -e "serverName_Inner=http://10.1.234.163:14131" -e "logOutServerUrl_Inner=http://10.1.234.163:8080/logout" -e "logOutBackUrl_Inner=http://10.1.234.163:14131" -e "innerDomains=sso.citicdao.com,billingwebui.citicdao.com,billing.citicdao.com,user.citicdao.com,webui.citicdao.com,aliapt.citicdao.com,scapt.cticdao.com,service.citicdao.com" -e "ZK_ADDR=10.1.130.84:39181" -e "DUBBO_REGISTRY=10.1.130.84:39181" -e "DUBBO_PROTOCOL=dubbo" -e "DUBBO_PORT=10880" citic-billing-web:1.0

#默认网络模式
docker rm -fv citic-billing-web-14.3
docker run -d --name=citic-billing-web-14.3 -p 14131:8080 -e "casServerLoginUrl=http://10.1.235.245:14125/citic-uac/login" -e "casServerUrlPrefix=http://10.1.235.245:14125/citic-uac" -e "serverName=http://10.1.234.163:14131" -e "logOutServerUrl=http://10.1.235.245:14125/citic-uac/logout" -e "logOutBackUrl=http://10.1.234.163:14131" -e "casServerLoginUrl_Inner=http://10.1.235.245:14125/citic-uac/login" -e "casServerUrlPrefix_Inner=http://10.1.235.245:14125/citic-uac" -e "serverName_Inner=http://10.1.234.163:14131" -e "logOutServerUrl_Inner=http://10.1.235.245:14125/citic-uac/logout" -e "logOutBackUrl_Inner=http://10.1.234.163:14131" -e "innerDomains=sso.citicdao.com,billingwebui.citicdao.com,billing.citicdao.com,user.citicdao.com,webui.citicdao.com,aliapt.citicdao.com,scapt.cticdao.com,service.citicdao.com" -e "ZK_ADDR=10.1.130.84:39181" -e "DUBBO_REGISTRY=10.1.130.84:39181" -e "DUBBO_PROTOCOL=dubbo" -e "DUBBO_PORT=10880" 10.1.234.164:5000/citic-billing-web:14.3
docker logs citic-billing-web-14.3
docker exec -it citic-billing-web-14.3 /bin/bash