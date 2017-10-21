package functionalprogramming

/**
 * Created by abdhesh on 6/25/15.
 */
object FunctionsApp {
  def executeFunction(callback: () => Unit) {
    callback()
  }

  val sayHello1 = () => {
    println("Hello")
  }
  executeFunction(sayHello1)

  //Using Partially Applied Functions
  val sum = (a: Int, b: Int, c: Int) => a + b + c
  val f = sum(1, 2, _: Int)
  f(9)

  def wrap(prefix: String, html: String, suffix: String) = {
    prefix + html + suffix
  }

  val wrapWithDiv = wrap("<div>", _: String, "</div>")
  wrapWithDiv("<p>Hello, world</p>")

  def saySomething(prefix: String) = (s: String) => {
    prefix + " " + s
  }

  val sayHello = saySomething("Hello")
  sayHello("Al")

  // Creating Partial Functions
  val divide = (x: Int) => 42 / x
  //divide(0)

  val divideSafe = new PartialFunction[Int, Int] {
    def apply(x: Int) = 42 / x

    def isDefinedAt(x: Int) = x != 0
  }

  if (divideSafe.isDefinedAt(1)) divide(1)

  val divide2: PartialFunction[Int, Int] = {
    case d: Int if d != 0 => 42 / d
  }
  //divide2.isDefinedAt(0)
  /*
  A partial function of type PartialFunction[A, B] is a unary function where the domain
does not necessarily include all values of type A. The function isDefinedAt allows [you]
to test dynamically if a value is in the domain of the function.
   */

  // converts 1 to "one", etc., up to 5
  val convert1to5 = new PartialFunction[Int, String] {
    val nums = Array("one", "two", "three", "four", "five")

    def apply(i: Int) = nums(i - 1)

    def isDefinedAt(i: Int) = i > 0 && i < 6
  }
  // converts 6 to "six", etc., up to 10
  val convert6to10 = new PartialFunction[Int, String] {
    val nums = Array("six", "seven", "eight", "nine", "ten")

    def apply(i: Int) = nums(i - 6)

    def isDefinedAt(i: Int) = i > 5 && i < 11
  }

  val handle1to10 = convert1to5 orElse convert6to10

  handle1to10(3)
  handle1to10(8)


  /*List(0, 1, 2) map {
    divide2
  } *///fail

  List(0, 1, 2) collect {
    divide2
  }

  List(42, "cat") collect { case i: Int => i + 1 }

  val isEven: PartialFunction[Int, String] = {
    case x if x % 2 == 0 => x + " is even"
  }
  val sample = 1 to 5
  val evenNumbers = sample collect isEven

  val isOdd: PartialFunction[Int, String] = {
    case x if x % 2 == 1 => x + " is odd"
  }

  val numbers = sample map (isEven orElse isOdd)

  // Add up the numbers in a list, up to 100 max
  def max100Withreturn(ns: List[Int]): Int =
    ns.foldLeft(0) { (n, m) =>
      if (n + m > 100)
        return 100
      else
        n + m
    }

  // Add up the numbers in a list, up to 100 max
  def max100WithTailifElse(ns: List[Int]): Int = {
    def go(ns: List[Int], a: Int): Int =
      if (a >= 100) 100
      else ns match {
        case n :: ns => go(ns, n + a)
        case Nil => a
      }
    go(ns, 0)
  }

  // Add up the numbers in a list, up to 100 max
  def max100WithTailPattern(ns: List[Int]): Int = {

    def go(ns: List[Int], a: Int): Int =
      ns match {
        case Nil => a
        case ns if a >= 100 => 100
        case n :: ns => go(ns, n + a)
      }

    go(ns, 0)
  }

  def main(args: Array[String]) = {
    println(";:::::::::::" + max100WithTailPattern(1 to 50 toList))
  }
}
