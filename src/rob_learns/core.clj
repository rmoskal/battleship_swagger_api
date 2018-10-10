(ns rob-learns.core
  (:use clojure.pprint)
  (:require [rob-learns.helpers :refer :all]
            [failjure.core :as f]))

(declare place-on-board is-on-board is-not-taken validate-move, validate-placed validate-placable shell
         mark-sunk mark-defender mark-hit is-setup)

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
           (validate-placable fleet)
           (validate-placed inventory)
           (function x y)
           (validate-many (partial validate-move board))
           (reduce (fn [a each] (place-on-board a each ship)) board)))

(defn attack-defender
  "Attack the board"
  [attacker defender [x y]]
  (f/if-let-failed? [result
                   (f/ok->> [x y]
                            (is-setup defender (name `defender))
                            (is-setup attacker  (name `attacker))
                            (is-on-board (:board defender))
                            (shell defender) )]
   (assoc defender  :status {:error (:message result)})
                    result))




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

(defn is-setup
  "Checks whether a user is setup"
  [user name [x y]]
  (pprint  (:inventory user))
  (if (empty? (:inventory user)) [x y]
                           (f/fail "%s is not set up" name)))


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


(defn shell
  "shell me a ship, decrement the hit counter if hit "
  [state [x y]]
  (let [loc (get-in (:board state) [x y])]
    (cond
      (= loc "0") (assoc state :status {:success "Miss"} :board (assoc-in (:board state) [x y] 1))
      (= loc "1") (f/fail "%s %s  is already been called" x y)
      :else (let [state (assoc state :board (assoc-in (:board state) [x y] "1")
                                     :fleet (update (:fleet state) (keyword loc) dec)
                                     :status {:success (str "Hit " loc)})]
             (mark-sunk state loc)
             ))))



(defn mark-sunk
  [state loc]
  (if (not-any? #(= loc %) (flatten (:board state)))
    (assoc state :status {:success (str "Sunk " loc)})
    state))









