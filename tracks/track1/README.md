# Local deployment of App with Ansible to Vagrant VMs -Track 1 and 2

The main goal for this assignment was to create Three Virtual Machines using a Vagrantfile: one that contains the backend of our application, a second one that holds our frontend, and a third one with the database. These machines are to be configured and managed with Ansible. On track 1 the Maven project deployed locally, and on track 2, the project converted previously to Gradle also was deployed locally.

### 1: Create a Vagrantfile to create Virtual Machines

On each track (1 and 2), a Vagrantfile was created in order to generate 4 Virtual Machines: one containing the backend (be), a second one the database (db), a third one the frontend (fe), and finally a fourth one containing ansible (ansible). For this assignment "[envimation/ubuntu-xenial](https://app.vagrantup.com/envimation/boxes/ubuntu-xenial)" box was used for every virtual machine.
On the top section of this file, we defined the settings common to every virtual machine:

            Vagrant.configure("2") do |config|
              config.vm.box = "envimation/ubuntu-xenial"

              # This provision is common for both VMs
              config.vm.provision "shell", inline: <<-SHELL
                sudo apt-get update -y
                sudo apt-get install iputils-ping -y
                sudo apt-get install -y avahi-daemon libnss-mdns
                sudo apt-get install -y unzip
                sudo apt-get install openjdk-8-jdk-headless -y
                sudo apt-get install python3 --yes
                # ifconfig
              SHELL

The "iputils" is necessary in order to ping other virtual machines to check the connection between them. Python 3 was also installed because Ansible will need to use it.

After that, we need to define the specific configurations of the database VM:

            config.vm.define "db" do |db|
                db.vm.box = "envimation/ubuntu-xenial"
                db.vm.hostname = "db"
                db.vm.network "private_network", ip: "192.168.33.11"

                # We want to access H2 console from the host using port 8082
                # We want to connect to the H2 server using port 9092
                db.vm.network "forwarded_port", guest: 8082, host: 8082
                db.vm.network "forwarded_port", guest: 9092, host: 9092

                # We need to download H2
                db.vm.provision "shell", inline: <<-SHELL
                  wget https://repo1.maven.org/maven2/com/h2database/h2/1.4.200/h2-1.4.200.jar
                SHELL

                # To connect to H2 use: jdbc:h2:tcp://192.168.33.11:9092/./jpadb
                db.vm.provision "shell", :run => 'always', inline: <<-SHELL
                  java -cp ./h2*.jar org.h2.tools.Server -web -webAllowOthers -tcp -tcpAllowOthers -ifNotExists > ~/out.txt &
                SHELL
              end

The database VM has the following IP address:192.168.33.11. The other virtual machines will have IP addresses in the same network. The jar file of the h2 also is downloaded to this virtual machine. Port 8082 accesses the h2 console, and we can use port 9092 to connect to the h2 server.
Next, the following configurations were used for the backend VM:

    config.vm.define "be" do |be|
        be.vm.box = "envimation/ubuntu-xenial"
        be.vm.hostname = "be"
        be.vm.network "private_network", ip: "192.168.33.10"

    # We set more ram memmory for this VM
    be.vm.provider "virtualbox" do |v|
      v.memory = 1024
    end

    # We want to access tomcat from the host using port 8080
                be.vm.network "forwarded_port", guest: 8080, host: 8080

                be.vm.provision "shell", inline: <<-SHELL, privileged: false
                  sudo apt install tomcat8 -y
                  sudo apt install tomcat8-admin -y
                  # If you want to access Tomcat admin web page do the following:
                  # Edit /etc/tomcat8/tomcat-users.xml
                  # uncomment tomcat-users and add manager-gui to tomcat user
                SHELL

              end

