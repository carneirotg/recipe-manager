#Recipe Manager
A system designed to keep track of recipes, exposing endpoints to Create, Update, Read and Delete recipes from a database.

##Requirements
    - Maven
    - Java 16
    - Docker

##Build

To build the project, the database must be up because of the controller tests.
So in the root folder of the project, there is a `docker-compose.yml` file
`mvn clean install`

