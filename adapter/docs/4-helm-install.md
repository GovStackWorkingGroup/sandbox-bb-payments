# Helm Installation

## Debug chart

Helm Upgrade command for Govstack sandbox
```
    helm upgrade payment-bb-adapter ./helm --install --create-namespace --namespace payment-bb-adapter --dry-run --debug
```

## Install chart

```
    helm upgrade payment-bb-adapter ./helm --install --create-namespace --namespace payment-bb-adapter
```

## Install chart with persisted database state

 Note: The DB state will not be flushed on every pod restart
```
    helm upgrade --set payment_bb_adapter.dbPersist.flushStorageOnInit=false payment-bb-adapter ./helm --install --create-namespace --namespace payment-bb-adapter
```

## Install chart with no persisted database state

 Useful for development purposes
 Note: The DB state will be flushed on every pod restart
```
    helm upgrade --set payment_bb_adapter.dbPersist.flushStorageOnInit=true payment-bb-adapter ./helm --install --create-namespace --namespace payment-bb-adapter
```

## Uninstall chart

Uninstall chart
```
    helm uninstall payment-bb-adapter --namespace payment-bb-adapter
```
