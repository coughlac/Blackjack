import scala.annotation.tailrec

//http://aperiodic.net/phil/scala/s-99/
object ListExercises {

  def elementAt[A](n: Int, elems: List[A]): A = {
    @tailrec
    def loop(n: Int, counter: Int, elems: List[A]): A = {
      elems match {
        case (head :: tail) if counter == n => head
        case (head :: tail)                 => loop(n, counter + 1, tail)
      }
    }
    loop(n, 0, elems)
  }

  def last[A](elems: List[A]): A = elementAt(elems.length - 1, elems)

  def penultimate[A](elems: List[A]): A = elementAt(elems.length - 2, elems)

  def length[A](elems: List[A]): Int = {
    @tailrec
    def loop(n: Int, elems: List[A]): Int = {
      elems match {
        case (head :: tail) if tail.isEmpty => n
        case (head :: tail)                 => loop(n + 1, tail)
      }
    }
    loop(1, elems)
  }

  def flatten(values: List[_]): List[Any] = {
    def flattenOneTier(elems: List[_]): List[Any] = {
      elems.foldLeft(List.empty[Any])((acc, elems) => elems match {
        case as: List[_] => acc ::: as
        case a           => a :: acc
      })
    }
    values.foldLeft(List.empty[Any])((acc, elems) => elems match {
      case as: List[_] => acc ::: flattenOneTier(as)
      case a           => a :: acc
    })
  }
}
