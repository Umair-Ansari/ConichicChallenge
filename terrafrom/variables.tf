##################################################################################
# VARIABLES
##################################################################################

variable "aws_access_key" {}
variable "aws_secret_key" {}
variable "environment_tag" {
	default = "dev"
}
variable "private_key_path" {}

variable "key_name" {
  default = "t928515"
}

variable "subnet_count" {
  default = "1"
}

variable "instance_count" {
  default = "1"
}

variable "network_info" {
  default = "10.0.0.0/16"
}
