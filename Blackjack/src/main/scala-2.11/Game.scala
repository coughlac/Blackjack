object Game {

  def isWon(player: Hand, dealer: Hand): Boolean =
    player.hasBlackjack || dealer.hasBlackjack
}
