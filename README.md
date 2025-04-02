# Sistema de Cadastro de Usuários

Este é um sistema simples de cadastro, atualização, remoção e consulta de usuários. O projeto foi desenvolvido em Java e utiliza um banco de dados MySQL para armazenar os dados dos usuários.

## Funcionalidades

- **Cadastro de usuários**: Permite cadastrar novos usuários informando ID, CPF, nome e idade.
- **Atualização de usuários**: Permite editar as informações de um usuário cadastrado.
- **Remoção de usuários**: Permite excluir um usuário existente.
- **Consulta de usuários**: Possibilita consultar todos os usuários cadastrados ou filtrar por CPF ou iniciais do nome.

## Tecnologias Utilizadas

- **Java**: Linguagem de programação para o desenvolvimento do sistema.
- **MySQL**: Banco de dados utilizado para armazenar as informações dos usuários.
- **JDBC**: Conexão entre o Java e o MySQL, utilizando `PreparedStatement` para executar as operações de CRUD no banco de dados.

## Pré-requisitos

Antes de rodar o projeto, certifique-se de ter os seguintes itens instalados:

- **Java**: JDK 8 ou superior.
- **MySQL**: Instale o MySQL e crie um banco de dados.
- **MySQL JDBC Driver**: Certifique-se de que o driver JDBC do MySQL esteja disponível no seu projeto.
