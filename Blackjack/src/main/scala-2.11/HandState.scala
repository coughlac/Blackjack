
sealed trait HandState

object Lost extends HandState

object Won extends HandState

object Stick extends HandState

object InPlay extends HandState

case class HandWithState(hand: Hand, state: HandState) {
  def score: Int = hand.score
}

object HandWithState {
  val blackjackScore = 21
  def hasBlackjack(handWithState: HandWithState): HandWithState =
    if (handWithState.hand.cards == 2 && handWithState.score == 21)
      HandWithState(handWithState.hand, Won)
    else
      handWithState

  def dealACard(handWithState: HandWithState): HandWithState =
    HandWithState(Hand.dealACard(handWithState.hand), InPlay)
}

