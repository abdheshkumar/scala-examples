object EquiLeader {

  // 11%
  def solution(A: Array[Int]): Int = {
    def findLeader(A: Array[Int]): Int = {
      val n = A.length
      var size = 0
      var value = -1
      for (i <- 0 until n) {
        if (size == 0) {
          size = size + 1
          value = A(i)
        } else if (value != A(i)) size = size - 1
        else size = size + 1
      }
      var candidate = -1
      if (size > 0) candidate = value
      var leader = -1
      var count = 0
      for (k <- 0 until n) if (A(k) == candidate) count = count + 1
      if (count >= n / 2) leader = candidate
      A.indexOf(leader)
    }

    var count = 0
    for (i <- 0 to A.length - 2) {
      val as1 = A.slice(0, i + 1)
      println(as1.toList)
      val as2 = A.slice(i + 1, A.length)
      println(as2.toList)
      if (findLeader(as1) == findLeader(as2)) {
        println(s"equileader found: $i")
        count = count + 1
      }
    }

    count
  }

  val ar1 = Array(4, 3, 4, 4, 4, 2)
  val ar2 = Array(4, 4)
  solution(ar1)
  solution(ar2)
  /*
  A non-empty zero-indexed array A consisting of N integers is given.
  The leader of this array is the value that occurs in more than half of the elements of A.

  An equi leader is an index S such that 0 ≤ S < N − 1 and two sequences A[0], A[1], ..., A[S] and A[S + 1], A[S + 2], ..., A[N − 1] have leaders of the same value.

  For example, given array A such that:

      A[0] = 4
      A[1] = 3
      A[2] = 4
      A[3] = 4
      A[4] = 4
      A[5] = 2
  we can find two equi leaders:

  0, because sequences: (4) and (3, 4, 4, 4, 2) have the same leader, whose value is 4.
  2, because sequences: (4, 3, 4) and (4, 4, 2) have the same leader, whose value is 4.
  The goal is to count the number of equi leaders.

  Write a function:

  object Solution { def solution(A: Array[Int]): Int }

  that, given a non-empty zero-indexed array A consisting of N integers, returns the number of equi leaders.

  For example, given:

      A[0] = 4
      A[1] = 3
      A[2] = 4
      A[3] = 4
      A[4] = 4
      A[5] = 2
  the function should return 2, as explained above.

  Assume that:

  N is an integer within the range [1..100,000];
  each element of array A is an integer within the range [−1,000,000,000..1,000,000,000].
  Complexity:

  expected worst-case time complexity is O(N);
  expected worst-case space complexity is O(N), beyond input storage (not counting the storage required for input arguments).
  Elements of input arrays can be modified.

   */
}