#!/bin/sh

echo "create opt-sys service on k8s cluster...."

exist=`kubectl get svc --namespace=unit-test |awk '{print $1}'| egrep "^opt-sys$" `
if [ "$exist" = "" ]
then
  echo "opt-sys service not exist! NOT need delete "
else
  kubectl delete svc opt-sys --namespace=unit-test
fi

kubectl create -f ./script/opt-sys-service.json
echo "opt-sys service done"

echo "create opt-sys ReplicationController on k8s cluster...."
exist=`kubectl get ReplicationController --namespace=unit-test | awk '{print $1}' | egrep "^opt-sys-rc$" `
if [ "$exist" = "" ]
then
  echo "opt-sys ReplicationController not exist! NOT need delete"
else
  kubectl delete ReplicationController opt-sys-rc --namespace=unit-test
fi

kubectl create -f ./script/opt-sys-ReplicationController.json
echo "opt-sys ReplicationController done"


