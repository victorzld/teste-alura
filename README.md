# Resolução do Desafio Técnico Alura - Fullstack

Neste repositório, apresento a implementação de uma plataforma educacional simulada, conforme os requisitos do desafio. Aqui você poderá avaliar como estruturei o código, apliquei os conceitos de orientação a objetos e resolvi os problemas propostos utilizando Java com o ecossistema Spring.

## Requisitos Técnicos

* **Java 21**
* **Spring Boot**
* **Spring Data JPA**
* **MySQL**
* **Flyway** para migrações de banco de dados
* **HTML, CSS, e JavaScript**
* **JSP** para renderização de views

## Como Executar o Projeto Localmente

### Pré-requisitos
* Java 21 (ou superior) instalado.
* Maven instalado.
* MySQL rodando na sua máquina.

### Passos para Execução
1.  **Clone o repositório:**
    ```bash
    git clone https://github.com/victorzld/teste-alura
    cd alura-case-main
    ```

2.  **Configure o Banco de Dados:**
    * Certifique-se de que seu MySQL está rodando.
    * Abra o arquivo `src/main/resources/application.properties`.
    * Altere as propriedades `spring.datasource.url`, `spring.datasource.username` e `spring.datasource.password` com as suas credenciais do MySQL. O Flyway criará o banco e as tabelas automaticamente na primeira vez que a aplicação for executada.

3.  **Dê permissão de execução ao Maven Wrapper:**
    * No Linux ou macOS:
        ```bash
        chmod +x mvnw
        ```
    * No Windows, o `mvnw.cmd` já deve ter as permissões necessárias.

4.  **Execute a aplicação:**
    * Utilize o Maven Wrapper para iniciar o projeto:
        ```bash
        ./mvnw spring-boot:run
        ```
    * A aplicação estará disponível em `http://localhost:8080`.

## Funcionalidades Implementadas

### Questão 1: Cadastro de Cursos
Foi implementada a funcionalidade de cadastro de cursos, permitindo a criação de novos cursos na plataforma.
* **Implementação:** A entidade `Course` foi criada com as validações necessárias, o `CourseController` gerencia as rotas de cadastro e listagem (`/admin/courses`, `/admin/course/new`), e a view `newForm.jsp` fornece o formulário para a criação.

### Questão 2: Inativação de Cursos
Foi desenvolvida a funcionalidade para inativar cursos existentes.
* **Implementação:** A rota `POST /course/{code}/inactive` foi criada no `CourseController`. Na entidade `Course`, o método `inactivate()` altera o status para `INACTIVE` e registra a data de inativação.

### Questão 3: Front-end da Página de Login
A página de login foi desenvolvida para ser dinâmica, carregando as categorias e cursos ativos, com uma estilização que segue o design proposto no Figma.
* **Implementação:** O `LoginController` busca os dados e os envia para a view `login.jsp`, que os renderiza dinamicamente. O estilo está no arquivo `login.css`.

### Questão 4 (Bônus): Edição de Categorias e Cursos
Foram implementadas as funcionalidades de edição para categorias e cursos, permitindo a atualização de suas informações e o gerenciamento de status (ativo/inativo) para os cursos.
* **Implementação:** Foram criadas as rotas `GET` e `POST` para `/admin/category/edit/{id}` e `/admin/course/edit/{id}` nos seus respectivos controllers, com as views de formulário correspondentes.

### Questão 5 (Bônus): Matrícula de Alunos via API
Foi desenvolvida uma API para a matrícula de alunos em cursos, garantindo que um aluno não possa se matricular duas vezes no mesmo curso e que o curso esteja ativo.
* **Implementação:** O endpoint `POST /registration/new` no `RegistrationController` recebe o e-mail do aluno e o código do curso e aplica as regras de negócio.

### Questão 6 (Bônus): Relatório de Cursos Mais Acessados via API
Foi criada uma API que gera um relatório dos cursos com o maior número de matrículas, otimizado para performance.
* **Implementação:** A rota `GET /registration/report` utiliza uma *native query* no `RegistrationRepository` para evitar o anti-pattern N+1, retornando os cursos ordenados pelo número de inscrições.

### Funcionalidade Extra: Gerenciamento de Alunos
Além do solicitado, foi implementada uma página de administração para visualizar e cadastrar novos alunos diretamente pela interface gráfica.
* **Implementação:** O `UserAdminController` gerencia a rota `/admin/students`, que exibe uma lista de todos os alunos e um formulário para a criação de novos, utilizando a view `list.jsp` em `admin/student`.

## Como Avaliar as Funcionalidades

Para avaliar as funcionalidades, você pode usar as rotas da interface gráfica em um navegador ou os endpoints da API com uma ferramenta como o Postman.

### Interface Gráfica (Navegador)
* **Página de Login (com Cursos e Categorias):** `http://localhost:8080/`
* **Listagem de Categorias:** `http://localhost:8080/admin/categories`
* **Listagem de Cursos:** `http://localhost:8080/admin/courses`
* **Gerenciamento de Alunos (Extra):** `http://localhost:8080/admin/students`

### Endpoints da API (Postman)
* **Criar Novo Aluno:**
    * `POST` `http://localhost:8080/user/newStudent`
    * **Body (raw/json):**
        ```json
        {
          "name": "Nome do Aluno",
          "email": "aluno@exemplo.com",
          "password": "senha-forte-123"
        }
        ```

* **Matricular Aluno em um Curso:**
    * `POST` `http://localhost:8080/registration/new`
    * **Body (raw/json):**
        ```json
        {
          "studentEmail": "aluno@exemplo.com",
          "courseCode": "spring-bt" 
        }
        ```
    * *Observação: O `courseCode` deve corresponder a um curso ativo existente.*

* **Relatório de Cursos Mais Acessados:**
    * `GET` `http://localhost:8080/registration/report`

* **Inativar um Curso:**
    * `POST` `http://localhost:8080/course/{code}/inactive`
    * *Substitua `{code}` pelo código de um curso ativo, por exemplo: `http://localhost:8080/course/java-basic/inactive`.*

## Considerações sobre os Testes

Ao desenvolver o projeto, encontrei alguns desafios para depurar e corrigir todos os erros apontados pelos testes automatizados. Deixei o código funcional e cobrindo os requisitos, mas gostaria de usar a oportunidade da entrevista para discutir esses pontos, entender as melhores abordagens para resolver os problemas que encontrei e aprimorar minhas habilidades em testes.
