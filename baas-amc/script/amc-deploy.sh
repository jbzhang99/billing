#!/bin/sh

echo "create amc service on k8s cluster...."

exist=`kubectl get svc --namespace=unit-test |awk '{print $1}'| egrep "^amc$" `
if [ "$exist" = "" ]
then
  echo "amc service not exist! NOT need delete "
else
  kubectl delete svc amc --namespace=unit-test
fi

kubectl create -f ./script/amc-service.json
echo "amc service done"

echo "create amc ReplicationController on k8s cluster...."
exist=`kubectl get ReplicationController --namespace=unit-test | awk '{print $1}' | egrep "^amc-rc$" `
if [ "$exist" = "" ]
then
  echo "amc ReplicationController not exist! NOT need delete"
else
  kubectl delete ReplicationController amc-rc --namespace=unit-test
fi

kubectl create -f ./script/amc-ReplicationController.json
echo "amc ReplicationController done"


