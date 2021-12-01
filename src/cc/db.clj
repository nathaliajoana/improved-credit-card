(ns cc.db
  (:require [java-time :as jt]))

; ---------------------------------------------------------------------------
; Cliente & Cartão de Crédito

(def cliente1
  {:id-cliente  1
   :nome        "Fulano B. da Silva"
   :cpf         "111.111.111-11"
   :email       "fulanobdasilva@gmail.com"
   :cartao      {:numero "0000 0000 0000 0000", :cvv "123", :validade "01/25", :limite 5000.00}})


(defn cliente []
  [cliente1])

; ---------------------------------------------------------------------------
; Lista de Compras

(def compra1 {:id-cliente      1
              :id-compra       1
              :data            (jt/local-date 2021 11 29)
              :valor           54.99
              :estabelecimento "Hamburgueria A"
              :categoria       "Alimentação"})

(def compra2 {:id-cliente      1
              :id-compra       2
              :data            (jt/local-date 2021 12 03)
              :valor           100.20
              :estabelecimento "Farmacia B"
              :categoria       "Saúde"})

(def compra3 {:id-cliente      1
              :id-compra       3
              :data            (jt/local-date 2021 12 05)
              :valor           599.50
              :estabelecimento "Faculdade C"
              :categoria       "Educação"})

(def compra4 {:id-cliente      1
              :id-compra       4
              :data            (jt/local-date 2021 12 11)
              :valor           550.99
              :estabelecimento "Mercado D"
              :categoria       "Alimentação"})

(def compra5 {:id-cliente      1
              :id-compra       5
              :data            (jt/local-date 2021 12 20)
              :valor           400.00
              :estabelecimento "Consultorio E"
              :categoria       "Saúde"})

(def compra6 {:id-cliente      1
              :id-compra       6
              :data            (jt/local-date 2021 12 25)
              :valor           145.90
              :estabelecimento "Curso F"
              :categoria       "Educação"})

(def id-e-compras
  {1 compra1, 2 compra2, 3 compra3, 4 compra4, 5 compra5, 6 compra6})     ; schema Compras

(defn compras []
  [compra1, compra2, compra3, compra4, compra5, compra6])