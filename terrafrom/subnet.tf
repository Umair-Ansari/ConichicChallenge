##################################################################################
# RESOURCE
##################################################################################

######################### SUBNET ########################
resource "aws_subnet" "subnet" {
  vpc_id                  = "${aws_vpc.terrafom_vpc.id}"
  cidr_block              = "${cidrsubnet(var.network_info, 8, 1)}"
  map_public_ip_on_launch = true
  availability_zone       = "${data.aws_availability_zones.available.names[0]}"

  tags {
    Name = "terraform_subnet"
  }
}
