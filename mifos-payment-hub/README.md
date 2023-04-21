# Installation instructions PaymentHubEE and Fineract

## Fineract: [Description](https://fineract.apache.org/)

  Will be used as Financial Service Provider (FSP) 

  Source Repo: [Repo](https://github.com/fynarfin/fineract-env/blob/master/helm/g2p-Sandbox/values.yaml)

## PaymentHubEE: [Description](https://payments.mifos.org/)

  Will be used as payment orchestration API

  Source Repo: [Repo](https://github.com/openMF/ph-ee-env-labs/tree/master/helm/g2p-sandbox)

## Install chart

Helm dependency update
```
    helm dependency update helm/g2p-sandbox
```

Helm Upgrade command for Govstack sandbox
```
    helm upgrade -f helm/g2p-sandbox/values.yaml -f helm/g2p-sandbox/values-sandbox.yaml --set fin-engine.namespace=paymenthub g2pconnect helm/g2p-sandbox --install --create-namespace --namespace paymenthub
```

NOTE: If different namespace is used should be changed in " helm/g2p-sandbox/values-sandbox.yaml" for hosts related with "ph_ee_connector_ams_mifos"

Example: 
    https://fineract-server-local.paymenthub.svc.cluster.local/ ->
    https://fineract-server-local.namespace.svc.cluster.local/

## Uninstall chart

Uninstall chart
```
    helm uninstall g2pconnect --namespace paymenthub
```

Delete all related Persistent Volume Claims
```
    kubectl delete pvc data-fineract-mysql-0 data-operationsmysql-0 data-zeebe-zeebe-0 ph-ee-elasticsearch-ph-ee-elasticsearch-0 -n paymenthub
```

Delete Elasticsearch secrets
```
kubectl delete secrets elastic-certificates elastic-certificate-pem elastic-certificate-crt -n paymenthub
```



## Known Issue Payment hub EE:
Migration script race condition Operation app startup issue work around

### Solution

1. port forward ops-mysql(pod: "operationsmysql") -3307
2. connect the mysql with root password 
3. delete tenants 
4. Run the SQL scripts which didn’t run successfully

```
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
5. restart ops-app (pod: "ph-ee-operations-app")

## Known Issue Fineract

There is race condition with migrations runned in fineract-server pod. When migrations are not runned completely login in fineract "Community app" is not possible with tennant "gorilla".

### Solution

Restart fineract-server pod, then check that database "gorilla" in fineract-mysql pod is populated.

## ElasticSearch secrets

Source documentation provied from Mifos: [INFO](https://docs.google.com/document/d/1Pk4fHdAONAwZ9j65YuI8qA8MgDmv_oMnlvqNUQGsMTA/edit)

```
    // Curent elasticsearch version 7.16.3
    // Current chart namespace "paymenthub"
    // Change "elasticsearch version" or/and "namespace" if other namespace is used 
    //.  when chart is installed or different version
    //.  of elasticsearch is used in the chart 

    docker pull docker.elastic.co/elasticsearch/elasticsearch:7.16.3

    docker run --name elastic-helm-charts-certs -i -w /app \
            docker.elastic.co/elasticsearch/elasticsearch:7.16.3 \
            /bin/sh -c " \
                elasticsearch-certutil ca --out /app/elastic-stack-ca.p12 --pass '' && \
                elasticsearch-certutil cert --name security-master --dns security-master --ca /app/elastic-stack-ca.p12 --pass '' --ca-pass '' --out /app/elastic-certificates.p12"

    docker cp elastic-helm-charts-certs:/app/elastic-certificates.p12 ./ 
    openssl pkcs12 -nodes -passin pass:'' -in elastic-certificates.p12 -out elastic-certificate.pem
    openssl x509 -outform der -in elastic-certificate.pem -out elastic-certificate.crt

    kubectl create secret generic elastic-certificates --from-file=elastic-certificates.p12 --namespace paymenthub
    kubectl create secret generic elastic-certificate-pem --from-file=elastic-certificate.pem --namespace paymenthub
    kubectl create secret generic elastic-certificate-crt --from-file=elastic-certificate.crt --namespace paymenthub
```

## X-Road connection 

### Port forward:
* `kubectl port-forward service/govstack-xroad-ssp 8000:4000 -n govstack`
* `kubectl port-forward service/govstack-xroad-ssc 7000:4000 -n govstack`

### Services endpoints:
* Fineract server: `https://fineract-server.paymenthub.svc.cluster.local:8443`
* Payment Hub Operations App: `http://ph-ee-operations-app.paymenthub.svc.cluster.local`


### X-ROAD API endpoints:

* Fineract server: `http://localhost:8080/r1/DEV/GOV/222/PROVIDER/Paymenthub-fineract-server/`
* Payment Hub Operations App: `http://localhost:8080/r1/DEV/GOV/222/PROVIDER/Paymenthub-operations-app/`


### Example API calls:

#### Create client in Fineract:
```
    curl --location 'http://localhost:8080/r1/DEV/GOV/222/PROVIDER/Paymenthub-fineract-server/fineract-provider/api/v1/clients' \
    --header 'X-Road-Client: DEV/GOV/111/CONSUMER' \
    --header 'accept: application/json, text/plain, */*' \
    --header 'accept-language: en-GB,en-US;q=0.9,en;q=0.8' \
    --header 'authorization: Basic {fineract-credentials}' \
    --header 'content-type: application/json;charset=UTF-8' \
    --header 'fineract-platform-tenantid: rhino' \
    --data '{
        "address": [],
        "familyMembers": [],
        "officeId": 1,
        "firstname": "Firstname",
        "lastname": "Lastname",
        "active": true,
        "legalFormId": 1,
        "locale": "en",
        "dateFormat": "dd MMMM yyyy",
        "activationDate": "19 April 2023",
        "submittedOnDate": "19 April 2023",
        "savingsProductId": null
    }'
```

#### Payment Hub Operations App Authorization:
```
    curl --location 'http://localhost:8080/r1/DEV/GOV/222/PROVIDER/Paymenthub-operations-app/oauth/token?username={operations-app-username}&password={operations-app-password}&grant_type=password' \
    --header 'X-Road-Client: DEV/GOV/111/CONSUMER' \
    --header 'Platform-TenantId: gorilla' \
    --header 'Authorization: Basic {operations-app-credentials}' \
    --header 'Content-Type: text/plain' \
    --data '{}'
```

#### Get users from Payment Hub Operations App:
```
    curl --location 'http://localhost:8080/r1/DEV/GOV/222/PROVIDER/Paymenthub-operations-app/api/v1/users' \
    --header 'X-Road-Client: DEV/GOV/111/CONSUMER' \
    --header 'Platform-TenantId: gorilla'
```

## To run Circle CI Deploy Workflow:

To run "Deloy PaymentHub and Fineract to EKS cluster" workflow, pipeline parameter "deploy_allowed" of type Boolean should be set to "true"

Example:
1. navigate to project in CircleCI
2. select branch from the dropdown
3. select "Trigger pipline" action
4. Add paramether of type "Boolean", named "deploy_allowed" and set value to "true"
5. Then trigger the pipeline
