(ns rob-learns.helpers
  (:use clojure.pprint))

(require '[failjure.core :as f])

(defn failjureize [message fn val]
  (if (fn val)
    val
    (f/fail message val)))