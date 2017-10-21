package partialapplied

/**
 * Created by abdhesh on 7/5/15.
 */
object PartialAppliedApp extends App {
  val sum = (a: Int, b: Int, c: Int) => a + b + c
  println(sum(1, 2, 3))
  val partialSum = sum(1, _: Int, 3)
  println(partialSum(2))
}

object Processor {
  val apply = (func: Int => Int, x: Int) => func(x)
  val f = (arg: Int) => arg * 2
  println(apply(f, 10))
}