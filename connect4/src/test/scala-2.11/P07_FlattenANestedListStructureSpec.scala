import ListExercises.flatten
import org.scalatest.{Matchers, FreeSpec}

class P07_FlattenANestedListStructureSpec extends FreeSpec with Matchers {
  "flatten" in {
    flatten(List(List(1, 1), 2, List(3, List(5, 8)))) should be(List(1, 1, 2, 3, 5, 8))
  }
}
