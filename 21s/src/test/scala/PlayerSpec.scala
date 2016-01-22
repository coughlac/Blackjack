import org.scalatest.{Matchers, FreeSpec}

import scalaz.NonEmptyList

class PlayerSpec extends FreeSpec with Matchers {
  "A player" - {
    "can deal a card when" - {
      "a hand is in play and has a score of less than 17" in {
        val playersHand = HandWithState(Hand(NonEmptyList(1, 2)), IN_PLAY)

        val dealtHand = Game.dealAPlayerACard(playersHand)

        dealtHand.state should be(IN_PLAY)
        dealtHand.hand.count should be(playersHand.hand.count + 1)
      }
    }
    "cannot deal a card when " - {
      "a hand is blackjack" in {
        val playersHand = HandWithState(Hand(NonEmptyList(1, 2)), BLACKJACK)

        Game.dealAPlayerACard(playersHand) should be(playersHand)
      }
      "a hand is lost" in {
        val playersHand = HandWithState(Hand(NonEmptyList(1, 2)), LOST)

        Game.dealAPlayerACard(playersHand) should be(playersHand)
      }
      "a hand is stopped" in {
        val playersHand = HandWithState(Hand(NonEmptyList(1, 2)), STOPPED)

        Game.dealAPlayerACard(playersHand) should be(playersHand)
      }
    }
    "must stop dealing cards when" - {
      "a hand is in play and has a score of 17 or more" in {
        val playersHand = HandWithState(Hand(NonEmptyList(10, 5, 2)), IN_PLAY)

        val dealtHand = Game.dealAPlayerACard(playersHand)
        dealtHand.hand should be(playersHand.hand)
        dealtHand.state should be(STOPPED)
      }
      "a hand has a score of more than 21" in {
        val playersHand = HandWithState(Hand(NonEmptyList(10, 5, 9)), IN_PLAY)

        val dealtHand = Game.dealAPlayerACard(playersHand)
        dealtHand.hand should be(playersHand.hand)
        dealtHand.state should be(LOST)
      }
    }
    "must lose with a score of more than 21" in {
      val playersHand = HandWithState(Hand(NonEmptyList(10, 5, 9)), IN_PLAY)

      val dealtHand = Game.dealAPlayerACard(playersHand)
      dealtHand.hand should be(playersHand.hand)
      dealtHand.state should be(LOST)
    }
  }
}
