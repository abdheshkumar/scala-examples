package session5

/**
 * Created by abdhesh on 6/11/15.
 */
object Main extends App {
  List(1, 2, 3, 4) map (_ + 10) filter (_ % 2 == 0) map (_ * 3)
  false && {
    println("!!");
    true
  } // does not print anything

  true || {
    println("!!")
    true
  }

  // doesn't print anything either

  /**
   * In Scala, we can write non-strict functions by accepting some of our
   * arguments unevaluated, using the following syntax:
   */

  def if2[A](cond: Boolean, onTrue: => A, onFalse: => A): A =
    if (cond) onTrue else onFalse

  //Call by-name parameter
  def pairByname(i: => Int) = (i, i)

  //Call by-value parameter
  def pairByValue(i: Int) = (i, i)

  def pairByLazy(i: => Int) = {
    lazy val j = i
    (j, j)
  }

  /*pairByname {
    println("hi")
    1 + 41
  }*/

  /*pairByValue {
    println("hi")
    1 + 41
  }*/
  pairByLazy {
    println("hi")
    1 + 41
  }
}
