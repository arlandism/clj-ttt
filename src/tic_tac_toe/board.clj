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

(defmacro generate-sector [generator board]
  `(if (vals (:state ~board))
    (for [sector# ~generator
          :let [sector-values# (vals (select-keys (:state ~board) sector#))]
          :when (not (nil? sector-values#))]
     sector-values#)
    (list)))

(defn rows [board]
  (generate-sector
    (partition board-size (range 1 (+ 1 (* board-size board-size))))
    board))

(defn columns [board]
    (let [column-beginnings (range 1 (+ 1 board-size))]
     (generate-sector
      (map (fn [start]
             (range start (+ 1 (* board-size board-size)) board-size))
             column-beginnings)
      board)))

(defn diagonals [board]
  (let [diagonal-one (range 1 (+ 1 (* board-size board-size)) (+ 1 board-size))
        diagonal-two (range board-size (* board-size board-size) (- board-size 1)) ]
    (generate-sector (list diagonal-one diagonal-two) board)))
