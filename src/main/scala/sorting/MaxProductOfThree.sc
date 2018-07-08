object MaxProductOfThree {

  // 44%
  def brutalSolution(A: Array[Int]): Int = {
    var max = A(0) * A(1) * A(2)
    for {
      p <- 0 to A.length - 3
      q <- p + 1 to A.length - 2
      r <- q + 1 until A.length
    } {
      val prod = A(p) * A(q) * A(r)
      if (prod > max) max = prod
    }
    max
  }

  // 100%
  def betterSolution(A: Array[Int]): Int = {
    val sortedA = A.toList.sorted.toArray
    val len = A.length
    val maxP = sortedA(len - 1) * sortedA(len - 2) * sortedA(len - 3)
    val maxN = sortedA(0) * sortedA(1) * sortedA(len - 1)
    Math.max(maxN, maxP)
  }

  def test(a: Array[Int]) = {
    (brutalSolution(a), betterSolution(a))
  }

  val ar1 = Array(-3, 1, 2, -2, 5, 6)
  val ar2 = Array(0, 0, 0)
  val ar3 = Array(3, 5, 7, 9, 10, -12, -25)
  val ar4 = Array(-3, -20, -1, -6, -90, -12, -12)
  test(ar1)
  test(ar2)
  test(ar3)
  test(ar4)

  /*
  A non-empty zero-indexed array A consisting of N integers is given. The product of triplet (P, Q, R) equates to A[P] * A[Q] * A[R] (0 ≤ P < Q < R < N).

  For example, array A such that:

    A[0] = -3
    A[1] = 1
    A[2] = 2
    A[3] = -2
    A[4] = 5
    A[5] = 6
  contains the following example triplets:

  (0, 1, 2), product is −3 * 1 * 2 = −6
  (1, 2, 4), product is 1 * 2 * 5 = 10
  (2, 4, 5), product is 2 * 5 * 6 = 60
  Your goal is to find the maximal product of any triplet.

  Write a function:

  object Solution { def solution(A: Array[Int]): Int }

  that, given a non-empty zero-indexed array A, returns the value of the maximal product of any triplet.

  For example, given array A such that:

    A[0] = -3
    A[1] = 1
    A[2] = 2
    A[3] = -2
    A[4] = 5
    A[5] = 6
  the function should return 60, as the product of triplet (2, 4, 5) is maximal.

  Assume that:

  N is an integer within the range [3..100,000];
  each element of array A is an integer within the range [−1,000..1,000].
  Complexity:

  expected worst-case time complexity is O(N*log(N));
  expected worst-case space complexity is O(1), beyond input storage (not counting the storage required for input arguments).
  Elements of input arrays can be modified.

   */
}