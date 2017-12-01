#!/bin/sh

for file in ${COMMON_LIB_HOME}/libs/**/*.jar;
do CP=${CP}:$file;
done

DUBBO_CONFIG_PATH=${COMMON_LIB_HOME}/config
LOG_PATH=${COMMON_LIB_HOME}/logs/opt-baas-logs/baas-rtm-rest.log
PROPERTY=${COMMON_LIB_HOME}/property

DUBBO_PORT="10776"
PROCESS_NAME="DubboServiceStart"
PROCESS_PARM="baas.abm.dubbo.port=$DUBBO_PORT"
CUR_USER=`whoami`
RUNNER_PRODUCT_NAME=baas.abm
BIN_PATH=/baas-abm-service
LOG_PATH=/baas-abm-service/logs/baas-abm-dubbo-$DUBBO_PORT.log
CLASSPATH="${CLASSPATH}"
echo "CLASS_PATH = ${CLASSPATH}"

MEM_ARGS="-Xms256m -Xmx512m -XX:PermSize=64M -XX:MaxPermSize=128M"

DEPOLY_PATH=/baas-abm-service

export DEPOLY_PATH

for file in ${DEPOLY_PATH}/libs/**/*.jar;
do CP=${CP}:$file;
done

CLASSPATH="${CP}"
export CLASSPATH

CLASSPATH="${DEPOLY_PATH}/config:${CLASSPATH}"
echo $CLASSPATH
JAVA_OPTIONS=" -Dfile.encoding=UTF-8 -Djava.net.preferIPv4Stack=true -Dsun.net.inetaddr.ttl=10"
MEM_ARGS="-Xms128m -Xmx512m"

START_CMD=" ${MEM_ARGS} -Ddubbo.registry.address=${DUBBO_REGISTRY_ADDR} -Ddubbo.protocol.port=${DUBBO_PORT} -Ddubbo.protocol.contextpath=${CONTEXT_PATH} -Ddubbo.protocol=${DUBBO_PROTOCOL} -D${PROCESS_PARM}  ${JAVA_OPTIONS} com.ai.opt.sdk.appserver.DubboServiceStart >> $LOG_PATH & 2>&1&"

echo ${JAVA_HOME}
echo ${CLASSPATH}
echo ${DUBBO_PORT}
echo ${START_CMD}

sed -i "s/paas.sdk.mode=.*/paas.sdk.mode=${SDK_MODE}/g" /baas-abm-service/config/paas/paas-conf.properties
sed -i "s/ccs.appname=.*/ccs.appname=${CCS_NAME}/g" /baas-abm-service/config/paas/paas-conf.properties
sed -i "s/ccs.zk_address=.*/ccs.zk_address=${ZK_ADDRESS}/g" /baas-abm-service/config/paas/paas-conf.properties

sed -i "s%dubbo.registry.address=.*%dubbo.registry.address=${DUBBO_REGISTRY_ADDR}%g" /baas-abm-service/config/dubbo/dubbo.properties
sed -i "s%dubbo.protocol=.*%dubbo.protocol=${DUBBO_PROTOCOL}%g" /baas-abm-service/config/dubbo/dubbo.properties
sed -i "s%dubbo.protocol.port=.*%dubbo.protocol.port=${DUBBO_PORT}%g" /baas-abm-service/config/dubbo/dubbo.properties
sed -i "s%dubbo.protocol.contextpath=.*%dubbo.protocol.contextpath=${CONTEXT_PATH}%g" /baas-abm-service/config/dubbo/dubbo.properties

echo "------------------- baas abm service start --------------------"

java ${START_CMD}

echo "$RUNNER_PRODUCT_NAME dubbo server started!! logs at $LOG_PATH"


