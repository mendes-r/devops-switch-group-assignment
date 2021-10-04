##DOCKERFILE_DATABASE
# update environment
FROM ubuntu
# install unzip and wget
RUN apt-get update && \
    apt-get install -y openjdk-8-jdk-headless && \
    apt-get install unzip -y && \
    apt-get install wget -y
# create folder app
RUN mkdir -p /usr/src/app
# move to created folder /app
WORKDIR /usr/src/app/
# download h2 jar file
RUN wget https://repo1.maven.org/maven2/com/h2database/h2/1.4.200/h2-1.4.200.jar
# expose ports
EXPOSE 8082
EXPOSE 9092
# run database (start database)
CMD java -cp ./h2-1.4.200.jar org.h2.tools.Server -web -webAllowOthers -tcp -tcpAllowOthers -ifNotExists
