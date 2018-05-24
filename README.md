# Magneto-api

<p align="center">
  <a href="https://imgbb.com/"><img src="https://image.ibb.co/eSzngT/images.jpg" alt="images" border="0"></a>
</p>

Magneto is an api to recruit mutants.

## Features
- Validate mutant DNAs
- Retrieve mutant statistics

## Setup & run
- mvn clean install
- mvn spring-boot:run

## Database
Magneto is using an embedded H2 database for a simple setup, fast CRUD operations and convenient inspection.

**Consider changing it for a Postgress or Oracle db for a production environment.**

Db admin page:

*http://localhost:8080/h2*

## Documentation
https://documenter.getpostman.com/view/151326/magneto-api/RW8An8Jb