(ns tic-tac-toe.rules-spec
  (:require [speclj.core :refer :all ]
            [tic-tac-toe.rules :refer :all]))

(describe "other-token"
  (it "returns other player's token"
      (should= player-one-token (other-token player-two-token))
      (should= player-two-token (other-token player-one-token))))

