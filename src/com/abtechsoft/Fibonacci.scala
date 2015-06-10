package com.abtechsoft

/**
 * Created by abdhesh on 6/11/15.
 */
object Fibonacci {

  def fib(count: Int): List[Int] = {
    val b = List(1, 1).toBuffer
    while (b.size < count) b += b.takeRight(2).sum
    b.toList
  }

  def fibAdd(l: List[Int], count: Int): List[Int] = {
    val b = l.toBuffer
    for (i <- 1 to count) b += b.takeRight(2).sum
    b.toList
  }

  val more = fibAdd(List(1, 1, 2, 3), 10)

  def fibAddRecursive(l: List[Int], count: Int): List[Int] = {
    if (count < 1) l
    else {
      val k = l :+ l.takeRight(2).sum
      fibAdd(k, count - 1)
    }
  }

  def fib(a: Long, b: Long): Stream[Long] = a #:: fib(b, a + b)

  val fibonaccis = fib(1, 1).take(100).toList

  val report = fibonaccis grouped 10 map (_.mkString(","))

  report foreach println
}
