import scala.util.Random

case class Hand(dealtCards: List[Int]) {

  def score: Int = dealtCards.sum

  def cards: Int = dealtCards.size
}

object Hand {
  def dealACard(hand: Hand): Hand = Hand(Random.nextInt(11) :: hand.dealtCards)
}

