(ns cc.db
  (:use clojure.pprint)
  (:require [java-time :as jt]
            [datomic.api :as d]
            [cc.models.model :as m]
            [cc.logic.resumo :as r]
            [cc.logic.fatura :as f]
            [cc.database.datomic :as dtm]
            [cc.logic.gastos-categoria :as gc]))

(println (dtm/apaga-db!))

(def conn (dtm/abre-conexao!))
(dtm/cria-schema! conn)

(dtm/cria-cliente! conn [(m/cliente 1 "Fulano" "111.111.111-11" "fulano@gmail.com")
                         (m/cliente 2 "Beltrano" "222.222.222-22" "beltrano@hotmail.com")])

(dtm/cria-cartao! conn [(m/cartao 1 "0000 0000 0000 0000" "123" (jt/sql-timestamp 2025 01 01) 5000.00M)
                        (m/cartao 2 "0202 0202 0202 0202" "444" (jt/sql-timestamp 2024 01 01) 1000.00M)])

(dtm/cria-compra! conn [(m/compra 1 (jt/sql-timestamp 2021 11 29) 54.99M "Hamburgueria A" "Alimentação")
                        (m/compra 1 (jt/sql-timestamp 2021 12 03) 100.20M "Farmacia B" "Saúde")
                        (m/compra 1 (jt/sql-timestamp 2021 12 05) 599.50M "Faculdade C" "Educação")
                        (m/compra 1 (jt/sql-timestamp 2021 12 11) 550.99M "Mercado D" "Alimentação")
                        (m/compra 1 (jt/sql-timestamp 2021 12 20) 400.00M "Consultorio E" "Saúde")
                        (m/compra 1 (jt/sql-timestamp 2021 12 25) 145.90M "Curso F" "Educação")
                        (m/compra 2 (jt/sql-timestamp 2021 11 02) 39.99M "Lanchonete G" "Alimentação")])

; --- TODAS AS COMPRAS ---
(def compras (flatten [(dtm/todas-as-compras (d/db conn))]))
(pprint compras)

; --- DADOS DO CLIENTE ---
(pprint (dtm/dados-cliente (d/db conn) 1))

; --- DADOS DO CARTAO ---
(pprint (dtm/dados-cartao (d/db conn) 1))

; --- RESUMO DOS CLIENTES --
(pprint (r/resumo compras))

; --- GASTOS POR CATEGORIA POR CLIENTE ---
(println (gc/gasto-por-categoria compras))

; --- FATURA DO MÊS POR CLIENTE --- (not working yet)
;(println (f/fatura-do-mes compras))

