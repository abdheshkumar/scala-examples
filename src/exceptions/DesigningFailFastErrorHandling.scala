package exceptions

import scala.io.StdIn._

/**
 * Created by abdhesh on 6/19/15.
 */
object DesigningFailFastErrorHandling {

  Option(1) flatMap { x =>
    println("Got x")
    Option.empty[Int] flatMap { y =>
      println("Got y")
      Option(3) map { z =>
        println("Got z")
        x + y + z
      }
    }
  }

  for {
    x <- Option(1)
    y <- Option.empty[Int]
    z <- Option(3)
  } yield x + y + z

  def readInt: Either[String, Int] =
    try {
      Right(readLine.toInt)
    } catch {
      case exn: NumberFormatException => Left("Please enter a number")
    }


  for {
    x <- readInt.right
    y <- readInt.right
    z <- readInt.right
  } yield (x + y + z)
}
