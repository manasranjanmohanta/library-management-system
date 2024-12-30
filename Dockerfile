FROM openjdk:17-alpine

WORKDIR /app

COPY target/library-management-system-0.0.1-SNAPSHOT.jar /app/library-management-system-0.0.1-SNAPSHOT.jar

EXPOSE 8080

CMD ["java", "-jar", "library-management-system-0.0.1-SNAPSHOT.jar"]