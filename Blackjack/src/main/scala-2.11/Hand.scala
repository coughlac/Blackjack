

case class Hand(dealtCards: List[Int]) {
  private val blackjack = 21

  def score: Int = dealtCards.sum

  def cards: Int = dealtCards.size

  def hasBlackjack: Boolean = cards == 2 && score == blackjack

  def hasLost: Boolean = score > blackjack
}

