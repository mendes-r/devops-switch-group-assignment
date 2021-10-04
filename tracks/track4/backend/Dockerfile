FROM tomcat

RUN apt-get update -y

RUN apt-get install -f

RUN apt-get install openjdk-11-jdk-headless -y

ADD ./build/libs/project-1.0-SNAPSHOT.jar /usr/local/tomcat/webapps/

ADD ./src/main/resources/application-production-docker.properties /root/

CMD java -jar /usr/local/tomcat/webapps/*.jar --spring.config.location=file:///root/application-production-docker.properties

EXPOSE 8080