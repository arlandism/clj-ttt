(ns tic-tac-toe.core
  (:require [tic-tac-toe.rules :refer [game-over? other-token other-player
                                       player-one player-one-token]]
            [tic-tac-toe.board :refer [empty-board make-move]]
            [tic-tac-toe.player :refer [next-move]])
  (:gen-class))

(defn play-game
  ([game player token]
    (if (game-over?)
      game
      (let [next-token (other-token token)
            next-player (other-player player)
            move (next-move player (:state game))]
        (recur
          (make-move game move token) next-player next-token))))
  ([] (play-game empty-board player-one player-one-token)))

(defn -main [& args]
  (println "Running"))
