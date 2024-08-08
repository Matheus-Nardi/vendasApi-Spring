
## Endpoints da API

#### Cria um novo produto

```http
POST sistema-vendas/produtos
```

| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `produto`   | `Produto`  | **Obrigatório**. O produto a ser criado. |

```
  STATUS: 201 - CREATED
```

REQUEST JSON
```json
{
    "descricao": "Produto Exemplo",
    "preco": 99.99
}
```

#### Retorna produto por ID

```http
GET sistema-vendas/produtos/{id}
```

| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `id`        | `int`      | **Obrigatório**. A chave do produto. |

```
  STATUS: 200 - OK
```

#### Deleta um produto por ID

```http
DELETE sistema-vendas/produtos/{id}
```

| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `id`        | `int`      | **Obrigatório**. A chave do produto a ser deletado. |

```
  STATUS: 204 - NO CONTENT
```

#### Atualiza um produto por ID

```http
PUT sistema-vendas/produtos/{id}
```

| Parâmetro          | Tipo       | Descrição                                      |
| :----------------- | :--------- | :--------------------------------------------- |
| `id`               | `int`      | **Obrigatório**. A chave do produto a ser atualizado. |
| `produtoAtualizado`| `Produto`  | **Obrigatório**. O produto com as informações atualizadas. |

```
  STATUS: 204 - NO CONTENT
```

REQUEST JSON
```json
{
    "descricao": "Produto Exemplo Atualizado",
    "preco": 89.99
}
```

#### Busca produtos com filtros

```http
GET sistema-vendas/produtos?descricao=DescricaoExemplo
```

| Parâmetro      | Tipo       | Descrição                                      |
| :------------- | :--------- | :--------------------------------------------- |
| `descricao`    | `String`   | **Opcional**. Filtro para buscar produtos pela descrição. |

```
  STATUS: 200 - OK
```
