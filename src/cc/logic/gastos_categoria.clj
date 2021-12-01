(ns cc.logic.gastos-categoria
  (:require [cc.logic.resumo :as l.resumo]))

(defn organizacao-categoria
  [[categoria detalhes-categoria]]
   {categoria (l.resumo/total-das-compras detalhes-categoria)})

(defn gasto-por-categoria
  [compras]
  (->> compras
       (group-by :categoria)
       (map organizacao-categoria)))