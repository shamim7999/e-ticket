FROM eclipse-temurin:21-jdk

WORKDIR /eticket-app

COPY target/*.war eticket-app.jar

EXPOSE 8080

CMD ["java", "-jar", "eticket-app.jar"]