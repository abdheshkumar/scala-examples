/**
 * Created by abdhesh on 5/20/15.
 */
object MainApp extends App {
  val ZERO = 0
  /*val multipleof: PartialFunction[Int, Unit] = {
    case number: Int if (number % 21 == 0 ) => println("Yip")
    case number: Int if (number % 3 == 0 && number % 5 == 0) => println("YeeHaa")
    case number: Int if (number % 11 == 0) => println("Woo")
    case number: Int if (number % 3 == 0) => println("Yee")
    case number: Int if (number % 5 == 0) => println("Haa")
    case number => println(number)
  }*/

  /*
    val multipleofThree: PartialFunction[Int, Unit] = {
      case number: Int if number % 3 == 0 => println("Yee")
    }

    val multipleofFive: PartialFunction[Int, Unit] = {
      case number: Int if number % 5 == 0 => println("Haa")
    }

    val multipleofBoth: PartialFunction[Int, Unit] = {
      case number: Int if number % 3 == 0 && number % 5 == 0 => println("YeeHaa")
    }

    val multipleof11: PartialFunction[Int, Unit] = {
      case number: Int if number % 11 == 0 => println("Woo")
    }

    val multipleof21: PartialFunction[Int, Unit] = {
      case number: Int if number % 21 == 0 => print("Yip \n")
    }
    val noneOf: PartialFunction[Int, Unit] = {
      case number => println(number)
    }

    val multipleOfNumber = multipleofThree orElse multipleof21 orElse multipleofFive orElse multipleofBoth orElse multipleof11 orElse noneOf
    val isMultipleOf = (number: Int, multipleof: Int) => number % multipleof == 0
  */

  val isMultipleOf = (number: Int, multipleof: Int) => number % multipleof == 0
  val multipleOfNumbers = Map(
    ("Yee", isMultipleOf(_: Int, 3)),
    ("Haa", isMultipleOf(_: Int, 5)),
    ("Woo", isMultipleOf(_: Int, 11)),
    ("Yie", isMultipleOf(_: Int, 21))
  )
  val numbers = 1 to 100
  numbers.foreach {
    f =>

      val result = multipleOfNumbers.filter {
        case (key, fun) => fun(f)
      }.keys
      if (result.isEmpty) println(f)
      else println(result.mkString)
  }
}
