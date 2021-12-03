(ns cc.db
  (:require [cc.model :as m]
            [java-time :as jt]
            [schema.core :as s]))

; ---------------------------------------------------------------------------
; Cliente & Cartão de Crédito

(s/def cliente1 :- m/Cliente
  {:id-cliente  1
   :nome        "Fulano B. da Silva"
   :cpf         "111.111.111-11"
   :email       "fulanobdasilva@gmail.com"
   :cartao      {:numero "0000 0000 0000 0000", :cvv "123", :validade (jt/local-date 2025 01), :limite 5000.00}})

(s/defn cliente :- [m/Cliente] []
  [cliente1])

; ---------------------------------------------------------------------------
; Lista de Compras

(s/def compra1 :- m/Compra
  {:id-cliente      1
   :id-compra       1
   :data            (jt/local-date 2021 11 29)
   :valor           54.99
   :estabelecimento "Hamburgueria A"
   :categoria       "Alimentação"})

(s/def compra2 :- m/Compra
  {:id-cliente      1
   :id-compra       2
   :data            (jt/local-date 2021 12 03)
   :valor           100.20
   :estabelecimento "Farmacia B"
   :categoria       "Saúde"})

(s/def compra3 :- m/Compra
  {:id-cliente      1
   :id-compra       3
   :data            (jt/local-date 2021 12 05)
   :valor           599.50
   :estabelecimento "Faculdade C"
   :categoria       "Educação"})

(s/def compra4 :- m/Compra
  {:id-cliente      1
   :id-compra       4
   :data            (jt/local-date 2021 12 11)
   :valor           550.99
   :estabelecimento "Mercado D"
   :categoria       "Alimentação"})

(s/def compra5 :- m/Compra
  {:id-cliente      1
   :id-compra       5
   :data            (jt/local-date 2021 12 20)
   :valor           400.00
   :estabelecimento "Consultorio E"
   :categoria       "Saúde"})

(s/def compra6 :- m/Compra
  {:id-cliente      1
   :id-compra       6
   :data            (jt/local-date 2021 12 25)
   :valor           145.90
   :estabelecimento "Curso F"
   :categoria       "Educação"})

(s/def id-e-compras :- m/Compras
  {1 compra1, 2 compra2, 3 compra3, 4 compra4, 5 compra5, 6 compra6})

(s/def compras :- [m/Compra]
  [compra1, compra2, compra3, compra4, compra5, compra6])