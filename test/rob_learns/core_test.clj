(ns rob-learns.core-test
  (:use clojure.pprint)
  (:require [clojure.test :refer :all]
            [rob-learns.core :refer :all]
            [failjure.core :as f]))

(deftest main-test
  (testing "The whole magilla"
    (let [board (place-ship get-coordinates-h (fleet) (make-board) 1 0 "destroyer")]
      (is (= "destroyer" (get-in (first board) [1])) "got first one right")
      (is (= "destroyer" (get-in (first board) [2])) "got first one right")

      )
    )
  )

(deftest a-test
  (testing "Test board generation"
    (let [result (make-board)]
      (is (= "0" (get-in result [0 0])))
      (is (= "0" (get-in result [0 9])))
      (is (= "0" (get-in result [9 0])))
      (is (= "0" (get-in result [9 9])))
      (is (= nil (get-in result [10 9])))
      )
    )
  )


(deftest placement-test
  (testing "Test horizontal ship placement"
    (let [results (get-coordinates-h 1 1 5)]
      (is (= 5 (count results)) "got the right number of square")
      (is (= [1 1] (first results)) "got first one right")
      (is (= [1 5] (last results)) "got last one right")
      )
    )
  (testing "Test vertical ship placement"
    (let [results (get-coordinates-v 1 2 5)]
      (is (= 5 (count results)) "got the right number of square")
      (is (= [2, 1] (first results)) "got first one right")
      (is (= [6, 1] (last results)) "got last one right")
      )
    )
  (testing "Test actual placement"
    (let [board (place-on-board (make-board) [0 1] "submarine")]
      (is (= "submarine" (get-in board [0 1])))
      (is (f/failed? (is-not-taken board [0 1])))
      (is (= [0 0] (is-not-taken board [0 0])))
      )
    )
  )

(deftest bounds-test
  (testing "Test onboard function"
    (let [board (make-board)]
      (is (= [0 1] (is-on-board board [0, 1])))
      (is (f/failed? (is-on-board board [99 0])))
      )
    )
  )








