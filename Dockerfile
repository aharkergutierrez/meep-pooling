# For Java 8
FROM openjdk:8-jdk-buster

ENV GCSFUSE_REPO=gcsfuse-buster
RUN apt-get update
RUN apt-get -y install sudo
RUN apt-get install -y ffmpeg
RUN apt-get install -y curl
RUN echo "deb http://packages.cloud.google.com/apt $GCSFUSE_REPO main" > /etc/apt/sources.list.d/gcsfuse.list
RUN curl https://packages.cloud.google.com/apt/doc/apt-key.gpg | apt-key add -
RUN apt-get update
RUN apt-get install -y gcsfuse wget

LABEL maintainer="meep.com"

# cd /opt/app
WORKDIR /opt/app

# Refer to Maven build -> finalName
ARG JAR_FILE=target/*.jar

# cp target/spring-boot-web.jar /opt/app/app.jar
COPY ${JAR_FILE} app.jar

COPY ini.sh ini.sh

# java -jar /opt/app/app.jar
ENTRYPOINT ["bash","ini.sh"]