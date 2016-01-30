
case class Hand(card1: Int, card2: Int, drawnCards: Option[List[Int]] = None) {
  private val blackjack = 21

  def score: Int = card1 + card2

  def hasBlackjack = score == blackjack

  def cards = drawnCards.fold(2)(cs => 2 + cs.sum)
}

