#!/bin/sh

echo --------------

COMMON_LIB_HOME=$HOME/applications/runner-center-services/runner-bmc-deduct
echo -----------------------------------------COMMON_LIB_HOME $COMMON_LIB_HOME
export COMMON_LIB_HOME

for file in ${COMMON_LIB_HOME}/libs/**/*.jar;
do CP=${CP}:$file;
done

CLASSPATH="${CP}"
export CLASSPATH

CLASSPATH="${CLASSPATH}:${COMMON_LIB_HOME}/config"
echo ${CLASSPATH}

export JAVA_OPTIONS=" -Dfile.encoding=UTF-8 -Djava.net.preferIPv4Stack=true -Dsun.net.inetaddr.ttl=10"
export MEM_ARGS="-Xms128m -Xmx512m"

PROCESS_NAME="BmcDeductStart"
CUR_USER=`whoami`
RUNNER_PRODUCT_NAME=runner.bmc.deduct
#BIN_PATH=${HOME}/bin/runner-center/runner-bmc
LOG_PATH=$HOME/logs/runner-center-logs/runner-bmc-deduct.log
MEM_ARGS="-Xms256m -Xmx256m -XX:PermSize=64M -XX:MaxPermSize=128M"

if [ "$1" = "start" ]; then
        PROCESS_ALIVE_STATUS=$($HOME/sbin/monitor_process.sh $PROCESS_NAME $PROCESS_PARM)
        if [ "$PROCESS_ALIVE_STATUS" = "PROCESS_EXIST" ];
        then
        echo " $RUNNER_PRODUCT_NAME ats listener had already started!! process param is [$PROCESS_PARM]"
        exit 0;
        fi


	${JAVA_HOME}/bin/java ${MEM_ARGS} -D${PROCESS_PARM}  ${JAVA_OPTIONS} com.ai.runner.center.bmc.deduct.consumer.AuthConsumer >> $LOG_PATH & 2>&1&
        echo "$RUNNER_PRODUCT_NAME ats listener started!! logs at $LOG_PATH"
else if [ "$1" = "stop" ]; then
        PROCESS_NUMBER=0
        ps -ef|grep $PROCESS_NAME |grep ${CUR_USER} | grep $PROCESS_PARM | grep java | grep -v grep | awk '{print $2}' |while read pid
        do
        PROCESS_NUMBER=PROCESS_NUMBER+1
        kill ${pid} 2>&1 >/dev/null
        echo "stopped $RUNNER_PRODUCT_NAME ats listener process name :${PROCESS_NAME},process param:${PROCESS_PARM},PID:${pid} "
        done
        if [ "$PROCESS_NUMBER" = 0 ];
        then
                echo "$RUNNER_PRODUCT_NAME ats listener not exists. process name :${PROCESS_NAME},process param:${PROCESS_PARM}"
        fi
        exit 0;
else if [ "$1" = "monitor" ]; then
        PROCESS_ALIVE_STATUS=$($HOME/sbin/monitor_process.sh $PROCESS_NAME $PROCESS_PARM)
        if [ "$PROCESS_ALIVE_STATUS" = "PROCESS_EXIST" ];
        then
        echo "$RUNNER_PRODUCT_NAME ats listener started!! process param is [$PROCESS_PARM]"
        exit 0;
        else
                echo "$RUNNER_PRODUCT_NAME ats listener not exists. process name :${PROCESS_NAME},process param:${PROCESS_PARM}"
                exit 0;
        fi
else
                        echo "ERROR: Please input a correct argument,such as: start or stop or monitor"
        exit 1
fi
fi
fi

