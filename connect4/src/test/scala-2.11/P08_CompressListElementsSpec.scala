import ListExercises.compress
import org.scalatest.{FreeSpec, Matchers}
//P08 (**) Eliminate consecutive duplicates of list elements.
//  If a list contains repeated elements they should be replaced with a single copy of the element. The order of the elements should not be changed.
//  Example:
//
//  scala> compress(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e))
//res0: List[Symbol] = List('a, 'b, 'c, 'a, 'd, 'e)
class P08_CompressListElementsSpec extends FreeSpec with Matchers {
  "Eliminate consecutive duplicates of list elements" in {
    compress(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e)) should be(List('a, 'b, 'c, 'a, 'd, 'e))
  }

}
