
case class Hand(card1: Int, card2: Int) {
  private val blackjack = 21

  def score: Int = card1 + card2

  def hasBlackjack = score == blackjack
}

