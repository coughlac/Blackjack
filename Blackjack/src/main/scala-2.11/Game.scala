import scala.annotation.tailrec
import scala.util.Random

object Game {
  @tailrec
  def playerTakesTurn(player: Hand): Hand =
    if (player.score >= 17)
      player
    else
      playerTakesTurn(dealACard(player))

  def playerDrawsACard(dealer: Hand, player: Hand) =
    if (isWon(dealer, player))
      (dealer, player)
    else
      (dealer, dealACard(player))

  def isWon(dealer: Hand, player: Hand): Boolean =
    player.hasBlackjack || dealer.hasBlackjack

  private def dealACard(player: Hand) =
    player.copy(
      dealtCards = Random.nextInt(11) :: player.dealtCards
    )
}
