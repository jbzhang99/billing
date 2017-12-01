# baas-cust
docker run -d --name baas-cust -p 30774:10774 \
-e "REST_REGISTRY_ADDR=172.20.2.139:2181" \
-e "REST_PORT=30774" \
-e "CONTEXT_PATH=baas-cust" \
-e "PROTOCOL=rest" \
-e "SDK_MODE=1" \
-e "PAAS_AUTH_URL=http://10.1.245.4:19811/service-portal-uac-web/service/auth" \
-e "PAAS_AUTH_PID=D14F7D708109471AB6F3084B2ABAE9A6" \
-e "PAAS_CCS_ID=CCS004" \
-e "PAAS_CCS_PWD=123456" \
-e "CCS_NAME=aiopt-baas-cust" \
-e "ZK_ADDRESS=172.20.2.191:28481" \
10.1.234.164:5000/baas-cust:0.1 
