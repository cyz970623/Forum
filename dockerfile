FROM java:8
EXPOSE 8080
ARG JAR_FILE
ADD target/${JAR_FILE} /forum.jar
ENTRYPOINT ["java", "-jar","/forum.jar"]



