# Use the OpenJDK 17 as the base image
FROM openjdk:17-jdk-alpine

# Create a volume for the /tmp directory
VOLUME /tmp

# Copy the target/*.jar file to the container as app.jar
COPY target-a/*.jar app.jar

# Set the entrypoint to run the Java command with the app.jar file
ENTRYPOINT ["java","-jar","/app.jar"]

# Expose port 8080 for the container
EXPOSE 8080
