# Backend

#### Setup desenvolvimento
###### jdk-1.8
###### apache-maven-3.6.2
###### mysql-8.0.21

## Banco de Dados
##### Uma copia do script para gerar o banco dados encontra-se em `src/main/resources/db-schema`, o arquivo `create-databases.txt`

## Api Rest

*O serviço http rest expõem os 6 endpoint abaixo:*

| Método | Endpoint                  | Descrição do serviço                      |
|--------|---------------------------|-------------------------------------------|
| GET    | marcas                    | – Obter todas as marcas                   |
| GET    | marcas/{id}               | – Obter uma marca por ID                  |
| GET    | marcas/{id}/patrimônios   | – Obter todos os patrimônios de uma marca |
| POST   | marcas                    | – Inserir uma nova marca                  |
| PUT    | marca/{id}                | – Alterar os dados de uma marca           |
| DELETE | marca/{id}                | – Excluir uma marca                       |

### Como instalar e executar o projeto

1. Clone o repositório.
2. Acesse o diretório do projeto.
3. Instale as dependências.
4. Execute o servidor tomcat embedded.

```console
git clone https://10.1.26.109/Lucas/sos-api.git
cd sos-api
mvn clean install
mvn spring-boot:run
```