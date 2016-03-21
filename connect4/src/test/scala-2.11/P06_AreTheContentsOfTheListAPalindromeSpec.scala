import ListExercises.isPalindrome
import org.scalatest.{FreeSpec, Matchers}

//P06 (*) Find out whether a list is a palindrome.
//  Example:
//  scala> isPalindrome(List(1, 2, 3, 2, 1))
//res0: Boolean = true

class P06_AreTheContentsOfTheListAPalindromeSpec extends FreeSpec with Matchers {
  "a list that is a palindrome should return true" in {
    isPalindrome(List(1, 2, 3, 2, 1)) shouldBe true
  }

  "a list that is not a palindrome should return false" in {
    isPalindrome(List(1, 2, 3, 2)) shouldBe false
  }
}
