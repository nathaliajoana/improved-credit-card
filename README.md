# Credit Card (improved version)
Projeto em Clojure simulando (de forma básica) o cartão de crédito de um cliente Nubank.
* Java-Time, Plumatic Schema, Datomic e testes de unidade automatizados

Toda funcionalidade está disponível para visualização no arquivo **db.clj**

## Funcionalidades - Week 1
* Representação dos dados do cliente (nome, cpf, email)
* Representação dos dados do cartão (número, cvv, validade, limite)
* Apresentação de resumo (id do usuário, número de compras no cartão, valor total gasto)
* Listagem de compras realizadas (data, valor, estabelecimento, categoria)
* Valor dos gastos agrupados por categoria
* Cálculo do valor da fatura (ciclo: primeiro ao último dia do mês atual)

## Testes automatizados - Week 2
* Teste da função que adiciona uma compra na lista de compras realizadas
* Teste da função que lista as compras realizadas
* Teste da função que realiza o cálculo dos gastos agrupados por categoria

## Integração Datomic - Week 3
* Conectado a uma instância local do banco de dados Datomic
* Função que armazena as informações do(a) cliente e de seu cartão
* Função que armazena as informações das compras realizadas
* Query que recupera e listar as compras realizadas


## Autora
* **Nathália Joana**: @nathaliajoana (https://github.com/nathaliajoana)
* **Nubank** Yes, She Codes! Alura Bootcamp
