(ns cc.model
  (:use clojure.pprint)
  (:require [schema.core :as s])
  (:import (java.time LocalDate)))

(s/set-fn-validation! true)

(def PosInt (s/pred pos-int?))                              ; número inteiro positivo
(def PosNum (s/pred pos?))                                  ; número positivo

(def Cartao
  "Schema de um cartão de crédito"
  {:numero s/Str, :cvv s/Str, :validade LocalDate, :limite PosNum})

(def Cliente
  "Schema de um cliente com cartão de crédito"
  {:id-cliente  PosInt
   :nome        s/Str
   :cpf         s/Str
   :email       s/Str
   :cartao      Cartao})

(def Compra
  "Schema de uma compra"
  {:id-cliente      PosInt
   :id-compra       PosInt
   :data            LocalDate
   :valor           PosNum
   :estabelecimento s/Str
   :categoria       s/Str})

(def Compras
  "Schema de lista de compras tipo id-e-compras"
  {PosInt Compra})

(def Resumo
  "Schema do resumo de compras"
  {:usuario           PosInt
   :numero-de-compras PosInt
   :valor-total       s/Str})

(def Categoria
  "Schema de gastos por categoria"
  {s/Str s/Str})