package session1

/**
 * Created by abdhesh on 6/17/15.
 */
object PatternMatchingAsFunctions extends App {

  case class User(name: String)

  val list = List("Hello,world", 34, 45.45, User("mvtech"))
  list.filter(a => a match {
    case s: String => true
    case _ => false
  })

  //into the following snippet:

  list.filter {
    case s: String => true
    case _ => false
  }

  val even: PartialFunction[Int, String] = {
    case number if number % 2 == 0 => "Even"
  }

  val odd: PartialFunction[Int, String] = {
    case number if number % 2 != 0 => "Odd"
  }

  val nothing: PartialFunction[Int, String] = {
    case number => "Nothing"
  }

  val funResult = even orElse odd orElse nothing

  funResult(10)
}
