(ns rob-learns.game-server
  (:use clojure.pprint)
  (:require [rob-learns.helpers :refer :all]
            [rob-learns.core :refer :all]
            [failjure.core :as f]))


(def player_1 (atom nil))
(def player_2 (atom nil))

(def direction {:h get-coordinates-h :v get-coordinates-v})


(defn set-game-state
  [player inventory fleet status board]
  (reset! player {:board board :inventory inventory :fleet fleet :status status}))



(defn get-game-state
  [player]
  player)

(defn initialize-game
  []
  (set-game-state player_1  (fleet) (fleet) nil (make-board))
  (set-game-state player_2  (fleet) (fleet) nil (make-board)))


(defn place-move
  [state ship direction x y]
  (let [result
        (f/ok->> ship
                 ((partial place-ship direction  (:fleet @state) (:inventory @state) (:board @state) x y))
                 ((partial set-game-state state (dissoc (:inventory @state) (keyword ship)) (:fleet @state)
                            {:success"Placed a piece."})))]

    (if (f/failed? (swap! state assoc :status {:error result})) result)))



(initialize-game)
(place-move player_1 "destroyer" (:h direction) 0 1)
(pprint player_1)
(place-move player_1 "submarine"  (:h direction) 2 1)
(pprint player_1)
(place-move player_1 "carrier"  (:v direction)  0 2)
(pprint player_1)
(place-move player_1 "carrier"  (:v direction)  0 2)
(pprint player_1)
(place-move player_1 "harrier"  (:v direction)  0 2)
(pprint player_1)
(place-move player_1 "cruiser"  (:v direction)  3 2)
(pprint player_1)
(place-move player_1 "battleship"  (:h direction) 0 9)
(pprint player_1)
(place-move player_1 "battleship"  (:h direction) 0 9)
(pprint player_1)


