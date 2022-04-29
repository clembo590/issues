module "project" {
  source          = "./project"
  project_name    = var.project_name
  region          = var.region
  billing_account = var.billing_account
  org_id          = var.org_id
}

module "network" {
  source                     = "./network"
  name                       = var.project_name
  project_id                 =  module.project.project_id
  region                     = var.region
  webservers_subnet_name     = "webservers"
  webservers_subnet_ip_range = var.webservers_subnet_ip_range
  management_subnet_name     = "management"
  management_subnet_ip_range = var.management_subnet_ip_range
  ssh_key                    = var.ssh_key
}


module "bastion" {
  source        = "./bastion"
  name          = "${var.project_name}-bastion"
  project_id    = module.project.project_id
  zones         = module.project.project_available_zones
  subnet_name   = module.network.management_subnet
  image         = var.bastion_image
  instance_type = var.bastion_instance_type
  ssh_username = var.ssh_username
  ssh_key       = var.ssh_key
}



module "artifact_registry"{
  source          = "./artifact_registry"
  project_id = module.project.project_id
  service_account_for_artifact_registry = var.service_account_for_artifact_registry
  artifact_repository_id = var.artifact_repository_id
  region = var.region
}

module "instance" {
  source = "./instance"
  ssh_key = var.ssh_key
  ssh_username = var.ssh_username
  project_id = module.project.project_id
  available_zones = module.project.project_available_zones
  serviceAccountOfInstance = module.project.defaultComputeEngineServiceAccountEmail
  artifact_repository_id = module.artifact_registry.my_repository_id
}