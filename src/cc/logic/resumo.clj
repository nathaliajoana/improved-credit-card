(ns cc.logic.resumo
  (:require [cc.model :as m]
            [schema.core :as s]))

(s/defn total-das-compras :- s/Str
  "Calcula a soma total e formata em reais"
  [detalhes]
  (->> detalhes
       (map :valor)
       (reduce +)
       (format "R$%.2f")))

(s/defn resumo-do-cliente :- m/Resumo
  "Retorna o mapa de resumo da conta"
  [[id-cliente detalhes-compras]]
  {:usuario           id-cliente
   :numero-de-compras (count detalhes-compras)
   :valor-total       (total-das-compras detalhes-compras)})

(s/defn resumo :- [m/Resumo]
  "Retorna o resumo da conta (id, número de compras e valor total gasto"
  [compras :- [m/Compra]]
  (->> compras
       (group-by :id-cliente)
       (map resumo-do-cliente)))