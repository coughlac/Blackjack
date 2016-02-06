import HandWithState.blackjackScore

import scala.annotation.tailrec

object Player {

  private val stickingScore = 17

  @tailrec
  def takesTurn(player: HandWithState): HandWithState = {
    if (player.score > blackjackScore)
      HandWithState(player.hand, Lost)
    else {
      if (player.score >= stickingScore)
        HandWithState(player.hand, Stick)
      else
        takesTurn(HandWithState.dealACard(player))
    }
  }
}
