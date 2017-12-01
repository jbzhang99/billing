#!/bin/sh

COMMON_LIB_HOME=baas-job

for file in ${COMMON_LIB_HOME}/libs/**/*.jar;
do CP=${CP}:$file;
done

DUBBO_CONFIG_PATH=${COMMON_LIB_HOME}/config
LOG_PATH=${COMMON_LIB_HOME}/logs/opt-baas-logs/baas-job-citicbank-charge-autotask.log
CLASSPATH="${CP}"
CLASSPATH="${DUBBO_CONFIG_PATH}:${CLASSPATH}"
export CLASSPATH

MEM_ARGS="-Xms256m -Xmx512m  "
JAVA_OPTIONS="-Dfile.encoding=UTF-8 -Djava.net.preferIPv4Stack=true -Dsun.net.inetaddr.ttl=10 -Djava.security.egd=file:/dev/./urandom"

START_CMD1="${MEM_ARGS} ${JAVA_OPTIONS} com.ai.baas.job.task.CiticChargeAutoTask >> $LOG_PATH & 2 > 1 &"
echo ${START_CMD1}
#sed -i "s/paas.sdk.mode=.*/paas.sdk.mode=${SDK_MODE}/g" /baas-job/config/paas/paas-conf.properties
#sed -i "s/ccs.appname=.*/ccs.appname=${CCS_NAME}/g" /baas-job/config/paas/paas-conf.properties
#sed -i "s/ccs.zk_address=.*/ccs.zk_address=${ZK_ADDRESS}/g" /baas-job/config/paas/paas-conf.properties
#sed -i "s/dubbo.registry.address=.*/dubbo.registry.address=${REGISTRY_ADDRESS}/g" /baas-job/config/dubbo/dubbo.properties

echo "------------------- baas   service start --------------------"
java ${START_CMD1}
echo "------------------- baas   service started! -------------------"
