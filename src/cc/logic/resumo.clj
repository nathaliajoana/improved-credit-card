(ns cc.logic.resumo
  (:require [cc.model :as m]
            [schema.core :as s]))

(s/defn total-das-compras :- s/Str
  [detalhes]
  (->> detalhes
       (map :valor)
       (reduce +)
       (format "R$%.2f")))

(s/defn resumo-do-cliente :- m/Resumo
  [[id-cliente detalhes-compras]]
  {:usuario           id-cliente
   :numero-de-compras (count detalhes-compras)
   :valor-total       (total-das-compras detalhes-compras)})

(s/defn resumo :- m/LSeq
  [compras :- m/PVec]
  (->> compras
       (group-by :id-cliente)
       (map resumo-do-cliente)))