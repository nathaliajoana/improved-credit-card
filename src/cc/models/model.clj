(ns cc.models.model
  (:use clojure.pprint)
  (:require [schema.core :as s]
            [java-time :as jt]
            [cc.models.schema :as ms])
  (:import (java.util UUID)
           (java.util Date)))

(defn uuid []  (UUID/randomUUID))

(s/defn cartao :- ms/Cartao
  [id         :- Long
   numero     :- s/Str
   cvv        :- s/Str
   validade   :- Date
   limite     :- BigDecimal]
  {:cartao/cliente-id   id
   :cartao/numero       numero
   :cartao/cvv          cvv
   :cartao/validade     validade
   :cartao/limite       limite})

(s/defn cliente :- ms/Cliente
  [id       :- Long
   nome     :- s/Str
   cpf      :- s/Str
   email    :- s/Str]
  {:cliente/id       id
   :cliente/nome     nome
   :cliente/cpf      cpf
   :cliente/email    email})

(s/defn compra :- ms/Compra
  [id                :- Long
   data              :- Date
   valor             :- BigDecimal
   estabelecimento   :- s/Str
   categoria         :- ms/Categoria]
  {:compra/cliente-id        id
   :compra/id                (uuid)
   :compra/data              data
   :compra/valor             valor
   :compra/estabelecimento   estabelecimento
   :compra/categoria         categoria})

; cartao, cliente e compra
(def datomic-schema
  [{:db/ident         :cartao/cliente-id
    :db/valueType     :db.type/long
    :db/cardinality   :db.cardinality/one
    :db/unique        :db.unique/identity
    :db/doc           "ID do cliente na entidade cartão"}
   {:db/ident         :cartao/numero
    :db/valueType     :db.type/string
    :db/cardinality   :db.cardinality/one
    :db/unique        :db.unique/identity
    :db/doc           "Número identificador do cartão"}
   {:db/ident         :cartao/cvv
    :db/valueType     :db.type/string
    :db/cardinality   :db.cardinality/one
    :db/doc           "CVV do cartão"}
   {:db/ident         :cartao/validade
    :db/valueType     :db.type/instant
    :db/cardinality   :db.cardinality/one
    :db/doc           "Data de validade do cartão"}
   {:db/ident         :cartao/limite
    :db/valueType     :db.type/bigdec
    :db/cardinality   :db.cardinality/one
    :db/doc           "Limite do cartão"}

   {:db/ident         :cliente/id
    :db/valueType     :db.type/long
    :db/cardinality   :db.cardinality/one
    :db/unique        :db.unique/identity
    :db/doc           "ID do cliente"}
   {:db/ident         :cliente/nome
    :db/valueType     :db.type/string
    :db/cardinality   :db.cardinality/one
    :db/doc           "Nome do cliente"}
   {:db/ident         :cliente/cpf
    :db/valueType     :db.type/string
    :db/cardinality   :db.cardinality/one
    :db/doc           "CPF do cliente"}
   {:db/ident         :cliente/email
    :db/valueType     :db.type/string
    :db/cardinality   :db.cardinality/one
    :db/doc           "Email do cliente"}

   {:db/ident         :compra/cliente-id
    :db/valueType     :db.type/long
    :db/cardinality   :db.cardinality/one
    :db/doc           "ID do cliente na entidade compra"}
   {:db/ident         :compra/id
    :db/valueType     :db.type/uuid
    :db/cardinality   :db.cardinality/one
    :db/unique        :db.unique/identity
    :db/doc           "ID da compra realizada"}
   {:db/ident         :compra/data
    :db/valueType     :db.type/instant
    :db/cardinality   :db.cardinality/one
    :db/doc           "Data da compra"}
   {:db/ident         :compra/valor
    :db/valueType     :db.type/bigdec
    :db/cardinality   :db.cardinality/one
    :db/doc           "Valor da compra"}
   {:db/ident         :compra/estabelecimento
    :db/valueType     :db.type/string
    :db/cardinality   :db.cardinality/one
    :db/doc           "Estabelecimento responsável pela compra"}
   {:db/ident         :compra/categoria
    :db/valueType     :db.type/string
    :db/cardinality   :db.cardinality/one
    :db/doc           "Categoria da compra realizada"}])