# Simple RESTful API with Validator and Unit Testing

![CI](https://github.com/lavantien/springboot-restapi/actions/workflows/ci.yml/badge.svg)

## Includes

- [x] Utilize Java 20
- [x] Spring Command Line Runner for migrating players data
- [x] Handle LocalDate serialize/deserialize
- [x] Spring Web CRUD
- [x] Spring Data JPA
- [x] PostgreSQL
- [x] Docker Compose
- [x] Standard Response Codes
- [x] Combinator Validation
- [x] Spring Boot Test Starter (Mockito + AssertJ)
- [x] GitHub Actions CI Pipeline
- ~~[ ] Exceptions~~ Deprecated
- ~~[ ] Spring HATEOAS~~ Deprecated

## References

- [Java Versions and Features](https://www.marcobehler.com/guides/a-guide-to-java-versions-and-features)
- [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)
- [Cross Site Request Forgery (CSRF)](https://docs.spring.io/spring-security/reference/servlet/exploits/csrf.html#servlet-csrf-configure-disable)
- [Unit Testing with Spring Boot](https://reflectoring.io/unit-testing-spring-boot/)
- [Setup Postgres in GitHub Actions](https://remarkablemark.org/blog/2021/03/14/setup-postgresql-in-github-actions/)
- [Adding workflow status badge](https://docs.github.com/en/actions/monitoring-and-troubleshooting-workflows/adding-a-workflow-status-badge)
- [Learn Spring Boot 3](https://youtu.be/-mwpoE0x0JQ)
- [Java Functional Programming](https://youtu.be/VRpHdSFWGPs)

## Local Setup

### With an IDE

- Install `Java 20`, latest `Docker`, and `IntelliJ Community`
  with the `IdeaVim` & `Docker` plugins installed
- Run the database by open project with IntelliJ, open `compose.yaml` and hit run
- Create `player` database in a terminal:

```bash
docker exec -it postgres bash
psql -U postgres
create database player;
```

- Run the backend by open `com/lavantien/restapi/RestapiApplication.java`

### With Neovim

- Install `Java 20`, latest `Docker`, and `Neovim` with the `Mason` plugins;
  Use `Mason` to install a Java LS
- Run the database in a terminal:

```bash
docker compose up -d
```

- Create `player` database in a terminal:

```bash
docker exec -it postgres bash
psql -U postgres
create database player;
```

- Run the backend in a terminal:

```bash
mvn install
mvn spring-boot:run
```

### Common

- Run unit test with `mvn test`, or more specifically `ClassName#methodName`:

```bash
mvn test -Dtest=ControllerTest#allPlayers test
```

- Using `curl` or any mock callers to test the endpoints at `localhost:8081`
- Or you can use `Kreya` and take advantage of the `mock-caller` directory
- There will be an existing list of players migrated to the database
  for testing purpose

## API Documentation

- `GET /api/players` retrieves a list of all players

```json
[
  {
    "id": 2,
    "name": "player b",
    "email": "b@b.com",
    "password": "password",
    "dateOfBirth": "2023-05-21"
  },
  {
    "id": 3,
    "name": "player c",
    "email": "c@c.com",
    "password": "password",
    "dateOfBirth": "2023-05-21"
  },
  {
    "id": 4,
    "name": "player d",
    "email": "d@d.com",
    "password": "password",
    "dateOfBirth": "2023-05-21"
  }
]
```

- `GET /api/players/{id}` retrieves a specific player with the matching `id`

```json
{
  "id": 6,
  "name": "player f",
  "email": "f@f.com",
  "password": "password",
  "dateOfBirth": "2023-05-21"
}
```

- `POST /api/players` creates a new player; returned the newly created player

```json
{
  "id": 32,
  "name": "player 0",
  "email": "player0@gmail.com",
  "password": "12345670",
  "dateOfBirth": "1990-02-01"
}
```

- `PATCH /api/players` creates a batch of new players;
  returned the failed players

```json
[
  {
    "id": 27,
    "name": "player 1",
    "email": "player0gmail.com",
    "password": "12345671",
    "dateOfBirth": "1990-01-01"
  },
  {
    "id": 28,
    "name": "player 1",
    "email": "player1@gmail.com",
    "password": "12345672",
    "dateOfBirth": "2020-01-02"
  }
]
```

- `PUT /api/players/{id}` edits an existing player with the matching `id`;
  returned the newly edited player; creates new player if not existed

```json
{
  "id": 6,
  "name": "player 6",
  "email": "player6@gmail.com",
  "password": "12345670",
  "dateOfBirth": "1990-02-01"
}
```

- `DELETE /api/players/{id}` deletes an existing player with the matching `id`;
  returned nothing if success

```json

```

## Errors Handling

- Based on standard HTTP status codes
- The returned error payload contains the error message relates to the root cause
