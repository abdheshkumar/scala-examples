package arrays

object TestCodility extends App {

  def solution(A: Array[Int]): Int = {
    type Index = Int
    type Value = Int
    val length = A.length
    def inner(counter: Int, map: Map[Value, List[(Index, Int)]]): Int = {
      if (counter >= length) {
        val (v, items) = map.maxBy(_._2.length)
        math.abs(items.head._1 - items.last._1)
      } else {
        val value = A(counter)
        map.get(value) match {
          case Some(foundResult) =>
            val next = foundResult :+ (counter -> value)
            inner(counter + 1, map + (value -> next))
          case None =>
            inner(counter + 1, map + (value -> List((counter -> value))))
        }
      }
    }
    inner(0, Map.empty)

    /*A.zipWithIndex
      .groupBy(_._1)
      .map { f =>
        val h = f._2.head._2
        val t = f._2.last._2
        math.abs(t - h)
      }
      .max*/
  }

  /*def solution(A: Array[Int]): Int = {


    A.zipWithIndex
      .groupBy(_._1)
      .map { f =>
        val h = f._2.head._2
        val t = f._2.last._2
        math.abs(t - h)
      }
      .max
  }
   */
  def solution2(a: Int, b: Int): Int = {
    /* if ((a <= b) && a >= 1 && a <= 1000000000 && b >= 1 && b <= 1000000000) {
      val startSeed = scala.math.sqrt(a)
      val endSeed = scala.math.sqrt(b)
      (startSeed.toInt - 1 to endSeed.toInt).toList.foldLeft(0) { (acc, x) =>
        val v = x * (x + 1)
        if (v >= a && v <= b) acc + 1 else acc
      }
    } else sys.error("Input values not matched")*/
    if ((a <= b) && a >= 1 && a <= 1000000000 && b >= 1 && b <= 1000000000) {
      val startSeed = scala.math.sqrt(a)
      val endSeed = scala.math.sqrt(b)
      (startSeed.toInt - 1 to (endSeed.toInt)).toList.foldLeft(0) { (acc, x) =>
        val v = x * (x + 1)
        if (v >= a && v <= b) acc + 1 else acc
      }
    } else sys.error("Input values not matched")
  }

  /*def solution3() = {
    @scala.annotation.tailrec
    def recLength(firstBead: Int, nextIndex: Int, length: Int): Int = {
      val nextBead = a(nextIndex)
      if (firstBead == nextBead) {
        length
      } else recLength(firstBead, nextBead, length + 1)
    }
    a.foldLeft((0, 0, 0))({
        case ((longest, index, totalCounted), bead) => {
          val l = recLength(index, bead, 1)
          if (l > longest) {
            (longest, index + 1, totalCounted)
          } else {
            (l, index + 1, totalCounted)
          }
        }
      })
      ._1
  }*/

  def solution3(a: Array[Int]): Int = {
    // write your code in Scala 2.12
    @scala.annotation.tailrec
    def recLength(firstBead: Int,
                  bead: Int,
                  length: Int,
                  encounteredIndexes: Set[Int]): (Int, Set[Int]) = {
      if (firstBead == bead) {
        length -> encounteredIndexes
      } else
        recLength(firstBead, a(bead), length + 1, encounteredIndexes + bead)
    }
    val (longest, _, t) = a.foldLeft((0, 0, Set.empty[Int]))({
      case ((longest, index, allEncounteredIndexes), bead)
        if !allEncounteredIndexes.contains(index) => {
        val (l, allBeads) =
          recLength(index, bead, 1, allEncounteredIndexes + index)
        if (l > longest) {
          (l, index + 1, allBeads)
        } else {
          (longest, index + 1, allBeads)
        }
      }
      case ((longest, index, allEncounteredIndexes), _) =>
        (longest, index + 1, allEncounteredIndexes)
    })
    longest
  }
  println(solution(Array(6, 4, 2, 2, 6, 6, 1)))
  println(solution(Array.fill(50001)(2)))
  println(solution2(21, 29))

  //println(solution(Array.fill(3)(2)))
  println(List(2) :+ 1)
}
