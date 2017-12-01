#!/bin/sh

sed -i "s/paas.sdk.mode=.*/paas.sdk.mode=${SDK_MODE}/g" /baas-job/config/paas/paas-conf.properties
sed -i "s/ccs.appname=.*/ccs.appname=${CCS_NAME}/g" /baas-job/config/paas/paas-conf.properties
sed -i "s/ccs.zk_address=.*/ccs.zk_address=${ZK_ADDRESS}/g" /baas-job/config/paas/paas-conf.properties
sed -i "s/dubbo.registry.address=.*/dubbo.registry.address=${REGISTRY_ADDRESS}/g" /baas-job/config/dubbo/dubbo.properties

echo "------------------- load config success --------------------"