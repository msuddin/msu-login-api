# MSU Login API

## Purpose
This is a Java API that is used to handle user login authentication and user data storage

## Dependencies
This project assumes that postgresql://localhost:5432/db is available.
This project assumes that the docker container found at https://github.com/msuddin/msu-db is running locally. Instructions on how to run the DB can be found at the URL.

## Instructions
How does this run?

chmod +x gradlew
./gradlew clean build
java -jar build/libs/<name>.jar

## Endpoints
curl http://localhost:8080/hello
Hello, I am awake

curl http://localhost:8080/login/admin/password
hello admin/password

curl http://localhost:8080/login/someone/something
Not authenticated

## TODO: add in the others here, loginName and login with curls

## DB Connectivity
This project used SpringBoot JpaRepository to connect to a local PostgreSQL database. In order to that, need to setup a repository, service and model.
A repository represent connectivity to a table.
A service represent the CRUD operations that can be performed on the table.
A model represents the POJO representation of a table (getters and setters for columns).

## Technologies
SpringBoot
JUnit
REST Assured
Hamcrest
