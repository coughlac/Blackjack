import ListExercises.penultimate
import org.scalatest.{FreeSpec, Matchers}

//P02 (*) Find the last but one element of a list.
//Example:
//  scala> penultimate(List(1, 1, 2, 3, 5, 8))
//res0: Int = 5

class P02_FindThePenultimateElementOfAListSpec extends FreeSpec with Matchers {
  "find the penultimate element in a list" in {
    penultimate(List(1, 2, 3, 5, 8)) should be(5)
  }
  "find the penultimate element in a list of strings" in {
    penultimate(List("abc", "def", "ghi")) should be("def")
  }
}
