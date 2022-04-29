output "project_id" {
  value = google_project.project.project_id
}

output "service_id" {
  value = google_project_service.service["compute.googleapis.com"].id
}

output "defaultComputeEngineServiceAccountEmail" {
  value = data.google_compute_default_service_account.defaultComputeEngineServiceAccount.email
}

output "project_available_zones" {
  value = data.google_compute_zones.available.names
}
