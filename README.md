# PullRequestCounterVsts
Utility web app written in Kotlin JS to show and update active pull requests on Microsoft Azure VSTS.

This project uses Aazure DevOps Services REST API and needs a Personal Access Token in order to obtain access to the API.

# How to setup
- Create personal access token: https://docs.microsoft.com/en-us/azure/devops/integrate/get-started/authentication/pats?view=azure-devops
- Create "Keys.kt" file with `val token = "YOUR_TOKEN_HERE"` property in it.
