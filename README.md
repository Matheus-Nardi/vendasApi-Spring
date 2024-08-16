
# Vendas API 💸

Um projeto destinado aos meus estudos do framework Spring Boot. Consiste em uma API de vendas que engloba e interage com recursos de clientes , produtos e pedidos.


## Stack utilizada

- Java
- Maven
- Spring Framework
- H2 Database
- Insomnia


## Rodando localmente  🖥️

Para instalar o projeto, siga os passos abaixo:

### Pré-requisitos

- Java JDK (versão 21 ou superior)
- Maven (versão 3.9.7 ou superior)

### Passos

1. Clone o repositório:

   ```sh
   git clone https://github.com/Matheus-Nardi/vendasApi-Spring
   ```

2. Entre no diretório do repositório:

   ```sh
   cd vendasApi-Spring
   ```

3. Baixe as dependências:

   ```sh
   mvn clean install
   ```

4. Rode a aplicação localmente:

   ```sh
   mvn spring-boot:run   
   ```
## Estrutura de Pastas 📂
 
Em busca da divisão de responsabilidades , o projeto estrutura pastas da seguinte forma

```
/main 
    /src
        /controller          # Controle das rotas HTTP
        /db                  # Responsável por scripts do banco de dados
        /dto                 # Responsável por modelar Responses e Requests
        /exception           # Responsável por armazenar exceptions específicas do projeto
        /infra               # Responsável por classes que auxiliam na infraestrutura
        /model               # Responsável por modelar as entidades
        /repository          # Responsável pelo acesso ao banco de dados
        /resources           # Responsável pelas propriedades da aplicação e recursos do banco
        /service             # Responsável pelo intermédio entre repository e controller

```
## Endpoitns da API 📍

Consulte a documentação de acordo com o recurso desejado:

[Clientes](https://github.com/Matheus-Nardi/vendasApi-Spring/blob/main/clientesEndpoint.md)

[Produtos](https://github.com/Matheus-Nardi/vendasApi-Spring/blob/main/produtosEndpoitns.md)

[Pedidos](https://github.com/Matheus-Nardi/vendasApi-Spring/blob/main/pedidosEndpoitns.md)

Ou, acesse o [Swagger Hub](https://app.swaggerhub.com/apis/MATHEUZNARDI/spring-vendas_api/v1.0)

