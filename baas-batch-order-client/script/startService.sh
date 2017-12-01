#!/bin/sh
JAVA_HOME=/usr/lib/jvm/java-7-openjdk-amd64
PATH=$JAVA_HOME/bin:$PATH
COMMON_LIB_HOME=/baas-batch-order-client
for file in ${COMMON_LIB_HOME}/libs/**/*.jar;
do CP=${CP}:$file;
done

PROCESS_NAME="DubboServiceStart"
PROCESS_PARM="baas.batch.dubbo.port=$DUBBO_PORT"
CUR_USER=`whoami`
RUNNER_PRODUCT_NAME=baas.batch
#BIN_PATH=${COMMON_LIB_HOME}/bin/opt-baas/baas-batch
LOG_PATH=${COMMON_LIB_HOME}/logs/opt-baas-logs/baas-batch-dubbo-$DUBBO_PORT.log
CLASSPATH="${CLASSPATH}"
MEM_ARGS="-Xms256m -Xmx512m -XX:PermSize=64M -XX:MaxPermSize=128M"

CLASSPATH="${CP}"
CLASSPATH="${COMMON_LIB_HOME}/config:${CLASSPATH}"
export PATH JAVA_HOME CLASSPATH

JAVA_OPTIONS=" -Dfile.encoding=UTF-8 -Djava.net.preferIPv4Stack=true -Dsun.net.inetaddr.ttl=10 -Djava.security.egd=file:/dev/./urandom"
MEM_ARGS="-Xms128m -Xmx512m"

sed -i "s/paas.sdk.mode=.*/paas.sdk.mode=${SDK_MODE}/g" /baas-batch-order-client/config/paas/paas-conf.properties
sed -i "s/ccs.appname=.*/ccs.appname=${CCS_NAME}/g" /baas-batch-order-client/config/paas/paas-conf.properties
sed -i "s/ccs.zk_address=.*/ccs.zk_address=${ZK_ADDRESS}/g" /baas-batch-order-client/config/paas/paas-conf.properties
sed -i "s/dubbo.registry.address=.*/dubbo.registry.address=${DUBBO_REGISTRY_ADDR}/g" /baas-batch-order-client/config/dubbo/dubbo.properties
sed -i "s/dubbo.protocol=.*/dubbo.protocol=${DUBBO_PROTOCOL}/g" /baas-batch-order-client/config/dubbo/dubbo.properties
sed -i "s/dubbo.protocol.port=.*/dubbo.protocol.port=${DUBBO_PORT}/g" /baas-batch-order-client/config/dubbo/dubbo.properties
#sed -i "s/rtm.address=.*/rtm.address=${RTM_ADDRESS}/g" /baas-batch-order-client/config/url/url-conf.properties
#sed -i "s/dubbo.appname=.*/dubbo.appname=${DUBBO_APPNAME}/g" /baas-batch-order-client/config/dubbo/dubbo.properties
#sed -i "s/dubbo.registry.protocol=.*/dubbo.registry.protocol=${REGISTRY_PROTOCOL}/g" /baas-batch-order-client/config/dubbo/dubbo.properties
#sed -i "s/dubbo.provider.timeout=.*/dubbo.provider.timeout=${PROVIDER_TIMEOUT}/g" /baas-batch-order-client/config/dubbo/dubbo.properties

START_CMD=" ${MEM_ARGS} ${JAVA_OPTIONS} com.ai.opt.sdk.appserver.DubboServiceStart >> $LOG_PATH & 2>&1&"

echo "------------------- baas batch service start --------------------"

${JAVA_HOME}/bin/java ${START_CMD}

echo "$RUNNER_PRODUCT_NAME dubbo server started!! logs at $LOG_PATH"
