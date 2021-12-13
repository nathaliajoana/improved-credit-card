(ns cc.logic.resumo
  (:require [cc.models.schema :as ms]
            [schema.core :as s]))

(s/defn total-das-compras :- s/Str
  "Calcula a soma total e formata em reais"
  [detalhes]
  (->> detalhes
       (map :compra/valor)
       (reduce +)
       (format "R$%.2f")))

(s/defn resumo-do-cliente :- ms/Resumo
  "Retorna o mapa de resumo da conta"
  [[cliente-id detalhes-compras]]
  {:usuario             cliente-id
   :numero-de-compras   (count detalhes-compras)
   :valor-total         (total-das-compras detalhes-compras)
   })

(s/defn resumo :- [ms/Resumo]
  "Retorna resumo do cartao: id, número de compras e valor total gasto"
  [compras :- [ms/Compra]]
  (->> compras
       (group-by :compra/cliente-id)
       (map resumo-do-cliente)))