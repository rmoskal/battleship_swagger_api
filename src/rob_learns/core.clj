(ns rob-learns.core
  (:use clojure.pprint)
  (:require [rob-learns.helpers :refer :all]
            [failjure.core :as f]
            [rob-learns.core :refer :all]))

(declare place-on-board is-on-board is-not-taken validate-move, validate-placed validate-placable shell
         mark-sunk mark-defender mark-hit)

(defn fleet
  []
  {:submarine  1
   :destroyer  2
   :cruiser    3
   :battleship 4
   :carrier    5})


(defn place-ship
  "place a ship horizontally"
  [function fleet inventory board x y ship]
  (f/ok->> ship
           ((partial validate-placable fleet))
           ((partial validate-placed inventory))
           (function x y)
           ((partial validate-many (partial validate-move board)))
           (reduce (fn [a each] (place-on-board a each ship)) board)))

(defn attack-defender
  "Attack the board"
  [ defender [x y]]
  (f/ok->> [x y]
           (is-on-board (get defender :board))
           (mark-defender (get defender :board))
           (merge defender)
           (mark-sunk)
           )

  )


(defn mark-defender
  [board [x y]]
  (let [res (->> [x y]
                 (shell board)
                 (mark-hit board [x y])
                 )]
    res
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
           (is-on-board board)))


(defn get-coordinates-h
  "Get the horizontal coordinates for placing ship"
  [x y length]
  (map #(vec [y %]) (range x (+ x length))))


(defn get-coordinates-v
  "Get the horizontal  coordinates for placing ship"
  [x y length]
  (map #(vec [% x]) (range y (+ y length))))


(defn is-on-board
  "Checks whether a coordinate is on board"
  [board [x y]]
  (if (get-in board [x y]) [x y]
                           (f/fail "%s %s  is not on board" x y)))

(defn validate-placed
  [fleet ship]
  (if (fleet (keyword ship)) (fleet (keyword ship))
                             (f/fail "%s is already place" ship)))

(defn validate-placable
  [inventory ship]
  (if (inventory (keyword ship)) ship (f/fail "%s is not a valid ship" ship)))

(defn is-not-taken
  "Checks whether a coordinate is taken"
  [board [x y]]
  (if (= "0" (get-in board [x y])) [x y]
                                   (f/fail "%s %s  is taken" x y)))

(defn place-on-board
  "Place a ship on the board"
  [board [x y] ship]
  (assoc-in board [x y] ship))

(defn mark-hit
  "Place a ship on the board"
  [board [x y] ship]
  (pprint ship)
  ( if ship
    {:board (assoc-in board [x y] 1) :ship ship :message "Hit" }
    {:board (assoc-in board [x y] 1) :ship ship :message "Miss"}
    )
  )



(defn shell
  "mark a position"
   [board [x y]]
  (pprint board)
  (let [loc (get-in board [x y])]
    (cond
    (=  loc "0") false
    (= loc "1") false
    :else loc))
  )

(defn mark-sunk
  [{:keys [ship board fleet]}]
  (if (not-any? #( = ship %) (flatten board))
    {:ship ship :board board :fleet (update-in fleet  [(keyword ship)] dec ) :message "Sunk"} {:keys [ship board fleet]}

    )
)





