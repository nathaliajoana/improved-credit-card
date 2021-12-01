(ns cc.logic.resumo)

(defn total-das-compras
  [detalhes]
  (->> detalhes
       (map :valor)
       (reduce +)
       (format "R$%.2f")))

(defn resumo-do-cliente
  [[id-cliente detalhes-compras]]
  {:usuario           id-cliente
   :numero-de-compras (count detalhes-compras)
   :valor-total       (total-das-compras detalhes-compras)})

(defn resumo
  [compras]
  (->> compras
       (group-by :id-cliente)
       (map resumo-do-cliente) ) )