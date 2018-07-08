object CountFactors {
  val N = 16
  var factorsCount = 0

  for (i <- 1 to math.sqrt(N).toInt) {
    if (N % i == 0) {
      println(i)
      factorsCount += 1
    }
  }
  factorsCount *= 2

  if (N % math.sqrt(N) == 0) factorsCount -= 1
  factorsCount

  /*def solution(N: Int): Int = {
    var i = 1
    var result = 0
    if (N <= 0) return 0
    if (N == 1) return 1
    val sN = math.sqrt(N).asInstanceOf[Int]
    while ( {
      i <= sN
    }) {
      if (N % i == 0) result += 2
      i += 1
    }
    if (sN * sN == N) {
      result -= 1; result + 1
    }
    result
  }
  solution(24)*/
  /*
  A positive integer D is a factor of a positive integer N if there exists an integer M such that N = D * M.

  For example, 6 is a factor of 24, because M = 4 satisfies the above condition (24 = 6 * 4).

  Write a function:

  object Solution { def solution(N: Int): Int }

  that, given a positive integer N, returns the number of its factors.

  For example, given N = 24, the function should return 8, because 24 has 8 factors, namely 1, 2, 3, 4, 6, 8, 12, 24. There are no other factors of 24.

  Assume that:

  N is an integer within the range [1..2,147,483,647].
  Complexity:

  expected worst-case time complexity is O(sqrt(N));
  expected worst-case space complexity is O(1).

   */
}