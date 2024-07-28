# API na Nuvem Usando Spring Boot 3, java 17 e Railway

RESTful API construída em Java 17 com Spring Boot 3.

## Diagrama de Classes

```mermaid
classDiagram
    class User {
        - String name
        - Account account
        - List~Game~ games
    }

    class Account {
        - String number
        - String accountType
    }

    class Game {
        - String name
        - String description
    }

    User "1" *-- "1" Account
    User "1" *-- "N" Game
```

### [API (Swagger) 💻](https://api-desafio-prd.up.railway.app/swagger-ui/index.html)
Disponível no Railway por um período limitado.