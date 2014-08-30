(ns tic-tac-toe.rules-spec
  (:require [speclj.core :refer :all]
            [tic-tac-toe.board :refer [new-board]]
            [tic-tac-toe.rules :refer :all]))

(describe "other-token"
  (it "returns other player's token"
      (should= player-one-token (other-token player-two-token))
      (should= player-two-token (other-token player-one-token))))

(describe "other-player"
  (it "returns other player"
    (should= player-one (other-player player-two))
    (should= player-two (other-player player-one))))

(describe "winner"
  (context "row winner"
    (xit "returns the winning token"
      (let [board (new-board {1 "X" 2 "X" 3 "X"})]
        (should= "X" (winner board)))))

  (context "column winner"
    (xit "returns the winning token"
      (let [board (new-board {3 "O" 6 "O" 9 "O"})]
        (should= "O" (winner board))))))
