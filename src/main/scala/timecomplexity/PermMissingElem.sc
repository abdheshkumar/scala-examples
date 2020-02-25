object PermMissingElem {
  // 100%
  def solution(A: Array[Int]): Int = {
    def findMissing(i: Int, L: List[Int]): Int = {
      if (L.isEmpty || L.head != i + 1) i + 1
      else findMissing(i + 1, L.tail)
    }
    if (A.length == 0) 1
    else findMissing(0, A.toList.sorted)
  }

  def solutionNext(A: Array[Int]): Int = {
    val N = (A.length + 1).toLong
    val expectedSum = (N) * (N + 1) / 2
    val actualSum = A.sum
    (expectedSum - actualSum).toInt
  }

  solution(Array(2, 3, 1, 5))
  solution(Array())
  solution(Array(1))
  solution(Array(4, 5, 3, 1))

  /*
  A zero-indexed array A consisting of N different integers is given. The array contains integers in the range [1..(N + 1)], which means that exactly one element is missing.

  Your goal is to find that missing element.

  Write a function:

  object Solution { def solution(A: Array[Int]): Int }

  that, given a zero-indexed array A, returns the value of the missing element.

    For example, given array A such that:

    A[0] = 2
  A[1] = 3
  A[2] = 1
  A[3] = 5
  the function should return 4, as it is the missing element.

  Assume that:

    N is an integer within the range [0..100,000];
  the elements of A are all distinct;
  each element of array A is an integer within the range [1..(N + 1)].
  Complexity:

    expected worst-case time complexity is O(N);
  expected worst-case space complexity is O(1), beyond input storage (not counting the storage required for input arguments).
  Elements of input arrays can be modified.

 */
}
