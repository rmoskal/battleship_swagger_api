(defn _foo
  [arr]
  (loop [working arr res []]
    (if (empty? working) res
                         (if (= (first working) (second working)) (recur (drop 2 working) res)
                                                                  (recur (rest working) (conj res (first working)))))
    ))


(defn foo
  [arr]
  (loop [current arr]
    (println current)
    (if (apply distinct? current) current
                                  (recur (_foo current)))
    ))



(let [in (slurp *in*)]
  (println (clojure.string/split in #"")))






