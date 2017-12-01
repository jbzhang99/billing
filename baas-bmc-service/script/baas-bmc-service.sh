#!/bin/sh

for file in ${COMMON_LIB_HOME}/libs/**/*.jar;
do CP=${CP}:$file;
done

DUBBO_CONFIG_PATH=${COMMON_LIB_HOME}/config
LOG_PATH=${COMMON_LIB_HOME}/logs/opt-baas-logs/baas-bmc-service.log
CLASSPATH="${CP}"
CLASSPATH="${DUBBO_CONFIG_PATH}:${CLASSPATH}"

export CLASSPATH

MEM_ARGS="-Xms512m -Xmx1024m -XX:PermSize=64M -XX:MaxPermSize=128M"
JAVA_OPTIONS="-Dfile.encoding=UTF-8 -Djava.net.preferIPv4Stack=true -Dsun.net.inetaddr.ttl=10 -Ddubbo.provider.timeout=20000 -Djava.security.egd=file:/dev/./urandom"

echo"TTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTT"
START_CMD="${MEM_ARGS} ${JAVA_OPTIONS} com.ai.opt.sdk.appserver.DubboServiceStart  >> $LOG_PATH & 2 > 1 &"

echo ${JAVA_HOME}
echo ${CLASSPATH}
echo ${DUBBO_PORT}
echo ${START_CMD}
sed -i "s/paas.sdk.mode=.*/paas.sdk.mode=${SDK_MODE}/g" /baas-bmc-service/config/paas/paas-conf.properties
sed -i "s/ccs.appname=.*/ccs.appname=${CCS_NAME}/g" /baas-bmc-service/config/paas/paas-conf.properties
sed -i "s/ccs.zk_address=.*/ccs.zk_address=${ZK_ADDRESS}/g" /baas-bmc-service/config/paas/paas-conf.properties

sed -i "s/dubbo.registry.address=.*/dubbo.registry.address=${REST_REGISTRY_ADDR}/g" /baas-bmc-service/config/dubbo/dubbo.properties
sed -i "s/dubbo.protocol=.*/dubbo.protocol=${PROTOCOL}/g" /baas-bmc-service/config/dubbo/dubbo.properties
sed -i "s/dubbo.protocol.port=.*/dubbo.protocol.port=${REST_PORT}/g" /baas-bmc-service/config/dubbo/dubbo.properties

echo "------------------- baas bmc service start --------------------"
java ${START_CMD}
echo "------------------- baas bmc service started! -------------------"
