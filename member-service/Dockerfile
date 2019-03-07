FROM java:8

LABEL maintainer="patrickd.djomo@gmail.com"

EXPOSE 8081

#install Spring Boot artifact
VOLUME /tmp

ADD target/member-service-0.0.1-SNAPSHOT.jar member-service-0.0.1-SNAPSHOT.jar

RUN sh -c 'touch /member-service-0.0.1-SNAPSHOT.jar'

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/member-service-0.0.1-SNAPSHOT.jar"]