FROM openjdk:17-jdk-slim
VOLUME /tmp
COPY target/wallet-service-0.0.1-SNAPSHOT.jar wallet-service.jar
ENTRYPOINT ["java", "-jar", "/wallet-service.jar"]