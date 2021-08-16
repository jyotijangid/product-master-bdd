#FROM openjdk:8-jdk-alpine
FROM adoptopenjdk/openjdk11:alpine-jre
#RUN addgroup -S spring && adduser -S spring -G spring
#USER spring:spring
VOLUME /tmp
ARG JAR_FILE=target/product-management-service.jar
#COPY ${JAR_FILE} product-management-service.jar
ADD ${JAR_FILE} product-management-service.jar
ENTRYPOINT ["java","-jar","/product-management-service.jar"]
#EXPOSE 2222
EXPOSE 9090