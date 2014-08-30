(ns tic-tac-toe.board)

(def empty-board {:state {}})

(def board-size 3)

; could memoize below
(defn- all-moves []
  (range 1 (+ 1 (* board-size board-size))))

(defn open-move? [taken-moves move]
  (let [taken? (not (nil? (some #{move} taken-moves)))]
   (not taken?)))

(defn available-spaces [board]
  (let [current-state (:state board)
        taken-moves (keys current-state)]
    (filter  #(open-move? taken-moves %) (all-moves)) ))

(defn make-move [current-state move token]
  (assoc-in current-state [:state move] token))

(defn new-board [initial-state]
  (assoc {} :state initial-state))
