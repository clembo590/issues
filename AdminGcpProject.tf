variable "admin_project_id" {}

data "google_project" "admin_project" {
  project_id = var.admin_project_id
}

output "gcp_admin_project" {
  value = data.google_project.admin_project.number
}

