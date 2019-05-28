# PullRequestCounterVsts
Utility web app written in Kotlin JS to show and update active pull requests on Microsoft Azure VSTS.

This project uses Aazure DevOps Services REST API and needs a Personal Access Token in order to obtain access to the API.

# How to setup
- Create personal access token: https://docs.microsoft.com/en-us/azure/devops/integrate/get-started/authentication/pats?view=azure-devops
- Create "Keys.kt" file with:
  - `const val token = "YOUR_TOKEN_HERE"` property. Paste Personal Access Token there.
  - `const val organiztion = "YOUR_ORGANIZATION"` - VSTS Organization
  - `const val project = "YOUR_PROJECT"` - VSTS Project
