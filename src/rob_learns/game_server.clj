(ns rob-learns.game-server
  (:use clojure.pprint)
  (:require [rob-learns.helpers :refer :all]
            [rob-learns.core :refer :all]
            [failjure.core :as f]))


(def player_1 (atom nil))
(def player_2 (atom nil))

(def direction {:left get-coordinates-h :right get-coordinates-v})


(defn set-game-state
  [player fleet msg board]
  (reset! player {:board board :fleet fleet :msg msg })

  )

(defn get-game-state
  [player]
  player)

(defn initialize-game
  []
  (set-game-state player_1  (fleet) nil (make-board))
  (set-game-state player_2  (fleet) nil (make-board))
  )

(defn place-move
  [state ship direction x y]
  ;(pprint [direction (:fleet @state) (:board @state)])
  (let [result
  (f/ok->> ship
           ((partial place-ship direction (:fleet @state) (:board @state) x y))
           (p-print)
           ((partial set-game-state state (dissoc (:fleet @state) (keyword ship))
                     "Placed a piece." ))
           ) ]
    (if (f/failed? result) pprint "fail"))
  )


(initialize-game)
(place-move player_1 "destroyer"  get-coordinates-h 0 1)
(place-move player_1 "destroyer"  get-coordinates-h 0 1)


;(pprint player_1)

;(pprint(place-ship get-coordinates-h (:fleet @player_1) (:board @player_1) 0 1"destroyer"))



