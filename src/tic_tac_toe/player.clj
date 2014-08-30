(ns tic-tac-toe.player
  (:require [tic-tac-toe.rules :refer [game-over? winner]]))

(defmulti next-move (fn [player _] player))

(defmethod next-move :ai [_ game-state]
  (if (game-over? game-state)
    3
    nil))

(defn- score-game [game]
  (when (= "O" (winner game) 1)))

(defn- score-moves [game-state]
  (when (game-over? game-state)
    (score-game game-state)))
