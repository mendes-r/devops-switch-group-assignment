# Track 2 - Gradle Project - Execution of the App with Ansible to Vagrant VM's

The main goal for this track was to, use our _Web Application - Family Piggy Bank_, on a  _Gradle_ version, and to run each service,
_frontend, backend and database_  with ansible on different _Virtual Machines_.
The first step towards the main goal was to execute each service in a different _Virtual Machine_. 

### Part1 : Convert the maven project to a gradle project 
Since our project was created on maven it was required to pass it to gradle ...



### Part2: Create our vagrant file to create the virtual machines
First we define our initial settings for our virtual machines, which will be the same for all : 

            
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

After that we need to define the specific configurations to the database VM

            config.vm.define "db" do |db|
                db.vm.box = "envimation/ubuntu-xenial"
                db.vm.hostname = "db"
                db.vm.network "private_network", ip: "192.168.33.11"
            
                # We want to access H2 console from the host using port 8082
                # We want to connet to the H2 server using port 9092
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
     

Furthermore we define the specific configurations to the backend server VM

              config.vm.define "be" do |be|
                be.vm.box = "envimation/ubuntu-xenial"
                be.vm.hostname = "be"
                be.vm.network "private_network", ip: "192.168.33.10"
            
We set more ram memmory for this VM
                
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

Then we create the specific configuration for the frontend VM 


                 config.vm.define "fe" do |fe|
                    fe.vm.box = "envimation/ubuntu-xenial"
                    fe.vm.hostname = "fe"
                    fe.vm.network "private_network", ip: "192.168.33.12"
                
                    # We set more ram memmory for this VM
                    fe.vm.provider "virtualbox" do |v|
                      v.memory = 1024
                    end
                
                    # We want to access tomcat from the host using port 80
                    fe.vm.network "forwarded_port", guest: 80, host: 80
                
                    fe.vm.provision "shell", inline: <<-SHELL, privileged: false
                      sudo apt-get install nodejs -y
                      sudo apt-get install npm -y
                      sudo ln -s /usr/bin/nodejs /usr/bin/node
                      sudo apt-get install apache2 -y
                    SHELL
                
                  end
                  
Configurations specific to the jenkins server VM  

                 config.vm.define "ansible" do |ansible|
                   config.vm.provider "virtualbox" do |v|
                         v.memory = 2048
                       end
                    ansible.vm.box = "envimation/ubuntu-xenial"
                    ansible.vm.hostname = "ansible"
                    ansible.vm.network "private_network", ip: "192.168.33.13"
               
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






### Part7: Create our playbook.yml 
We create the playbook.yml with the purpose to describe all tasks, then to the ansible run on the number of computers.
Ansible performs all playbook tasks on the number of computers specified in the "hosts" field and then outputs a report.
Hosts are defined as computers that we want to control with ansible.

