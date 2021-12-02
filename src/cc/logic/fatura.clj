(ns cc.logic.fatura
  (:require [cc.model :as m]
            [java-time :as jt]
            [schema.core :as s]
            [cc.logic.resumo :as l.resumo]))

(s/defn mes-atual? :- s/Bool
  [compras]
  (= (jt/as (:data compras) :year :month-of-year) (jt/as (jt/local-date) :year :month-of-year)))

(s/defn fatura-do-mes :- s/Str
  [compras :- m/PVec]
  (->> compras
       (filter mes-atual?)
       (l.resumo/total-das-compras)))