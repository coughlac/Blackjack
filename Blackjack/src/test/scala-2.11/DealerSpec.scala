import org.scalatest.{FreeSpec, Matchers}

import scalaz.NonEmptyList

class DealerSpec extends FreeSpec with Matchers {
  "A dealer" - {
    "can deal a card to himself when" - {
      "a player's hand has 'stopped' and a dealer's hand is 'in play'" in {
        val playersHand = HandWithState(Hand(NonEmptyList(15, 2)), STOPPED)
        val dealersHand = HandWithState(Hand(NonEmptyList(1, 2)), IN_PLAY)

        val gameState = Game.dealerTakesACard(GameState(dealersHand, playersHand))

        gameState.dealer.state should be(IN_PLAY)
        gameState.dealer.hand.count should be(dealersHand.hand.count + 1)

        gameState.player should be(playersHand)
      }
    }
    "cannot deal a card when " - {
      "the dealer's hand is in a state of " - {
        "blackjack" in {
          val playersHand = HandWithState(Hand(NonEmptyList(1, 2)), STOPPED)
          val dealersHand = HandWithState(Hand(NonEmptyList(1, 2)), BLACKJACK)

          val originalGameState: GameState = GameState(dealersHand, playersHand)

          Game.dealerTakesACard(originalGameState) should be(originalGameState)
        }
        "lost" in {
          val playersHand = HandWithState(Hand(NonEmptyList(1, 2)), STOPPED)
          val dealersHand = HandWithState(Hand(NonEmptyList(1, 2)), LOST)

          val originalGameState: GameState = GameState(dealersHand, playersHand)

          Game.dealerTakesACard(originalGameState) should be(originalGameState)
        }
        "won" in {
          val playersHand = HandWithState(Hand(NonEmptyList(1, 2)), STOPPED)
          val dealersHand = HandWithState(Hand(NonEmptyList(1, 2)), WON)

          val originalGameState: GameState = GameState(dealersHand, playersHand)

          Game.dealerTakesACard(originalGameState) should be(originalGameState)
        }
      }
      "the player's hand is in a state of" - {
        "blackjack" in {
          val playersHand = HandWithState(Hand(NonEmptyList(1, 2)), BLACKJACK)
          val dealersHand = HandWithState(Hand(NonEmptyList(1, 2)), IN_PLAY)

          val originalGameState: GameState = GameState(dealersHand, playersHand)

          Game.dealerTakesACard(originalGameState) should be(originalGameState)
        }
        "lost" in {
          val playersHand = HandWithState(Hand(NonEmptyList(1, 2)), LOST)
          val dealersHand = HandWithState(Hand(NonEmptyList(1, 2)), IN_PLAY)

          val originalGameState: GameState = GameState(dealersHand, playersHand)

          Game.dealerTakesACard(originalGameState) should be(originalGameState)
        }
        "won" in {
          val playersHand = HandWithState(Hand(NonEmptyList(1, 2)), WON)
          val dealersHand = HandWithState(Hand(NonEmptyList(1, 2)), IN_PLAY)

          val originalGameState: GameState = GameState(dealersHand, playersHand)

          Game.dealerTakesACard(originalGameState) should be(originalGameState)
        }
        "in play" in {
          val playersHand = HandWithState(Hand(NonEmptyList(1, 2)), IN_PLAY)
          val dealersHand = HandWithState(Hand(NonEmptyList(1, 2)), IN_PLAY)

          val originalGameState: GameState = GameState(dealersHand, playersHand)

          Game.dealerTakesACard(originalGameState) should be(originalGameState)
        }
      }
    }
    "loses when their hand has a score of more than 21" in {
      val playersHand = HandWithState(Hand(NonEmptyList(15, 2)), STOPPED)
      val dealersHand = HandWithState(Hand(NonEmptyList(11, 11)), IN_PLAY)

      val gameState = Game.dealerTakesACard(GameState(dealersHand, playersHand))

      gameState.dealer.state should be(LOST)
      gameState.dealer.hand.count should be(dealersHand.hand.count)

      gameState.player should be(playersHand)
    }
    "wins when their hand has a score greater than the player and not greater than 21" in {
      val playersHand = HandWithState(Hand(NonEmptyList(15, 2)), STOPPED)
      val dealersHand = HandWithState(Hand(NonEmptyList(11, 7)), IN_PLAY)

      val gameState = Game.dealerTakesACard(GameState(dealersHand, playersHand))

      gameState.dealer.state should be(WON)
      gameState.dealer.hand.count should be(dealersHand.hand.count)

      gameState.player should be(playersHand)
    }
  }
}
