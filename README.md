# Simple RESTful API with Validator and Unit Testing

## Includes

- Utilize Java 20
- Spring Command Line Runner for migrating players data
- Spring Web CRUD
- Spring Data JPA
- Spring HATEOAS
- PostgreSQL
- Docker Compose
- Combinator Validation
- Spring Boot Test Starter (Mockito + AsserJ)

## References

- [Java Versions and Features](https://www.marcobehler.com/guides/a-guide-to-java-versions-and-features)
- [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)
- [Cross Site Request Forgery (CSRF)](https://docs.spring.io/spring-security/reference/servlet/exploits/csrf.html#servlet-csrf-configure-disable)
- [Unit Testing with Spring Boot](https://reflectoring.io/unit-testing-spring-boot/)
- [Learn Spring Boot 3](https://youtu.be/-mwpoE0x0JQ)
- [Java Functional Programming](https://youtu.be/VRpHdSFWGPs)

## Detailed Design Diagrams (UML + Sequence)

- Writing diagrams is a form of masturbation

## Local Setup

### With an IDE

- Install `Java 20`, latest `Docker`, and `IntelliJ Community` with the `IdeaVim` & `Docker` plugins installed
- Run the database by open project with IntelliJ, open `compose.yaml` and hit run
- Create `player` database in a terminal:

```bash
$ docker exec -it postgres bash
# psql -U postgres
# create database player;
```

- Run the backend by open `com/lavantien/restapi/RestapiApplication.java` and hit run

### With Neovim

- Install `Java 20`, latest `Docker`, and `Neovim` with the `Mason` plugins installed; Use `Mason` to installed a Java LS
- Run the database in a terminal:

```bash
$ docker compose up -d
```

- Create `player` database in a terminal:

```bash
$ docker exec -it postgres bash
# psql -U postgres
# create database player;
```

- Run the backend in a terminal:

```bash
$ mvn install
$ mvn spring-boot:run
```

### Common

- Using `curl` or any mock callers to test the endpoints at `localhost:8081`
- Or you can use `Kreya` and take advantage of the `mock-caller` directory
- There will be an existing list of players migrated to the database for testing purpose

## API Documentation

- `GET /api/players` retrieves a list of all players

```json

```

- `GET /api/players/{id}` retrieves a specific player with the matching `id`

```json

```

- `POST /api/players` creates a new player; returned the newly created player

```json

```

- `PATCH /api/players` creates a batch of new players; returned the newly created players

```json

```

- `PUT /api/players/{id}` edits an existing player with the matching `id`; returned the newly edited player; creates new player if not existed

```json

```

- `DELETE /api/players/{id}` deletes an existing player with the matching `id`; returned nothing if success

```json

```

## Errors Handling

- Based on standard HTTP status codes
- The returned error payload contains the error message relates to the root cause
