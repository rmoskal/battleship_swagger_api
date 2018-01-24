(ns rob-learns.game-server
  (:use clojure.pprint)
  (:require [rob-learns.helpers :refer :all]
            [rob-learns.core :refer :all]
            [failjure.core :as f]))



(def board_player_1 (make-board))
(def board_player_2 (make-board))

