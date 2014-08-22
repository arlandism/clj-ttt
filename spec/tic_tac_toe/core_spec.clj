(ns tic-tac-toe.core-spec
  (:require [speclj.core :refer :all]
            [tic-tac-toe.core :refer :all]))

(describe "playing a game"
  (it "takes a move"
    (with-in-str "1"
     (should= "X" ((:state (play-game)) 1) ))))
