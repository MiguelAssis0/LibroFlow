
# Libro Flow

## Descrição
Este é um projeto feito com Java Spring Boot, onde consiste em uma API para um gerenciamento de biblioteca, com onde é possível cadastrar livros, realiza empréstimos e cadastrar usuários com diferentes tipos de permissão.

## Especificações
- Java 17
- Spring Boot 3.4.2
- Maven

## Documentação

Para acessar os endpoints do projeto, é necessário iniciar o projeto em alguma IDE (IntelliJ, Eclipse, entre outros), e acessar pelo navegador a seguinte url:<br/>

http://localhost:8080/swagger-ui/index.html

## Conexão no Banco de Dados
O banco de dados utilizado, é o banco de dados h2, que já é criado automaticamente ao iniciar o projeto, aonde também, há algumas inserções iniciais para ver o projeto rodando. Você pode ver os inserções do banco de dados no arquivo import.sql, dentro da pasta resources.

## Cadastro e Login

Para cadastrar um usuário, é necessário acessar a rota:
localhost:8080/users/register
É necessário estar logado como administrador para criar novos cadastros. <br/>

Para realizar o login, é necessário acessar a rota:
localhost:8080/login <br/>
a partir dessa rota, você deverá indicar em um JSON o username e o password. Após fazer isso, será gerado um token, onde deverá ser inserido para fazer as requisições nas demais rotas da aplicação. <br/>

- Login como administrador:

    {
        "username":"admin",
        "password":"12345"
    }
- Login como usuário do sistema:

    {
        "username":"user",
        "password":"12345"
    }

## Enums

Alguns atributos das tabelas só aceitam valores especificos, importante que seja inseridos em UPPERCASE.
<br/>
Role: USER,ADMIN <br/>
Genre: ROMANCE, COMEDIA, SUSPENSE, AVENTURA, DRAMA, TERROR, FANTASIA, INFANTIL, AUTO_AJUDA, INFORMATICA



