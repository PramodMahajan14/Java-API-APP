FROM openjdk:20 AS BUILD_IMAGE
RUN apt update && apt install maven -y
FROM tomcat:9-jre11