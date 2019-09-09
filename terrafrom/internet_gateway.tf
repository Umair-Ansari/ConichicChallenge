##################################################################################
# RESOURCE
##################################################################################

######################### INTERNET GATEWAY ########################
# a vpc can attach to one interget gateway
resource "aws_internet_gateway" "terraform_igway" {
  vpc_id = "${aws_vpc.terrafom_vpc.id}"

  tags {
    Name = "${var.environment_tag}_terraform_gateway"
  }
}

resource "aws_route_table" "terraform_rt" {
  vpc_id = "${aws_vpc.terrafom_vpc.id}"

  route {
    cidr_block = "0.0.0.0/0"
    gateway_id = "${aws_internet_gateway.terraform_igway.id}" # every subnet associated with this route will be public
  }

  route {
    ipv6_cidr_block = "::/0"
    gateway_id      = "${aws_internet_gateway.terraform_igway.id}"
  }

  tags {
    Name = "${var.environment_tag}_terraform_gateway"
  }
}

resource "aws_route_table_association" "terraform_rasso" {
  subnet_id      = "${element(aws_subnet.subnet.*.id,count.index)}"
  route_table_id = "${aws_route_table.terraform_rt.id}"
}

######################### SECURTY GROUP ########################

resource "aws_security_group" "terraform_sg" {
  name   = "terraform_sg"
  vpc_id = "${aws_vpc.terrafom_vpc.id}"

  # SSH access from anywhere
  ingress {
    from_port   = 22
    to_port     = 22
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }

  # HTTP access from anywhere
  ingress {
    from_port   = 80
    to_port     = 80
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }

  ingress {
    from_port   = 3389
    to_port     = 3389
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }

  # outbound internet access
  egress {
    from_port   = 0
    to_port     = 0
    protocol    = "-1"
    cidr_blocks = ["0.0.0.0/0"]
  }

  tags {
    Name = "${var.environment_tag}_terraform_gateway"
  }
}
