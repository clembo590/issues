
provider "google" {
  region = var.region
}

resource "random_id" "id" {
  byte_length = 4
  prefix      = var.project_name
}

resource "google_project" "project" {
  name            = var.project_name
  project_id      = random_id.id.hex
  billing_account = var.billing_account
  org_id          = var.org_id
}

resource "google_project_service" "service" {
  for_each = toset([
    "compute.googleapis.com",
    "containerregistry.googleapis.com",
    "artifactregistry.googleapis.com"
  ])

  service = each.key

  project            = google_project.project.project_id
  disable_on_destroy = false
}


data "google_compute_zones" "available" {
  project    = google_project.project.project_id
  depends_on = [google_project_service.service]
}

data "google_compute_default_service_account" "defaultComputeEngineServiceAccount" {
  depends_on = [google_project_service.service]
  project = google_project.project.project_id
}