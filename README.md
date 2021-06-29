#Recipe Manager
A system designed to keep track of recipes, exposing endpoints to Create, Update, Read and Delete recipes from a database.

##Requirements
    - Maven
    - Java 16
    - Docker

##Build

First, let's put the databse up and running:

`docker-compose up -d dabatase`

Then run flyway migration to create the database and the Recipe table:

`mvn clean flyway:migrate -Dflyway.configFile=db/flyway.properties`

Build the project itself:

`mvn clean install -Dspring.datasource.url=jdbc:sqlserver://localhost;databaseName=RecipeManager`

And finally run it:

`docker-compose up -d api`

