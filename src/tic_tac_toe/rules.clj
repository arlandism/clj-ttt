(ns tic-tac-toe.rules)

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

(defn winner [board]
  "X")
