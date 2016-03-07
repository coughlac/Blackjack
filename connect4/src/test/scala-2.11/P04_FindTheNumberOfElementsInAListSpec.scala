import org.scalatest.{FreeSpec, Matchers}

//P04 (*) Find the number of elements of a list.
//  Example:
//  scala> length(List(1, 1, 2, 3, 5, 8))
//res0: Int = 6

class P04_FindTheNumberOfElementsInAListSpec extends FreeSpec with Matchers {
  "find the length of a list" in {
    ListExercises.length(List(1, 2, 3, 5, 8)) should be(5)
  }
}
