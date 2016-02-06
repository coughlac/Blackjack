import HandWithState.blackjackScore

import scala.annotation.tailrec

object Dealer {

  @tailrec
  def takesATurn(gameWithState: GameWithState): GameWithState = {
    val dealer: HandWithState = gameWithState.dealer
    val player: HandWithState = gameWithState.player
    val state: GameState = gameWithState.state

    if (dealer.score > blackjackScore)
      GameWithState(HandWithState(dealer.hand, Lost), HandWithState(player.hand, Won), Over)
    else {
      if (dealer.score > player.score)
        GameWithState(HandWithState(dealer.hand, Won), HandWithState(player.hand, Lost), Over)
      else if (player.state == InPlay)
        GameWithState(dealer, player, state)
      else if (player.state == Won )
        GameWithState(dealer.copy(state = Lost), player, Over)
      else if (player.state == Lost)
        GameWithState(dealer.copy(state = Won), player, Over)
      else {
        takesATurn(GameWithState(HandWithState.dealACard(dealer), player, state))
      }
    }
  }
}
