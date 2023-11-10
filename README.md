# Projeto Bando de sangue
Sistema desenvolvido como teste para a empresa WK technology

## Arquétipo Spring Rest

> Foi utilizado um arquétipo Spring Rest para iniciar uma nova aplicação com segurança, OAuth2, JWT, hateoas, Swagger entre outras configurações de minha propriedade
 
### Tecnologias utilizadas no back

* Java 12
* Spring Boot Web; JPA; Data;
* Swagger 2
* PostgreSql
* Lombok
* ModelMapper
* POO
* Flyway
* Rest
* Maven
* JSON
* Docker

### Tecnologias utilizadas no front-end
* Angular versão 13
* npm
* HTML5
* CSS3
* Javascript
* Typescript
* Ajax

### Flyway

>O projeto utiliza o Flyway que é uma ferramenta de migração de banco de dados de código aberto. Sendo assim, ao dar start no back ele cria as tabela e popula o banco de dados criado com alguns registros iniciais.


### Para criar a imagem docker executa o comando:
```shell script
docker-compose up
```

### Para iniciar o docker já com a imagem criada executa o comando:
```shell script
docker-compose up -d
```

### Para encerrar a execução da imagem com o banco postgres criado utilizar o comando:
```shell script
docker stop arquetipo
```

### Para executar o start com o banco postgres criado utilizar o comando:
```shell script
docker start arquetipo
```

#### Url padarão do back
```
http://localhost:8080
```

#### Url padarão do front
```
http://localhost:4200
```

#### Swagger
Desenvolvimento:
```
http://localhost:8080/swagger-ui.html#/
```
### Arquivo de Collection Json - Usar no Postman

> O arquivo de collection.json esta na pasta docs. Pode copiar ele e importar no postman para testar os payloads da api

