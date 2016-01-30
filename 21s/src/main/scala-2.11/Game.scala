
object Game {
  def dealerStartsGame(generator: () => GameState) : GameState = generator()

  def blackjack(handWithState: HandWithState): HandWithState = {
    if (handWithState.hand.blackjack)
      updateState(handWithState, BLACKJACK)
    else
      handWithState
  }

  def initalBlackjackCheck(gameState: GameState): GameState = {
    def dealerCheck: GameState = {
      Game.blackjack(gameState.dealer) match {
        case HandWithState(_, BLACKJACK) => dealerBlackjack(gameState)
        case HandWithState(_ , _)        => gameState
      }
    }
    Game.blackjack(gameState.player) match {
      case HandWithState(_, BLACKJACK) => playerBlackjack(gameState)
      case HandWithState(_ , _)        => dealerCheck
    }
  }

  def dealerTakesACard(game: GameState): GameState = {
    val dealer = game.dealer
    val player = game.player
    val playersHand = game.player.hand

    def dealersTurn: GameState = {
      dealer match {
        case HandWithState(_, _: EndResult)                                 =>
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

  def isOver(gameState: GameState) : Boolean = {
    gameState match {
      case GameState(dealer, player) if dealer.state == IN_PLAY && player.state == IN_PLAY => false
      case _                                                                               => true
    }
  }

  def dealAPlayerACard(player: HandWithState): HandWithState = player match {
    case HandWithState(_, _: EndResult)                           => player
    case HandWithState(_, STOPPED)                                => player
    case HandWithState(hand, IN_PLAY) if hand.under17Score        => takeCard(player)
    case HandWithState(hand, IN_PLAY) if hand.between17and21Score => updateState(player, STOPPED)
    case HandWithState(hand, IN_PLAY) if hand.over21Score         => updateState(player, LOST)
  }

  private def dealerLost(gameState: GameState) : GameState = gameState.copy(
    dealer = updateState(gameState.dealer, LOST),
    player = updateState(gameState.player, WON)
  )

  private def dealerWon(game: GameState) : GameState = game.copy(
    dealer = updateState(game.dealer, WON),
    player = updateState(game.player, LOST)
  )

  private def playerBlackjack(game: GameState) : GameState = game.copy(
    dealer = updateState(game.dealer, LOST),
    player = updateState(game.player, BLACKJACK)
  )

  private def dealerBlackjack(game: GameState) : GameState = game.copy(
    dealer = updateState(game.dealer, BLACKJACK),
    player = updateState(game.player, LOST)
  )

  private def updateState(handWithState: HandWithState, value: Result): HandWithState =
    handWithState.copy(state = value)

  private def takeCard(handWithState: HandWithState): HandWithState = {
    handWithState.copy(hand = handWithState.hand.copy(cards = handWithState.hand.cards.<::(1)))
  }
}
