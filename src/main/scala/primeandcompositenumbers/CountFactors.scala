object CountFactors extends App {
  //78%
  def solution(N: Int): Int = {
    if (N < 1) sys.error(s"Invalid input: $N")

    @scala.annotation.tailrec
    def foo(i: Int, total: Int): Int = {
      if (i > N) total
      else if (N % i == 0) {
        println(i)
        foo(i + 1, total + 1)
      }
      else foo(i + 1, total)
    }

    foo(i = 1, total = 0)
  }

  //92%
  def solution3(N: Int): Int = {
    if (N < 1) sys.error(s"Invalid input: $N")

    @scala.annotation.tailrec
    def foo(i: Int, total: Int): (Int, Int) = {
      if ((i * i) >= N) (total, i)
      else if (N % i == 0) foo(i + 1, total + 2)
      else foo(i + 1, total)
    }

    val (results, x) = foo( 1, total = 0)

    if (x * x == N) results + 1
    else results
  }

  def solution1(N: Int): Int = {
    val root = math.sqrt(N)
    val factorsCount = (1 to root.toInt).filter(N % _ == 0).length
    val total = factorsCount * 2
    if (N % root == 0) total - 1
    else total
  }
  println("Result" + solution(24), 25 % math.sqrt(25))
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
