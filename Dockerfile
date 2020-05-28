FROM java:8
ADD /target/backend-0.0.1-SNAPSHOT.war backend.war
ENTRYPOINT ["java", "-jar", "/backend.war"]
