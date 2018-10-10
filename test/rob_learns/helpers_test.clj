(ns rob-learns.helpers-test
  (:use clojure.pprint)
  (:require [clojure.test :refer :all]
            [rob-learns.helpers :refer :all]
            [failjure.core :as f]))

(deftest a-test
  (testing "function is exported"
    (is (false? (function? failjureize)))
    (is (true? (failjureize "Is trrue?" identity  true)))
    (is ( f/failed? (failjureize "Is trrue?" identity  false)))))

