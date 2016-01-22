import scalaz.NonEmptyList

case class Hand(cards:  NonEmptyList[Int]) {
  def score: Int = cards.list.sum

  def count: Int = cards.size

  def under17Score: Boolean = score < 17

  def over21Score: Boolean = score > 21

  def between17and21Score: Boolean = !over21Score && !under17Score

  def notOver21Score: Boolean = !over21Score
}
case class HandWithState(hand: Hand, state: Result)

