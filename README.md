# Car Project 🚗

Este é um projeto de demonstração desenvolvido com **Spring Boot** para o gerenciamento de veículos (carros). O sistema oferece uma API RESTful completa para realizar operações de CRUD (Create, Read, Update, Delete) de forma eficiente e estruturada.

## 🚀 Tecnologias Utilizadas

O projeto foi construído utilizando as seguintes tecnologias e ferramentas:

*   **Java 21**: Versão mais recente do Java para aproveitar os novos recursos da linguagem.
*   **Spring Boot 4.0.1**: Framework principal para agilizar o desenvolvimento da aplicação.
*   **Spring Data JPA**: Para persistência de dados e integração com o banco de dados.
*   **H2 Database**: Banco de dados em memória utilizado para desenvolvimento e testes rápidos.
*   **Lombok**: Biblioteca para reduzir o código boilerplate (getters, setters, construtores, etc.).
*   **SpringDoc OpenAPI (Swagger)**: Para documentação automática e testes da API.
*   **Maven**: Gerenciador de dependências e automação de build.

## 🛠️ Funcionalidades

*   **Cadastro de Carros**: Permite adicionar novos veículos ao sistema.
*   **Listagem de Carros**: Recupera a lista de todos os carros cadastrados.
*   **Busca por ID**: Localiza um veículo específico através de seu identificador único.
*   **Atualização de Dados**: Permite modificar as informações de um carro existente.
*   **Remoção de Carros**: Exclui um registro de veículo do sistema.
*   **Filtros Avançados**: Suporte a especificações (Specs) para buscas personalizadas.

## 📂 Estrutura do Projeto

A aplicação segue uma arquitetura em camadas bem definida:

*   `controller`: Camada de exposição da API (Endpoints REST).
*   `service`: Camada de regras de negócio.
*   `repository`: Camada de acesso aos dados (Spring Data JPA).
*   `domain`: Contém as entidades do banco de dados (`entities`), objetos de transferência de dados (`dto`) e especificações de busca (`specs`).
*   `config`: Configurações gerais da aplicação.

## 🏁 Como Executar o Projeto

### Pré-requisitos

*   Java 21 instalado.
*   Maven instalado (ou utilize o `mvnw` incluso no projeto).

### Passos para execução

1.  Clone o repositório:
    ```bash
    git clone https://github.com/gustavo-amaraltech/car-project.git
    ```
2.  Navegue até o diretório do projeto:
    ```bash
    cd car-project
    ```
3.  Execute a aplicação:
    ```bash
    ./mvnw spring-boot:run
    ```

A aplicação estará disponível em `http://localhost:8080`.

## 📖 Documentação da API (Swagger)

Após iniciar a aplicação, você pode acessar a documentação interativa da API através do Swagger UI no seguinte endereço:

[http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

## 🗄️ Banco de Dados H2

O console do banco de dados H2 pode ser acessado em:
`http://localhost:8080/h2-console`

*   **JDBC URL**: `jdbc:h2:mem:testdb` (ou conforme configurado no `application.properties`)
*   **User**: `sa`
*   **Password**: (vazio)

---

Desenvolvido por [Gustavo Amaral](https://github.com/gustavo-amaraltech).
