object EquiLeader extends App {

  /* def findEquLeader(A: Array[Int], leader: Int, leaders: List[Int]) = {
    val aLimit = A.length
    def check(index: Int, limit: Int, found: Boolean): Boolean = {
      if (index >= limit) found
      else {
        if (A(index) == leader) true
        else check(index + 1, limit, found)
      }
    }
    def go(l: List[Int], count: Int): Int = {
      l match {
        case Nil => count
        case head :: tail =>
          if (check(0, head, false) && check(
                head + 1,
                aLimit,
                head + 1 == aLimit
              )) go(tail, count + 1)
          else go(tail, count)
        // 0 to head  and head +1 to length
      }
    }
    go(leaders, 0)
  }*/
  def findEq(A: Array[Int], dominator: Int, count: Int) = {
    var dominatorCopies1 = 0
    var dominatorCopies2 = count
    var numberEquiLeaders = 0
    var i = 0
    while (i < A.length) {
      if (A(i) == dominator) {
        dominatorCopies1 += 1
        dominatorCopies2 -= 1
      }
      if (dominatorCopies1 > (i + 1).toDouble / 2 && dominatorCopies2 > (A.length - i - 1).toDouble / 2)
        numberEquiLeaders += 1
      i += 1
    }
    numberEquiLeaders
  }
  //100%
  def solution(A: Array[Int]): Int = {
    val length = A.length
    val half = length / 2
    def go(index: Int, m: Map[Int, (List[Int], Int)], domintor: Int): Int = {
      if (index >= length)
        m.get(domintor).map(f => findEq(A, domintor, f._2)).getOrElse(0)
      else {
        val k = A(index)
        m.get(k) match {
          case Some((value, count)) =>
            if (count > half) {
              go(index + 1, m + (k -> ((index +: value) -> (count + 1))), k)
            } else
              go(
                index + 1,
                m + (k -> ((index +: value) -> (count + 1))),
                domintor
              )
          case None => go(index + 1, m + (k -> (List(index), 1)), domintor)
        }
      }
    }
    if (length != 0) go(0, Map.empty, A(0))
    else 0
  }

  val ar1 = Array(4, 3, 4, 4, 4, 2)
  val ar2 = Array(4, 4)
  //println(solution1(ar1))
  println(solution(ar1)) //4, 2
  println(solution(ar2)) // 4, 1
  println(solution(Array(1, 2))) //
  println(solution(Array(4, 4, 2, 5, 3, 4, 4, 4)))
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
