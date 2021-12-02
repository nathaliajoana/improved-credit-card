(ns cc.logic.gastos-categoria
  (:require [cc.model :as m]
            [schema.core :as s]
            [cc.logic.resumo :as l.resumo]))

(s/defn organizacao-categoria :- m/Categoria
  [[categoria detalhes-categoria]]
   {categoria (l.resumo/total-das-compras detalhes-categoria)})

(s/defn gasto-por-categoria :- m/LSeq
  [compras :- m/PVec]
  (->> compras
       (group-by :categoria)
       (map organizacao-categoria)))