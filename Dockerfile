FROM java:8
EXPOSE 8080
ADD /target/assessment-0.0.1.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]