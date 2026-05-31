# 🌌 DesculpaAI - Backend

> *No início, o Universo foi criado. Isso deixou muitas pessoas muito furiosas e amplamente considerado como um movimento estúpido. Mas no que diz respeito ao DesculpaAI, bem... isso ainda é bem estúpido.*

## 📖 O que é isso?

DesculpaAI é um backend RESTful que gera desculpas criativas e absurdas para qualquer situação. Desenvolvido em **Spring Boot 3** com **Java 21** durante um hackathon, porque em vez de consertar seus problemas, é muito mais fácil ter desculpas criativas para eles.

A resposta para tudo é 42, mas a resposta para quantos endpoints temos é 12. Coincidência? Talvez.

## ⭐ Características (ou Razões para Usar Isso)

- ✅ **API RESTful** com Spring Web - Para fazer requisições HTTP sem deixar seu sofá
- ✅ **Banco de Dados** com Spring Data JPA - Porque dados são coisas que existem em algum lugar
- ✅ **Validação de Dados** integrada - Para evitar enviar dados tão ruins quanto suas desculpas
- ✅ **Hot Reload** com Spring DevTools - Para não precisar reiniciar a vida toda vez
- ✅ **Java 21** - A versão tão futura que ainda estamos tentando compreendê-la
- ✅ **Swagger UI** - Documentação interativa, porque ler é para perdedores
- ✅ **12 Endpoints** - Todos tão úteis quanto uma porta giratória no ar

## 🛸 Tecnologias Utilizadas

```
Java 21 ........................... Linguagem de programação que constrói universos
Spring Boot 3.5.15 ................ O que faz tudo funcionar, principalmente por magia
Spring Data JPA ................... Para conversar com seu banco de dados
Spring Web ........................ API REST para quando você precisa fazer requisições
Maven 3.8.7 ....................... Faz coisas compilarem quando você grita para ela
Spring DevTools ................... Codificação sem reiniciar, uma bênção divina
Springdoc OpenAPI 2.0.2 ........... Transforma código em documentação lindinha
PostgreSQL ........................ Onde vivem seus dados (quando você os encontra)
```

## ⚙️ Antes de Começar (O Impaciente)

### Pré-requisitos (infelizmente necessários)

