#!/bin/sh
 
if [ "$JAVA_HOME" != "" ]; then
  #echo "run java in $JAVA_HOME"
  JAVA_HOME=$JAVA_HOME
fi

SIMULATOR_HOME=$(cd "../$(dirname "$0")";pwd)
export SIMULATOR_HOME
 
for file in ${SIMULATOR_HOME}/libs/*.jar;
do CP=${CP}:$file;
done

export CONFIG_PATH=$SIMULATOR_HOME/conf

DATA_PATH=${SIMULATOR_HOME}/data/"$1"

echo ${DATA_PATH}

CLASSPATH="${CP}:${CONFIG_PATH}"
#echo ${CLASSPATH}
 
export JAVA_OPTIONS=" -Dfile.encoding=UTF-8 -Djava.net.preferIPv4Stack=true -Dsun.net.inetaddr.ttl=10"
MEM_ARGS="-Xms256m -Xmx512m -XX:PermSize=64M -XX:MaxPermSize=128M"
 
CLASS=com.ai.baas.bmc.topology.simulator.BaasClientProducer

${JAVA_HOME}/bin/java ${MEM_ARGS} -classpath ${CLASSPATH}  ${JAVA_OPTIONS} ${CLASS} ${DATA_PATH} &
 
