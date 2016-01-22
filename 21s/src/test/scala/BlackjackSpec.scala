import org.scalatest.{FreeSpec, Matchers}

import scalaz.NonEmptyList

class BlackjackSpec extends FreeSpec with Matchers {
  "blackjack is " - {
    "a score of 21 with only 2 cards" in {
      Game.blackjack(Hand(NonEmptyList(11, 10))).state should be(BLACKJACK)
    }
    "a score of anything other than 21 with 2 cards is not blackjack" in {
      Game.blackjack(Hand(NonEmptyList(11, 1))).state should be(IN_PLAY)
    }
    "a hand with a score of 21 with more or less than 2 cards is not blackjack" in {
      Game.blackjack(Hand(NonEmptyList(9))).state should be(IN_PLAY)
      Game.blackjack(Hand(NonEmptyList(9, 10, 2))).state should be(IN_PLAY)
    }
  }
}