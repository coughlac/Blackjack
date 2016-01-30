import scala.annotation.tailrec
import scala.util.Random

sealed trait HandState

object Lost extends HandState

object Won extends HandState

object Stick extends HandState

object InPlay extends HandState

sealed trait GameState

object Playing extends GameState

object Over extends GameState

case class GameWithState(dealer: HandWithState, player: HandWithState, state: GameState)

case class HandWithState(hand: Hand, state: HandState)

object Game {
  @tailrec
  def playerTakesTurn(player: HandWithState): HandWithState =
    if (player.hand.score > 21)
      player.copy(state = Lost)
    else if (player.hand.score >= 17)
      player.copy(state = Stick)
    else
      playerTakesTurn(HandWithState(dealACard(player.hand), InPlay))


  def playerDrawsACard(dealer: Hand, player: Hand) =
    if (isWon(dealer, player))
      (dealer, player)
    else
      (dealer, dealACard(player))

  def dealerDrawsACard(dealer: HandWithState, player: HandWithState): GameWithState =
    player.state match {
      case Stick =>
        GameWithState(HandWithState(dealACard(dealer.hand), InPlay), player, Playing)
      case _     => GameWithState(dealer, player, Over)
    }

  def isWon(dealer: Hand, player: Hand): Boolean =
    player.hasBlackjack || dealer.hasBlackjack

  private def dealACard(hand: Hand) =
    hand.copy(
      dealtCards = Random.nextInt(11) :: hand.dealtCards
    )
}
