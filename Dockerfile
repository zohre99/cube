#whatever image you are creating you always need to base it in another image
FROM openjdk:8-jdk-alpine

ENV       POSTGRES_USER=postgres \
          POSTGRES_PASSWORD=123

#this command is executed in container
#RUN mkdir -p /home/app

WORKDIR /home/app/cube

#this command is executed in host - this is why I can transfer file from host to the container
COPY . .
COPY src/main/resources/lib/debezium-connector-postgres /usr/share/kafka-connect/lib
#RUN mvn -f /home/app/pom.xml clean package -DskipTests

#EXPOSE 8082

ENTRYPOINT ["java","-jar","target/cube-0.0.1-SNAPSHOT.jar"]
