#!/bin/sh

CONFIG_PATH=${APP_HOME}/config
LOG_PATH=${APP_HOME}/logs/opt-sys-dubbo.log

BIN_PATH=${APP_HOME}/libs
PROCESS_PARM="opt.sys.dubbo.port=$DUBBO_PORT"

for file in ${BIN_PATH}/**/*.jar;
do CP=${CP}:$file;
done

CLASSPATH="${CP}"

CLASSPATH="${CONFIG_PATH}:${CLASSPATH}"
export CLASSPATH
echo $CLASSPATH
JAVA_OPTIONS=" -Dfile.encoding=UTF-8 -Djava.net.preferIPv4Stack=true -Dsun.net.inetaddr.ttl=10"
MEM_ARGS="-Xms128m -Xmx512m"

START_CMD=" ${MEM_ARGS} -Ddubbo.registry.address=${DUBBO_REGISTRY_ADDR} -Ddubbo.protocol.port=${DUBBO_PORT} -Ddubbo.protocol=${DUBBO_PROTOCOL} -D${PROCESS_PARM}  ${JAVA_OPTIONS} com.ai.opt.sdk.appserver.DubboServiceStart >> $LOG_PATH & 2>&1&"

sed -i "s/paas.sdk.mode=.*/paas.sdk.mode=${SDK_MODE}/g" /opt-sys-service/config/paas/paas-conf.properties
sed -i "s/ccs.appname=.*/ccs.appname=${CCS_NAME}/g" /opt-sys-service/config/paas/paas-conf.properties
sed -i "s/ccs.zk_address=.*/ccs.zk_address=${ZK_ADDRESS}/g" /opt-sys-service/config/paas/paas-conf.properties

sed -i "s%dubbo.registry.address=.*%dubbo.registry.address=${DUBBO_REGISTRY_ADDR}%g" /opt-sys-service/config/dubbo/dubbo.properties
sed -i "s%dubbo.protocol=.*%dubbo.protocol=${DUBBO_PROTOCOL}%g" /opt-sys-service/config/dubbo/dubbo.properties
sed -i "s%opt.sys.dubbo.port=.*%opt.sys.dubbo.port=${DUBBO_PORT}%g" /opt-sys-service/config/dubbo/dubbo.properties


echo "------------------- opt sys service start --------------------"

java ${START_CMD}

echo "------------------- opt sys service started --------------------"


