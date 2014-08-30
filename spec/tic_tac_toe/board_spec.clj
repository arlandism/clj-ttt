(ns tic-tac-toe.board-spec
  (:require [speclj.core :refer :all]
            [tic-tac-toe.board :refer :all]))

(describe "board"
  (context "available-spaces"
    (it "returns full board when empty"
      (should= (range 1 10) (available-spaces empty-board)))
    (it "returns spaces not taken with a move made"
     (should= (list 1 3 4 5 6 7 8 9) (available-spaces (make-move empty-board 2 "X"))))
    (it "returns an empty list when the board is full"
      (let [full-board (new-board {1 "X" 2 "O" 3 "X" 4 "O" 5 "X" 6 "O" 7 "X" 8 "O" 9 "X"})]
       (should= (list) (available-spaces full-board)))))

  (context "rows"
    (it "returns the rows of the board when empty"
      (should= (list) (rows empty-board)))
    (it "returns the rows of the board with moves on one row"
      (should= (list '("O")) (rows (make-move empty-board 3 "O"))))
    (it "returns the rows of the board with moves on two rows"
      (should=
        (list '("O") '("X"))
        (rows (new-board {3 "O" 5 "X"}))))
    (it "returns the rows of the board with moves on three rows"
      (should=
        (list '("O") '("X") '("O" "X"))
        (rows (new-board {3 "O" 5 "X" 7 "X" 8 "O"}))))))

