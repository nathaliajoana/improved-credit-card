# Credit Card (improved version)
Projeto em Clojure simulando (de forma básica) o cartão de crédito de um cliente Nubank.
* Utiliza Java-time e Plumatic Schema
* Testes de unidade automatizados

## Funcionalidades
* Representação dos dados do cliente (nome, cpf, email)
* Representação dos dados do cartão (número, cvv, validade, limite)
* Apresentação de resumo (id do usuário, número de compras no cartão, valor total gasto)
* Listagem de compras realizadas (data, valor, estabelecimento, categoria)
* Valor dos gastos agrupados por categoria
* Cálculo do valor da fatura (ciclo: primeiro ao último dia do mês atual)

## Testes automatizados
* Teste da função que adiciona uma compra na lista de compras realizadas;
* Teste da função que lista as compras realizadas;
* Teste da função que realiza o cálculo dos gastos agrupados por categoria

## Utilização
* Toda funcionalidade está disponível para visualização no arquivo **hall.clj**

## Autora
* **Nathália Joana**: @nathaliajoana (https://github.com/nathaliajoana)
* **Nubank** Yes, She Codes! Alura Bootcamp - Week 1 and 2
