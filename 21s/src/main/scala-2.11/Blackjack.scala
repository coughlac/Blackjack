
object Blackjack {
  def evaluate(hand: List[Int]): HandWithState = {
    val handOf2Cards = hand.nonEmpty && hand.size == 2

    if (handOf2Cards && hand.sum == 21) {
      HandWithState(hand, BLACKJACK)
    } else {
      HandWithState(hand, NOT_BLACKJACK)
    }
  }
}
