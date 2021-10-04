FROM httpd

RUN apt-get update -y

RUN apt-get install -f

#RUN apt-get install git -y

RUN apt-get install nodejs -y

RUN apt-get install npm -y

ADD ./frontend/build/ /usr/local/apache2/htdocs/

EXPOSE 80
