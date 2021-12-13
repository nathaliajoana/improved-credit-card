(ns cc.logic-test
  (:import (clojure.lang ExceptionInfo))
  (:require [java-time :as jt]
            [schema.core :as s]
            [clojure.test :refer :all]
            [cc.logic.compras :refer :all]
            [cc.logic.gastos-categoria :refer :all]))

;TODO fix tests to match datomic integration
(s/set-fn-validation! true)

(deftest adiciona-compra-test

  (testing "Add compra numa lista de compras"
    (let [lista-de-compras {1 {:id-cliente 1, :id-compra 1, :data (jt/local-date 2021 10 02), :valor 22.00, :estabelecimento "A", :categoria "Saúde"}}
          compra-nova {:id-cliente 1, :id-compra 7, :data (jt/local-date 2021 11 27), :valor 100.00 , :estabelecimento "B", :categoria "Alimentação"}]
      (is (= {7 compra-nova}
             (adiciona-compra {} compra-nova)))
      (is (= {1 {:id-cliente 1, :id-compra 1, :data (jt/local-date 2021 10 02), :valor 22.00, :estabelecimento "A", :categoria "Saúde"} 7 compra-nova}
             (adiciona-compra lista-de-compras compra-nova)))))

  (testing "Não add compra quando já existe o id"
    (let [lista-de-compras {1 {:id-cliente 1, :id-compra 1, :data (jt/local-date 2021 10 02), :valor 22.00, :estabelecimento "A", :categoria "Saúde"}}
          compra-nova {:id-cliente 1, :id-compra 1, :data (jt/local-date 2021 11 27), :valor 100.00 , :estabelecimento "B", :categoria "Alimentação"}]
      (is (= lista-de-compras (adiciona-compra lista-de-compras compra-nova)))))

  (testing "Não add compra quando não segue o Schema"
    (let [compra-nova  {:id-cliente 1, :id-compra 1, :data (jt/local-date 2021 11 27), :valor "100.00"}]
      (is (thrown? ExceptionInfo (adiciona-compra {} compra-nova)))
      (is (thrown? ExceptionInfo (adiciona-compra {} [])))
      (is (thrown? ExceptionInfo (adiciona-compra {} 23123123214)))
      (is (thrown? ExceptionInfo (adiciona-compra {} "string")))))

  (testing "Não add compra em casos vazios/nulos"
    (let [compra-nova {:id-cliente 1, :id-compra 7, :data (jt/local-date 2021 11 27), :valor 100.00 , :estabelecimento "Lanchonete A", :categoria "Alimentação"}]
      (is (thrown? ExceptionInfo (adiciona-compra {} nil)))
      (is (thrown? ExceptionInfo (adiciona-compra nil {})))
      (is (thrown? ExceptionInfo (adiciona-compra nil compra-nova))))))


(deftest lista-compras-test

  (testing "Retorna lista com compras padrão"
    (let [compra-nova [{:id-cliente 1, :id-compra 7, :data (jt/local-date 2021 11 27), :valor 100.00 , :estabelecimento "Lanchonete A", :categoria "Alimentação"}]]
      (is (= compra-nova (lista-compras compra-nova)))))

  (testing "Retorna um vetor vazio quando a lista de compras é vazia"
    (is (= [] (lista-compras nil)))
    (is (= [] (lista-compras []))))

  (testing "Não retorna lista quando não segue o Schema"
    (let [compra-nova  [{:id-cliente 1, :id-compra 1, :data (jt/local-date 2021 11 27)}]]
          (is (thrown? ExceptionInfo (lista-compras compra-nova)))
          (is (thrown? ExceptionInfo (lista-compras {})))
          (is (thrown? ExceptionInfo (lista-compras 121323123)))
          (is (thrown? ExceptionInfo (lista-compras "nao é uma compra"))))))

(deftest gasto-por-categoria-test

  (testing "Retorna gastos por categoria com compras padrão"
    (let [compra-nova [{:id-cliente 1, :id-compra 7, :data (jt/local-date 2021 11 27), :valor 100.00 , :estabelecimento "Lanchonete A", :categoria "Alimentação"}]]
      (is (= [{"Alimentação" "R$100.00"}] (gasto-por-categoria compra-nova)))))

  (testing "Retorna um vetor vazio quando a lista de compras é vazia"
    (is (= [] (gasto-por-categoria nil)))
    (is (= [] (gasto-por-categoria []))))

  (testing "Não retorna gastos por categoria quando não segue o Schema"
    (let [compra-nova  [{:id-cliente "1", :id-compra 1, :data "???"}]]
      (is (thrown? ExceptionInfo (gasto-por-categoria compra-nova)))
      (is (thrown? ExceptionInfo (gasto-por-categoria {})))
      (is (thrown? ExceptionInfo (gasto-por-categoria 24971237123)))
      (is (thrown? ExceptionInfo (gasto-por-categoria "STRING"))))))
