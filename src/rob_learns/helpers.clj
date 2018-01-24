(ns rob-learns.helpers
  (:use clojure.pprint))

(require '[failjure.core :as f])

(defn failjureize [message fn val]
  (if (fn val)
    val
    (f/fail message val)))

(defn have-failure
  "Looks at a collection and returns a failure monad if there is one"
  [coll]
  (first (filter f/failed? coll))
)

(defn p-print
  [_in]
  (pprint _in)
  _in
  )

(defn validate-many
  "Allows you to map over a series of values and to test them using failjure
  It returns the first error found. It seems to me that this must be unidiomatic"
  [validator coll]
  (let [result (map validator coll)]
    (if (f/failed? (have-failure result)) (have-failure result) result)
    )
  )