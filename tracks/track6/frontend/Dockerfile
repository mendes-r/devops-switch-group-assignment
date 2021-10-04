##Dockerfile_Frontend
# create through httpd enviroment
FROM httpd
# update
RUN apt-get update -y
# install
RUN apt-get install -f
# install node.js
RUN apt-get install nodejs -y
# install npm
RUN apt-get install npm -y
# copy build directory to /usr/local/apache2/htdocs/
ADD ./frontend/build/ /usr/local/apache2/htdocs/
# expose port
EXPOSE 80
