##################################################################################
# RESOURCE
##################################################################################

######################### EC2 INSTANCE ########################
resource "aws_instance" "terraform_ec2" {
  count                  = 2
  ami                    = "ami-4e3576a4"   
  instance_type          = "t2.micro"
  key_name               = "${var.key_name}"
  vpc_security_group_ids = ["${aws_security_group.terraform_sg.id}"]
  subnet_id              = "${element(aws_subnet.subnet.*.id,0)}"

  user_data = <<EOF
  <powershell>
  invoke-expression 'Start-Process -Verb RunAs -FilePath powershell { Rename-Computer -Computer $env:COMPUTERNAME -NewName terraform007; restart-computer -force}'
  </powershell>
  EOF

  tags {
    Name = "${var.environment_tag}_terraform_gateway"
  }

  provisioner "local-exec" {
    command = "echo ${aws_instance.web.private_ip} >> private_ips.txt"
  }
}

# Start-Process powershell -Verb runAs
  # Rename-Computer -newname “teraform${count.index}”
  # restart-computer -force
#wmic "computersystem where caption='IP-0A64Temp1' rename teraform"
# Start-Process -Verb RunAs -FilePath wmic "computersystem where caption='teraform' rename teraform00"
#   restart-computer -force