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

# Presentation

## Steps:
- Create patch endpoint for professions
  - Update UserRepository with updateUserProfession
  - Create Profession enum
  - Create ProfessionInput with Profession as input value
  - Update UserService `updateUserProfession(String id, Profession profession)`
  - Generate endpoint in UserResource
  - Update UserRepositoryTest
  - Try and let Copilot write more tests (example testCreateUserWithNullName and testCreateUserWithEmptyName)
    - Fix Illegal argument exception missing
  - (OPTIONAL) Create UserServiceTest
- Use ChatGPT with code interpreter
  - to refactor ...
  - to write documentation for ...
  