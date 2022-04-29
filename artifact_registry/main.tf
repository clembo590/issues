

resource "google_service_account" "my_artificat_registry_service_account" {
  project = var.project_id
  account_id   = var.service_account_for_artifact_registry
  display_name = "Service Account For Artifcat Registry"
}

resource "google_project_iam_member" "project" {
  for_each = toset(["roles/artifactregistry.repoAdmin","roles/artifactregistry.admin"])
  project = var.project_id
  role    = each.value
  member  = "serviceAccount:${google_service_account.my_artificat_registry_service_account.email}"
}

resource "google_service_account_key" "mykey" {
  service_account_id = google_service_account.my_artificat_registry_service_account.name
}


resource "google_artifact_registry_repository" "my-repo" {
  provider = google-beta
  project = var.project_id
  location = var.region
  repository_id = var.artifact_repository_id
  description = "example docker repository"
  format = "DOCKER"
}