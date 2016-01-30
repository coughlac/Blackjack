import org.scalatest.{FreeSpec, Matchers}

import scalaz.NonEmptyList

class GameSpec extends FreeSpec with Matchers {
  "Dealer starts game by dealing 2 hands of 2 cards, one hand each " in {
    val game = Game.dealerStartsGame(
      () => GameState(
        dealer = HandWithState(Hand(NonEmptyList(1, 10)), IN_PLAY),
        player = HandWithState(Hand(NonEmptyList(1, 2)), IN_PLAY)
      )
    )
    game.dealer.hand.count should be(2)
    game.player.hand.count should be(2)
  }

  "If the player has blackjack they win and the dealer loses" in {
    val game = Game.dealerStartsGame(
      () => GameState(
        dealer = HandWithState(Hand(NonEmptyList(11, 10)), IN_PLAY),
        player = HandWithState(Hand(NonEmptyList(1, 2)), IN_PLAY)
      )
    )
    val gameAfterInitialDeal = Game.initalBlackjackCheck(game)
    gameAfterInitialDeal.dealer.state should be(BLACKJACK)
    gameAfterInitialDeal.player.state should be(LOST)
  }

  "If the dealer has blackjack they win and the player loses" in {
    val game = Game.dealerStartsGame(
      () => GameState(
        dealer = HandWithState(Hand(NonEmptyList(1, 10)), IN_PLAY),
        player = HandWithState(Hand(NonEmptyList(11, 10)), IN_PLAY)
      )
    )
    val gameAfterInitialDeal = Game.initalBlackjackCheck(game)
    gameAfterInitialDeal.dealer.state should be(LOST)
    gameAfterInitialDeal.player.state should be(BLACKJACK)
  }

  "If neither player has blackjack, the play can continue" in {
    assert(!Game.isOver(GameState(
      dealer = HandWithState(Hand(NonEmptyList(1, 10)), IN_PLAY),
      player = HandWithState(Hand(NonEmptyList(1, 10)), IN_PLAY)
    )))
    assert(Game.isOver(GameState(
      dealer = HandWithState(Hand(NonEmptyList(1, 10)), IN_PLAY),
      player = HandWithState(Hand(NonEmptyList(1, 10)), BLACKJACK)
    )))
    assert(Game.isOver(GameState(
      dealer = HandWithState(Hand(NonEmptyList(1, 10)), BLACKJACK),
      player = HandWithState(Hand(NonEmptyList(1, 10)), IN_PLAY)
    )))
  }

  "the player draws cards until they win, lose or stick" in {

  }
}