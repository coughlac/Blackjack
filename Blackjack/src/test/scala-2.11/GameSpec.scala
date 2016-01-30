import org.scalatest.{FreeSpec, Matchers}

class GameSpec extends FreeSpec with Matchers {
  "The game is won if either of the two initial hands is blackjack " in {
    Game.isWon(Hand(10, 3), Hand(10, 11)) shouldBe true
    Game.isWon(Hand(11, 10), Hand(10, 1)) shouldBe true

    Game.isWon(Hand(9, 10), Hand(10, 1)) shouldBe false
  }

  "The player " - {
    "can start drawing cards if neither player has blackjack" in {
      val dealersInitialHand = Hand(1, 2)
      val playersInitialHand = Hand(3, 4)

      val (_, playersHand) = Game.playerDrawsACard(dealersInitialHand, playersInitialHand)

      playersHand.cards should equal(playersInitialHand.cards + 1)
    }
    "cannot start drawing cards if the player already has blackjack" in {
      val dealersInitialHand = Hand(1, 2)
      val blackjackHand = Hand(11, 10)

      val (_, playersBlackjackHand) = Game.playerDrawsACard(dealersInitialHand, blackjackHand)

      playersBlackjackHand.cards shouldEqual blackjackHand.cards
    }
    "cannot start drawing cards if the dealer already has blackjack" in {
      val blackjackHand = Hand(11, 10)
      val playersInitialHand = Hand(3, 4)

      val (dealersBlackjackHand, _) = Game.playerDrawsACard(blackjackHand, playersInitialHand)

      dealersBlackjackHand.cards shouldEqual blackjackHand.cards
    }
  }
}