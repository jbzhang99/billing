#!/bin/sh


#set base home
TOMCAT_HOME=/opt/apache-tomcat-8.0.35
RESOURCES_HOME=${TOMCAT_HOME}/webapps/ROOT/WEB-INF/classes

#change config
pushd ${RESOURCES_HOME}
sed -i "s%ccs.zk_address=.*%ccs.zk_address=${ZK_ADDR}%g" ./paas/paas-conf.properties

sed -i "s%baas.dubbo.registry.address=.*%baas.dubbo.registry.address=${DUBBO_REGISTRY}%g" ./dubbo.properties
sed -i "s%dubbo.protocol=.*%dubbo.protocol=${DUBBO_PROTOCOL}%g" ./dubbo.properties
sed -i "s%dubbo.protocol.port=.*%dubbo.protocol.port=${DUBBO_PORT}%g" ./dubbo.properties
popd


nohup /opt/apache-tomcat-8.0.35/bin/catalina.sh run >> /logs/baas-mt-web.log