# toll-parking
toll-parking is an API for the management of a toll car park.

## Getting Started
These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.
### Prerequisites
To execute the application you need
* Java 11
* Maven
* IntelliJ (or any preferred IDE)

installed on your machine.
### Installing
1. From the top menu, File -> new -> Project from Version Control...
2. Fill with github URL and clone.
3. Enable auto import from pop up in IntelliJ after opening the project.
4. From the top menu, File -> Project Structure.
5. Choose Dependencies tab.
6. Choose Module SDK as 11.

## Running the application
### Directly from Intellij
1. Right click on `TollParkingApplication.java` class can be found in `src.main.com.assignment` package.
2. Choose run `TollParkingApplication.java...main()`.
### Running from command line
```bash
mvn clean spring-boot:run
```
## Running the tests
1. Right click on package `java` in the package `src.test` package.
2. Choose Run 'All tests' or Run 'All tests' with Coverage for test coverage details.

## Usage via Swagger UI
### Access the Swagger UI
After running the application, you can use the api via local Swagger UI.
1. Open your favourite browser and go to `http://localhost:8080/swagger-ui.html`.
2. Enjoy testing the Api Toll-Parking.
### Api usage
The Api `/tollParking` contains four endPoint :
* `/initparking` : The first action to call in the Api to initiate parking configuration.
* `/checkin` : handles the action of a car entering the parking.
* `/checkout` : handles the action of a car exiting the parking.
* `/bill` : generate billing information after exiting the parking.

## In-memory H2 Database
The Api storage is an H2 in-memory database.

The database file is created during api usage, the database file name is `data.mv.db` in project root.
You can visualize data stored in `.db` database file with any database tool reader (RazorSQL for example).

