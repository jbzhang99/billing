#!/bin/sh

echo "create pay service on k8s cluster...."

exist=`kubectl get svc --namespace=unit-test | grep -v pay-web | grep pay`
if [ "$exist" = "" ]
then
  echo "pay service not exist! NOT need delete "
else
  kubectl delete svc pay --namespace=unit-test
fi

kubectl create -f ./script/pay-service.json
echo "pay service done"

echo "create pay ReplicationController on k8s cluster...."
exist=`kubectl get ReplicationController --namespace=unit-test | grep -v "pay-rc" |grep pay`
if [ "$exist" = "" ]
then
  echo "pay ReplicationController not exist! NOT need delete"
else
  kubectl delete ReplicationController pay-rc --namespace=unit-test
fi

kubectl create -f ./script/pay-ReplicationController.json
echo "pay ReplicationController done"


