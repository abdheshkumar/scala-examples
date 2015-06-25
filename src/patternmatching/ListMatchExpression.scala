package patternmatching

/**
 * Created by abdhesh on 6/25/15.
 */
object ListMatchExpression {
  def listToString(list: List[String]): String = list match {
    case s :: rest => s + " " + listToString(rest)
    case Nil => ""
  }

  val fruits = "Apples" :: "Bananas" :: "Oranges" :: Nil

  listToString(fruits)

  def sum(list: List[Int]): Int = list match {
    case Nil => 1
    case n :: rest => n + sum(rest)
  }

  def multiply(list: List[Int]): Int = list match {
    case Nil => 1
    case n :: rest => n * multiply(rest)
  }

  val nums = List(1, 2, 3, 4, 5)
  sum(nums)
  multiply(nums)
}
