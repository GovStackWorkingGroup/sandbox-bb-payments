# CI CD

## Build image & Deployment
Build image & Deployment process of creating image and installing Payment Builing Block EMULATOR in EKS cluster.

Image Version will be based on the project version, defined in [build.gradle file](./../implementation/build.gradle)

### CI environment variables

| Variable              | Description                                         | Value                                      |
|:----------------------|:----------------------------------------------------|:-------------------------------------------|
| AWS_CICD_ROLE         | AWS role for the Circle CI                          | "CICDPipeline_sandbox-bb-payments_Role_dev"|
| ECR_EMULATOR_NAME     | Name of the AWS ECR                                 | "bb/payments/emulator/dev-backend"         |
| EMULATOR_NAMESPACE    | K8S namespace where the chart will be installed     | "payment-bb-emulator"                      |

### Circle CI Deploy Workflow:

To run follow those steps:

1. navigate to project in CircleCI
2. select branch from the dropdown
3. select "Trigger pipeline" action
4. Add parameter of type "Boolean", named "emulator_build_deploy_allowed" and set value to "true"
5. (Optional) If needed to install the emulator in development mode (on restart to reinit the database in initial state) add parameter of type "Boolean", named "emulator-db-flush" and set value to "true"
6. Then trigger the pipeline
7. Approve the first CI Job