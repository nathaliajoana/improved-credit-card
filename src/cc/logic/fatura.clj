(ns cc.logic.fatura
  (:require [java-time :as jt]
            [cc.logic.resumo :as l.resumo]))

(defn valores
  [[_ detalhes]]
  ( detalhes))

(defn mes-atual?
  [compras]
  (= (jt/as (:data compras) :year :month-of-year) (jt/as (jt/local-date) :year :month-of-year)))

(defn fatura-do-mes
  [compras]
  (->> compras
       (filter mes-atual?)
       (l.resumo/total-das-compras)))
