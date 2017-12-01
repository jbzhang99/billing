#!/bin/sh
JAVA_HOME=/usr/lib/jvm/java-7-openjdk-amd64
PATH=$JAVA_HOME/bin:$PATH
COMMON_LIB_HOME=/baas-rtm-client
for file in ${COMMON_LIB_HOME}/libs/**/*.jar;
do CP=${CP}:$file;
done

DUBBO_CONFIG_PATH=${COMMON_LIB_HOME}/config
LOG_PATH=${COMMON_LIB_HOME}/logs/baas-rtm-client.log
CLASSPATH="${CP}"
CLASSPATH="${DUBBO_CONFIG_PATH}:${CLASSPATH}"
CCSPROP=${COMMON_LIB_HOME}/ccsprop
export PATH JAVA_HOME CLASSPATH

MEM_ARGS="-Xms256m -Xmx512m -XX:PermSize=64M -XX:MaxPermSize=128M"
JAVA_OPTIONS="-Dfile.encoding=UTF-8 -Djava.net.preferIPv4Stack=true"

START_CMD1="${MEM_ARGS} ${JAVA_OPTIONS} com.ai.baas.collect.start.MainStart >> $LOG_PATH & 2 > 1 &"
echo ${START_CMD1}

echo ${JAVA_HOME}
echo ${CLASSPATH}


echo "------------------- baas rtm client start --------------------"
${JAVA_HOME}/bin/java  -classpath ${CLASSPATH} ${START_CMD1}
#java ${START_CMD}
echo "------------------- baas rtm client started! -------------------"
