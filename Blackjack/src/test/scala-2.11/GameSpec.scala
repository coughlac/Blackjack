import org.scalatest.{FreeSpec, Matchers}

class GameSpec extends FreeSpec with Matchers {
  "The game is won if either of the two initial hands is blackjack " in {
    Game.isWon(Hand(10 :: 3 :: Nil), Hand(10 :: 11 :: Nil)) shouldBe true
    Game.isWon(Hand(11 :: 10 :: Nil), Hand(10 :: 1 :: Nil)) shouldBe true

    Game.isWon(Hand(9 :: 10 :: Nil), Hand(10 :: 1 :: Nil)) shouldBe false
  }

  "The player " - {
    "can start drawing cards if neither player has blackjack" in {
      val dealersInitialHand = Hand(1 :: 2 :: Nil)
      val playersInitialHand = Hand(3 :: 4 :: Nil)

      val (_, playersHand) = Game.playerDrawsACard(dealersInitialHand, playersInitialHand)

      playersHand.cards should equal(playersInitialHand.cards + 1)
    }
    "cannot start drawing cards if the player already has blackjack" in {
      val dealersInitialHand = Hand(1 :: 2 :: Nil)
      val blackjackHand = Hand(11 :: 10 :: Nil)

      val (_, playersBlackjackHand) = Game.playerDrawsACard(dealersInitialHand, blackjackHand)

      playersBlackjackHand.cards shouldEqual blackjackHand.cards
    }
    "cannot start drawing cards if the dealer already has blackjack" in {
      val blackjackHand = Hand(11 :: 10 :: Nil)
      val playersInitialHand = Hand(3 :: 4 :: Nil)

      val (dealersBlackjackHand, _) = Game.playerDrawsACard(blackjackHand, playersInitialHand)

      dealersBlackjackHand.cards shouldEqual blackjackHand.cards
    }
    "draws cards until their score is 17 or higher" in {
      val playersInitialHand = Hand(3 :: 4 :: Nil)

      assert(Game.playerTakesTurn(playersInitialHand).score >= 17)
    }
    "stops drawing cards if their score is 17 or higher" in {
      val playersHandOf17 = Hand(3 :: 4 :: 10 :: Nil)
      playersHandOf17 shouldBe Game.playerTakesTurn(playersHandOf17)

      val playersHandGreaterThan17 = Hand(5 :: 4 :: 10 :: Nil)
      playersHandGreaterThan17 shouldBe Game.playerTakesTurn(playersHandGreaterThan17)
    }
  }
}