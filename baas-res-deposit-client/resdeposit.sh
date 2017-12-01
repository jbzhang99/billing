#!/bin/sh

export BOOK_SVC_HOME=$HOME/applications/opt-baas-services/baas-res-deposit-client
export LOGGER_PATH=$HOME/logs/resdeposit.log
for file in ${BOOK_SVC_HOME}/libs/3rd-libs/*.jar;
do CP=${CP}:$file;
done

for file in ${BOOK_SVC_HOME}/libs/*.jar;
do CP=${CP}:$file;
done

export CONFIG_PATH=$BOOK_SVC_HOME/config

CLASSPATH="${CP}:${CONFIG_PATH}"
export CLASSPATH
echo ${CLASSPATH}

export JAVA_OPTIONS=" -Dfile.encoding=UTF-8 -Djava.net.preferIPv4Stack=true -Dsun.net.inetaddr.ttl=10"
MEM_ARGS="-Xms256m -Xmx512m -XX:PermSize=64M -XX:MaxPermSize=128M"


if [ "$1" = "start" ]; then
        ${JAVA_HOME}/bin/java ${MEM_ARGS}   ${JAVA_OPTIONS} com.ai.runner.center.bmc.resdeposit.application.StartServer> $LOGGER_PATH & 2>&1 &
        echo "resdeposit server started!!  Logs at $LOGGER_PATH "
else if [ "$1" = "stop" ]; then
        PROCESS_NUMBER=0
        ps -ef|grep resdeposit |grep StartServer | grep java | grep -v grep | awk '{print $2}' |while read pid
        do
        PROCESS_NUMBER=PROCESS_NUMBER+1
        kill ${pid} 2>&1 >/dev/null
        echo "stopped resdeposit "
        done
        exit 0;
else
        echo "ERROR: Please input a correct argument,such as: start or stop"
        exit 1
fi
fi
