# CI/CD instructions

## To run Circle CI Deploy Workflow:

To run "Deloy PaymentHub and Fineract to EKS cluster" workflow, pipeline parameter "deploy_allowed" of type Boolean should be set to "true"

Example:
1. navigate to project in CircleCI
2. select branch from the dropdown
3. select "Trigger pipline" action
4. Add paramether of type "Boolean", named "deploy_allowed" and set value to "true"
5. Then trigger the pipeline