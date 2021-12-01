(ns cc.logic.nova-compra
  (:require [schema.core :as s]
            [cc.model :as model]))

(s/defn adiciona-compra :- model/Compras
  [compras :- model/Compras, compra :- model/Compra]
  (let [id (:id-compra compra)]
    (assoc compras id compra)))