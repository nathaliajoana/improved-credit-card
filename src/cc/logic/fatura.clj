(ns cc.logic.fatura
  (:require [cc.models.schema :as ms]
            [java-time :as jt]
            [schema.core :as s]
            [cc.logic.resumo :as l.resumo]))

; TODO - fix mes-atual? inst->localdate conversion issue

(s/defn mes-atual? :- s/Bool
  "Verifica se o mês da compra equivale ao mês atual"
  [compra]
  (= (jt/as (jt/local-date-time (:compra/data compra)) :year :month-of-year) (jt/as (jt/local-date) :year :month-of-year)))

(s/defn fatura-do-cliente :- ms/Fatura
  "Retorna o valor da fatura do cliente"
  [[cliente-id detalhes-compras]]
  {:usuario cliente-id
   :fatura  (->> detalhes-compras
                 (filter mes-atual?)
                 (l.resumo/total-das-compras))})

(s/defn fatura-do-mes :- [ms/Fatura]
  "Retorna o valor total das compras feitas durante o mês atual"
  [compras :- [ms/Compra]]
  (->> compras
       (group-by :compra/cliente-id)
       (map fatura-do-cliente)))