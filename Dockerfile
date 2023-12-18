# Build
FROM maven as build
WORKDIR /src
COPY src/main src/main
COPY pom.xml pom.xml

# Publish
FROM build as publish
WORKDIR /src
RUN mvn clean compile assembly:single

# Run
FROM amazoncorretto:11
WORKDIR /app
COPY --from=publish /src/target/MailRestServer-1.0-SNAPSHOT-jar-with-dependencies.jar MailRestServer.jar
CMD java -jar MailRestServer.jar