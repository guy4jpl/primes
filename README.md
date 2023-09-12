# Primes ReSTful Service
Technical test

# Getting started

## Requirements

For building and running the application you need:

- [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Maven 3](https://maven.apache.org)

## Running the application locally

There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the `com.guychisholm.rbs.primes.PrimesApplication` class from your IDE.

Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```shell
mvn spring-boot:run
```
### Running tests
```shell
mvn test
```
### Packaging the application as a jar (and generating RestDocs)
```shell
mvn package
```


## Design

### Technology
 - Java 8
 - Maven
 - Spring Boot
 - Spring MVC
 - Spring Cache
 - Spring MVC Test
   - Junit
   - Mockito
   - Spring RestDocs (for integration testing and documentation)

### HLA

    +-Spring Boot Application------------------+
    |                                          |
    | +------------------+   +---------------+ |
    | | PrimesController +---+ PrimesService + |
    | +--------+---------+   +---------------+ |
    |          |                               |
    +-------Rest API---------------------------+
               |
               v
           /primes


## Improvements
- Using a service factory dependency within the controller to switch algorithms

## Outline
Write a RESTful service which calculates and returns all the prime numbers up to and including a number provided.

### Example

The REST call would look something like http://your.host.com/primes/10 and should return JSON content:
```json
{
  "Initial":  10,
  "Primes": [2,3,5,7]
}
```
## Requirements
- [x] The project must be written in Java 7 / 8.
- [x] The project must use Maven OR Gradle to build, test and run.
- [x] The project must have unit and integration tests.
- [x] The project must be runnable in that the service should be hosted in a container e.g. Tomcat, Jetty, Spring Boot etc.
- [x] You may use any frameworks or libraries for support e.g. Spring MVC, Apache CXF etc.
- [x] The project must be accessible from Github.

#### Optional Extensions
- [x] Deploy the solution to a chosen platform that we can access.
- [x] Consider supporting varying return content types such as XML based, that should be configurable using the requested media type.
- [x] Consider ways to improve overall performance e.g. caching results, concurrent algorithm
- [x] Consider supporting multiple algorithms that can be switched based on optional parameters
