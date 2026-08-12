# Brinka API

API REST do **Brinka**, plataforma de e-commerce desenvolvida para integrar o front-end da aplicação a serviços de autenticação, catálogo, usuários, carrinho e pedidos.

O projeto foi desenvolvido em **Java 21** com **Spring Boot**, utilizando **PostgreSQL** como banco relacional e **Redis** para persistência do carrinho. A aplicação segue uma arquitetura inspirada em **Clean Architecture**, mantendo as regras de negócio no domínio e isolando detalhes de infraestrutura.

## Funcionalidades

- Autenticação e cadastro de usuários com JWT.
- Gerenciamento de perfil do usuário.
- Gerenciamento de endereço.
- Gerenciamento de cartão.
- Consulta de categorias.
- Consulta de produtos.
- Cadastro, atualização e remoção de produtos para usuários com perfil administrativo.
- Consulta opcional de avaliações junto ao produto.
- Carrinho persistido em Redis.
- Criação e consulta de pedidos.
- Validação de estoque durante a criação do pedido.
- Cálculo de frete integrado aos Correios.
- Tratamento global de exceções.
- CORS configurado para integração com o front-end.
- Validação de dados de entrada com Jakarta Validation.

## Stack

| Tecnologia | Uso |
|---|---|
| Java 21 | Linguagem e runtime |
| Spring Boot 4.1 | Framework da aplicação |
| Spring Web MVC | API HTTP/REST |
| Spring Security | Autenticação e autorização |
| JWT | Tokens de autenticação |
| Spring Data JPA | Persistência relacional |
| Hibernate | ORM |
| PostgreSQL | Banco de dados principal |
| Spring Data Redis | Persistência do carrinho |
| Redis | Armazenamento do carrinho |
| MapStruct | Mapeamento entre modelos e entidades |
| Lombok | Redução de código boilerplate |
| Gradle Kotlin DSL | Build e gerenciamento de dependências |
| Jsoup | Parsing da resposta HTML dos Correios |
| Docker | Containerização |

## Arquitetura

A aplicação é organizada em quatro grandes áreas:

```text
org.brinka.brinkaapi
├── domain
│   ├── model
│   ├── repository
│   ├── exception
│   └── enums
│
├── application
│   ├── usecase
│   ├── dto
│   ├── gateway
│   ├── service
│   └── annotation
│
├── entrypoint
│   ├── controller
│   ├── dto
│   ├── mapper
│   └── exception
│
└── infra
    ├── config
    ├── external
    ├── persistence
    └── security
```

A ideia central é que o domínio não dependa de Spring, JPA, Redis ou outros detalhes externos. Interfaces de repositório e gateways são definidas nas camadas internas e implementadas na infraestrutura.

Consulte:

- [`docs/ARCHITECTURE.md`](docs/ARCHITECTURE.md)
- [`docs/CLEAN-ARCHITECTURE.md`](docs/CLEAN-ARCHITECTURE.md)

## Principais módulos

### Autenticação

```text
POST /auth/login
POST /auth/signup
```

O login valida as credenciais e retorna um JWT. O cadastro cria o usuário e também retorna um token.

### Produtos

```text
GET    /products
GET    /products/{id}
POST   /products
PATCH  /products/{id}
DELETE /products/{id}
DELETE /products?ids=1,2,3
```

As operações de escrita exigem a role `ADMIN`.

A consulta individual aceita o parâmetro opcional `avaliacoes`:

```text
GET /products/1?avaliacoes=true
```

Quando `true`, o produto é carregado junto com suas avaliações.

### Usuário

```text
GET   /usuarios
PATCH /usuarios
```

Também estão disponíveis recursos de cartão, endereço e carrinho:

```text
GET   /usuarios/cartao
POST  /usuarios/cartao
PATCH /usuarios/cartao

GET   /usuarios/address
PATCH /usuarios/address

GET    /usuarios/carrinho
POST   /usuarios/carrinho?productId=1
PATCH  /usuarios/carrinho/{productId}?operation=INCREMENT
DELETE /usuarios/carrinho/{productId}
```

### Pedidos

```text
POST /pedidos
GET  /pedidos
GET  /pedidos/{id}
```

A criação do pedido valida usuário, endereço, cartão quando necessário, carrinho, existência dos produtos e estoque disponível.

### Categorias

```text
GET /categorias
```

O endpoint exige autenticação.

## Autenticação

A API utiliza JWT em uma arquitetura stateless.

Após realizar login ou cadastro, o cliente deve enviar o token no header:

```http
Authorization: Bearer <token>
```

O `SecurityFilter` intercepta a requisição, valida o token e estabelece a autenticação no contexto do Spring Security.

