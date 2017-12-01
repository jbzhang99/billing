#!/bin/sh

for file in ${COMMON_LIB_HOME}/libs/**/*.jar;
do CP=${CP}:$file;
done

DUBBO_CONFIG_PATH=${COMMON_LIB_HOME}/config
LOG_PATH=${COMMON_LIB_HOME}/logs/opt-baas-logs/baas-ccp-rest.log
PROPERTY=${COMMON_LIB_HOME}/property

DUBBO_PORT="10772"
PROCESS_NAME="DubboServiceStart"
PROCESS_PARM="baas.bmc.dubbo.port=$DUBBO_PORT"
CUR_USER=`whoami`
RUNNER_PRODUCT_NAME=baas.ccp
BIN_PATH=/baas-ccp
LOG_PATH=/baas-ccp/logs/baas-ccp-dubbo-$DUBBO_PORT.log
CLASSPATH="${CLASSPATH}"
echo "CLASS_PATH = ${CLASSPATH}"

MEM_ARGS="-Xms256m -Xmx512m -XX:PermSize=64M -XX:MaxPermSize=128M"

DEPOLY_PATH=/baas-ccp
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

sed -i "s/paas.sdk.mode=.*/paas.sdk.mode=${SDK_MODE}/g" /baas-bmc/config/paas/paas-conf.properties
sed -i "s/ccs.appname=.*/ccs.appname=${CCS_NAME}/g" /baas-bmc/config/paas/paas-conf.properties
sed -i "s/ccs.zk_address=.*/ccs.zk_address=${ZK_ADDRESS}/g" /baas-bmc/config/paas/paas-conf.properties

sed -i "s/dubbo.registry.address=.*/dubbo.registry.address=${REST_REGISTRY_ADDR}/g" /baas-bmc/config/dubbo/dubbo.properties
sed -i "s/dubbo.protocol=.*/dubbo.protocol=${PROTOCOL}/g" /baas-bmc/config/dubbo/dubbo.properties
sed -i "s/dubbo.protocol.port=.*/dubbo.protocol.port=${REST_PORT}/g" /baas-bmc/config/dubbo/dubbo.properties

echo "------------------- baas rtm service start --------------------"

java ${START_CMD}

echo "$RUNNER_PRODUCT_NAME dubbo server started!! logs at $LOG_PATH"


