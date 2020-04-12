package futuretraining

object ArrayInversionCount extends App {
  //100%
  def solution(a: Array[Int]): Int = {
    // write your code in Scala 2.12
    val length = a.length
    def get(currentElem: Int, index: Int, count: Int): Int = {
      if (index >= length) count
      else {
        if (a(index) < currentElem) get(currentElem, index + 1, count + 1)
        else get(currentElem, index + 1, count)
      }
    }
    def go(index: Int, count: Int): Int = {
      if (count >= 1000000000) -1
      else if (index >= length) count
      else {
        val next = index + 1
        val c = get(a(index), next, count)
        go(next, c)
      }
    }

    go(0, 0)
  }
  //63%
  def solution1(a: Array[Int]) = {
    val A = a.zipWithIndex
    val result = A.foldLeft(List.empty[(Int, Int)]) {
      case (acc, (value, index)) =>
        val aa = A
          .drop(index + 1)
          .filter { r =>
            r._1 < value
          }
          .map {
            case (v, i) =>
              (index, i)
          }
          .toList
        aa ++ acc
    }

    result.length
  }
  println(solution(Array(-1, 6, 3, 4, 7, 4)))

}
