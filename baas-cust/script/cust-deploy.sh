#!/bin/sh

echo "create cust service on k8s cluster...."

exist=`kubectl get svc --namespace=unit-test | grep cust `
if [ "$exist" = "" ]
then
  echo "cust service not exist! NOT need delete "
else
  kubectl delete svc cust --namespace=unit-test
fi

kubectl create -f ./script/cust-service.json
echo "cust service done"

echo "create cust ReplicationController on k8s cluster...."
exist=`kubectl get ReplicationController --namespace=unit-test | grep "cust-rc" `
if [ "$exist" = "" ]
then
  echo "cust ReplicationController not exist! NOT need delete"
else
  kubectl delete ReplicationController cust-rc --namespace=unit-test
fi

kubectl create -f ./script/cust-ReplicationController.json
echo "cust ReplicationController done"


