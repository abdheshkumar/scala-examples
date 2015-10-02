package commons

/**
 * Created by abdhesh on 6/17/15.
 */
object ClosureApp extends App {
  /*Closure
A closure is a function, whose return value depends on the value of one or more variables declared outside
this function
  */
  var y = 3
  val multiplier = (x: Int) => x * y
  multiplier(3)
  y = 4
  multiplier(3)
}
