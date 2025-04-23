FROM amazoncorretto:17.0.14
LABEL authors="SandeeraJ"

WORKDIR /app/springDockerDemo

COPY target/SpringbootDocker-2.0.0.jar /app/springDockerDemo/SpringbootDocker-2.0.0.jar

EXPOSE 8082

ENTRYPOINT ["java", "-jar","SpringbootDocker-2.0.0.jar"]