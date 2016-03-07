import scala.annotation.tailrec

object ListExercises {

  @tailrec
  def last[A](elems: List[A]): A = {
    elems match {
      case (head :: tail) if tail.isEmpty => head
      case (head :: tail)                 => last(tail)
    }
  }

  @tailrec
  def penultimate[A](elems: List[A]): A = {
    elems match {
      case (head :: tail) if tail.length == 1 => head
      case (head :: tail)                     => penultimate(tail)
    }
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
