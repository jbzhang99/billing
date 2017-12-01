#!/bin/sh

CUR_USER=`whoami`
CONFIG_PATH=${APP_HOME}/config
LOG_PATH=${APP_HOME}/logs/opt-sys-cache.log

BIN_PATH=${APP_HOME}/libs
PROCESS_PARM="opt.sys.cache"

for file in ${BIN_PATH}/**/*.jar;
do CP=${CP}:$file;
done

CLASSPATH="${CP}"

CLASSPATH="${CONFIG_PATH}:${CLASSPATH}"
export CLASSPATH
echo $CLASSPATH
JAVA_OPTIONS=" -Dfile.encoding=UTF-8 -Djava.net.preferIPv4Stack=true -Dsun.net.inetaddr.ttl=10"
MEM_ARGS="-Xms128m -Xmx512m"

START_CMD=" ${MEM_ARGS}  -D${PROCESS_PARM}  ${JAVA_OPTIONS} com.ai.opt.sdk.appserver.CacheServiceStart >> $LOG_PATH & 2>&1&"

echo "------------------- opt sys cache start --------------------"

java ${START_CMD}

echo "------------------- opt sys cache end --------------------"