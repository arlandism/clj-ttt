(ns tic-tac-toe.core
  (:gen-class))

(def player-one-token "X")

(defn play-game []
  (let [first-move (Integer. (read-line)) 
        state {:state {first-move player-one-token}}]
    state))

(defn -main [& args]
  (println "Running"))
