# CI CD

## Build image & Deployment
Build image & Deployment process of creating image and installing Payment Builing Block Adapter in EKS cluster.

Image Version will be based on the project version, defined in [build.gradle file](./../implementation/build.gradle)

### CI environment variables

| Variable              | Description                                         | Value                                      |
|:----------------------|:----------------------------------------------------|:-------------------------------------------|
| AWS_CICD_ROLE         | AWS role for the Circle CI                          | "CICDPipeline_sandbox-bb-payments_Role_dev"|
| ECR_ADAPTER_NAME      | Name of the AWS ECR                                 | "bb/payments/adapter/dev-backend"          |
| ADAPTER_NAMESPACE     | K8S namespace where the chart will be installed     | "payment-bb-adapter"                       |

### Circle CI Deploy Workflow:

To run follow those steps:

1. Navigate to project in CircleCI
2. Select branch from the dropdown
3. Select "Trigger pipeline" action
4. Add parameter of type "Boolean", named "adapter_build_deploy_allowed" and set value to "true"
5. Then trigger the pipeline
6. Approve the first CI Job
