(ns tic-tac-toe.core-spec
  (:require [speclj.core :refer :all]
            [tic-tac-toe.core :refer :all]
            [tic-tac-toe.rules :refer [game-over? player-one]]
            [tic-tac-toe.player :refer [next-move]]))

(describe "playing a game"
  (it "takes a move"
   (let [game-over-returns (atom [false true])]
     (with-redefs [next-move (fn [_ board] 1)
                   game-over? (fn [& _] (let [next-val (first @game-over-returns)]
                                             (swap! game-over-returns rest)
                                             next-val))]
     (should= "X" ((:state (play-game)) 1)))))

  (it "takes two moves"
      (let [moves (atom [1 3])
            game-over-returns (atom [false false true])]
        (with-redefs [next-move (fn [_ board]
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

  (it "takes moves until the game is over"
      (let [moves (atom [1 8 2 5 3])
            game-over-returns (atom [false false false false false true])]
        (with-redefs [next-move (fn [_ board]
                                  (let [next-move (first @moves)]
                                    (swap! moves rest)
                                    next-move))
                      game-over? (fn [& _]
                                  (let [next-val (first @game-over-returns)]
                                    (swap! game-over-returns rest)
                                    next-val))]
          (let [game-state (:state (play-game))]
           (should=
             {1 "X" 8 "O" 2 "X" 5 "O" 3 "X"}
             game-state)))))

  (it "passes the current game state to 'next-move'"
      (let [passed-board (atom nil)
            game-over-returns (atom [false true])]
        (with-redefs [next-move (fn [_ board] (reset! passed-board board))
                      game-over? (fn [& _]
                                  (let [next-val (first @game-over-returns)]
                                    (swap! game-over-returns rest)
                                    next-val))]
          (play-game)
          (should= {} @passed-board))))

  (it "passes the current player to 'next-move'"
      (let [passed-player (atom nil)
            game-over-returns (atom [false true])]
        (with-redefs [next-move (fn [player board] (reset! passed-player player))
                      game-over? (fn [& _]
                                  (let [next-val (first @game-over-returns)]
                                    (swap! game-over-returns rest)
                                    next-val))]
          (play-game)
          (should= player-one @passed-player)))))
