# Notes

kubectl delete clusterroles `kubectl get clusterroles |grep c-role|cut -d ' ' -f1` || echo ' '
kubectl delete clusterrolebinding `kubectl get clusterrolebinding |grep c-role|cut -d ' ' -f1` || echo ' '
kubectl delete namespace paymenthub-sandbox || echo ' '
kubectl create namespace paymenthub-sandbox || echo ' '

helm repo update

helm dependency build

helm install my-ph-ee-g2psandbox  ./helm/ -n paymenthub-sandbox --create-namespace



helm upgrade --install my-ph-ee-g2psandbox  ./helm/ -n paymenthub-sandbox --create-namespace


## Fix Known Issue
Migration script race condition Operation app startup issue work around

1. On pod: operationsmysql-0 in {namespace} with PortForward or Shell in the pod
2. connect the mysql with root passwrod
3. delete tenants
4. Run the SQL scripts which didn’t run successfully

```sql
DROP DATABASE `tenants`;
DROP DATABASE `rhino`;
DROP DATABASE `gorilla`;

CREATE DATABASE `tenants`;
GRANT ALL PRIVILEGES ON `tenants`.* TO 'mifos';
CREATE DATABASE `rhino`;
CREATE DATABASE `gorilla`;
GRANT ALL PRIVILEGES ON `rhino`.* TO 'mifos';
GRANT ALL PRIVILEGES ON `gorilla`.* TO 'mifos';
GRANT ALL ON *.* TO 'root'@'%';
```

5. restart ph-ee-operations-app pod


## Apply secrets

### Elastic Search
```
export ENV_NAMESPACE=paymenthub-sandbox
cd ./es-secret/
make secrets || echo "elastic-certificates" already exists
```
### Kibana
```
// Clone repo or directory from the link above

export ENV_NAMESPACE=paymenthub-sandbox
cd ./kibana-secret/
make secrets || echo "kibana-certificates" already exists
```


# BPMN deployment

```shell
#!/bin/sh
HOST="https://zeebeops.sandbox.fynarfin.io/zeebe/upload"
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
```

## run test

helm test my-ph-ee-g2psandbox --namespace paymenthub-sandbox
