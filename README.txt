1. The Angular client source code is in the directory timesheetapp1Client
2. The Java/Spring Boot source code is in the directory timesheetapp1

#####PLEASE FOLLOW THE INSTRUCTIONS BELOW TO RUN AND LOGIN TO THE APPLICATION.#####

1. Please ensure that you have DOCKER Desktop installed on your local machine. You can download and install it from the following location: https://www.docker.com/products/docker-desktop

2. Please run the following docker command from this directory. 

Windows
=======
PowerShell.exe -ExecutionPolicy Bypass -File install.ps1

Linux / Mac
===========
1. Make sure install.sh is executable (chmod 755 install.sh)
2. ./install.sh

This command will create four different containers with pre-installed dependencies and run the generated application.

The front-end UI will run on port 4700 and the back-end API will run on port 5555.

3. Login to the Application using the instructions provided in the tutorial at https://tutorials.getfastcode.com/docs/extend-the-generated-application