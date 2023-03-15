FROM openjdk:17-jdk-slim
WORKDIR /app

# Copy the application jar file to the container
COPY build/libs/WeatherApp-0.0.1-SNAPSHOT.jar app.jar

# Set the environment variable for the log file path
ENV LOG_FILE_PATH /logs/log.txt

# Run the application when the container starts
CMD ["java", "-jar", "app.jar"]
