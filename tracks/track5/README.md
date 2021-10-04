The main goal for this track was to, use our _Web Application - Family Piggy Bank_, the _Maven_ version, and to run each service,
_frontend, backend and database_ in different _Cloud Containers_.
The first step towards the main goal was to execute each service in a different _Docker Container_.

This track was very similar to the previous track 3, there were no changes made to the _dockerfiles_ or to the _docker-compose_, so I will not repeat here
what was explained previously.
I will only explain where the tracks diverge.

_1.Creating a pipeline_

After entering _Jenkins_ in my local machine, I created a new pipeline, named _Track 5_ and installed two new plugins, _Jacoco_ plugin and _nodejs_.
In the pipeline configuration I used the _Jenkinsfile_ that was the in the repository on the folder _tracks/track5_.
In the following _Jenkinsfile_ where is the (...) is where the stages are the same as the previous _Jenkinsfiles_ (track 3 and 4).

```
pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                echo 'Checking out...'
                git credentialsId: 'devops-bitbucket-credenciais', url: 'https://BVSousa90@bitbucket.org/BVSousa90/devops_g2.git'
            }
        }
       (...)
        stage('Assemble Frontend') {
            steps {
                echo 'Assembling...'
                dir("frontend/"){
                    script{
                        sh 'npm install; REACT_APP_URL_API=http://192.168.33.10:8080 CI=false npm run build' --> (1)
                    }
                }
            }
        }
       (...)
       (...)
        stage('Building and pushing images'){
            steps{
                echo 'Building and pushing Image...'
                dir('tracks/track5'){
                    script{
                        sh 'docker-compose build --no-cache'
                        sh 'docker-compose up -d'
                        docker.withRegistry('', '1201761'){
                           sh "docker tag track5_frontend 1201761/devops2021:frontend_${env.BUILD_NUMBER}" --> tagging the image ...
                           sh "docker tag track5_backend 1201761/devops2021:backend_${env.BUILD_NUMBER}"
                           sh "docker tag track5_database 1201761/devops2021:database_${env.BUILD_NUMBER}"
                           sh "docker push 1201761/devops2021:frontend_${env.BUILD_NUMBER}" --> and pushing it to  my personal docker hub repository
                           sh "docker push 1201761/devops2021:backend_${env.BUILD_NUMBER}"
                           sh "docker push 1201761/devops2021:database_${env.BUILD_NUMBER}"
                        }
                    }
                }
            }
        }
        (...)
    }
}
```

In step 1, since I was running _Jenkins_ in my local machine, and my operating system is _Linux_, I was able to use the npm command and set the
environment variable differently. This step allows the override of the variable that was in the project, and allows the communication between frontend and backend.
As far as I understood, this variable points to the backend 'virtual IP' (the one created on docker-compose), and the docker infrastructure forwards the request to the right place.

After some failed builds, and some problems pushing the docker image to the docker hub repository, I was able, finally, to have a successful build with the images
pushed to docker hub.

![docker-hub](./images/track5/docker-hub)

After this, it was time to check if my application was ready, so in my browser searching for '_localhost:8081_':
![app-login](./images/track5/app_login1.png)

And the _H2_ console, '_localhost:8082_':
![h2](./images/track5/h2.png)

_2.Smoke tests_

For this track, some smoke tests were executed manually such as:
--> Login into the app;
--> Creating a family with and without phone numbers;
--> Creating a family member, with and without phone numbers;
--> Checking the family profile, and the member profile;
--> Checking to see if all error messages were correct;
--> Checking if the database had the added family and members;

The images of some of these tests are presented in track 3.

This step was particular difficult since after a lot of research I was a little confused about which way to go, manual smoke testing ou automatic smoke testing.
In the case of automatic smoke tests, it was necessary to create a bash script, and add another stage to the _Jenkins_ pipeline where the script
was going to be used. And the smoke tests executed during the build.
One example of a script/smoke test that could be in the jenkins stage is:

```
curl -o - http://myapplication/ --max-time 600  |  grep "Family Piggy Bank"
if [ $? -eq 0 ] ; then
    echo "Success"
    exit 0
else
    echo "Fail"
    exit 1
fi
```

In this example, it would check if the page was successfully built by looking for the expression "Family Piggy Bank" in the application home page

_3.Creating a Server and preparing the environment_

The last part of this track was to execute the application in a cloud container.
For this step I created a new server in the _ISEP_ Network.

![server](./images/track5/server.png)

I choose this server because it already had _Docker_ support making my task easier.
Before sending my docker-compose file to the server I had to install _compose_, then I access the server through _sftp_ in order to send my docker-compose to the server.

```
sftp> lls --> to see where I am in my local machine
(...)
sftp> lcd --> to change the directory in my local machine, I have to be in the same folder as the docker-compose
(...)
sftp> put docker-compose.yml
Uploading docker-compose.yml to /root/docker/docker-compose.yml
docker-compose.yml                                          100%
sftp> ls --> to check if the docker-compose is in my server
```

After this I had to edit my file in order for it to use the docker images that I had pushed to the docker hub repository in the previous step.

```
version: '3'
services:

  backend:
    image: 1201761/devops2021:backend_53
    ports:
      - "80:8080"
    networks:
      default:
        ipv4_address: 192.168.33.10
    depends_on:
      - "database"

  database:
    image: 1201761/devops2021:database_53
    ports:
      - "8082:8082"
      - "9092:9092"
    volumes:
      - ./data:/usr/src/data
    networks:
      default:
        ipv4_address: 192.168.33.11

  frontend:
    image: 1201761/devops2021:frontend_53
    ports:
      - "8081:80"
    networks:
      default:
        ipv4_address: 192.168.33.12
    depends_on:
      - "backend"

(...)

```

Then it was time to run the _docker-compose up_, all images were pulled from the repository, and the application was initiated without any problems.

Opening the browser with '_http://vs464.dei.isep.ipp.pt:8081/_':

![server-app](./images/track5/server-app.png)

And _H2_ console with '_http://vs464.dei.isep.ipp.pt:8082/_':

![server-h2](./images/track5/h2-console.png)

![server-h2](./images/track5/h2-server.png)

When I tried to enter the application with the correct credentials, I always got the same message "Unable to connect". As far as I understood, I could connect the frontend container with the backend.

So in the _Jenkinsfile_ I made a change in the "Assembling Frontend" stage:

```
  (...)
        stage('Assemble Frontend') {
            steps {
                echo 'Assembling...'
                dir("frontend/"){
                    script{
                        sh 'npm install; REACT_APP_URL_API=root@vs464.dei.isep.ipp.pt:80 CI=false npm run build'
                    }
                }
            }
        }
       (...)
```

My first option was to replace the IP address for the name of the service that recieves the requests from frontend, in my case "backend". For this I decided to create a new pipeline, and copied the script directly to _Jenkins_ and made the necessary changes.
The build was successfull mas when running the app in the server, I couldn't connect to the backend.

Then, and for my final version, I tried using the address of my server and the port where the backend was running....Success!
I finally gained access to my app, and was able to enter, and add families and members that were added to the database also.

![server-app]()
![server-app1]()
![h2-server]()

Both _jenkinsfile_ ,for the new pipeline, and _docker-compose_ ,that I used in the server, are in the path _track5/server_.

The last step was to stop the containers that were running in the server, and run the docker compose again but this time with '_-d_' flag, '_docker-compose up -d_' , in order for the application to remain running in the server.

Ending this track.
