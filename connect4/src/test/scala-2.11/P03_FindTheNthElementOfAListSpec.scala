import ListExercises.elementAt
import org.scalatest.{FreeSpec, Matchers}

//P03 (*) Find the Kth element of a list.
//By convention, the first element in the list is element 0.
//Example:
//
//  scala> nth(2, List(1, 1, 2, 3, 5, 8))
//res0: Int = 2

class P03_FindTheNthElementOfAListSpec extends FreeSpec with Matchers {
  "find the 3 element in a list" in {
    elementAt(3, List(1, 2, 3, 5, 8)) should be(5)
  }
}
