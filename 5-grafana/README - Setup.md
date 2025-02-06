# Grafana

1. Deploy Grafana using the Helm chart:

```shell
helm upgrade --install grafana grafana/grafana -f grafana-config.yaml
kubectl apply -f extras-config.yaml
```

2. Get grafana admin password:

```shell
kubectl get secret --namespace default grafana -o jsonpath="{.data.admin-password}" | base64 --decode ; echo
``` 
2. Forward Grafana port:

```shell script
kubectl port-forward svc/grafana 3000:80
```

Open it in the browser [localhost:3000](http://localhost:3000/)