(ns cc.logic.gastos-categoria
  (:require [cc.model :as m]
            [schema.core :as s]
            [cc.logic.resumo :as l.resumo]))

(s/defn organizacao-categoria :- m/Categoria
  "Retorna o mapa de categoria e valor"
  [[categoria detalhes-categoria]]
   {categoria (l.resumo/total-das-compras detalhes-categoria)})

(s/defn gasto-por-categoria :- [m/Categoria]
  "Retorna gastos por categoria das compras"
  [compras :- [m/Compra]]
  (->> compras
       (group-by :categoria)
       (map organizacao-categoria)))