# CI/CD instructions

## To run Circle CI Deploy Workflow:

To run "Deploy PaymentHub and Fineract to EKS cluster" workflow, pipeline parameter "deploy_allowed" of type Boolean should be set to "true"

Example:
1. navigate to project in CircleCI
2. select branch from the dropdown
3. select "Trigger pipeline" action
4. Add parameter of type "Boolean", named "deploy_allowed" and set value to "true"
5. Then trigger the pipeline

## Testing
Deployment process covered with a tests. They are checking if all pods (deployment and statefulset) are up and run in 60 seconds.
