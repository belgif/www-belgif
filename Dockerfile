# syntax=docker/dockerfile:1

# start with downloading / converting data and compiling latest sources

FROM maven:3-openjdk-17-slim as builder
MAINTAINER bart.hanssens@bosa.fgov.be

RUN apt-get update && \
  apt-get install -y git-core && \
  useradd -m belgif

USER belgif

RUN mkdir -p /tmp/belgif/specification/rest

RUN cd /home/belgif && \
  git clone https://github.com/belgif/rest-guide && \
  cd /home/belgif/rest-guide && \
  mvn clean compile && \
  mv /home/belgif/rest-guide/target/generated-docs /tmp/belgif/specification/rest/api-guide

RUN cd /home/belgif && \
  git clone https://github.com/belgif/rest-security && \
  cd /home/belgif/rest-security && \
  mvn clean site && \
  mv /home/belgif/rest-security/target/site/doc /tmp/belgif/specification/rest/security-guide


RUN cd /home/belgif && \
  git clone https://github.com/belgif/www-belgif && \
  cd /home/belgif/www-belgif && \
  mvn clean install -DskipTests && \
  java -jar target/www*.jar /home/belgif/www-belgif/data /tmp/belgif 
