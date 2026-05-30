# DesculpaAI - Backend

Backend da aplicação DesculpaAI desenvolvido para hackathon usando **Spring Boot 3** e **Java 21**.

## 📋 Características Principais

- ✅ **API RESTful** com Spring Web
- ✅ **Banco de Dados** com Spring Data JPA
- ✅ **Validação de Dados** integrada
- ✅ **Hot Reload** com Spring DevTools
- ✅ **Java 21** - Última versão LTS

## 🛠️ Tecnologias Utilizadas

- **Java 21**
- **Spring Boot 3.5.15**
- **Spring Data JPA**
- **Spring Web (REST)**
- **Maven** para build
- **Spring DevTools** para desenvolvimento

## ⚙️ Configuração do Projeto

### Pré-requisitos

- Java 21 instalado
- Maven 3.6.0+

### Instalação

```bash
# Clone o repositório
git clone https://github.com/PietraValen/DesculpaAI-backend.git
cd DesculpaAI-backend

# Instale as dependências
./mvnw clean install
```

## 🚀 Como Executar

```bash
# Modo desenvolvimento (com hot reload)
./mvnw spring-boot:run

# Ou gere o JAR e execute
./mvnw clean package
java -jar target/DesculpaAI-0.0.1-SNAPSHOT.jar
```

A aplicação estará disponível em `http://localhost:8080`

## 📁 Estrutura do Projeto

```
src/
├── main/
│   ├── java/com/hackathon/DesculpaAI/
│   │   └── DesculpaAiApplication.java      # Classe principal Spring Boot
│   └── resources/
│       └── application.properties           # Configurações da aplicação
└── test/
    └── java/com/hackathon/DesculpaAI/     # Testes unitários
```

## 📝 Configurações

Edite `src/main/resources/application.properties` para configurar:

- Porta da aplicação (padrão: 8080)
- Banco de dados
- Outras configurações específicas

## 🧪 Testes

```bash
# Execute todos os testes
./mvnw test
```

## 📚 Documentação

- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
- [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)

## 👥 Contribuidores

- Desenvolvido para hackathon

## 📄 Licença

Este projeto está sob licença a definir.

---

**Nota:** Este README é um template básico. Atualize com informações específicas sobre os endpoints, banco de dados e funcionalidades conforme o desenvolvimento do projeto.
