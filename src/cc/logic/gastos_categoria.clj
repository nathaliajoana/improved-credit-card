(ns cc.logic.gastos-categoria
  (:require [cc.models.schema :as ms]
            [schema.core :as s]
            [cc.logic.resumo :as l.resumo]))

(s/defn organizacao-categoria :- ms/Categoria-valor
  "Retorna o mapa de categoria e valor"
  [[categoria detalhes-categoria]]
   {categoria (l.resumo/total-das-compras detalhes-categoria)})

(s/defn categorias-do-cliente :- ms/Categoria-cliente
  "Retorna gastos por categoria de um cliente"
  [[cliente-id detalhes-compras]]
  {:usuario cliente-id
   :gastos-por-categoria  (->> detalhes-compras
                              (group-by :compra/categoria)
                              (map organizacao-categoria))})

(s/defn gasto-por-categoria :- [ms/Categoria-cliente]
  "Retorna gastos por categoria das compras"
  [compras :- [ms/Compra]]
  (->> compras
       (group-by :compra/cliente-id)
       (map categorias-do-cliente)))