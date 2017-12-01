#!/bin/sh

#DUBBO_PORT="11055"
PROCESS_NAME="DubboServiceStart"
PROCESS_PARM="baas.batch.dubbo.port=$DUBBO_PORT"
CUR_USER=`whoami`
RUNNER_PRODUCT_NAME=baas.batch
#BIN_PATH=${COMMON_LIB_HOME}/bin/opt-baas/baas-batch
LOG_PATH=${COMMON_LIB_HOME}/logs/opt-baas-logs/baas-batch-dubbo-$DUBBO_PORT.log
CLASSPATH="${CLASSPATH}"
MEM_ARGS="-Xms256m -Xmx512m -XX:PermSize=64M -XX:MaxPermSize=128M"

. "/setEnv.sh"

		sed -i "s/paas.sdk.mode=.*/paas.sdk.mode=${SDK_MODE}/g" /baas-batch-order-client/config/paas/paas-conf.properties
		sed -i "s/ccs.appname=.*/ccs.appname=${CCS_NAME}/g" /baas-batch-order-client/config/paas/paas-conf.properties
		sed -i "s/ccs.zk_address=.*/ccs.zk_address=${ZK_ADDRESS}/g" /baas-batch-order-client/config/paas/paas-conf.properties

		#sed -i "s/rtm.address=.*/rtm.address=${RTM_ADDRESS}/g" /baas-batch-order-client/config/url/url-conf.properties
		#sed -i "s/dubbo.appname=.*/dubbo.appname=${DUBBO_APPNAME}/g" /baas-batch-order-client/config/dubbo/dubbo.properties
		#sed -i "s/dubbo.registry.protocol=.*/dubbo.registry.protocol=${REGISTRY_PROTOCOL}/g" /baas-batch-order-client/config/dubbo/dubbo.properties
		#sed -i "s/dubbo.provider.timeout=.*/dubbo.provider.timeout=${PROVIDER_TIMEOUT}/g" /baas-batch-order-client/config/dubbo/dubbo.properties
		
		sed -i "s/dubbo.registry.address=.*/dubbo.registry.address=${DUBBO_REGISTRY_ADDR}/g" /baas-batch-order-client/config/dubbo/dubbo.properties
		sed -i "s/dubbo.protocol=.*/dubbo.protocol=${DUBBO_PROTOCOL}/g" /baas-batch-order-client/config/dubbo/dubbo.properties
		sed -i "s/dubbo.protocol.port=.*/dubbo.protocol.port=${DUBBO_PORT}/g" /baas-batch-order-client/config/dubbo/dubbo.properties


if [ "$1" = "start" ]; then
        PROCESS_ALIVE_STATUS=$($HOME/sbin/monitor_process.sh $PROCESS_NAME $PROCESS_PARM)
        if [ "$PROCESS_ALIVE_STATUS" = "PROCESS_EXIST" ];
        then
        echo " $RUNNER_PRODUCT_NAME dubbo server had already started!! process param is [$PROCESS_PARM]"
        exit 0;
        fi



        ${JAVA_HOME}/bin/java ${MEM_ARGS} -D${PROCESS_PARM}  ${JAVA_OPTIONS} com.ai.opt.sdk.appserver.DubboServiceStart >> $LOG_PATH & 2>&1&
        echo "$RUNNER_PRODUCT_NAME dubbo server started!! logs at $LOG_PATH"
		
		

else if [ "$1" = "stop" ]; then
        PROCESS_NUMBER=0
        ps -ef|grep $PROCESS_NAME |grep ${CUR_USER} | grep $PROCESS_PARM | grep java | grep -v grep | awk '{print $2}' |while read pid
        do
        PROCESS_NUMBER=PROCESS_NUMBER+1
        kill ${pid} 2>&1 >/dev/null
        echo "stopped $RUNNER_PRODUCT_NAME dubbo server process name :${PROCESS_NAME},process param:${PROCESS_PARM},PID:${pid} "
        done
        if [ "$PROCESS_NUMBER" = 0 ];
        then
                echo "$RUNNER_PRODUCT_NAME dubbo server not exists. process name :${PROCESS_NAME},process param:${PROCESS_PARM}"
        fi
        exit 0;
else if [ "$1" = "monitor" ]; then
        PROCESS_ALIVE_STATUS=$($HOME/sbin/monitor_process.sh $PROCESS_NAME $PROCESS_PARM)
        if [ "$PROCESS_ALIVE_STATUS" = "PROCESS_EXIST" ];
        then
        echo "$RUNNER_PRODUCT_NAME dubbo server started!! process param is [$PROCESS_PARM]"
        exit 0;
        else
                echo "$RUNNER_PRODUCT_NAME dubbo server not exists. process name :${PROCESS_NAME},process param:${PROCESS_PARM}"
                exit 0;
        fi
else
                        echo "ERROR: Please input a correct argument,such as: start or stop or monitor"
        exit 1
fi
fi
fi
