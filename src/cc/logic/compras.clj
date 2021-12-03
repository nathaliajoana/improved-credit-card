(ns cc.logic.compras
  (:require [cc.model :as m]
            [schema.core :as s]))

(s/defn adiciona-compra :- m/Compras
  "Retorna lista de compras com adição de nova compra"
  [compras :- m/Compras, compra :- m/Compra]
  (let [id (:id-compra compra)]
    (if-not (contains? compras id)
      (assoc compras id compra)
      compras)))

(s/defn lista-compras :- [m/Compra]
  "Retorna a listagem de compras"
  [compras :- [m/Compra]]
  (if (seq compras)
    compras
    []))
