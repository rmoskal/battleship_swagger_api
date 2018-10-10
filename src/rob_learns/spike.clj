(defn fizzBuzz [each]
  (cond
    (and (zero? (mod each 3)) (zero? (mod each 5))) "FizzBuzz"
    (zero? (mod each 3)) "Fizz"
    (zero? (mod each 5)) "Buzz"
    :else each
    )
    )

(->>(read-line)                 ; Reading input from STDIN
   read-string
   range
   rest
   (map fizzBuzz)
   (clojure.string/join "\n")
   println)
