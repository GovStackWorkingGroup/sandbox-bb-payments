# Payment building block adapter

Building docker image:
`./gradlew bootBuildImage`

Docker Run:
`docker run --name  paymentBBAdapter -p 15000:8080 -d bb/payment/adapter:0.0.1`