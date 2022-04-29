output "name" {
  value = google_compute_network.network.name
}

output "gateway_ipv4"  {
  value = google_compute_network.network.gateway_ipv4
}

output "management_subnet"{
  value = module.management_subnet.self_link
}

output "webservers_subnet"{
  value = module.webservers_subnet.self_link
}