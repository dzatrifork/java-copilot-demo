# OUTLINE:

Service that holds and manages user data.

## Tech stack

- Quarkus
- Microservice
- Java
- Firebase
- Docker

## Endpoints

- POST user { name, age, profession }
- GET user { name, age, profession }
- PATCH user/{id}/name
- PATCH user/{id}/age
- PATCH user/{id}/profession
  - (Make ChatGPT give me an enum of possible professions)


## Generation

- Generate tests with Mockito
- Generate documentation
- Generate OpenAPI spec
- Generate Dockerfile
- Generate NotFoundExceptionMapper