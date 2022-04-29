variable "ssh_username" {}
variable "ssh_key" {}
variable "project_name" {}
variable "billing_account" {}
variable "org_id" {}
variable "region" {}
variable "service_account_for_artifact_registry" {}
variable "artifact_repository_id" {}



variable "webservers_subnet_ip_range" { default = "192.168.1.0/24"}
variable "management_subnet_ip_range" { default = "192.168.100.0/24"}

variable "bastion_image" { default = "centos-7-v20170918" }
variable "bastion_instance_type" { default = "f1-micro" }