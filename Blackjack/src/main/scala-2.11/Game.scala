
object Game {
  def playerDrawsACard(dealer: Hand, player: Hand) = {
    if (isWon(dealer, player)) {
      (dealer, player)
    } else {
      (dealer, dealACard(player))
    }
  }

  def isWon(dealer: Hand, player: Hand): Boolean =
    player.hasBlackjack || dealer.hasBlackjack

  private def dealACard(player: Hand): Hand =
    player.copy(card1 = player.card1, card2 = player.card2, drawnCards = Some(1 :: Nil))
}
