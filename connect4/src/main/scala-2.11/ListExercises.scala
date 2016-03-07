import scala.annotation.tailrec

object ListExercises {
  @tailrec
  private def elementAt[A](elems: List[A], offsetFromEndOfList: Int): A = {
    elems match {
      case (head :: tail) if tail.length == offsetFromEndOfList => head
      case (head :: tail)                                       => elementAt(tail, offsetFromEndOfList)
    }
  }

  def last[A](elems: List[A]): A = elementAt(elems, 0)

  def penultimate[A](elems: List[A]): A = elementAt(elems, 1)

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
