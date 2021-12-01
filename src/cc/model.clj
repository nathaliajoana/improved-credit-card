(ns cc.model
  (:use clojure.pprint)
  (:require [schema.core :as s]))

(s/set-fn-validation! true)

(def PosInt (s/pred pos-int?))                              ; número inteiro positivo
(def PosNum (s/pred pos?))                                  ; número positivo e double

(def Cartao
  "Schema de um cartão de crédito"
  {:numero s/Str, :cvv s/Str, :validade s/Str, :limite PosNum})

(def Cliente
  "Schema de um cliente com cartão de crédito"
  {:id-cliente  PosInt
   :nome        s/Str
   :cpf         s/Str
   :email       s/Str
   :cartao      Cartao})

(def Compra
  "Schema de uma compra"
  {:id-cliente       PosInt
   :id-compra        PosInt
   :data             java.time.LocalDate
   :valor            PosNum
   :estabelecimento  s/Str
   :categoria        s/Str})

(def Compras
  "Schema de lista de compras: id-e-compras"
  {PosInt Compra})