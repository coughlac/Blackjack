import org.scalatest.{FreeSpec, Matchers}
import ListExercises.reverse

//P05 (*) Reverse a list.
//Example:
//  scala> reverse(List(1, 1, 2, 3, 5, 8))
//res0: List[Int] = List(8, 5, 3, 2, 1, 1)

class P05_ReverseTheElementsOfAListSpec extends FreeSpec with Matchers {
  "find the length of a list" in {
    reverse(List(1, 2, 3, 5, 8)) should be(List(8, 5, 3, 2, 1))
  }
}