First we define our _db host_ where: 
1 - Update _apt cache_ and install jdk with: 
         ```
            
            -name: update apt cache
            apt: update_cache=yes 
            - name: install jdk
             apt: name=openjdk-8-jdk-headless state=present   
           
             
2- Create a directory if it does not exist and then remove h2 jar files:  
   
            - name: create a directory if it does not exist
                  ansible.builtin.file:
                    path: /usr/src/app
                    state: directory
                - name: remove h2 jar files
                  ansible.builtin.file:
                    path: /usr/src/app/*.jar*
                    state: absent   

3- Download h2 jar with the following : 
           
           - name: download h2 jar
                 get_url:
                   url: https://repo1.maven.org/maven2/com/h2database/h2/1.4.200/h2-1.4.200.jar
                   dest: /usr/src/app

4- Kill the h2 process and then start the h2 server: 

            - name: kill h2 process
                 shell: pkill -9 java 
                 ignore_errors: yes   
               - name: start h2 server
                 shell: nohup java -cp /usr/src/app/h2*.jar org.h2.tools.Server -web -webAllowOthers -tcp -tcpAllowOthers -ifNotExists </dev/null >/dev/null 2>&1 &
                 
                 
                 
Next we define our _backend host_ where: 

1- Update the apt cache and install jdk:        

            - name: update apt cache
                apt: update_cache=yes
            - name: install jdk
                apt: name=openjdk-8-jdk-headless state=present

2-  Copy the jar file and the application properties file to server

             - name: copy the jar file to server
                copy: src=../../build/libs/project-1.0-SNAPSHOT.jar dest=/var/lib/tomcat8/webapps
             - name: copy the application properties file to server
                copy: src=../../src/main/resources/application-production-vagrant.properties  dest=/var/lib/tomcat8/webapps

3-  Kill backend process

             - name: kill backend process
                  shell: pkill -9 java
                  ignore_errors: yes

4- Start backend server

              - name: start backend server
                  shell: nohup java -jar /var/lib/tomcat8/webapps/project-1.0-SNAPSHOT.jar --spring.config.location=file:///var/lib/tomcat8/webapps/application-production-vagrant.properties </dev/null >/dev/null 2>&1 &


In the end we specify our _frontend host_ with the following:

1- Update apt cache and clean frontend folder in server

         - name: update apt cache
              apt: update_cache=yes
            - name: clean frontend folder in server
              ansible.builtin.file:
                path: /var/www/html/*
                state: absent

2- copy frontend build folder to server

        copy: src=../../frontend/build/  dest=/var/www/html/
        
        
        
### Part8: Create Hosts file        

The host are a group of virtual machines , which we will control with ansible defines the machines and indicates their ip.

            
            [devopsservers]
            db ansible_ssh_host=192.168.33.11 ansible_ssh_port=22 ansible_ssh_user=vagrant ansible_ssh_pass=vagrant
            backend ansible_ssh_host=192.168.33.10 ansible_ssh_port=22 ansible_ssh_user=vagrant ansible_ssh_pass=vagrant
            frontend ansible_ssh_host=192.168.33.12 ansible_ssh_port=22 ansible_ssh_user=vagrant ansible_ssh_pass=vagrant



### Part9 : Jenkins

Jenkins
Before starting the pipeline some plugins must be added to Jenkins:

Nodejs to be able to run the npm command;
Jacoco to publish the test coverage;
HTML Publisher to publish the javadoc; and
Ansible to handle the ansible stage.
For the Nodejs plugin we need to set up some extra configurations.



                        
### Part10: Create our jenkins file 

By adding a specific Nodejs installation in Jenkins' Global Tool Configurations, we are able to use npm command by adding the following line to our script:

            tools {nodejs "node"}

1- First we define our checkout stage with our credentials from bitbucket, and with the link to access our repository
        
         git credentialsId: 'devops-repo-credentials', url: 'https://tiagoswitch@bitbucket.org/BVSousa90/devops_g2.git'

2- After that we elucite the assemble backend step where we will run our bootJar from gradlew.         
With that purpose first we run the _sh ./gradlew clean_ and then _sh ./gradlew bootJar_

                    script {
                    if (isUnix()){
                        sh './gradlew clean'
                        sh './gradlew bootJar'
                    }
                    else
                    {
                       bat  './gradlew clean'
                       bat './gradlew bootJar'
                    }

If you have maven the command is this: 

        sh './mvnw package -Dmaven.test.skip=true'
        
3- Later we designate the script for assemble frontend, there for we describe the folder path,
then we erase the node_module folder subsquentily install the react dependencies and define the react app url:            


                  stage('Assemble Frontend') {
                            steps {
                                echo 'Assembling...'
                                dir("frontend/"){
                                    script{
                                        sh 'rm -rf node_modules/; npm install; REACT_APP_URL_API=http://192.168.33.10:8080 CI=false npm run build'
                                    }
                                }
                            }
                        }

4- Afterwards we settle our javadoc step, which will show the class and packages glossary, 
                
                  steps {       
                   echo 'Generating javadoc report...'
                         script {
                             if (isUnix())
                                sh './gradlew javadoc'
                             else
                                bat './gradlew javadoc'
                             publishHTML (target : [allowMissing: false,
                                               alwaysLinkToLastBuild: true,
                                               keepAll: true,
                                               reportDir: 'build/docs/javadoc/',
                                               reportFiles: 'index.html',
                                               reportName: 'JavaDoc Report',
                                               reportTitles: 'The Report'])
                                  }
                              }
                          }

On maven to execute the javadocs we type: 

         sh './mvnw javadoc:javadoc'
instead of _sh './gradlew javadoc'_ 


5- Subsequently we explain the Test stage, where we run the tests and build .xml with the tests results
                    
                              
                  script {
                            if (isUnix())
                              sh './gradlew test'
                            else
                               bat './gradlew test'
                               }
                              junit 'build/test-results/**/*.xml'
                              jacoco(execPattern: 'build/jacoco/test.exec')
                              
                            }
                      }  
                      
On maven we type  _sh './mvnw test'_ to execute the test task and substitute the last two rows for the following:                      

         junit 'target/surefire-reports/*.xml'
         jacoco(execPattern: 'target/jacoco.exec')
         
To verify the tests and the coverage for your classes on the target folder
                      
Then we have our archive stage where we archive our .jar on the build/libs folder:                     

                stage('Archive') {
                            steps {
                                echo 'Archiving...'
                                archiveArtifacts 'build/libs/*.jar'
                            }
                        }
                        
On maven we substitute the archive artifacts for :                     

             archiveArtifacts 'target/*.jar'

to go to the target folder.            
                        
6- The last stage consists on deploying the application locally
        
                stage('Deploy') {
                            steps {
                                echo 'Deploying...'
                                ansiblePlaybook(inventory: 'tracks/track2/hosts', playbook: 'tracks/track2/playbook.yml')
                            }
                        }
                         