(ns rob-learns.core
  (:use clojure.pprint)
  (:require [rob-learns.helpers :refer :all]
            [failjure.core :as f]))

(declare place-on-board get-coordinates-horizontal, validate-move)
(def fleet {:submarine  1
            :destroyer  2
            :cruiser    3
            :battleship 4
            :carrier    5})

(defn make-board
  "I make a 2 d array loaded with 0s"
  []
  (vec (repeat 10 (vec (repeat 10 "0")))))


(defn place-ship-horizontally
  "place a ship horizontally"
  [fleet board x y ship]
  (let [result (f/ok->> ship
       (#(fleet (keyword %)))
       (get-coordinates-horizontal x y)
       (p-print)
       ((partial validate-move board))
       (reduce (fn [a each] (place-on-board a each ship)) board)
       )]

        (when (f/failed? result))
        result
        )

  )

(defn validate-move
  [board [x y]]
  (f/fail "Hello, %s" "Failjure")
  )

(defn get-coordinates-horizontal
  "Get the horizontal coordinates for placing ship"
  [x y length]
  (map #(vec [y %]) (range x (+ x length)))
  )

(defn get-coordinates-vertical
  "Get the horizontal  coordinates for placing ship"
  [x y length]
  (map #(vec [% x]) (range y (+ y length)))
  )

(defn is-on-board?
  "Checks whether a coordinate is on board"
  [board [x y]]
  (if (get-in board [x y]) true false)
  )

(defn is-not-taken?
  "Checks whether a coordinate is taken"
  [board [x y]]
  (if (= "0" (get-in board [x y])) true false)
  )

(defn place-on-board
  "Place a ship on the board"
  [board [x y] ship]
  (assoc-in board [x y] ship)
  )



(println (place-ship-horizontally fleet (make-board) 1 0 "destroyer"))