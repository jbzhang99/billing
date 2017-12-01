#!/bin/sh

echo "create omc service on k8s cluster...."

exist=`kubectl get svc --namespace=unit-test | grep -v omc  `
if [ "$exist" = "" ]
then
  echo "omc service not exist! NOT need delete "
else
  kubectl delete svc omc --namespace=unit-test
fi

kubectl create -f ./script/omc-service.json
echo "omc service done"

echo "create omc ReplicationController on k8s cluster...."
exist=`kubectl get ReplicationController --namespace=unit-test |  grep omc `
if [ "$exist" = "" ]
then
  echo "omc ReplicationController not exist! NOT need delete"
else
  kubectl delete ReplicationController omc-rc --namespace=unit-test
fi

kubectl create -f ./script/omc-ReplicationController.json
echo "omc ReplicationController done"


