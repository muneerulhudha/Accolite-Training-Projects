README.md

Steps taken to do the assignment:

-- Manually created a spot instance and terminated it to get to know how ec2 works.

-- Install aws sdk toolkit for Eclipse.

-- Create a new AWS Java Project.

-- Code automatically got generated for creating instances and for creating security groups

-- Added a security group by changing the default security group name.

-- Created a ec2 spot instance by editing the code.

-- Wrote a method to pass user data to the spot instance. 

-- The user data is base64 encoded.

-- A sample file is created and the file is copied to another file through a shell command which will be executed in the spot instance. The config file is also created using the credentials.

-- Tried running the user data through a putty and verified if its working.

-- Ran the java code and that created the instance. You can verify the instance created by checking the security group description and the file created can be checked in my bucket "muneer.hudha"