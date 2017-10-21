package exceptions

import scala.io.StdIn
import scala.util.{Failure, Success, Try}

/**
 * Created by abdhesh on 6/19/15.
 */
object ExceptionsApp {

  def divide: Try[Int] = {
    val dividend = Try(StdIn.readLine("Enter an Int that you'd like to divide:\n").toInt)
    val divisor = Try(StdIn.readLine("Enter an Int that you'd like to divide by:\n").toInt)
    val problem = dividend.flatMap(x => divisor.map(y => x / y))
    problem match {
      case Success(v) =>
        println("Result of " + dividend.get + "/" + divisor.get + " is: " + v)
        Success(v)
      case Failure(e) =>
        println("You must've divided by zero or entered something that's not an Int. Try again!")
        println("Info from the exception: " + e.getMessage)
        divide
    }
  }
}