Account REST api

Requiremwnts:

Java 1.8
Spring Boot
Cygwin

To compile: mvn install

To run: mvn spring-boot:run

For testing please use Cygwin

Get all accounts
curl -H 'Content-Type: application/json' -X GET -i http://localhost:8080/accounts/ will return empty JSON.Account REST api