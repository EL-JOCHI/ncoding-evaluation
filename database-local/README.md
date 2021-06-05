# Database Postgres for local development
In the development I used a local database using docker. 

## Use
In order to use the local database, please check that your [application.properties](/backend/src/main/resources/application.properties) has the proper configurations.
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/db
spring.datasource.username=postgres
spring.datasource.password=postgres
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.properties.hibernate.jdbc.lob.non_contextual_creation= true
```

Just run the [docker-compose](/database-local/docker-compose.yml) command to start the dockerized database, inside the [database-local](/database-local) folder:

```shell
docker-compose up
```