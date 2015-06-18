package session3

/**
 * Created by abdhesh on 6/11/15.
 */
object Main extends App {

  /**
   * how to write a loop without mutation
   * there is no additional work left to do after the recursive call returns is said be
   * tail recursion.
   */

  def factorial(n: Int): Int = {
    @annotation.tailrec
    def go(n: Int, acc: Int): Int =
      if (n <= 0) acc
      else go(n - 1, n * acc)

    go(n, 1)
  }

  def formatResult(name: String, n: Int, f: Int => Int) = {
    val msg = "The %s of %d is %d."
    msg.format(n, f(n))
  }

  /**
   * Annonymous functions
   */
  def annonymousFuntions = {
    println(formatResult("absolute value", -42, math.abs))
    println(formatResult("factorial", 7, factorial))
    println(formatResult("increment", 7, (x: Int) => x + 1))
    println(formatResult("increment2", 7, (x) => x + 1))
    println(formatResult("increment3", 7, x => x + 1))
    println(formatResult("increment4", 7, _ + 1))
    println(formatResult("increment5", 7, x => {
      val r = x + 1;
      r
    }))
  }

  /**
   * Functions are ordinary objects
   * functions and methods are not exactly the same thing in Scala
   */
  val lessThan = new Function2[Int, Int, Boolean] {
    def apply(a: Int, b: Int) = a < b
  }

  /**
  this is really syntax sugar for  object creation:
  functions are really just ordinary Scala objects, we say that they are
  first-class values. We will often use "function" to refer to either such a first-class function
    */
  val fun = (a: Int, b: Int) => a < b

  def binarySearch(ds: Array[Double], key: Double): Int = {
    @annotation.tailrec
    def go(low: Int, mid: Int, high: Int): Int = {
      if (low > high) -mid - 1
      else {
        val mid2 = (low + high) / 2
        val d = ds(mid2)
        if (d == key) mid2
        else if (d > key) go(low, mid2, mid2 - 1)
        else go(mid2 + 1, mid2, high)
      }
    }
    go(0, 0, ds.length - 1)
  }
}
