
sealed trait GameState

object Playing extends GameState

object Over extends GameState

case class GameWithState(dealer: HandWithState, player: HandWithState, state: GameState)

