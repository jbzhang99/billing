1.编译打包
gradle build -x test -x check

2.生成镜像
docker build -t biu-bj-baas-app3:5000/baas-op-web:1.0 ./

3. 运行镜像
docker run -d --name=baas-op-web-1.0 \
--net=host -p 30770:8080 \
-e "casServerLoginUrl=http://10.1.235.245:14125/citic-uac/login" \
-e "casServerUrlPrefix=http://10.1.235.245:14125/citic-uac" \
-e "serverName=http://10.1.234.163:14131" \
-e "logOutServerUrl=http://10.1.235.245:14125/citic-uac/logout" \
-e "logOutBackUrl=http://10.1.234.163:14131" \ 
-e "casServerLoginUrl_Inner=http://10.1.235.245:14125/citic-uac/login" \ 
-e "casServerUrlPrefix_Inner=http://10.1.235.245:14125/citic-uac"  \
-e "serverName_Inner=http://10.1.234.163:14131" \
-e "logOutServerUrl_Inner=http://10.1.235.245:14125/citic-uac/logout" \ 
-e "logOutBackUrl_Inner=http://10.1.234.163:14131" \
-e "innerDomains=sso.citicdao.com,billingwebui.citicdao.com,billing.citicdao.com,user.citicdao.com,webui.citicdao.com,aliapt.citicdao.com,scapt.cticdao.com,service.citicdao.com" \ 
-e "ZK_ADDR=10.1.234.160:28381" \
-e "DUBBO_REGISTRY=10.1.234.160:28381" \
-e "DUBBO_PROTOCOL=dubbo" \
-e "DUBBO_PORT=10880" \
biu-bj-baas-app3:5000/baas-op-web:1.0

网站的初始用户名和密码：13811095237/123456




