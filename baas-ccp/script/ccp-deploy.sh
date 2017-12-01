#!/bin/sh

echo "create ccp service on k8s cluster...."

exist=`kubectl get svc --namespace=unit-test |   grep ccp`
if [ "$exist" = "" ]
then
  echo "ccp service not exist! NOT need delete "
else
  kubectl delete svc ccp --namespace=unit-test
fi

kubectl create -f ./script/ccp-service.json
echo "ccp service done"

echo "create ccp ReplicationController on k8s cluster...."
exist=`kubectl get ReplicationController --namespace=unit-test | grep "ccp-rc" `
if [ "$exist" = "" ]
then
  echo "ccp ReplicationController not exist! NOT need delete"
else
  kubectl delete ReplicationController ccp-rc --namespace=unit-test
fi

kubectl create -f ./script/ccp-ReplicationController.json
echo "ccp ReplicationController done"


