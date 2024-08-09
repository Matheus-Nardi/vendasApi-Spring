

## Endpoints da API

#### Cria um novo pedido

```http
POST sistema-vendas/pedidos
```

| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `cliente`   | `int`      | **Obrigatório**. ID do cliente que está fazendo o pedido. |
| `itens`     | `List<ItemDTO>` | **Obrigatório**. Lista de itens no pedido, incluindo ID do produto e quantidade. |

```
  STATUS: 201 - CREATED
```

REQUEST JSON
```json
{
    "cliente": 1,
    "itens": [
        {
            "produto": 1,
            "quantidade": 2
        }
    ]
}
```

#### Retorna detalhes do pedido por ID

```http
GET sistema-vendas/pedidos/{id}
```

| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `id`        | `int`      | **Obrigatório**. A chave do pedido. |

```
  STATUS: 200 - OK
```

RESPONSE JSON
```json
{
    "codigo": 123,
    "cpfCliente": "12345678901",
    "nomeCliente": "João da Silva",
    "total": 200.00,
    "dataPedido": "2024-08-08",
    "status": "REALIZADO",
    "itens": [
        {
            "descricaoProduto": "Produto Exemplo",
            "precoUnitario": 100.00,
            "quantidade": 2
        }
    ]
}
```

#### Cancela um pedido por ID

```http
PATCH sistema-vendas/pedidos/{id}
```

| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `id`        | `int`      | **Obrigatório**. A chave do pedido a ser cancelado. |

```
  STATUS: 204 - NO CONTENT
```
