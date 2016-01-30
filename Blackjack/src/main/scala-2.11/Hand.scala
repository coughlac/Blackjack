

case class Hand(dealtCards: List[Int]) {
  private val blackjack = 21

  def score: Int = dealtCards.sum

  def hasBlackjack = cards == 2 && score == blackjack

  def cards = dealtCards.size
}

