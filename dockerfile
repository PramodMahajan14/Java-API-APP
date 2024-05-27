FROM openjdk:20 AS BUILD_IMAGE
RUN apt update && apt install maven -y
RUN git clone https://github.com/PramodMahajan14/Java-API-APP.git
RUN cd Java-API-APP && git checkout docker && mvn install

FROM tomcat:9-jre11
RUN rm -rf /usr/local/tomcat/webapps/*
COPY --from=BUILD_IMAGE Java-API-APP/target/Java-API-APP-v2.jar usr/local/tomcat/webapps/ROOT.jar

EXPOSE 8080
CMD [ "catalina.sh","run" ]