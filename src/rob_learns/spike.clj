(defn fib-step [[a b]]
  [b (+ a b)])

(defn fib-seq []
  (map first (iterate fib-step [0 1])))

(println(take 20 (fib-seq)))

