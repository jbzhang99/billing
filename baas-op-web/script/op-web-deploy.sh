#!/bin/sh

echo "create op-web service on k8s cluster...."

exist=`kubectl get svc --namespace=unit-test | awk '{print $1}' |egrep "^op-web$"  `
if [ "$exist" = "" ]
then
  echo "op-web service not exist! NOT need delete "
else
  kubectl delete svc op-web --namespace=unit-test
fi

kubectl create -f ./script/op-web-service.json
echo "op-web service done"

echo "create op-web ReplicationController on k8s cluster...."
exist=`kubectl get ReplicationController --namespace=unit-test | awk '{print $1}' | egrep "^op-web-rc$" `
if [ "$exist" = "" ]
then
  echo "op-web ReplicationController not exist! NOT need delete"
else
  kubectl delete ReplicationController op-web-rc --namespace=unit-test
fi

kubectl create -f ./script/op-web-ReplicationController.json
echo "op-web ReplicationController done"


