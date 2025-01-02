Nunes Bar Web é um sistema simples para gestão de produtos, usuários e clientes de um bar, desenvolvido em Java utilizando Spring Boot, Thymeleaf e Bootstrap. O sistema também implementa funcionalidades de caixa e venda de produtos, facilitando a administração do bar e o controle de estoque.

Requisitos do Projeto
Backend
Linguagem: Java
Framework: Spring Boot
Dependências:
Spring Web: Para construir e gerenciar as rotas HTTP e o controle das requisições.
Spring DevTools: Para melhorar a experiência de desenvolvimento, com reinicialização automática e outros recursos.
Thymeleaf: Para renderizar templates HTML dinâmicos.
Frontend
HTML com Thymeleaf: Utilizado para criar as páginas dinâmicas, com templates baseados nos dados fornecidos pelo backend.
Bootstrap: Framework CSS utilizado para estilizar as páginas e proporcionar um design responsivo.
JavaScript: Para funcionalidades interativas, como a adição de produtos ao carrinho e interação com o sistema de caixa.
Outros
O sistema não utiliza banco de dados. Os dados (como produtos e carrinho de compras) são armazenados no localStorage do navegador.
O sistema de caixa permite a venda de produtos, onde os itens podem ser adicionados ao carrinho e comprados diretamente na interface.
Funcionalidades
Gestão de Produtos: Cadastro, listagem, edição e exclusão de produtos.
Venda de Produtos: O sistema permite adicionar produtos ao carrinho e realizar a compra no sistema de caixa.
Busca e Filtros: O usuário pode pesquisar produtos por nome e categoria.
Interação com o Carrinho: Os produtos podem ser adicionados ao carrinho e finalizados diretamente na interface.
