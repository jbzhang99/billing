#!/bin/sh

for file in ${COMMON_LIB_HOME}/libs/**/*.jar;
do CP=${CP}:$file;
done

DUBBO_CONFIG_PATH=${COMMON_LIB_HOME}/config
LOG_PATH=${COMMON_LIB_HOME}/logs/opt-baas-logs/baas-batch-order-client.log
CLASSPATH="${CP}"
CLASSPATH="${DUBBO_CONFIG_PATH}:${CLASSPATH}"
CCSPROP=${COMMON_LIB_HOME}/ccsprop
export CLASSPATH

MEM_ARGS="-Xms512m -Xmx1024m -XX:PermSize=64M -XX:MaxPermSize=128M"
JAVA_OPTIONS="-Dfile.encoding=UTF-8 -Djava.net.preferIPv4Stack=true -Dsun.net.inetaddr.ttl=10 -Ddubbo.provider.timeout=20000 -Djava.security.egd=file:/dev/./urandom"

START_CMD1="${MEM_ARGS} ${JAVA_OPTIONS} com.ai.baas.batch.client.core.AmountStart >> $LOG_PATH & 2 > 1 &"
echo ${START_CMD1}
#java ${START_CMD}
echo"TTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTT"
START_CMD="${MEM_ARGS} -Ddubbo.registry.address=${REST_REGISTRY_ADDR} -Dbaas.citic.uac.dubbo.port=${REST_PORT} -Ddubbo.protocol.contextpath=${CONTEXT_PATH} -Ddubbo.protocol=${PROTOCOL} ${JAVA_OPTIONS} com.ai.opt.sdk.appserver.DubboServiceStart  >> $LOG_PATH & 2 > 1 &"

echo ${JAVA_HOME}
echo ${CLASSPATH}
echo ${DUBBO_PORT}
#echo ${START_CMD}
#sed -i "s/paas.sdk.mode=.*/paas.sdk.mode=${SDK_MODE}/g" /baas-batch-order-client/config/paas/paas-conf.properties
#sed -i "s/ccs.appname=.*/ccs.appname=${CCS_NAME}/g" /baas-batch-order-client/config/paas/paas-conf.properties
#sed -i "s/ccs.zk_address=.*/ccs.zk_address=${ZK_ADDRESS}/g" /baas-batch-order-client/config/paas/paas-conf.properties

#sed -i "s/rtm.address=.*/rtm.address=${RTM_ADDRESS}/g" /baas-batch-order-client/config/url/url-conf.properties
#sed -i "s/dubbo.appname=.*/dubbo.appname=${DUBBO_APPNAME}/g" /baas-batch-order-client/config/dubbo/dubbo.properties
#sed -i "s/dubbo.registry.protocol=.*/dubbo.registry.protocol=${REGISTRY_PROTOCOL}/g" /baas-batch-order-client/config/dubbo/dubbo.properties
#sed -i "s/dubbo.registry.address=.*/dubbo.registry.address=${REGISTRY_ADDRESS}/g" /baas-batch-order-client/config/dubbo/dubbo.properties
#sed -i "s/dubbo.registry.file=.*/dubbo.registry.file=${REGISTRY_FILE}/g" /baas-batch-order-client/config/dubbo/dubbo.properties
#sed -i "s/dubbo.protocol=.*/dubbo.protocol=${DUBBO_PROTOCOL}/g" /baas-batch-order-client/config/dubbo/dubbo.properties
#sed -i "s/baas.bmc.dubbo.port=.*/baas.bmc.dubbo.port=${DUBBO_PORT}/g" /baas-batch-order-client/config/dubbo/dubbo.properties
#sed -i "s/dubbo.provider.timeout=.*/dubbo.provider.timeout=${PROVIDER_TIMEOUT}/g" /baas-batch-order-client/config/dubbo/dubbo.properties

echo "------------------- baas amount service start --------------------"
java ${START_CMD1}
#java ${START_CMD}
echo "------------------- baas amount service started! -------------------"
