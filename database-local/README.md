# Database Postgres for local development
In the development I used a local database using docker. 

## Use
In order to use the local database, please check that your [application.properties](/backend/src/main/resources/application.properties) has the proper configurations.

Just run the [docker-compose](/database-local/docker-compose.yml) command to start the dockerized database, inside the [database-local](/database-local) folder:

```shell
docker-compose up
```