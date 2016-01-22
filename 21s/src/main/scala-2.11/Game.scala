

case class GameState(dealer: HandWithState, player: HandWithState)

object Game {
  def dealerTakesACard(game: GameState): GameState = {
    val dealer = game.dealer
    val player = game.player
    val playersHand = game.player.hand

    def dealersTurn: GameState = {
      dealer match {
        case HandWithState(_, _:EndResult)                                  =>
          game
        case HandWithState(dealersHand, IN_PLAY) if dealersHand.over21Score =>
          game.copy(dealer = updateState(game.dealer, LOST), game.player)
        case HandWithState(dealersHand, IN_PLAY)
          if dealersHand.score > playersHand.score
            && dealersHand.notOver21Score                                   =>
          game.copy(dealer = updateState(game.dealer, WON), game.player)
        case _                                                              =>
          game.copy(dealer = takeCard(dealer), game.player)
      }
    }
    player.state match {
      case STOPPED => dealersTurn
      case _       => game
    }
  }

  def blackjack(hand: Hand): HandWithState = {
    if (hand.count == 2 && hand.score == 21)
      HandWithState(hand, BLACKJACK)
    else
      HandWithState(hand, IN_PLAY)
  }

  def dealAPlayerACard(player: HandWithState): HandWithState = player match {
    case HandWithState(_, _: EndResult)                           => player
    case HandWithState(_, STOPPED)                                => player
    case HandWithState(hand, IN_PLAY) if hand.under17Score        => takeCard(player)
    case HandWithState(hand, IN_PLAY) if hand.between17and21Score => updateState(player, STOPPED)
    case HandWithState(hand, IN_PLAY) if hand.over21Score         => updateState(player, LOST)
  }

//  private def dealerLost(game: GameState) : GameState = game.copy(
//    dealer = updateState(game.dealer, LOST),
//    player = updateState(game.player, WIN)
//  )
//
//  private def dealerWon(game: GameState) : GameState = game.copy(
//    dealer = updateState(game.dealer, WIN),
//    player = updateState(game.player, LOST)
//  )

  private def updateState(handWithState: HandWithState, value: Result): HandWithState =
    handWithState.copy(state = value)

  private def takeCard(handWithState: HandWithState): HandWithState = {
    handWithState.copy(hand = handWithState.hand.copy(cards = handWithState.hand.cards.<::(1)))
  }
}
