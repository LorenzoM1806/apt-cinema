FROM openjdk:17-jdk-alpine
EXPOSE 8080
ADD out/artifacts/product_service_jar/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]