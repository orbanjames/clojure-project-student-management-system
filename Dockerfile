FROM openjdk:8-alpine

COPY target/uberjar/student-management-system.jar /student-management-system/app.jar

EXPOSE 3000

CMD ["java", "-jar", "/student-management-system/app.jar"]
