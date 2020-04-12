import java.util.BitSet
object PermCheck extends App {

  // 100%
  def solution(A: Array[Int]): Int = {
    def isPerm(L: List[Int]): Int = {
      if (L.tail.isEmpty) 1
      else if (L.tail.head != L.head + 1) 0
      else isPerm(L.tail)
    }

    val list: List[Int] = A.toList.sorted
    if (list.head != 1) 0
    else if (list.last != list.size) 0
    else isPerm(list)
  }

  def solution1(A: Array[Int]): Int = {
    val bitz = new BitSet(A.size + 1) //We could use SortedSet here

    val good = A.foldLeft(true)(
      (current, i) =>
        if (current) {

          (i, bitz.get(i)) match {
            case (x, _) if x > A.size =>
              println("Mathc-")
              false

            case (x, _) if x < 1 || x > 1000000000 =>
              false

            case (0, _) =>
              false

            case (_, true) =>
              false

            case _ =>
              bitz.set(i)
              println(i)
              true
          }

        } else false
    )

    if (good && A.size > 0 && A.size <= 100000) 1
    else 0
  }

  def solution2(A: Array[Int]): Int = {
    import scala.collection.BitSet

    @scala.annotation.tailrec
    def go(index: Int, remaining: BitSet): Int = {
      if (index >= A.length) {
        if (remaining.isEmpty) 1 else 0
      } else {
        val currentValue = A(index)
        if (!remaining.contains(currentValue)) 0
        else go(index + 1, remaining - currentValue)
      }
    }

    go(0, BitSet(1 to A.length: _*))
  }
  //println(solution1(Array(4, 1, 3, 2)))
  println(solution2(Array(4, 1, 3)))
  //println(solution(Array(5, 3, 2, 1, 4)))
  /*

  A non-empty zero-indexed array A consisting of N integers is given.

  A permutation is a sequence containing each element from 1 to N once, and only once.

  For example, array A such that:

      A[0] = 4
      A[1] = 1
      A[2] = 3
      A[3] = 2
  is a permutation, but array A such that:

      A[0] = 4
      A[1] = 1
      A[2] = 3
  is not a permutation, because value 2 is missing.

  The goal is to check whether array A is a permutation.

  Write a function:

  object Solution { def solution(A: Array[Int]): Int }

  that, given a zero-indexed array A, returns 1 if array A is a permutation and 0 if it is not.

  For example, given array A such that:

      A[0] = 4
      A[1] = 1
      A[2] = 3
      A[3] = 2
  the function should return 1.

  Given array A such that:

      A[0] = 4
      A[1] = 1
      A[2] = 3
  the function should return 0.

  Assume that:

  N is an integer within the range [1..100,000];
  each element of array A is an integer within the range [1..1,000,000,000].
  Complexity:

  expected worst-case time complexity is O(N);
  expected worst-case space complexity is O(N), beyond input storage (not counting the storage required for input arguments).
  Elements of input arrays can be modified.

 */
}
