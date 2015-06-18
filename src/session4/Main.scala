package session4

/**
 * Created by abdhesh on 6/11/15.
 */
object Main extends App {
  /**
   * throwing an exception breaks referential transparency
   */
  def failingFn(i: Int): Int = {
    val x: Int = 0 //throw new Exception("fail!")
    try {
      val y = 42 + 5
      x + y
    }
    catch {
      case e: Exception => 43
    }
  }

  /**
   * When we use it to indicate success or failure, by convention the Left constructor is reserved for the failure case
   */
  def mean(xs: IndexedSeq[Double]): Either[String, Double] =
    if (xs.isEmpty)
      Left("mean of empty list!")
    else
      Right(xs.sum / xs.length)

  val right: Either[String, Double] = Right(42)
  val left: Either[String, Double] = Left("invalid name")
  val double = Right(1000000.0)
}
