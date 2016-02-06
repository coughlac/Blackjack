

object Game {
  def play(dealer: HandWithState, player: HandWithState): GameWithState = {
    val gameWithState = blackjack(dealer, player)
    gameWithState.state match {
      case Over => gameWithState
      case _    => GameWithState(dealer, Player.takesTurn(player), Playing)
    }
  }

  def blackjack(dealer: HandWithState, player: HandWithState): GameWithState = {
    val playerHasBlackJack = HandWithState.hasBlackjack(player)
    val dealerHasBlackJack = HandWithState.hasBlackjack(dealer)

    if (playerHasBlackJack.state == Won)
      GameWithState(dealerHasBlackJack.copy(state = Lost), playerHasBlackJack, Over)
    else if (dealerHasBlackJack.state == Won)
      GameWithState(dealerHasBlackJack, playerHasBlackJack.copy(state = Lost), Over)
    else
      GameWithState(dealerHasBlackJack, playerHasBlackJack, Playing)
  }
}
