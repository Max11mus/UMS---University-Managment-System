# Docker File for UMS Application

# Using Maven Alpine image with Java 8
FROM maven:3-jdk-8-alpine AS mavenbuild

# Copy sourse files
WORKDIR /ums/app/src/
COPY src ./
COPY pom.xml ../

# Build Jar File
RUN mvn -f /ums/app/pom.xml clean package -DskipTests

# Using Alpine image with Java 8
FROM openjdk:8-jdk-alpine

WORKDIR /ums/lib/

COPY --from=mavenbuild /ums/app/target/ums-0.0.1-SNAPSHOT.jar ./ums.jar

EXPOSE 8080

# Run the Java Application
ENTRYPOINT [ "java","-jar","/ums/lib/ums.jar"]