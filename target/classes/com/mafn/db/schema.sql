create table
    cliente (
        id integer not null primary key auto_increment,
        nome varchar(100)
    );

create table
    produto (
        id integer not null primary key auto_increment,
        descricao varchar(100),
        preco numeric(20,2)
    );

create table
    pedido (
        id integer not null primary key auto_increment,
        cliente_id integer references cliente (id),
        data_pedido timestamp,
        total numeric(20, 2)
    );

create table
    item_pedido (
        id int not null primary key auto_increment,
        pedido_id int references pedido (id),
        produto_id int references produto(id),
        quantidade int
    );