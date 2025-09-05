# Microsserviço de Controle de Estoque e Insumos

Este microsserviço é responsável pelo gerenciamento de itens de estoque e insumos em um sistema. Ele permite criar,
visualizar, atualizar e excluir itens de estoque, além de controlar a quantidade disponível e os fornecedores.

## Funcionalidades

- **Gerenciamento de Itens**: Criar, visualizar, atualizar e excluir itens do estoque
  (ex: Arroz, Feijão, Carne, Alface, Tomate).
- **Propriedades dos Itens**:
    - Nome
    - Quantidade em Estoque
    - Unidade de Medida (Kg, L, Un)
    - Fornecedor
- **Regras de Negócio**:
    - Não é permitido que a quantidade de um item no estoque fique negativa ao ser atualizada.
- **Endpoint para Baixa de Estoque**:
    - Endpoint para baixar a quantidade de itens do estoque, com base na preparação de um cardápio.
      Esta operação será acionada por outro serviço ou pelo frontend.
