(ns tic-tac-toe.rules-spec
  (:require [speclj.core :refer :all]
            [tic-tac-toe.board :refer [columns new-board]]
            [tic-tac-toe.rules :refer :all]))

(describe "other-token"
  (it "returns other player's token"
      (should= player-one-token (other-token player-two-token))
      (should= player-two-token (other-token player-one-token))))

(describe "other-player"
  (it "returns other player"
    (should= player-one (other-player player-two))
    (should= player-two (other-player player-one))))

(describe "winner-in-set"
  (it "returns true if every item is player one's token"
    (should
      (winner-in-set
        (list player-one-token player-one-token player-one-token))))

  (it "returns true if every item is player two's token"
    (should
      (winner-in-set
        (list player-two-token player-two-token player-two-token))))

  (it "returns false if items are heterogenous"
    (should-not
      (winner-in-set
        (list player-two-token player-one-token player-two-token))))

  (context "given a size restriction"
    (it "returns false if count of items doesn't equal size"
      (should-not
        (winner-in-set (list "O" "O" "O") 2)))
    (it "returns true if count of items is correct and match"
      (should
        (winner-in-set (list "O" "O" "O") 3)))))

(describe "winner"
  (context "row winner"
    (it "returns the winning token"
      (let [board (new-board {1 "X" 2 "X" 3 "X" 4 "O" 5 "O"})]
        (should= "X" (winner board)))))

  (context "column winner"
    (it "returns the winning token"
      (let [board (new-board {1 "X" 2 "X" 3 "O" 4 "X" 6 "O" 9 "O"})]
        (should= "O" (winner board)))))

  (context "diagonal winner"
    (it "returns the winning token"
      (let [board (new-board {1 "X" 2 "O" 3 "O" 4 "O" 5 "X" 6 "O" 7 "O" 8 "O" 9 "X"})]
       (should= "X" (winner board))))))
