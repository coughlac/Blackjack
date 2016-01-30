import org.scalatest.{FreeSpec, Matchers}

import scalaz.NonEmptyList

class BlackjackSpec extends FreeSpec with Matchers {
  "blackjack is " - {
    "a score of 21 with only 2 cards" in {
      Game.blackjack(HandWithState(Hand(NonEmptyList(11, 10)), IN_PLAY)).state should be(BLACKJACK)
    }
    "a score of anything other than 21 with 2 cards is not blackjack" in {
      Game.blackjack(HandWithState(Hand(NonEmptyList(11, 1)), IN_PLAY)).state should be(IN_PLAY)
    }
    "a hand with a score of 21 with more or less than 2 cards is not blackjack" in {
      Game.blackjack(HandWithState(Hand(NonEmptyList(9)), IN_PLAY)).state should be(IN_PLAY)
      Game.blackjack(HandWithState(Hand(NonEmptyList(9, 10, 2)), IN_PLAY)).state should be(IN_PLAY)
    }
  }
}