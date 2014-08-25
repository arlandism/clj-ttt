(ns tic-tac-toe.board)

(def empty-board {:state {}})

(defn make-move [current-state move token]
  (assoc-in current-state [:state move] token))
