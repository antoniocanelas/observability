# Grafana Mimir

1. Install Grafana Mimir using the Helm chart::

```shell
helm repo add grafana https://grafana.github.io/helm-charts
helm repo update
```

```shell
helm -n mimir-test install mimir grafana/mimir-distributed --create-namespace
kubectl -n mimir-test get pods
```
