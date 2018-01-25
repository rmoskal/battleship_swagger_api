(ns rob-learns.core
  (:use clojure.pprint)
  (:require [rob-learns.helpers :refer :all]
            [failjure.core :as f]
            [rob-learns.core :refer :all]))

(declare place-on-board  is-on-board is-not-taken validate-move)

(defn fleet
  []
  {:submarine  1
            :destroyer  2
            :cruiser    3
            :battleship 4
            :carrier    5})


(defn place-ship
  "place a ship horizontally"
  [function fleet board x y ship]
  (f/ok->> ship
           (#(fleet (keyword %)))
           (function x y)
           ((partial validate-many (partial validate-move board)))
           (reduce (fn [a each] (place-on-board a each ship)) board)
           )
  )



(defn attack
  "Attack the board"
  [board [x y]]
  (f/ok->> [x y]
           (is-on-board board)

           )
  )

(defn make-board
  "I make a 2 d array loaded with 0s"
  []
  (vec (repeat 10 (vec (repeat 10 "0")))))


(defn validate-move
  [board [x y]]
  (f/ok->> [x y]
           (is-not-taken board)
           (is-on-board board)
           )
  )

(defn get-coordinates-h
  "Get the horizontal coordinates for placing ship"
  [x y length]
  (map #(vec [y %]) (range x (+ x length)))
  )

(defn get-coordinates-v
  "Get the horizontal  coordinates for placing ship"
  [x y length]
  (map #(vec [% x]) (range y (+ y length)))
  )

(defn is-on-board
  "Checks whether a coordinate is on board"
  [board [x y]]
  (if (get-in board [x y]) [x y]
                           (f/fail "%s %s  is not on board" x y))
  )

(defn is-not-taken
  "Checks whether a coordinate is taken"
  [board [x y]]
  (if (= "0" (get-in board [x y])) [x y]
                                   (f/fail "%s %s  is not on board" x y)
                                   ))

(defn place-on-board
  "Place a ship on the board"
  [board [x y] ship]
  (assoc-in board [x y] ship)
  )

(pprint (attack (make-board) [1 1]))