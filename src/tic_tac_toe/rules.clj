(ns tic-tac-toe.rules
  (:require [tic-tac-toe.board :refer [board-size rows columns diagonals]]))

(def player-one :human)

(def player-two :ai)

(def player-one-token "X")

(def player-two-token "O")

(defn game-over? [])

(defn other-token [token]
 (if (= token player-one-token)
  player-two-token
  player-one-token))

(defn other-player [player]
  (if (= player-one player)
    player-two
    player-one))

(defn winner-in-set
  ([set]
  (or
    (every? #(= player-one-token %) set)
    (every? #(= player-two-token %) set)))
  ([set size-restriction]
    (and
      (= size-restriction (count set))
      (winner-in-set set))))

(defn- winner-in-any? [section]
  (some #(winner-in-set % board-size) section))

(defn- get-winning-token [section]
  (first
    (first
      (filter #(winner-in-set % board-size) section))))

(defn winner [board]
  (let [rows (rows board)
        columns (columns board)
        diagonals (diagonals board)]
   (cond
     (winner-in-any? rows) (get-winning-token rows)
     (winner-in-any? columns) (get-winning-token columns)
     (winner-in-any? diagonals) (get-winning-token diagonals))))
