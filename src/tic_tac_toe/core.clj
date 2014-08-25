(ns tic-tac-toe.core
  (:require [tic-tac-toe.rules :refer [game-over? other-token
                                       player-one-token
                                       player-two-token]]
            [tic-tac-toe.board :refer [empty-board make-move]])
  (:gen-class))

(defn next-move [])

(defn play-game
  ([game token]
    (if (game-over?)
      game
      (let [next-token (other-token token)
            move (next-move)]
        (recur
          (make-move game move token)
          (other-token token)))))
  ([] (play-game empty-board player-one-token)))

(defn -main [& args]
  (println "Running"))
