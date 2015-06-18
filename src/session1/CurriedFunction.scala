package session1

/**
 * Created by abdhesh on 6/17/15.
 */
object CurriedFunction {
  /*
  Curried Function
  Currying converts a function with multiple parameters creating a chain of function, each expecting a single
  parameter.
   */
  def add1(x: Int, y: Int) = x + y

  def add2(x: Int)(y: Int) = x + y

  def add3(x: Int) = (y: Int) => x + y
}
