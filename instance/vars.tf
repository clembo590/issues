variable "project_id" {}

variable "ssh_username" {}
variable "ssh_key" {}
variable "serviceAccountOfInstance" {}
variable "artifact_repository_id" {}
variable "available_zones" {
  type = list(string)
}


