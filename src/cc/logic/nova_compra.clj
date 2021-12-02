(ns cc.logic.nova-compra
  (:require [cc.model :as m]
            [schema.core :as s]))

(s/defn adiciona-compra :- m/Compras
  [compras :- m/Compras, compra :- m/Compra]
  (let [id (:id-compra compra)]
    (assoc compras id compra)))