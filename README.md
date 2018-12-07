Account REST api

Requiremwnts:

Java 1.8
Spring Boot
Cygwin

To compile: mvn install

To run: mvn spring-boot:run

For testing please use Cygwin

Get all accounts
curl -H 'Content-Type: application/json' -X GET -i http://localhost:8080/accounts/ will return empty JSON.

Add an account "id" needs to be excluded from JSON
curl -d '{"firstName":"First","secondName":"Second","accountNumber":"1234"}' -H 'Content-Type: application/json' -X POST -i http://localhost:8080/accounts/

Get an account
curl -H 'Content-Type: application/json' -X GET -i http://localhost:8080/accounts/1

Get all accounts
curl -H 'Content-Type: application/json' -X GET -i http://localhost:8080/accounts/

Update an account
curl -d '{"id":1,"firstName":"First1","secondName":"Second2","accountNumber":"9876"}' -H 'Content-Type: application/json' -X PUT -i http://localhost:8080/accounts/1

Delete an account
curl -H 'Content-Type: application/json' -X DELETE -i http://localhost:8080/accounts/1

