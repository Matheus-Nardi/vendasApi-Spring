
## Endpoitns da API



#### Retorna cliente por ID

```http
GET sistema-vendas/clientes/{id}
```

| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `id`        | `int`      | **Obrigatório**. A chave do cliente. |

```
  STATUS: 200 - OK
```

#### Cria um novo cliente

```
POST sistema-vendas/clientes
```

| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `cliente`   | `Cliente`  | **Obrigatório**. O cliente a ser criado. |

```
  STATUS: 201 - CREATED
```
REQUEST JSON
```json
{
    "nome": "João da Silva",
    "cpf": "10955206012"
}
```



#### Deleta um cliente por ID

```http
DELETE sistema-vendas/clientes/{id}
```

| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `id`        | `int`      | **Obrigatório**. A chave do cliente a ser deletado. |

```http
  STATUS: 204 - NOT CONTENT
```

#### Atualiza um cliente por ID

```http
PUT sistema-vendas/clientes/{id}
```

| Parâmetro          | Tipo       | Descrição                                      |
| :----------------- | :--------- | :--------------------------------------------- |
| `id`               | `int`      | **Obrigatório**. A chave do cliente a ser atualizado. |
| `clienteAtualizado`| `Cliente`  | **Obrigatório**. O cliente com as informações atualizadas. |



```
  STATUS: 204 - NOT CONTENT
```

REQUEST JSON
```json
{
    "nome": "João da Silva NOVO",
    "cpf": "10955206022"
}
```

#### Busca clientes com filtros

```http
GET sistema-vendas/clientes?nome=NomeExeplo&cpf=444
```

| Parâmetro      | Tipo       | Descrição                                      |
| :------------- | :--------- | :--------------------------------------------- |
| `nome`| `String`  | **Opcional**. Filtros para buscar clientes.     |
| `cpf` | `String`  | **Opcional**. Filtros para buscar clientes.     |

```
  STATUS: 200 - OK
```

