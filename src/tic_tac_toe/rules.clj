(ns tic-tac-toe.rules)

(def player-one-token "X")

(def player-two-token "O")

(defn game-over? [])

(defn other-token [token]
 (if (= token player-one-token)
  player-two-token
  player-one-token))
