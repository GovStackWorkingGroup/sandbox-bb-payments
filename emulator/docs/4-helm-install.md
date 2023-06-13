# Helm Installation

## Debug chart

Helm Upgrade command for Govstack sandbox
```
    helm upgrade payment-bb-emulator ./helm --install --create-namespace --namespace payment-bb-emulator --dry-run --debug
```

## Install chart

```
    helm upgrade payment-bb-emulator ./helm --install --create-namespace --namespace payment-bb-emulator
```

## Uninstall chart

Uninstall chart
```
    helm uninstall payment-bb-emulator --namespace payment-bb-emulator
```