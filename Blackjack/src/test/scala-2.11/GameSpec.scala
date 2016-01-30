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
      val playersInitialHand = dealInitialHand(3, 4)

      val handWithState = Game.playerTakesTurn(playersInitialHand)

      handWithState.state shouldBe Stick
      assert(handWithState.hand.score >= 17)
    }
    "stops drawing cards if their score is 17 or higher" in {
      val playersHandOf17 = dealInitialHand(10, 7)

      val playersHandAfterTurn = Game.playerTakesTurn(playersHandOf17)

      playersHandAfterTurn.state shouldBe Stick
      playersHandAfterTurn.hand.score shouldBe playersHandOf17.hand.score
    }
    "stops drawing cards if their score is greater than 17" in {
      val playersHandGreaterThan17 = dealInitialHand(10, 9)

      val playersHandAfterTurn = Game.playerTakesTurn(playersHandGreaterThan17)

      playersHandAfterTurn.state shouldBe Stick
      playersHandAfterTurn.hand.score shouldBe playersHandGreaterThan17.hand.score
    }
    "looses the game if their score is over 21" in {
      val playersHandGreaterThan21 = dealInPlayHand(3 :: 5 :: 6 :: 10 :: Nil)

      val handWithState = Game.playerTakesTurn(playersHandGreaterThan21)

      handWithState.state shouldBe Lost
      assert(handWithState.hand.score > 21)
    }
  }
  "The dealer" - {
    " can start drawing cards when the player has finished drawing cards and has not lost" in {
      val dealersHand = dealInitialHand(1, 2)
      val playersHand = dealStickHand
      val gameState = Game.dealerDrawsACard(dealersHand, playersHand)

      gameState.dealer.state shouldBe InPlay
      gameState.dealer.hand.cards shouldBe dealersHand.hand.cards + 1

      gameState.player shouldBe playersHand
    }
  }

  private def dealInitialHand(card1: Int, card2: Int): HandWithState =
    HandWithState(Hand(card1 :: card2 :: Nil), InPlay)

  private def dealInPlayHand(cards: List[Int]): HandWithState =
    HandWithState(Hand(cards), InPlay)

  private def dealStickHand: HandWithState =
    HandWithState(Hand(10 :: 3 :: 5 :: Nil), Stick)
}