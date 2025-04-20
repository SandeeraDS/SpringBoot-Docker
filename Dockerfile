FROM amazoncorretto:17.0.14
LABEL authors="SandeeraJ"

WORKDIR /app/springDockerDemo

COPY target/SpringbootDocker-1.0.0.jar /app/springDockerDemo/SpringbootDocker-1.0.0.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar","SpringbootDocker-1.0.0.jar"]