Tomcat, a web server, was installed in this machine. An IP address of the same network was chosen for this VM. The RAM for this VM also was set in this file to help some future steps run more smoothly.
To configure the last virtual machine, the one that contains the frontend of our project:

                 config.vm.define "fe" do |fe|
                    fe.vm.box = "envimation/ubuntu-xenial"
                    fe.vm.hostname = "fe"
                    fe.vm.network "private_network", ip: "192.168.33.12"
                  # We set more ram memmory for this VM
                    fe.vm.provider "virtualbox" do |v|
                    v.memory = 1024
                  end


                 # We want to access http server from the host using port 80
                    fe.vm.network "forwarded_port", guest: 80, host: 80

                    fe.vm.provision "shell", inline: <<-SHELL, privileged: false
                      sudo apt-get install nodejs -y
                      sudo apt-get install npm -y
                      sudo ln -s /usr/bin/nodejs /usr/bin/node
                      sudo apt-get install apache2 -y
                    SHELL

                  end

In this VM, apache, an HTTP server was installed. Node and npm also were installed. Once again, an IP address was attributed to this VM in the same network.
The configurations specific to the last VM that contains Ansible are as follows:

```
    config.vm.define "ansible" do |ansible|
    ansible.vm.box = "envimation/ubuntu-xenial"
    config.vm.provider "virtualbox" do |v|
      v.memory = 2048
    end
    ansible.vm.hostname = "ansible"
    ansible.vm.network "private_network", ip: "192.168.33.13"

    # For some Windows and for running ansible "inside" jenkins
    # ansible.vm.synced_folder ".", "/vagrant", mount_options: ["dmode=777,fmode=777"]
    # It seems that ansible has security issues with the previous command. Use instead:
    ansible.vm.synced_folder ".", "/vagrant", mount_options: ["dmode=775,fmode=600"]

    # For acessing jenkins in 8081
    ansible.vm.network "forwarded_port", guest: 8080, host: 8081

    ansible.vm.provision "shell", inline: <<-SHELL
      sudo apt-get install -y --no-install-recommends apt-utils
      sudo apt-get install software-properties-common --yes
      sudo apt-add-repository --yes --u ppa:ansible/ansible
      sudo apt-get install git-all --yes
      sudo apt-get install nodejs --yes
      sudo apt-get install ansible --yes
      # For jenkins
      sudo wget http://mirrors.jenkins.io/war-stable/latest/jenkins.war
      sudo java -jar jenkins.war
    SHELL
  end

end
```

In this VM, we installed Jenkins and Ansible. For this VM, once again, the IP address set is in the same network. The "mount_options" permissions are needed because Jenkins and Ansible are running on the same machine.

##### 1.1: Running the VM

In the folder containing the Vagrantfile, the following command was issued to start the VMs:

    vagrant up

This command will launch the four virtual machines configured in the Vagrantfile.

##### 1.2: Check the VM's connection

After running the command displayed above, the virtual machines are now running. To check if this is indeed the case, the following command was used, and the succeeding result was displayed:

    vagrant status

[!Image1](Image1.png)

Next, to test the connection between the different virtual machines, we went inside ansible VM and tried to connect with the different virtual machines. For instance to check the connection with the db we write 'ping' and the db's IP:

    ping 192.168.33.11

[!image3](Image3.png)

### 2: Create a Playbook and Hosts

A playbook file was created to describe all the tasks that Ansible is to execute on a certain number of computers. Ansible performs all of the playbook's tasks on said number of computers that are defined as "hosts" and are specified in the "hosts" field. In this case, the hosts are the three other virtual machines (backend, frontend, and DB) created with the Vagrantfile. After performing those tasks, a report is issued.

Another file is used to define the host's IP addresses and credentials. This file is named 'hosts' and has the following content:

            [devopsservers]
            db ansible_ssh_host=192.168.33.11 ansible_ssh_port=22 ansible_ssh_user=vagrant ansible_ssh_pass=vagrant
            backend ansible_ssh_host=192.168.33.10 ansible_ssh_port=22 ansible_ssh_user=vagrant ansible_ssh_pass=vagrant
            frontend ansible_ssh_host=192.168.33.12 ansible_ssh_port=22 ansible_ssh_user=vagrant ansible_ssh_pass=vagrant

Going back to out Playbook file, first we define the tasks for our _db host_ where we :

