# Bid Management System

This project is a prototype for a blind auction platform, allowing sellers to register products for auction and buyers to place bids. 

#Technologies

Java 17,
Spring Boot 3,
Spring Data JPA,
H2 Database,
Lombok,
Swagger (OpenAPI),
Actuator,
JUnit 5,
Mockito

#Architecture

The application is designed as a multi-service architecture with separate services for users, products, and bids. User records are stored independently on a "Users" server, and authentication is based on tokens issued by the "Users" server.

### Database Initialization

The application uses an H2 in-memory database, and during startup, a script `data.sql` is executed to populate the database with initial testing data. This script can be found in the `src/main/resources` directory.

Please note that this data is for testing purposes.

# Accessing H2 Console

The H2 Console allows you to interact with the H2 in-memory database used by the application. Follow these steps to access the H2 Console:

1. Ensure that the application is running locally.

2. Open your web browser and go to [http://localhost:8080/h2-console](http://localhost:8080/h2-console).

3. Enter the following details in the H2 Console login form:
   - **JDBC URL:** jdbc:h2:mem:testdb
   - **Username:** sa
   - **Password:** (Leave it empty)

4. Click the "Connect" button.

5. Explore and interact with the database using the H2 Console.

# Getting Started
To run the application locally, follow these steps:

1. Clone the repository:

git clone https://github.com/subbiahsathish0391/BidManagementSystem.git

2. Navigate to the project directory:
cd blind-auction-platform

3. Build the JAR file using Maven:
./mvnw clean package

4. Run the application using the generated JAR file:

java -jar target/BidWinner-0.0.1-SNAPSHOT.jar

The application will start on http://localhost:8080.

# Project Structure

The project follows a modular structure with separate packages for controllers, services, repositories, and models for each component (users, products, bids).

src/main/java/com/bid/auctionedge/model/AuctionUser: User-related functionality
src/main/java/com/bid/auctionedge/model/Product: Product-related functionality
src/main/java/com/bid/auctionedge/model/Bid: Bid-related functionality

# Profiles

The application supports multiple profiles for different environments. To activate a profile, set the spring.profiles.active property in application.properties:

spring.profiles.active=dev

# API Documentation

API documentation is generated using Swagger (OpenAPI). Access the Swagger UI at:

http://localhost:8080/swagger-ui/index.html

# Testing

The project includes JUnit 5 test cases for controllers, services, and repositories. 

Run the tests using:

./mvnw test
