package session6

import scala.collection.immutable.LinearSeq

/**
 * Created by abdhesh on 6/11/15.
 */
object Main extends App {
  val seq = Seq(1, 2, 3)
  val set = Set(1, 2, 3)
  val map = Map(1 -> "a", 2 -> "b", 3 -> "c")
  val indexedSeq = IndexedSeq(1, 2, 3)
  val linearSeq = LinearSeq(1, 2, 3)

  1 :: 2 :: 3 :: Nil
  new ::(1, new ::(2, new ::(3, Nil)))
  ::(1, ::(2, ::(3, Nil)))

  val isOdd = (elem: Int) => elem % 2 == 0
  val isEven = (elem: Int) => elem % 2 != 0

  List(1, 2, 3).filter(x => x % 2 == 1)
  List(1, 2, 3).filter(x => isOdd(x))
  List(1, 2, 3).filter(isOdd)

  //Transformation
  val lowerCapsList = List("A", "Cat").map(s => s.toLowerCase)

  //Reduce
  val largest = (a: Int, b: Int) => if (a > b) a else b
  List(8, 6, 22, 2).reduceLeft((a, b) => a max b)
  List(8, 6, 22, 2).reduceLeft((a, b) => largest(a, b))
  List(8, 6, 22, 2).reduceLeft(largest)
  List(8, 6, 22, 2).reduceLeft(_ max _)
  List(1, 2).reduceLeft { (a, b) =>
    println((a -> b))
    a max b
  }
  //reduceLeft throws an exception on an Nil (empty) List

  List(1, 2, 3, 4).foldLeft(0)(_ + _)
  List(1, 2, 3, 4).foldLeft(1)(_ * _)


  val n = (1 to 3).toList
  val resultMap = n.map(i => n.map(j => i * j))
  val resultFlateMap = n.flatMap(i => n.map(j => i * j))

  1 to 10
  1 until 10
  1 to 10 by 2
  'a' to 'c'
}
