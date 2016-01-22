import org.scalatest.{FreeSpec, Matchers}

class BlackjackSpec extends FreeSpec with Matchers {
  "blackjack is " in {
    "a score of 21 with only 2 cards" in {
      Blackjack.evaluate(11 :: 10 :: Nil).outcome should be(BLACKJACK)
    }
    "a score of anything other than 21 with 2 cards is not blackjack" in {
      Blackjack.evaluate(11 :: 1 :: Nil).outcome should be(NOT_BLACKJACK)
    }
    "a hand of anything other than exactly 2 cards is not blackjack" in {
      Blackjack.evaluate(Nil).outcome should be(NOT_BLACKJACK)
      Blackjack.evaluate(9 :: Nil).outcome should be(NOT_BLACKJACK)
      Blackjack.evaluate(9 :: 10 :: 2 :: Nil).outcome should be(NOT_BLACKJACK)
    }
  }
}