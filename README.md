# Desafio Anota AI - Product Catalog API

API REST para gerenciamento de catálogo de produtos com integração AWS SNS.

## 🚀 Tecnologias

- **Java 21**
- **Spring Boot 3.5.5**
- **MongoDB**
- **AWS SNS**
- **Docker**
- **Lombok**
- **SpringDoc OpenAPI**

## 📋 Funcionalidades

### Categorias
- ✅ Criar categoria
- ✅ Listar todas as categorias
- ✅ Atualizar categoria
- ✅ Deletar categoria

### Produtos
- ✅ Criar produto
- ✅ Listar todos os produtos
- ✅ Atualizar produto
- ✅ Deletar produto
- ✅ Notificações via AWS SNS

## 🛠️ Configuração

### Pré-requisitos
- Java 21
- Docker e Docker Compose
- Conta AWS (para SNS)

### Variáveis de Ambiente
Crie um arquivo `.env` na raiz do projeto:

```env
AWS_KEY_ID=sua_aws_access_key
AWS_SECRET=sua_aws_secret_key
```

### Executar com Docker

```bash
# Subir MongoDB
docker-compose up -d

# Executar aplicação
./mvnw spring-boot:run
```

## 📡 Endpoints

### Categorias

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| POST | `/api/category` | Criar categoria |
| GET | `/api/category` | Listar categorias |
| PUT | `/api/category/{id}` | Atualizar categoria |
| DELETE | `/api/category/{id}` | Deletar categoria |

### Produtos

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| POST | `/api/product` | Criar produto |
| GET | `/api/product` | Listar produtos |
| PUT | `/api/product/{id}` | Atualizar produto |
| DELETE | `/api/product/{id}` | Deletar produto |

## 📄 Documentação API

Acesse a documentação Swagger em: `http://localhost:8090/swagger-ui.html`

## 🏗️ Arquitetura

```
src/main/java/com/fernando_rocha/desafio_anota_ai/
├── config/
│   ├── aws/          # Configuração AWS SNS
│   └── mongo/        # Configuração MongoDB
├── controllers/      # Controllers REST
├── domain/          # Entidades e DTOs
├── repositories/    # Repositórios MongoDB
└── services/        # Lógica de negócio
```

## 🔧 Configurações

### MongoDB
- Host: `localhost:27017`
- Database: `product-catalog`
- Usuário: `admin`
- Senha: `password`

### AWS SNS
- Região: `us-east-1`
- Tópico: `catalog-emit`

### Servidor
- Porta: `8090`

## 🚀 Como Executar

1. Clone o repositório
2. Configure as variáveis de ambiente
3. Execute o MongoDB via Docker:
   ```bash
   docker-compose up -d
   ```
4. Execute a aplicação:
   ```bash
   ./mvnw spring-boot:run
   ```

A API estará disponível em `http://localhost:8090`