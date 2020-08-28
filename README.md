# Java Exercise 6

In this excercise we start with a new, more complex project. It is a Spring Boot Web Service application using Spring Web, Spring Data, JPA and Hibernate.

We introduce MapStruct to convert from Entity classes to DTO classes.

## Running the server

Run the server in any one of the ways listed below. Once the application is started it will keep running until Ctrl-C is pressed.

### Maven

```
mvn spring-boot:run
```

### Java on the command line

From the command line, after running the Maven `install` command;

```
mvn install
```

Then run the jar file, in the target directory, directly.
```
java -jar target\java-exercise-6-1.0.0-SNAPSHOT.jar
```

### From Eclise / STS

Right-click the application class and select Run As -> Spring Boot Application.

NOTE: Do to a wrong configuration in the POM.XML file you need to first run `mvn install` on the command line at least once before running from the IDE.

## Rest enpoints

There are various APIs defined in this project. Here are examples of some of the GET operations;

```
curl localhost:8080/api/movies
curl localhost:8080/api/genres
curl localhost:8080/api/people
```