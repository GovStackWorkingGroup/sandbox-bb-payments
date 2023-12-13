#!/bin/sh
pkill kubectl
CHART_NAMESPACE="paymenthub-sandbox"
kubectl -n $CHART_NAMESPACE port-forward service/ph-ee-zeebe-ops 5000:80 & sleep 5
HOST="http://localhost:5000/zeebe/upload"

deploy(){
    cmd="curl --insecure --location --request POST $HOST \
    --header 'Platform-TenantId: gorilla' \
    --form 'file=@\"$PWD/$1\"'"
    echo $cmd
    eval $cmd 
    #If curl response is not 200 it should fail the eval cmd
    
    cmd="curl --insecure --location --request POST $HOST \
    --header 'Platform-TenantId: rhino' \
    --form 'file=@\"$PWD/$1\"'"
    echo $cmd
    eval $cmd
    #If curl response is not 200 it should fail the eval cmd
}

LOC=orchestration/feel/*.bpmn
for f in $LOC; do
    deploy $f
done

LOC2=orchestration/feel/example/*.bpmn
for f in $LOC2; do
    deploy $f
done

pkill kubectl