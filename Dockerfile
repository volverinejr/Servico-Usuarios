FROM openjdk:11-jdk-slim
VOLUME /tmp
ADD ./target/Servico-Usuarios-0.0.1-SNAPSHOT.jar app.jar
RUN bash -c 'touch /app.jar'
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app.jar"]