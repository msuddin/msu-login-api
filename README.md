# MSU Login API

## Purpose
This is a Java API that is used to handle user login authentication and user data storage

## Dependencies
This project assumes that postgresql://localhost:5432/db is available.
This project assumes that the docker container found at https://github.com/msuddin/msu-db is running locally. Instructions on how to run the DB can be found at the URL.

## Instructions
How does this run?
This is a Java project therefore need to run a Jar locally. Firstly, to build this project we will need to set the following permissions:
```
chmod +x gradlew
```

Once that is done, we can run the project using the following gradle command:
```
./gradlew clean build
```

Now use the command below to run the jar locally:
java -jar build/libs/<name>.jar

## Endpoints
Each endpoint can be triggered via curl once the Jar is running locally. Here are all the endpoints with example output:

### /hello
```
curl http://localhost:8080/hello
Hello, I am awake
```

### /login
```
curl http://localhost:8080/login/admin/password
hello admin/password

curl http://localhost:8080/login/someone/something
Not authenticated
```

### /login/{username}/{password}
```
curl http://localhost:8080/login/admin/password
hello admin/password%
```

### /loginName
```
curl --request GET --header "Content-Type: application/json" http://localhost:8080/loginName\?username\=admin\&password\=password
{"username":"admin","password":"password"}
```

### /login
```
curl --header "Content-Type: application/json" -d "{\"username\":\"admin\",\"password\":\"password\"}" http://localhost:8080/login
{"username":"admin","password":"password"}
```

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
