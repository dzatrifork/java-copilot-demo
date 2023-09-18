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

# Presentation

## Steps:
- Create patch endpoint for professions
  - Generate endpoint in UserResource
  - Update UserService `updateUserProfession(String id, Profession profession)`
  - Update UserRepository with updateUserProfession
  - Get more professions in Profession enum using ChatGPT
  - Update UserInput
  - update UserRepository.createUser with profession
  - Update UserRepositoryTest
    - Write test for assertUserExists
    - Try and let Copilot write more tests
      - testCreateUserWithNullName
      - testCreateUserWithEmptyName
        - Fix Illegal argument exception missing
- Use ChatGPT with code interpreter
  - to write documentation for UserResource: "Please write documentation as markdown for the endpoints defined in this code"
  - to refactoring suggestions for UserService: "Do you have any suggestions for refactoring in this file?"
- Use HuggingChat with web search
  - Write ContextRequestFilter: "In Java, using the Quarkus framework, give me a ContainerRequestFilter that checks the input for bad words"
  - Write BadRequestMapper: "In Java give me a BadRequestException ExceptionMapper that translates the exception into a response"

# Curls for testing:

## Create user
```
curl --location 'http://localhost:8080/user' \
--header 'Content-Type: application/json' \
--data '{
"name": "Ben",
"age": 23,
"profession": "TEACHER"
}'
```

## Get user
```
curl --location 'http://localhost:8080/user/O6phXT1wP2lCCeH6icG6'
```

## Patch Name

```
curl --location --request PATCH 'http://localhost:8080/user/O6phXT1wP2lCCeH6icG6/name' \
--header 'Content-Type: application/json' \
--data '{
    "name": "David"
}'
```

## Patch profession
```
curl --location --request PATCH 'http://localhost:8080/user/O6phXT1wP2lCCeH6icG6/profession' \
--header 'Content-Type: application/json' \
--data '{
    "profession": "CHEF"
}'
```
  