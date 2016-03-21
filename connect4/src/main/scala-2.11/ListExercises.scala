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

  def flatten(elems: List[_]): List[_] = {
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

  def compress[A](elems: List[A]): List[A]  = {
    @tailrec
    def loop(es: List[A], acc: List[A]): List[A] = {
      if (es.isEmpty)
        acc.reverse
      else
      {
        val head = es.head
        val tail = es.tail
        if (acc.head == head)
          loop(tail, acc)
        else
          loop(tail, head :: acc)
      }
    }

    loop(elems.tail, elems.head :: Nil)
  }

  def pack(elems: List[_]): List[List[_]] = {
    @tailrec
    def loop(es: List[_], acc: List[_], acc2: List[List[_]]): List[List[_]] = {
      if (es.isEmpty)
        (acc :: acc2).reverse
      else
      {
        val head = es.head
        val tail = es.tail
        if (acc.head == head)
          loop(tail, head :: acc, acc2)
        else
          loop(tail, head :: Nil, acc :: acc2)
      }
    }

    loop(elems.tail, elems.head :: Nil, Nil)
  }

  def encode(elems: List[_]): List[_]  = {
    val listsOfSameElement: List[List[_]] = pack(elems)
    def loop[A](elems: List[List[_]], acc: List[(Int, A)]): List[(Int, _)] = {
      if (elems.isEmpty)
        acc.reverse
      else{
        val subList = elems.head
        loop(elems.tail, (length(subList), subList.head) :: acc)
      }
    }
    loop(listsOfSameElement, Nil)
    //listsOfSameElement.map(elems => (length(elems), elems.head))
  }
}
