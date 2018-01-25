(ns rob-learns.game-server
  (:use clojure.pprint)
  (:require [rob-learns.helpers :refer :all]
            [rob-learns.core :refer :all]
            [failjure.core :as f]))


(def player_1 (atom nil))
(def player_2 (atom nil))


(defn set-game-state
  [player board, fleet, msg]
  (reset! player {:board board :fleet fleet :msg msg })

  )

(defn get-game-state
  [player]
  player)

(defn initialize-game
  []
  (set-game-state player_1 (make-board) (fleet) nil)
  (set-game-state player_2 (make-board) (fleet) nil)
  )

(defn place-move
  [state]
  ()
  )



(initialize-game)

(pprint player_2)