As regras de autorização estão centralizadas em `SecurityConfiguration`.

## Banco de dados

O banco principal é PostgreSQL e o Hibernate está configurado com:

```yaml
spring:
  jpa:
    hibernate:
      ddl-auto: validate
```

Isso significa que a aplicação **não cria nem altera o schema automaticamente**. O schema esperado precisa existir antes da aplicação iniciar.

O projeto utiliza JPA para persistência das entidades da infraestrutura, enquanto os casos de uso trabalham com modelos de domínio.

## Redis

O Redis é utilizado para persistir o carrinho.

As chaves seguem a convenção:

```text
cart:{userId}
```

Os itens são armazenados como hash, utilizando campos no formato:

```text
product:{productId} -> quantity
```

O Redis é acessado exclusivamente pela implementação de `CartRepository`, mantendo o detalhe de persistência fora do domínio.

## Variáveis de ambiente

Copie `.env.example` para `.env` em ambiente local e configure os valores:

```env
DB_URL=jdbc:postgresql://localhost:5432/brinka
DB_USERNAME=postgres
DB_PASSWORD=senha

APPLICATION_SECRET=uma-chave-secreta

REDIS_HOST=localhost
REDIS_PORT=6379
REDIS_PASSWORD=

CEP_ORIGEM=00000000
```

Em produção, prefira configurar as variáveis diretamente no ambiente da plataforma de hospedagem.

**Nunca versione credenciais reais.**

## Executando localmente

### Pré-requisitos

- JDK 21
- PostgreSQL
- Redis
- Git

### Build

Linux/macOS:

```bash
./gradlew build
```

Windows:

```powershell
.\gradlew.bat build
```

### Executar

Linux/macOS:

```bash
./gradlew bootRun
```

Windows:

```powershell
.\gradlew.bat bootRun
```

A aplicação inicia na porta padrão do Spring Boot, caso nenhuma porta diferente seja configurada.

## Docker

O projeto possui um `Dockerfile` próprio.

Build:

```bash
docker build -t brinka-api .
```

Execução:

```bash
docker run --env-file .env -p 8080:8080 brinka-api
```

Em produção, PostgreSQL e Redis devem ser serviços externos ou containers separados. Não é recomendado embutir esses serviços no mesmo container da API.

## Integração com o front-end

O front-end utiliza a API para autenticação, catálogo e operações autenticadas.

A API precisa permitir a origem do front-end através da propriedade:

```yaml
app:
  cors:
    allowed-origins: https://brinka-frontend.onrender.com
```

Para desenvolvimento local, essa propriedade pode ser configurada para as origens locais utilizadas pelo front-end.

O front-end deve armazenar apenas dados necessários para sua própria experiência. Credenciais de PostgreSQL, Redis e segredos JWT nunca devem chegar ao navegador.

## Validação e erros

As requisições utilizam Jakarta Validation, por exemplo:

```java
@Valid
@RequestBody ProductRequest request
```

Exceções de domínio como `ProductNotFoundException`, `UserNotFoundException` e `InsufficientStockException` são tratadas pelo `GlobalExceptionHandler`.

A API utiliza uma estrutura de erro padronizada através de `ErrorResponse`.

## Documentação adicional

- [`docs/ARCHITECTURE.md`](docs/ARCHITECTURE.md) - visão geral da arquitetura.
- [`docs/CLEAN-ARCHITECTURE.md`](docs/CLEAN-ARCHITECTURE.md) - aplicação dos princípios de Clean Architecture.
- [`docs/API.md`](docs/API.md) - endpoints, autenticação e exemplos de requisições.
- [`docs/DATABASE.md`](docs/DATABASE.md) - estratégia de persistência, PostgreSQL e Redis.
- [`docs/SECURITY.md`](docs/SECURITY.md) - autenticação, autorização, CORS e práticas de segurança.
- [`docs/DEVELOPMENT.md`](docs/DEVELOPMENT.md) - setup e fluxo de desenvolvimento.

## Estrutura de dependências

De forma simplificada:

```text
Entrypoint
    ↓
Application
    ↓
Domain

Infrastructure ─────→ Domain
Infrastructure ─────→ Application gateways
```

A infraestrutura implementa contratos definidos pelas camadas internas. Isso reduz o acoplamento com frameworks e facilita a substituição de tecnologias.

## Estado atual

O projeto está estruturado para integração com o front-end Brinka e possui os principais fluxos de catálogo, autenticação, usuário, carrinho e pedidos implementados.

Alguns componentes, como pagamento real, cálculo definitivo de frete no fluxo de checkout e observabilidade, podem evoluir conforme os requisitos do projeto.

## Licença

Consulte [`LICENSE`](LICENSE).
