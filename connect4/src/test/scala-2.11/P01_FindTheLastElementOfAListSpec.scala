import ListExercises.last
import org.scalatest.{FreeSpec, Matchers}

//P01 (*) Find the last element of a list.
//Example:
//  scala> last(List(1, 1, 2, 3, 5, 8))
//res0: Int = 8

class P01_FindTheLastElementOfAListSpec extends FreeSpec with Matchers {
  "find the last element in a list" in {
    last(List(1, 2, 3, 5, 8)) should be(8)
  }
  "find the last element in a list of strings" in {
    last(List("abc", "def", "ghi")) should be("ghi")
  }
}
