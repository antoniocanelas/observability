# Deployment of Services in Kubernetes

### Build and Load Docker Images
1. Build `service-a`:

```shell
cd service-a
quarkus build -Dquarkus.container-image.build=true
cd ..
```

2. Build `service-b`:

```shell
cd service-b
quarkus build -Dquarkus.container-image.build=true
cd ..
```

3. Load Docker images into Kind:

```shell
kind load docker-image ctw00293/service-a:1.0.0-SNAPSHOT
kind load docker-image ctw00293/service-b:1.0.0-SNAPSHOT
```

### Deploy Services with Helm

1. Deploy `service-a` and `service-b`:

```shell
helm upgrade --install service-a -f service-a/helm/kubernetes/service-a/values.yaml service-a/helm/kubernetes/service-a
helm upgrade --install service-b -f service-b/helm/kubernetes/service-b/values.yaml service-b/helm/kubernetes/service-b
```

### Delete Services with Helm

1. Delete `service-a` and `service-b`:

```shell Delete serices
helm delete service-a service-b
```

## Usage

### Service-A Greeting from Service-B

```shell script Kubernetes - service-a greeting from service-b 
curl --request POST \
--url 'http://localhost:80/service-a/proccess?language=k-pt&=' \
--header 'Content-Type: text/plain' \
--header 'correlationId: AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA' \
--data '{ Antonio Canelas }'
```

### Service-A Greeting Local

```shell script Kubernetes - service-a greeting local 
curl --request POST \
--url 'http://localhost:80/service-a/local?language=k-pt&=' \
--header 'Content-Type: text/plain' \
--header 'correlationId: AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA' \
--data '{ Antonio Canelas }'
```

### Service-B Greeting Local

```shell script Kubernetes - service-a greeting local 
curl --request POST \
--url 'http://localhost:80/service-b/proccess?language=k-pt&=' \
--header 'Content-Type: text/plain' \
--header 'correlationId: AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA' \
--data '{ Antonio Canelas }'
```

```shell request loader
while true; do curl --request POST \
--url 'http://localhost:80/service-a/proccess?language=k-pt&=' \
--header 'Content-Type: text/plain' \
--header 'correlationId: AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA' \
--data '{ Antonio Canelas }'; sleep 0.5; done;
```

```shell SCRIPT TEST
helm delete service-a service-b
helm upgrade --install service-a -f service-a/helm/kubernetes/service-a/values.yaml service-a/helm/kubernetes/service-a
helm upgrade --install service-b -f service-b/helm/kubernetes/service-b/values.yaml service-b/helm/kubernetes/service-b
```

