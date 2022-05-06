FROM alpine:3.15.4
LABEL author="Sebastian Gawron"

RUN  apk update \
  && apk add openjdk11

WORKDIR app
ADD target/TechnologieChmurowe-1.0.0-SNAPSHOT.jar /app/TechnologieChmurowe-1.0.0-SNAPSHOT.jar
EXPOSE 8080
CMD java -jar TechnologieChmurowe-1.0.0-SNAPSHOT.jar