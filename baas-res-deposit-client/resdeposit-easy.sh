#!/bin/sh

export BOOK_SVC_HOME=$HOME/applications/opt-baas-services/baas-res-deposit-client
export LOGGER_PATH=$HOME/logs/resdeposit.log

#把根目录下面所有的jar包都加入到CP环境变量中
for file in ${BOOK_SVC_HOME}/libs/3rd-libs/*.jar;
do CP=${CP}:$file;
done

for file in ${BOOK_SVC_HOME}/libs/core-libs/*.jar;
do CP=${CP}:$file;
done

#把config目录加入到CONFIG_PATH环境变量中
export CONFIG_PATH=$BOOK_SVC_HOME/config

#把CP和CONFIG_PATH都加入到CLASSPATH环境变量中
CLASSPATH="${CP}:${CONFIG_PATH}"
export CLASSPATH
echo ${CLASSPATH}

#定义JAVA_OPTIONS环境变量
export JAVA_OPTIONS=" -Dfile.encoding=UTF-8 -Djava.net.preferIPv4Stack=true -Dsun.net.inetaddr.ttl=10" 
MEM_ARGS="-Xms256m -Xmx512m -XX:PermSize=64M -XX:MaxPermSize=128M"

#启动java虚拟机和程序
${JAVA_HOME}/bin/java ${MEM_ARGS}   ${JAVA_OPTIONS} com.ai.runner.center.bmc.resdeposit.application.StartSDK  > $LOGGER_PATH  2>&1 &

echo "resdeposit server started!!  Logs at $LOGGER_PATH "