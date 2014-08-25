(ns tic-tac-toe.core-spec
  (:require [speclj.core :refer :all]
            [tic-tac-toe.core :refer :all]
            [tic-tac-toe.rules :refer [game-over?]]))

(describe "playing a game"
  (it "takes a move"
   (let [game-over-returns (atom [false true])]
     (with-redefs [next-move (fn [& _] 1)
                   game-over? (fn [& _] (let [next-val (first @game-over-returns)]
                                             (swap! game-over-returns rest)
                                             next-val))]
     (should= "X" ((:state (play-game)) 1)))))

  (it "takes two moves"
      (let [moves (atom [1 3])
            game-over-returns (atom [false false true])]
        (with-redefs [next-move (fn [& _]
                                  (let [next-move (first @moves)]
                                    (swap! moves rest)
                                    next-move))
                      game-over? (fn [& _]
                                  (let [next-val (first @game-over-returns)]
                                    (swap! game-over-returns rest)
                                    next-val))]
          (let [game-state (:state (play-game))]
           (should= "X" (game-state 1))
           (should= "O" (game-state 3))))))

  (xit "takes move until the game is over"
      (let [moves (atom [1 8 2 5 3])
            game-over-returns (atom [false false false true])]
        (with-redefs [next-move (fn [& _]
                                  (let [next-move (first @moves)]
                                    (swap! moves rest)
                                    next-move))
                      game-over? (fn [& _] false)]
          (let [game-state (:state (play-game))]
           (should= "X" (game-state 1))
           (should= "O" (game-state 3)))))))
