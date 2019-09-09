##################################################################################
# RESOURCE
##################################################################################

################### VPC #######################
resource "aws_vpc" "terrafom_vpc" {
  cidr_block                       = "${var.network_info}"
  assign_generated_ipv6_cidr_block = true                  # requesting aws for ipv6
  instance_tenancy                 = "default"             # dedicated mean  it will be on dedicated harware no other aws customers will be using it

  tags {
    Name = "${var.environment_tag}_terraform_vpc"
  }
}

# it should create a route table, network ACL - access control list, default security security group for this vpc

