locals {
  myDockerImageName = "us-central1-docker.pkg.dev/${var.project_id}/${var.artifact_repository_id}/my-jenkins:jcasc"
}

resource "google_compute_instance" "default" {
  project = var.project_id
  zone    = var.available_zones[0]
  name    = "tf-compute-1"
  allow_stopping_for_update = true

  machine_type = "g1-small"

  tags = ["http-server",  "https-server"]


  labels = {
    container-vm = "cos-stable-89-16108-470-1"
  }

  metadata = {
    ssh-keys = "${var.ssh_username}:${file(var.ssh_key)}"
    gce-container-declaration="spec:\n  containers:\n    - name: instance-2\n      image: >-\n        ${local.myDockerImageName}\n      stdin: false\n      tty: false\n  restartPolicy: Always\n\n# This container declaration format is not public API and may change without notice. Please\n# use gcloud command-line tool or Google Cloud Console to run Containers on Google Compute Engine."
    google-logging-enabled= "true"
  }

  service_account {
    email = var.serviceAccountOfInstance
    scopes = ["https://www.googleapis.com/auth/devstorage.read_only",
      "https://www.googleapis.com/auth/logging.write",
      "https://www.googleapis.com/auth/monitoring.write",
      "https://www.googleapis.com/auth/servicecontrol",
      "https://www.googleapis.com/auth/service.management.readonly",
      "https://www.googleapis.com/auth/trace.append"]
  }

  boot_disk {
    auto_delete = "true"
    device_name = "instance-container"
    initialize_params {
      image = "https://www.googleapis.com/compute/v1/projects/cos-cloud/global/images/cos-stable-89-16108-470-1"
      size  = "10"
      type  = "pd-standard"
    }
  }

  network_interface {
    network = "default"
    access_config {}
  }

  scheduling {
    preemptible       = false
    automatic_restart = true
  }

}





