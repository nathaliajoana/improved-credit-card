(ns cc.models.schema
  (:require [schema.core :as s])
  (:import (java.util Date)))


(def Categoria (s/enum "Alimentação" "Saúde" "Educação"))

(def Cartao
  "Schema de um cartão de crédito"
  {(s/optional-key :db/id)  s/Num
   :cartao/cliente-id       Long
   :cartao/numero           s/Str
   :cartao/cvv              s/Str
   :cartao/validade         Date
   :cartao/limite           BigDecimal})

(def Cliente
  "Schema de um cliente com cartão de crédito"
  {(s/optional-key :db/id)  s/Num
   :cliente/id              Long
   :cliente/nome            s/Str
   :cliente/cpf             s/Str
   :cliente/email           s/Str})

(def Compra
  "Schema de uma compra"
  {(s/optional-key :db/id)   s/Num
   :compra/cliente-id        Long
   :compra/id                s/Uuid
   :compra/data              Date
   :compra/valor             BigDecimal
   :compra/estabelecimento   s/Str
   :compra/categoria         Categoria})

(def Resumo
  "Schema do resumo de compras"
  {:usuario             Long
   :numero-de-compras   s/Num
   :valor-total         s/Str})

(def Categoria-valor
  "Schema de gastos por categoria"
  {Categoria s/Str})

(def Categoria-cliente
  "Schema de gastos por categoria por cliente"
  {:usuario                Long
   :gastos-por-categoria   [Categoria-valor]})

(def Fatura
  "Schema do valor da fatura por cliente"
  {:usuario Long
   :fatura s/Str})