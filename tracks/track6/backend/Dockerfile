##Backend Dockerfile
#
# create through tomcat environment
FROM tomcat
# update environment
RUN apt-get update -y
# intall ?
RUN apt-get install -f
# install java
RUN apt-get install openjdk-11-jdk-headless -y
# add jar to webapps directory
ADD ./build/libs/project-1.0-SNAPSHOT.jar /usr/local/tomcat/webapps
# add application-docker.properties to root directory
ADD ./src/main/resources/application-production-docker.properties /root/
# start application (with some configurations)
CMD java -jar /usr/local/tomcat/webapps/project-1.0-SNAPSHOT.jar --spring.config.location=file:///root/application-production-docker.properties
# define port
EXPOSE 8080
