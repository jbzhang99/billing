#!/bin/sh

echo "create pay web service on k8s cluster...."

exist=`kubectl get svc --namespace=unit-test | grep pay-web `
if [ "$exist" = "" ]
then
  echo "pay web service not exist! NOT need delete "
else
  kubectl delete svc pay-web --namespace=unit-test
fi

kubectl create -f ./script/pay-web-service.json
echo "pay web service done"

echo "create pay web ReplicationController on k8s cluster...."
exist=`kubectl get ReplicationController --namespace=unit-test | grep "pay-web-rc" `
if [ "$exist" = "" ]
then
  echo "pay web ReplicationController not exist! NOT need delete"
else
  kubectl delete ReplicationController pay-web-rc --namespace=unit-test
fi

kubectl create -f ./script/pay-web-ReplicationController.json
echo "pay web ReplicationController done"


