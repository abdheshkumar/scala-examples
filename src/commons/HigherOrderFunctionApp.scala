package commons

/**
 * Created by abdhesh on 6/17/15.
 */
object HigherOrderFunctionApp extends App {
  /*
First Class Function and Higher Order Function
In functional programming, functions are first-class citizens, meaning functions can be assigned
to variables, functions can be passed to other functions, and functions can be returned as values from other
functions. And such functions, which take functions as arguments or return a function, are called higher-order
functions.
   */

  //Function as Variable
  val doubler = (i: Int) => {
    i * 2
  }
  doubler(2)

  //Function as Parameter
  def operation(functionparam: (Int, Int) => Int) {
    println(functionparam(4, 4))
  }

  val add = (x: Int, y: Int) => x + y
  val multiply = (x: Int, y: Int) => x * y
  val subtract = (x: Int, y: Int) => {
    x - y
  }
  operation(add)
  operation(multiply)
  operation(subtract)

  //Returning a Function
  (name: String) => {
    "hello" + " " + name
  }

  def greeting() = (name: String) => {
    "hello" + " " + name
  }

  val greet = greeting()
  val _greet = greeting
  greet("Reader")
  _greet("Hello, Harvester Team")
}
