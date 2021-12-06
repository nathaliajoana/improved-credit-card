(ns cc.hall
  (:use clojure.pprint)
  (:require [schema.core :as s]
            [java-time :as jt]
            [cc.db :as db]
            [cc.logic.resumo :as l.resumo]
            [cc.logic.fatura :as l.fatura]
            [cc.logic.compras :as l.compra]
            [cc.logic.gastos-categoria :as l.categoria]))

(s/set-fn-validation! true)

; DADOS CLIENTE
(println "---DADOS CLIENTE---")
(pprint (db/cliente))

; RESUMO GERAL DA CONTA
(println "---RESUMO GERAL DA CONTA---")
(println (l.resumo/resumo db/compras))

; LISTAGEM DAS COMPRAS
(println "---LISTAGEM DE COMPRAS---")
;(pprint (db/compras))
(pprint (l.compra/lista-compras db/compras))

; GASTOS POR CATEGORIA
(println "---GASTOS POR CATEGORIA---")
(println (l.categoria/gasto-por-categoria db/compras))

; FATURA DO MES - do primeiro ao ultimo dia
(println "---FATURA DO MES---")
(println (l.fatura/fatura-do-mes db/compras))

; ADICIONAR COMPRA NA LISTAGEM DE COMPRAS
(println "---ADD COMPRA NA LISTAGEM DE COMPRAS---")
(pprint (l.compra/adiciona-compra db/id-e-compras
                           {:id-cliente      1
                            :id-compra       7
                            :data            (jt/local-date 2021 11 28)
                            :valor           20.00
                            :estabelecimento "Sorveteria H"
                            :categoria       "Alimentação"}))