- **Java 21** - Instale [aqui](https://www.oracle.com/java/technologies/downloads/) ou comente sobre o tempo
- **Maven 3.6.0+** - Ou use `./mvnw` que vem incluído (como sapatos em um sapato)

### Instalação em 3 Passos Fáceis (ou 47 difíceis)

```bash
# Passo 1: Clone o repositório (antes que ele desapareça)
git clone https://github.com/PietraValen/DesculpaAI-backend.git
cd DesculpaAI-backend

# Passo 2: Instale as dependências (deixe o computador pensar)
./mvnw clean install

# Passo 3: Desfrute do silêncio enquanto maven faz seu trabalho
# (Aproveite para fazer café, existencialismo, ou ambos)
```

## 🚀 Como Fazer Isso Funcionar

### Opção A: Para os Impacientes
```bash
./mvnw spring-boot:run
# Aplicação estará em http://localhost:8080 em questão de energias cósmicas
```

### Opção B: Para os Pacientes (ou sem internet)
```bash
./mvnw clean package
java -jar target/DesculpaAI-0.0.1-SNAPSHOT.jar
# Mesma coisa, mas você é mais autossuficiente
```

## 🌐 Endpoints - Guia Rápido de Sobrevivência

| Método | Endpoint | Para Quê | Desempenho |
|--------|----------|----------|-----------|
| GET | `/api/usuarios` | Ver todos | Rápido |
| POST | `/api/usuarios` | Criar novo | Depende do seu Wi-Fi |
| GET | `/api/usuarios/{id}` | Detalhe usuario | +/- 42ms |
| PUT | `/api/usuarios/{id}` | Atualizar usuario | Questionável |
| DELETE | `/api/usuarios/{id}` | Deletar usuario | Irreversível |
| POST | `/api/pedidos` | Criar desculpa | Impossível medir em tempo |
| GET | `/api/pedidos` | Ver desculpas | Como olhar um espelho |
| GET | `/api/pedidos/{id}` | Uma desculpa | Existencial |
| PUT | `/api/pedidos/{id}/status` | Mudar status desculpa | Confusão garantida |
| GET | `/api/pedidos/{id}/alibi` | Gerar alibi criativo | **Priceless** |

## � Deploy no Render (Quando Quiser Mostrar para Alguém)

Quer colocar sua API funcionando na nuvem? Temos tudo pronto para você:

- **[RENDER_DEPLOY_CHECKLIST.md](./RENDER_DEPLOY_CHECKLIST.md)** ⭐ **COMECE AQUI**
  - Passo a passo completo para deploy no Render
  - Checklist de verificação
  - Troubleshooting rápido

- **[RENDER_ENV_VARIABLES.md](./RENDER_ENV_VARIABLES.md)**
  - Variáveis de ambiente necessárias
  - Exemplos de configuração
  - Como verificar se está funcionando

- **[HIBERNATE_MYSQL_FIX.md](./HIBERNATE_MYSQL_FIX.md)**
  - Resolução do erro SEQUENCES (PostgreSQL → MySQL)
  - Configurações de Hibernate para MySQL 8.0
  - Referências técnicas

**TL;DR:** Migração concluída de PostgreSQL para MySQL 8.0. Docker pronto. Dockerfile configurado com Spring profiles. Tudo set para Render!

## �📚 Documentação (Se você realmente quer ler)

A documentação ao vivo está em: `http://localhost:8080/swagger-ui.html`

É como ter um guia do mochileiro, mas para sua API.

### Arquivos Importantes de Saber
```
src/
├── main/
│   ├── java/com/hackathon/DesculpaAI/
│   │   ├── controller/ ............ Recebem requisições (tipo garçons)
│   │   ├── service/ ............... Processam lógica (tipo cozinheiros)
│   │   ├── repository/ ............ Falam com banco de dados (tipo espiadoras)
│   │   ├── model/ ................. Entidades (tipo atores de novela)
│   │   ├── dto/ ................... Mensageiros (tipo WhatsApp do código)
│   │   ├── exception/ ............. Coisas dando errado (tipo viver)
│   │   └── config/ ................ Configurações (tipo receita de bolo)
│   └── resources/
│       └── application.properties .. Onde vivem as configurações
└── test/
    └── java/ ...................... Código que testa código (Meta!)
```

## 🔧 Configuração (Personalize sua Experiência)

Edite `src/main/resources/application.properties`:

```properties
# Porta (por padrão, 8080, o que é um bom número)
server.port=8080

# Banco de dados (mude se achar necessário)
spring.datasource.url=jdbc:postgresql://localhost:5432/desculpaai
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha

# Logging (veja o que está acontecendo nos bastidores)
logging.level.root=INFO
```

## 🧪 Testes (Para Verificar se Está Funcionando)

```bash
# Execute os testes
./mvnw test

# Se tudo passar, o universo ainda faz sentido
# Se falhar, bem... provavelmente é bug cósmico
```

## 📊 Estrutura de Dados

### Usuario (Você)
- `id` - Identificador único (como um número de segurança galáctico)
- `nome` - Como você prefere ser chamado
- `email` - Para quando precisamos encontrá-lo
- `criadoEm` - Nascimento no sistema
- `atualizadoEm` - Última vida pregressa

### Pedido (Sua Desculpa)
- `id` - Identificador único da desculpa
- `tipoAlibi` - Qual tipo de desculpa você precisa
- `nivelAbsurdo` - De 1 (leve) a 5 (universo)
- `status` - Aberto, Processando, Concluído, etc
- `usuario` - Quem pediu essa desculpa
- `criadoEm` - Quando você começou a mentir

## 🎯 Quick Start Script

Se você é realmente impaciente, existe `quick-start.sh` que faz tudo para você:
```bash
chmod +x quick-start.sh
./quick-start.sh
```

É como ter um mapa de tesouro, mas para seu backend.

## 🚨 Troubleshooting (Se algo deu errado)

**P: Maven não está funcionando**
R: Nem tudo funciona. Especialmente maven.

**P: Erro de porta já em uso**
R: Mude para outra porta em `application.properties`

**P: Precisava de uma desculpa mas só obtenho erros**
R: É meta. Pelo menos a desculpa é real.

**P: Nada disso faz sentido**
R: Bem-vindo à programação.

## 📝 Notas Importantes

- Este projeto foi criado em um **Hackathon**, significando que o código é bonito por acidente
- Não é responsável por desculpas ineficazes na vida real
- Se suas desculpas forem muito absurdas, pode ser culpa sua, não nossa
- A resposta para tudo continua sendo 42

## 👥 Contribuidores

- Desenvolvido para hackathon por pessoas que evitam responsabilidades

## 📄 Licença

MIT License - Use responsavelmente (ou não, isto é livre ainda)

---

**Aviso Final:** *Se você leu tudo isso, parabéns. Se não leu, clique em `/swagger-ui.html` e seja feliz.*

> "A vida é como um mochileiro atravessando a galáxia. Você nunca sabe o que vem a seguir, mas esse backend pode ajudar a inventar uma desculpa para quando chegue lá."