1 - Update _apt cache_ and install jdk

            -name: update apt cache
            apt: update_cache=yes
            - name: install jdk
             apt: name=openjdk-8-jdk-headless state=present

2- Create a directory, in case it does not exist, and then remove the h2 jar files

            - name: create a directory if it does not exist
                  ansible.builtin.file:
                    path: /usr/src/app
                    state: directory
                - name: remove h2 jar files
                  ansible.builtin.file:
                    path: /usr/src/app/*.jar*
                    state: absent

3- Download h2 jar:

           - name: download h2 jar
                 get_url:
                   url: https://repo1.maven.org/maven2/com/h2database/h2/1.4.200/h2-1.4.200.jar
                   dest: /usr/src/app

4- Kill the h2 process and then start the h2 server

            - name: kill h2 process
                 shell: pkill -9 java
                 ignore_errors: yes
               - name: start h2 server
                 shell: nohup java -cp /usr/src/app/h2*.jar org.h2.tools.Server -web -webAllowOthers -tcp -tcpAllowOthers -ifNotExists </dev/null >/dev/null 2>&1 &

Next, we define the tasks for our _backend host_ where we:

1- Update the apt cache and install jdk

            - name: update apt cache
                apt: update_cache=yes
            - name: install jdk
                apt: name=openjdk-8-jdk-headless state=present

2- Copy the jar file and the application properties file to the server

             - name: copy the jar file to server
                copy: src=../../build/libs/project-1.0-SNAPSHOT.jar dest=/home/
             - name: copy the application properties file to server
                copy: src=../../src/main/resources/application-production-vagrant.properties  dest=/home/

3- Kill the backend process

             - name: kill backend process
                  shell: pkill -9 java
                  ignore_errors: yes

4- Start the backend server

              - name: start backend server
                  shell: nohup java -jar /home/project-1.0-SNAPSHOT.jar --spring.config.location=file:///home/application-production-vagrant.properties </dev/null >/dev/null 2>&1 &

The dest folder indicates the destination folder on the VM. Ideally the path chosen would have been /var/lib/tomcat8/webapps . The reason as to why 'home' was chosen was because spring boot already uses Tomcat.

At the end, we specify the tasks for our _frontend host_ with the following:

1- Update apt cache and clean the frontend folder in the server

         - name: update apt cache
              apt: update_cache=yes
            - name: clean frontend folder in server
              ansible.builtin.file:
                path: /var/www/html/*
                state: absent

2- copy the frontend build folder to the server

        copy: src=../../frontend/build/  dest=/var/www/html/

### 3: Track 1 Creating a Jenkinsfile

This section refers to the Maven Project. As Maven was already part of the initial project, no conversion is needed. For the section containing information regarding the Gradle project, please refer to track 2.
By adding a specific Nodejs installation in Jenkins' Global Tool Configurations, we can use npm command by adding the following line to our script:

            tools {nodejs "node"}

1- The "Checkout" stage does the checkout of the git project. We define our checkout stage with our credentials from Bitbucket, and with the link to access our repository. The 'devops-repo-credentials' are a way to transmit credentials without specifying them in the Jenkinsfile:

      stages {
        stage('Checkout') {
            steps {
                echo 'Checking out...'
                git credentialsId: 'devops-repo-credentials', url: 'https://Ana_Gloria@bitbucket.org/BVSousa90/devops_g2.git'
            }
        }

2- the "Assemble" stages compile and produce the archive files. In the "Assemble Frontend" stage we erase the node_module folder, and subsequently, install the react dependencies and define the react app URL:

        stage('Assemble Backend') {
            steps {
                echo 'Assembling...'
                  script {
                    if (isUnix())
                        sh './mvnw package -Dmaven.test.skip=true'
                    else
                       bat './mvnw package -Dmaven.test.skip=true'
                    }
            }

             stage('Assemble Frontend') {
            steps {
                echo 'Assembling...'
                dir("frontend/"){
                    script{
                        sh 'rm -rf node_modules/; npm install; REACT_APP_URL_API=http://192.168.33.10:8080 CI=false npm run build'
                    }
                }
            }

3- Afterwards we settle our javadoc step, which will show the class and packages glossary:

```
  stage('Javadoc') {
            steps {
                    echo 'Generating javadoc report...'
                    script {
                     if (isUnix())
                        sh './mvnw javadoc:javadoc'
                     else
                       bat './mvnw javadoc:javadoc'
                publishHTML (target : [allowMissing: false,
                             alwaysLinkToLastBuild: true,
                             keepAll: true,
                             reportDir: 'target/site/apidocs/',
                             reportFiles: 'index.html',
                             reportName: 'JavaDoc Report',
                             reportTitles: 'The Report'])
                }
            }
```

4- Subsequently, the "Test" stage is the one that executes the Unit Tests and publishes its results in Jenkins, including coverage:

```
 stage('Test') {
            steps {
                    echo 'Testing...'
                    script {
                     if (isUnix())
                        sh './mvnw test'
                     else
                       bat './mvnw test'
                    }
                    junit 'target/surefire-reports/*.xml'
                    jacoco(execPattern: 'target/jacoco.exec')
                }
        }
```

5- The "Archive" stage is going to save to Jenkins the jar file produced in the assembling stage. In this case, what is saved is the jar file:

```
                 stage('Archive') {
            steps {
                echo 'Archiving...'
                archiveArtifacts 'target/*.jar'
            }
        }
```

6- The last stage consists of deploying the application locally:

```
      stage('Deploy') {
            steps {
                echo 'Deploying...'
                ansiblePlaybook(inventory: 'tracks/track1/hosts', playbook: 'tracks/track1/playbook.yml')
            }
        }
```

# 4: Running a pipeline on Jenkins

To open Jenkins, after running the VMs, we can go to the localhost set for the ansible VM:

    localhost:8081

Then it is necessary to sign up by inserting the name, email, and password. The default plugins are then installed.
Before starting the pipeline, other plugins must be added to Jenkins:

- **Nodejs** to be able to run the _npm_ command;
- **Jacoco** to publish the test coverage;
- **HTML Publisher** to pusblish the javadoc; and
- **Ansible** to handle the ansible stage.

For the _Nodejs_ plugin we need to set up some extra configurations.

![node configuration](img/jenkins-node.png)

By adding a specific _Nodejs_ installation in Jenkins' Global Tool Configurations, we are able to use `npm` command by adding the following line to our script:

```script
tools {nodejs "node"}
```

# 4.1 Javadocs

After running the pipeline, the Javadoc can not be observed due to Jenkins security issues. To bypass it, on the Jenkins "Script console" the following command was inserted:

    System.setProperty("hudson.model.DirectoryBrowserSupport.CSP", "")

Then another build was made and the following result were obtained by chosing "Javadoc report" on the left-hand side:

[!image2](Imagem2.png)

### 5 Local Deployment

After running the Pipeline, it is possible to access our application by typing the following URL in a browser:

    http://192.168.33.12/

This URL refers to the IP address set for the VM containing the frontend. To check if the VM that contains the frontend can communicate with the backend, it will suffice to go through the login process. If there is no communication or any other issue, the following message will appear:

    Unable to connect! Please try again or later.

After logging in, to check if the connection with the database is working, a new family was added:

[!image4](Image4.png)

Next, we type in the browser the URL containing the VM of the database and the port that accesses the h2-console:

    http://192.168.33.12/8082

on the JDBC URL field, the following was typed in, as can be seen in the image:

    jbdc:jdbc:h2:tcp://192.168.33.11:9092/./jpadb

[!image5](image5.png)

This URL was specified in the application-production-vagrant.properties.
After hitting the 'Connect' button, it is possible to find the family added:

[!image6](image6.png)

Another quick verification can be made by going inside the virtual machines and check for the files that were copied to each of them. As an example, we can go inside the frontend virtual machine by using the command:

    vagrant ssh fe

and inside the ' /var/www/html/' folder. There, we can find the index.html file copied to this folder:

[!image7](Image7.png)

This copy was a result of a task in the Playbook.
