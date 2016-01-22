import org.scalatest.{Matchers, FreeSpec}

class BlackjackSpec extends FreeSpec with Matchers {
  "blackjack is a score of 21 with only 2 cards and wins the game" in {
    Blackjack.evaluate(11 :: 10 :: Nil).outcome should be(WIN)

    Blackjack.evaluate(Nil).outcome should be(LOSE)
    Blackjack.evaluate(9 :: Nil).outcome should be(LOSE)
    Blackjack.evaluate(9 :: 10 :: 2 :: Nil).outcome should be(LOSE)
  }
}

sealed trait Result

case object WIN extends Result

case object LOSE extends Result

case class HandWithState(cards: List[Int], outcome: Result)

object Blackjack {
  def evaluate(hand: List[Int]): HandWithState = {
    val handOf2Cards = hand.nonEmpty && hand.size == 2

    if (handOf2Cards && hand.sum == 21) {
      HandWithState(hand, WIN)
    } else {
      HandWithState(hand, LOSE)
    }
  }
}