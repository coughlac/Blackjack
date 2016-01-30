
sealed trait HandState

object Lost extends HandState

object Won extends HandState

object Stick extends HandState

object InPlay extends HandState

case class HandWithState(hand: Hand, state: HandState)

