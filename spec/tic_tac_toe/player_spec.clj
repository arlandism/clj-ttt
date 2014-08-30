(ns tic-tac-toe.player-spec
  (:require [speclj.core :refer :all]
            [tic-tac-toe.player :refer :all]
            [tic-tac-toe.rules :refer [game-over? winner]]))

(describe "minimax"
  (it "takes an immediate win"
    (let [game-over-returns (atom [true])]
     (with-redefs [game-over? (fn [board] (let [next-val (first @game-over-returns)]
                                             (swap! game-over-returns rest)
                                             next-val))
                   winner (fn [board] "O")]
     (let [game-state {:state {1 "O" 2 "O"}}]
      (should= 3 (next-move :ai game-state)))))))
