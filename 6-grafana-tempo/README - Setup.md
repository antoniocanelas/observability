# OpenTelemetry POC

## Stack

## Setup

### Create Local Cluster using Kind to create local cluster https://kind.sigs.k8s.io

1. Create the cluster:
```shell
helm upgrade --install tempo grafana/tempo-distributed -f tempo-config.yaml
```