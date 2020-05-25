package commons

/**
 * Created by abdhesh on 6/17/15.
 */
object PatternMatching {

  def printNum(int: Int) {
    int match {
      case 0 => println("Zero")
      case 1 => println("One")
      case _ => println("more than one")
    }
  }

  val anyList = List(1, "A", 2, 2.5, 'a')

  for (value <- anyList) {
    value match {
      case i: Int => println("Integer: " + i)
      case s: String => println("String: " + s)
      case f: Double => println("Double: " + f)
      case other => println("other datatype: " + other)
    }
  }

  //Testing Data Types

  def testDataType(in: Any) = in match {
    case s: String => "String, length " + s.length
    case i: Int if i > 0 => "Natural Int"
    case i: Int => "Another Int"
    case a: AnyRef => a.getClass.getName
    case _ => "null"
  }

  def sumOdd(in: List[Int]): Int = in match {
    case Nil => 0
    case x :: rest if x % 2 == 1 => x + sumOdd(rest)
    case _ :: rest => sumOdd(rest)
  }

  def noPairs[T](in: List[T]): List[T] = in match {
    case Nil => Nil
    case a :: b :: rest if a == b => noPairs(a :: rest)
    case a :: rest => a :: noPairs(rest)
  }

  def ignore(in: List[String]): List[String] = in match {
    case Nil => Nil
    case _ :: "ignore" :: rest => ignore(rest)
    // If the second element in the List is "ignore" then return the ignore
    // method run on the balance of the List
    case x :: rest => x :: ignore(rest)
    // return a List created with the first element of the List plus the
    // value of applying the ignore method to the rest of the List
  }


  def getStrings(in: List[Any]): List[String] = in match {
    case Nil => Nil
    case (s: String) :: rest => s :: getStrings(rest)
    case _ :: rest => getStrings(rest)
  }
}
