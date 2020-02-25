object FrogRiverOne {

  // 100%
  def solution(X: Int, A: Array[Int]): Int = {
    val pos: Array[Int] = Array.ofDim(X + 1)

    def areAllLeavesInPlace = {
      def rec(i: Int): Boolean = {
        if (i == X + 1) true
        else if (pos(i) == 0) false
        else rec(i + 1)
      }
      rec(1)
    }

    def findTime(step: Int, L: List[Int]): Int = {
      if (L.isEmpty) -1
      else {
        pos(L.head) = pos(L.head) + 1
        if (areAllLeavesInPlace) step
        else findTime(step + 1, L.tail)
      }
    }

    findTime(0, A.toList)
  }

  val ar1 = Array(1, 3, 1, 4, 2, 3, 5, 4)
  val ar2 = Array(4, 3, 2, 1, 5, 4, 3, 2, 1, 6)
  solution(5, ar1)
  solution(6, ar2)
  /*

  A small frog wants to get to the other side of a river. The frog is currently located at position 0, and wants to get to position X. Leaves fall from a tree onto the surface of the river.

    You are given a non-empty zero-indexed array A consisting of N integers representing the falling leaves. A[K] represents the position where one leaf falls at time K, measured in seconds.

    The goal is to find the earliest time when the frog can jump to the other side of the river. The frog can cross only when leaves appear at every position across the river from 1 to X. You may assume that the speed of the current in the river is negligibly small, i.e. the leaves do not change their positions once they fall in the river.

  For example, you are given integer X = 5 and array A such that:

    A[0] = 1
  A[1] = 3
  A[2] = 1
  A[3] = 4
  A[4] = 2
  A[5] = 3
  A[6] = 5
  A[7] = 4
  In second 6, a leaf falls into position 5. This is the earliest time when leaves appear in every position across the river.

  Write a function:

  object Solution { def solution(X: Int, A: Array[Int]): Int }

  that, given a non-empty zero-indexed array A consisting of N integers and integer X, returns the earliest time when the frog can jump to the other side of the river.

  If the frog is never able to jump to the other side of the river, the function should return −1.

  For example, given X = 5 and array A such that:

    A[0] = 1
  A[1] = 3
  A[2] = 1
  A[3] = 4
  A[4] = 2
  A[5] = 3
  A[6] = 5
  A[7] = 4
  the function should return 6, as explained above.

    Assume that:

    N and X are integers within the range [1..100,000];
  each element of array A is an integer within the range [1..X].
  Complexity:

    expected worst-case time complexity is O(N);
  expected worst-case space complexity is O(X), beyond input storage (not counting the storage required for input arguments).
  Elements of input arrays can be modified.

 */
}
