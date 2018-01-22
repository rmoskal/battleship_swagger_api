(ns rob-learns.helpers-test
  (:require [clojure.test :refer :all]
            [rob-learns.helpers :refer :all]))

(deftest a-test
  (testing "function is exported"
    (is (true? (function? failjureize)))
    )
  )