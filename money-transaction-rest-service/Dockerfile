# Docker File for MoneyTransactionRestService

# Using Maven Alpine image with Java 8
FROM maven:3-jdk-8-alpine AS mavenbuild

# Copy sourse files
WORKDIR /money-transaction-rest-service/app/src/
COPY src ./
COPY pom.xml ../

# Build Jar File
RUN mvn -f /money-transaction-rest-service/app/pom.xml clean package -DskipTests

# Using Alpine image with Java 8
FROM openjdk:8-jdk-alpine

WORKDIR /money-transaction-rest-service/lib/

COPY --from=mavenbuild /money-transaction-rest-service/app/target/moneytransactionservice-0.0.1-SNAPSHOT.jar ./money-transaction-rest-service.jar

EXPOSE 7000

# Run the Java Application
ENTRYPOINT [ "java","-jar","/money-transaction-rest-service/lib/money-transaction-rest-service.jar"]