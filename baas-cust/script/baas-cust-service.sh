#!/bin/sh

DEPOLY_PATH=${APP_HOME}
DUBBO_PORT="10896"
RUNNER_PRODUCT_NAME=baas.cust
LOG_PATH=$DEPOLY_PATH/logs/baas-cust-dubbo-$DUBBO_PORT.log

export DEPOLY_PATH

for file in ${DEPOLY_PATH}/libs/**/*.jar;
do CP=${CP}:$file;
done

CLASSPATH="${CP}"
export CLASSPATH

CLASSPATH="${DEPOLY_PATH}/config:${CLASSPATH}"
echo $CLASSPATH
JAVA_OPTIONS=" -Dfile.encoding=UTF-8 -Djava.net.preferIPv4Stack=true -Dsun.net.inetaddr.ttl=10 -Djava.security.egd=file:/dev/./urandom"
MEM_ARGS="-Xms128m -Xmx512m"

START_CMD=" ${MEM_ARGS}   ${JAVA_OPTIONS} com.ai.opt.sdk.appserver.DubboServiceStart >> $LOG_PATH & 2>&1&"

sed -i "s/paas.sdk.mode=.*/paas.sdk.mode=${SDK_MODE}/g" /baas-cust/config/paas/paas-conf.properties
sed -i "s/ccs.appname=.*/ccs.appname=${CCS_NAME}/g" /baas-cust/config/paas/paas-conf.properties
sed -i "s/ccs.zk_address=.*/ccs.zk_address=${ZK_ADDRESS}/g" /baas-cust/config/paas/paas-conf.properties

sed -i "s/dubbo.registry.address=.*/dubbo.registry.address=${REST_REGISTRY_ADDR}/g" /baas-cust/config/dubbo/dubbo.properties
sed -i "s/dubbo.protocol=.*/dubbo.protocol=${PROTOCOL}/g" /baas-cust/config/dubbo/dubbo.properties
sed -i "s/dubbo.protocol.port=.*/dubbo.protocol.port=${REST_PORT}/g" /baas-cust/config/dubbo/dubbo.properties

sed -i "s%dubbo.protocol.contextpath=.*%dubbo.protocol.contextpath=${CONTEXT_PATH}%g" /baas-cust/config/dubbo/dubbo.properties

echo "------------------- baas rtm service start --------------------"

java ${START_CMD}

echo "$RUNNER_PRODUCT_NAME dubbo server started!! logs at $LOG_PATH"


