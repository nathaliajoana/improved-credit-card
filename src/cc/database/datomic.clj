(ns cc.database.datomic
  (:require [datomic.api :as d]
            [schema.core :as s]
            [cc.models.model :as m]
            [cc.models.schema :as ms]))

(def db-uri "datomic:dev://localhost:4334/creditcard")

(defn abre-conexao! []
  (d/create-database db-uri)
  (d/connect db-uri))

(defn apaga-db! []
  (d/delete-database db-uri))

(defn cria-schema! [conn]
  (d/transact conn m/datomic-schema))

(s/defn cria-cliente!
  [conn
   cliente :-  [ms/Cliente]]
  (d/transact conn cliente))

(s/defn cria-cartao!
  [conn
   cartao :- [ms/Cartao]]
  (d/transact conn cartao))

(s/defn cria-compra!
  [conn
   compra :- [ms/Compra]]
  (d/transact conn compra))


; queries
(defn todas-as-compras [db]
  (d/q '[:find (pull ?entity [*])
         :where [?entity :compra/id]] db))


(defn dados-cliente [db id-requisitado]
  (d/pull db '[:cliente/id :cliente/nome :cliente/cpf :cliente/email]
          [:cliente/id id-requisitado]))


(defn dados-cartao [db id-requisitado]
  (d/pull db '[:cartao/numero :cartao/cvv :cartao/validade :cartao/limite]
          [:cartao/cliente-id id-requisitado]))

