object MissingInteger extends App {

  // 100%
  def solution(a: Array[Int]): Int = {
    def inner(b: List[Int], c: Int): Int = {
      b match {
        case Nil => c
        case h :: t =>
          if (c != h) c
          else inner(t, c + 1)
      }
    }

    inner(a.filter(_ > 0).distinct.sorted.toList, 1)
  }

  def solution1(A: Array[Int]): Int = {
    // First build set of integers that exist.
    @scala.annotation.tailrec
    def buildSet(index: Int, accum: Set[Int]): Set[Int] = {
      if (index >= A.length) accum
      else {
        val nextValue = A(index)
        val nextAccum =
          if (nextValue > 0) accum + nextValue
          else accum
        buildSet(index + 1, nextAccum)
      }
    }
    val positiveValues = buildSet(0, Set.empty)

    // Next find lowest number not in set.
    @scala.annotation.tailrec
    def findLowest(i: Int): Int = {
      if (positiveValues contains i) findLowest(i + 1)
      else i
    }

    findLowest(1)
  }

  println(solution(Array(1, 3, 6, 4, 1, 2)))
  println(solution(Array(1, 2, 3)))
  println(solution(Array(-1, -3)))
  println(solution(Array(1)))
  println(solution(Array(3)))
  println(solution(Array(0)))
  println(solution(Array(-2)))
  println(solution(Array(-5, 0, -2, 1)))
  println(solution(Array(-2147483648, 2147483647)))

  /*

  Write a function:

  object Solution { def solution(A: Array[Int]): Int }

  that, given a non-empty zero-indexed array A of N integers, returns the minimal positive integer (greater than 0) that does not occur in A.

    For example, given:

    A[0] = 1
  A[1] = 3
  A[2] = 6
  A[3] = 4
  A[4] = 1
  A[5] = 2
  the function should return 5.

  Assume that:

    N is an integer within the range [1..100,000];
  each element of array A is an integer within the range [−2,147,483,648..2,147,483,647].
  Complexity:

    expected worst-case time complexity is O(N);
  expected worst-case space complexity is O(N), beyond input storage (not counting the storage required for input arguments).
  Elements of input arrays can be modified.

 */
}
