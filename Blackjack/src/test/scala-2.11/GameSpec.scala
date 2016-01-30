import org.scalatest.{FreeSpec, Matchers}

class GameSpec extends FreeSpec with Matchers {
  "The game is won if either of the two initial hands is blackjack " in {
    Game.isWon(Hand(10, 11), Hand(10, 3)) shouldBe true
    Game.isWon(Hand(10, 1), Hand(11, 10)) shouldBe true

    Game.isWon(Hand(10, 1), Hand(9, 10)) shouldBe false
  }
}