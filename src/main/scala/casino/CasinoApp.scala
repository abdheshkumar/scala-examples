package casino

import scala.annotation.tailrec

object CasinoApp extends App {

  def solution(n: Int, k: Int): Int = {
    @tailrec
    def innerSolution(k: Int, nrOfrounds: Int, totalChipsRequired: Int): Int = {
      if (totalChipsRequired == n) nrOfrounds
      else if (k > 0 && totalChipsRequired == math.abs(n / 4)) innerSolution(k - 1, nrOfrounds + 1, totalChipsRequired * 2)
      else if (k > 0 && totalChipsRequired == math.abs(n / 2)) innerSolution(k - 1, nrOfrounds + 1, totalChipsRequired * 2)
      else innerSolution(k, nrOfrounds + 1, totalChipsRequired + 1)
    }

    innerSolution(k, 0, 1)
  }

  println(solution(10, 10)) //4
  println(solution(18, 2)) //6
  println(solution(8, 0)) //7
}
