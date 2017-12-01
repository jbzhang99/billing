#!/bin/sh

for file in ${COMMON_LIB_HOME}/libs/**/*.jar;
do CP=${CP}:$file;
done

DUBBO_CONFIG_PATH=${COMMON_LIB_HOME}/config
LOG_PATH=${COMMON_LIB_HOME}/logs/opt-baas-logs/baas-rtm-rest.log
CLASSPATH="${CP}"
CLASSPATH="${DUBBO_CONFIG_PATH}:${CLASSPATH}"
PROPERTY=${COMMON_LIB_HOME}/property
export CLASSPATH

MEM_ARGS="-Xms256m -Xmx512m -XX:PermSize=64M -XX:MaxPermSize=128M"
JAVA_OPTIONS="-Dfile.encoding=UTF-8 -Djava.net.preferIPv4Stack=true -Dsun.net.inetaddr.ttl=10 -Dpaas.dubbo.provider.timeout=300000"

START_CMD1="${MEM_ARGS} ${JAVA_OPTIONS} com.ai.opt.sdk.appserver.LoadConfServiceStart $PROPERTY"
echo ${START_CMD1}
#java ${START_CMD}
echo"TTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTT"
START_CMD="${MEM_ARGS} -Dbaas.rest.registry.address=${REST_REGISTRY_ADDR} -Dbaas.rtm.rest.port=${REST_PORT} -Drest.contextpath=${CONTEXT_PATH} -Drest.protocol=${PROTOCOL} ${JAVA_OPTIONS} com.ai.opt.sdk.appserver.DubboServiceStart  >> $LOG_PATH & 2 > 1 &"

echo ${JAVA_HOME}
echo ${CLASSPATH}
echo ${DUBBO_PORT}
echo ${START_CMD}
sed -i "s/paas.sdk.mode=.*/paas.sdk.mode=${SDK_MODE}/g" /baas-rtm-service/config/paas/paas-conf.properties
sed -i "s/ccs.appname=.*/ccs.appname=${CCS_NAME}/g" /baas-rtm-service/config/paas/paas-conf.properties
sed -i "s/ccs.zk_address=.*/ccs.zk_address=${ZK_ADDRESS}/g" /baas-rtm-service/config/paas/paas-conf.properties
#sed -i "s/paas.ccs.servicepassword=.*/paas.ccs.servicepassword=${CCS_PWD}/g" /baas-rtm-service/config/paas/paas-conf.properties
sed -i "s/jdbc.url=.*/jdbc.url=jdbc:mysql:\/\/${DB_HOST}\/${DB_NAME}?useUnicode=true\&characterEncoding=UTF-8/g" /baas-rtm-service/config/context/mysql.db.properties
sed -i "s/jdbc.username=.*/jdbc.username=${DB_USRER}/g" /baas-rtm-service/config/context/mysql.db.properties
sed -i "s/jdbc.password=.*/jdbc.password=${DB_PWD}/g" /baas-rtm-service/config/context/mysql.db.properties

echo "------------------- baas rtm service start --------------------"
#java ${START_CMD1}
java ${START_CMD}
echo "------------------- baas rtm service started! -------------------"
#START_CMD="${MEM_ARGS} ${JAVA_OPTIONS} com.ai.opt.sdk.appserver.LoadConfServiceStart ${PROPERTY} >> $LOG_PATH & 2 > 1 &"
