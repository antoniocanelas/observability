# OpenTelemetry POC


### Create Local Cluster using Kind to create local cluster https://kind.sigs.k8s.io

1. Installing Cloud Provider KIND

```shell
go install sigs.k8s.io/cloud-provider-kind@latest
```

2. Installing Kubernetes Cloud Provider for KIND to enable Load
   Balancers https://github.com/kubernetes-sigs/cloud-provider-kind

```shell
sudo cloud-provider-kind
```

3. Create the cluster:

```shell
kind create cluster --config=cluster.yaml
```

4. Get cluster info:

```shell
kubectl cluster-info --context kind-kind
```

5. Apply Nginx configuration:

```shell
kubectl apply -f nginx.yaml
```

6. Wait for Nginx to be ready:

```shell
kubectl wait --namespace ingress-nginx \
--for=condition=ready pod \
--selector=app.kubernetes.io/component=controller \
--timeout=90s
kubectl get svc -A
```
```
NAMESPACE       NAME                                 TYPE           CLUSTER-IP     EXTERNAL-IP   PORT(S)                      AGE
default         kubernetes                           ClusterIP      10.96.0.1      <none>        443/TCP                      74s
ingress-nginx   ingress-nginx-controller             LoadBalancer   10.96.53.231   172.18.0.3    80:32020/TCP,443:30186/TCP   52s
ingress-nginx   ingress-nginx-controller-admission   ClusterIP      10.96.154.1    <none>        443/TCP                      52s
kube-system     kube-dns                             ClusterIP      10.96.0.10     <none>        53/UDP,53/TCP,9153/TCP       73s
```

