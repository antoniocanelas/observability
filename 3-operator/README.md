# Otel Operator

1. Install cert-manager (https://cert-manager.io/docs/installation/helm/)

### Deploy cert-manager

[cert-manager](https://cert-manager.io/docs/) is used by OpenTelemetry operator to provision TLS certificates for
admission webhooks.

```shell
helm repo add jetstack https://charts.jetstack.io --force-update
helm install \
cert-manager jetstack/cert-manager \
--namespace cert-manager \
--create-namespace \
--set crds.enabled=true
kubectl get pods -n cert-manager
```

```shell verify if it is installed and working (not needed)
kubectl apply -f ./cert-manager/test-resources.yaml;
kubectl describe certificate -n cert-manager-test | grep -z "The certificate has been successfully issued";
kubectl delete -f ./cert-manager/test-resources.yaml
```

2. Add OpenTelemetry repository

```shell Add OpenTelemetry and update repository
helm repo add open-telemetry https://open-telemetry.github.io/opentelemetry-helm-charts
helm repo update
```

3. Install OpenTelemetry Operator

```shell
helm upgrade --install opentelemetry-operator open-telemetry/opentelemetry-operator -f ./operator-config.yaml 
```

```shell DELETE OpenTelemetry Operator
helm delete opentelemetry-operator
```

# --namespace opentelemetry-operator-system --create-namespace


4. Deploy observability backend

This course is all about Observabilty, so a backend is needed. If you don't have one, you can install Prometheus for
metrics and Jaeger for traces as follows:

```bash
kubectl apply -f observability-backend/observability.yaml
kubectl get pods -n observability-backend
```

Afterwards, the backend can be found in the namespace `observability-backend`.

```bash
kubectl port-forward -n observability-backend service/jaeger-query 16686:16686
```

Open it in the browser [localhost:16686](http://localhost:16686/)

4. Create instrumentation CR and see traces in the Jaeger
   console (https://raw.githubusercontent.com/pavolloffay/kubecon-eu-2024-opentelemetry-kubernetes-tracing-tutorial/main/app/instrumentation.yaml)

Now let's instrument the services with the `Instrumentation` CR and see traces in the Jaeger console.

First the Instrumentation CR needs to be created in the `tutorial-application` namespace:

```bash
kubectl apply -f instrumentation.yaml
kubectl get pods
```                                                                                                                                                                                                                                                                                        

```                                                                                                                                                                                                                                                                                        
NAME                                      READY   STATUS    RESTARTS   AGE
opentelemetry-operator-64dc8845fd-5pwxc   2/2     Running   0          102s
```

```bash DELETE instrumentation
kubectl delete -f instrumentation.yaml
```                                                                                                                                                                                                                                                                                        

4. Install on auto-instrubemtation on `service-b` and `service-b`:
# EBPF not possible in kind cluster https://ebpf.io

```shell
kubectl patch deployment service-a -p '{"spec": {"template":{"metadata":{"annotations":{"instrumentation.opentelemetry.io/inject-java":"true"}}}} }'
kubectl patch deployment service-b -p '{"spec": {"template":{"metadata":{"annotations":{"instrumentation.opentelemetry.io/inject-java":"true"}}}} }'
```

```shell check if the pods aquired the env vars
kubectl describe pod | grep OTEL_
```

```
OTEL_NODE_IP:                         (v1:status.hostIP)
OTEL_POD_IP:                          (v1:status.podIP)
OTEL_LOGS_EXPORTER:                  otlp
OTEL_SERVICE_NAME:                   service-a
OTEL_EXPORTER_OTLP_ENDPOINT:         http://otel-collector.observability-backend.svc.cluster.local:4317
```

