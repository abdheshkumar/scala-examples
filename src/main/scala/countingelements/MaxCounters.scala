import MaxCounters.{ops, solution}

object MaxCounters extends App {

  // 88%
  def solution(N: Int, A: Array[Int]): Array[Int] = {
    val counters: Array[Int] = Array.ofDim(N)

    def increaseCounter(x: Int) = counters(x - 1) = counters(x - 1) + 1

    def maxCounters() = {
      val max = counters.max
      for (i <- counters.indices) counters(i) = max
    }

    def getCounters(ops: List[Int]): Array[Int] = {
      if (ops.isEmpty) counters
      else {
        ops.head match {
          case x: Int if x >= 1 && x <= N => increaseCounter(x)
          case x: Int if x > N            => maxCounters()
        }
        getCounters(ops.tail)
      }
    }

    getCounters(A.toList)
  }

  def solution1(N: Int, A: Array[Int]): Array[Int] = {
    @scala.annotation.tailrec
    def loop(index: Int, counters: Map[Int, Int], curMax: Int, floor: Int): Array[Int] = {
      if (index >= A.length) {
        val result = Array.fill(N)(0)
        (1 to N) foreach { counter =>
          val counterValue = counters.getOrElse(counter, 0) + floor
          result(counter - 1) = counterValue
        }
        result
      } else {
        val currentValue = A(index)
        if (currentValue >= 1 && currentValue <=N) {
          val nextCounterValue = counters.getOrElse(currentValue, 0) + 1 //Increase by 1
          val nextMax = curMax max nextCounterValue
          val nextCounters = counters + (currentValue -> nextCounterValue)
          println(s"Next: ${nextCounters}")
          loop(index + 1, nextCounters, nextMax, floor)
        } else if (currentValue == (N + 1)) {
          val nextFloor = floor + curMax // set max counter
          println(counters,nextFloor,currentValue)
          loop(index + 1, Map.empty, 0, nextFloor)
        } else {
          sys.error("Unexpected input")
        }
      }
    }

    loop(0, Map.empty, 0, 0)
  }



  val ops = Array(3, 4, 4, 6, 1, 4, 4)
  println(solution1(5, ops).toList)
}
/*

  You are given N counters, initially set to 0, and you have two possible operations on them:

    increase(X) − counter X is increased by 1,
  max counter − all counters are set to the maximum value of any counter.
  A non-empty zero-indexed array A of M integers is given. This array represents consecutive operations:

  if A[K] = X, such that 1 ≤ X ≤ N, then operation K is increase(X),
  if A[K] = N + 1 then operation K is max counter.
    For example, given integer N = 5 and array A such that:

    A[0] = 3
  A[1] = 4
  A[2] = 4
  A[3] = 6
  A[4] = 1
  A[5] = 4
  A[6] = 4
  the values of the counters after each consecutive operation will be:

    (0, 0, 1, 0, 0)
  (0, 0, 1, 1, 0)
  (0, 0, 1, 2, 0)
  (2, 2, 2, 2, 2)
  (3, 2, 2, 2, 2)
  (3, 2, 2, 3, 2)
  (3, 2, 2, 4, 2)
  The goal is to calculate the value of every counter after all operations.

    Write a function:

  object Solution { def solution(N: Int, A: Array[Int]): Array[Int] }

  that, given an integer N and a non-empty zero-indexed array A consisting of M integers, returns a sequence of integers representing the values of the counters.

    The sequence should be returned as:

    a structure Results (in C), or
  a vector of integers (in C++), or
  a record Results (in Pascal), or
  an array of integers (in any other programming language).
    For example, given:

    A[0] = 3
  A[1] = 4
  A[2] = 4
  A[3] = 6
  A[4] = 1
  A[5] = 4
  A[6] = 4
  the function should return [3, 2, 2, 4, 2], as explained above.

    Assume that:

    N and M are integers within the range [1..100,000];
  each element of array A is an integer within the range [1..N + 1].
  Complexity:

    expected worst-case time complexity is O(N+M);
  expected worst-case space complexity is O(N), beyond input storage (not counting the storage required for input arguments).
  Elements of input arrays can be modified.

 */
