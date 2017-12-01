#!/bin/sh

CUR_USER=`whoami`
CONFIG_PATH=${APP_HOME}/config
LOG_PATH=${APP_HOME}/logs/baas-amc-totalbill-cache.log

BIN_PATH=${APP_HOME}/libs
PROCESS_PARM="baas.amc.totalbill"

for file in ${BIN_PATH}/**/*.jar;
do CP=${CP}:$file;
done

CLASSPATH="${CP}"

CLASSPATH="${CONFIG_PATH}:${CLASSPATH}"
export CLASSPATH
echo $CLASSPATH
JAVA_OPTIONS=" -Dfile.encoding=UTF-8 -Djava.net.preferIPv4Stack=true -Dsun.net.inetaddr.ttl=10"
MEM_ARGS="-Xms128m -Xmx512m"

START_CMD=" ${MEM_ARGS}  -D${PROCESS_PARM}  ${JAVA_OPTIONS} com.ai.baas.amc.server.TotalBillServer >> $LOG_PATH & 2>&1&"

echo "------------------- 总账计算任务开始 --------------------"

java ${START_CMD}

echo "------------------- 总账计算任务完成 --------------------"