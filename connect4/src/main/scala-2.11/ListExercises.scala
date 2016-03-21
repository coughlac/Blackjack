import scala.annotation.tailrec

//http://aperiodic.net/phil/scala/s-99/
object ListExercises {

  def elementAt[A](n: Int, elems: List[A]): A = {
    @tailrec
    def loop(n: Int, counter: Int, elems: List[A]): A = {
      elems match {
        case (head :: tail) if counter == n => head
        case (head :: tail) => loop(n, counter + 1, tail)
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
        case (head :: tail) => loop(n + 1, tail)
      }
    }
    loop(1, elems)
  }

  def reverse[A](elems: List[A]): List[A] = {
    @tailrec
    def loop(n: Int, length: Int, elems: List[A], reversedList: List[A]): List[A] = {
      elems match {
        case _ if n == length  => reversedList
        case _ =>
          loop(n + 1, length, elems, elementAt(n, elems) :: reversedList)
      }
    }
    loop(0, length(elems), elems, Nil)
  }

  def isPalindrome[A](elems: List[A]): Boolean = elems == elems.reverse

  def flatten(elems: List[_]): List[Any] = {
    def loop(elems: List[_], acc :List[_]):List[_] =
      if (elems.isEmpty)
        acc
      else
        elems.head match {
        case as: List[_] =>
          loop(elems.tail, loop(as, acc))
        case a => loop(elems.tail, a :: acc)
    }

    loop(elems, Nil).reverse
  }
}
