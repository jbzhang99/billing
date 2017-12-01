#!/bin/sh
JAVA_HOME=/usr/lib/jvm/java-7-openjdk-amd64
PATH=$JAVA_HOME/bin:$PATH
COMMON_LIB_HOME=/baas-batch-collect
for file in ${COMMON_LIB_HOME}/libs/**/*.jar;
do CP=${CP}:$file;
done

DUBBO_CONFIG_PATH=${COMMON_LIB_HOME}/config
LOG_PATH=${COMMON_LIB_HOME}/logs/opt-baas-logs/baas-batch-collect.log
CLASSPATH="${CP}"
CLASSPATH="${DUBBO_CONFIG_PATH}:${CLASSPATH}"
CCSPROP=${COMMON_LIB_HOME}/ccsprop
export PATH JAVA_HOME CLASSPATH

MEM_ARGS="-Xms256m -Xmx512m -XX:PermSize=64M -XX:MaxPermSize=128M"
JAVA_OPTIONS="-Dfile.encoding=UTF-8 -Djava.net.preferIPv4Stack=true -Dsun.net.inetaddr.ttl=10 -Ddubbo.provider.timeout=20000 -Djava.security.egd=file:/dev/./urandom"

START_CMD1="${MEM_ARGS} ${JAVA_OPTIONS} com.ai.baas.batch.client.core.AmountStart >> $LOG_PATH & 2 > 1 &"
echo ${START_CMD1}

echo ${JAVA_HOME}
echo ${CLASSPATH}
echo ${DUBBO_PORT}

echo "------------------- baas batch collect start --------------------"
${JAVA_HOME}/bin/java  -classpath ${CLASSPATH} ${START_CMD1}
echo "------------------- baas batch collect started! -------------------"
