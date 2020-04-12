package futuretraining

object StrSymmetryPoint extends App {
  //100%
  def solution(s: String): Int = {
    val length = s.length
    // write your code in Scala 2.12
    def go(index: Int, mid: Int): Int = {
      if (index >= mid) mid
      else {
        if (s(index) == s(length - index - 1)) {
          go(index + 1, mid)
        } else -1
      }
    }
    if (s.isEmpty || length % 2 == 0 || length >= 2000000) -1
    else if (length == 1) 0
    else go(0, length / 2)
  }

  /**
    *
    */
  println(solution("racecar"), solution("abaaba"), solution("aaaaaaaaaaaaaaa"))
  /*
  Write a function:

object Solution { def solution(s: String): Int }

that, given a string S, returns the index (counting from 0) of a character such that the part of the string to the left of that character is a reversal of the part of the string to its right. The function should return −1 if no such index exists.

Note: reversing an empty string (i.e. a string whose length is zero) gives an empty string.

For example, given a string:

"racecar"

the function should return 3, because the substring to the left of the character "e" at index 3 is "rac", and the one to the right is "car".

Given a string:

"x"

the function should return 0, because both substrings are empty.

Write an efficient algorithm for the following assumptions:

the length of S is within the range [0..2,000,000].
 */
}
