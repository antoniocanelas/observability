# Collector

1. Installing opentelemetry-collector with kubectl
```bash
kubectl apply -f k8-collector-config.yaml
kubectl get pods -n observability-backend
```

# DELETE opentelemetry-collector on Kind Cluster

```bash
kubectl delete -f k8-collector-config.yaml
```
2. *TODO:  Installing opentelemetry-collector on Kind Cluster
```shell
helm repo add open-telemetry https://open-telemetry.github.io/opentelemetry-helm-charts
helm repo update
```

```shell
helm upgrade --install opentelemetry-collector open-telemetry/opentelemetry-collector \
--set image.repository="otel/opentelemetry-collector-k8s" \
--set mode=daemonset \
--namespace observability-backend \
--create-namespace \
-f collector-config.yaml
kubectl get pods -n observability-backend
```

```shell
helm delete opentelemetry-collector --namespace observability-backend
```
