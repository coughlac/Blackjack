import org.scalatest.{FreeSpec, Matchers}

class GameSpec extends FreeSpec with Matchers {
  "The game is won if either of the two initial hands is blackjack " in {
    Game.blackjack(dealInitialHand(10, 3), dealInitialHand(10, 11)).state shouldBe Over
    Game.blackjack(dealInitialHand(11, 10), dealInitialHand(10, 1)).state shouldBe Over

    Game.blackjack(dealInitialHand(9, 10), dealInitialHand(10, 1)).state shouldBe Playing
  }

  "The player " - {
    "can start drawing cards if neither player has blackjack" in {
      val dealersInitialHand = dealInitialHand(1, 2)
      val playersInitialHand = dealInitialHand(3, 4)

      val gameWithState = Game.play(dealersInitialHand, playersInitialHand)

      gameWithState.state should be(Playing)
      assert(gameWithState.player.hand.cards > playersInitialHand.hand.cards)
    }
    "cannot start drawing cards if the player already has blackjack" in {
      val dealersInitialHand = dealInitialHand(1, 2)
      val blackjackHand = dealInitialHand(11, 10)

      val gameWithState = Game.play(dealersInitialHand, blackjackHand)

      gameWithState.state should be(Over)

      gameWithState.dealer.state should be(Lost)
      gameWithState.player.state should be(Won)
    }
    "cannot start drawing cards if the dealer already has blackjack" in {
      val blackjackHand = dealInitialHand(11, 10)
      val playersInitialHand = dealInitialHand(3, 4)

      val gameWithState = Game.play(blackjackHand, playersInitialHand)

      gameWithState.state should be(Over)

      gameWithState.dealer.state should be(Won)
      gameWithState.player.state should be(Lost)
    }
    "draws cards until their score is 17 or higher" in {
      val playersInitialHand = dealInitialHand(3, 4)

      val handWithState = Player.takesTurn(playersInitialHand)

      assert(handWithState.score >= 17)
    }
    "stops drawing cards if their score is 17 or higher" in {
      val playersHandOf17 = dealInitialHand(10, 7)

      val playersHandAfterTurn = Player.takesTurn(playersHandOf17)

      playersHandAfterTurn.state shouldBe Stick
      playersHandAfterTurn.score shouldBe playersHandOf17.score
    }
    "stops drawing cards if their score is greater than 17" in {
      val playersHandGreaterThan17 = dealInitialHand(10, 9)

      val playersHandAfterTurn = Player.takesTurn(playersHandGreaterThan17)

      playersHandAfterTurn.state shouldBe Stick
      playersHandAfterTurn.score shouldBe playersHandGreaterThan17.score
    }
    "looses the game if their score is over 21" in {
      val playersHandGreaterThan21 = dealInPlayHand(3 :: 5 :: 6 :: 10 :: Nil)

      val handWithState = Player.takesTurn(playersHandGreaterThan21)

      handWithState.state shouldBe Lost
      assert(handWithState.score > 21)
    }
  }
  "The dealer" - {
    "can start drawing cards when the player has finished drawing cards and has not lost" in {
      val dealersHand = dealInitialHand(1, 2)
      val playersHand = dealStickHand
      val gameState = Dealer.takesATurn(GameWithState(dealersHand, playersHand, Playing))

      assert(gameState.dealer.score > dealersHand.score)

      gameState.player.score shouldBe playersHand.score
    }
    "cannot start drawing cards when the player has finished drawing cards but has lost" in {
      val dealersHand = dealInitialHand(1, 2)
      val playersHand = dealLostHand
      val gameState = Dealer.takesATurn(GameWithState(dealersHand, playersHand, Playing))

      gameState.dealer.score shouldBe dealersHand.score
      gameState.state shouldBe Over
    }
    "stops drawing cards when their total is higher than the Player" in {
      val dealersHand = dealInPlayHand(1 :: 11 :: 7 :: Nil)
      val playersHand = dealStickHand
      val gameState = Dealer.takesATurn(GameWithState(dealersHand, playersHand, Playing))

      gameState.dealer.score shouldBe dealersHand.score
      gameState.player.score shouldBe playersHand.score
      gameState.state shouldBe Over
    }
  }

  private def dealInitialHand(card1: Int, card2: Int): HandWithState =
    HandWithState(Hand(card1 :: card2 :: Nil), InPlay)

  private def dealInPlayHand(cards: List[Int]): HandWithState =
    HandWithState(Hand(cards), InPlay)

  private def dealStickHand: HandWithState =
    HandWithState(Hand(10 :: 3 :: 5 :: Nil), Stick)

  private def dealLostHand: HandWithState =
    HandWithState(Hand(10 :: 7 :: 5 :: Nil), Lost)
}