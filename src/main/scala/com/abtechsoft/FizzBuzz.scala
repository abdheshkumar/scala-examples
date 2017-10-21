package com.abtechsoft

/**
  * Created by abdhesh on 5/20/15.
  */
object FizzBuzz extends App {

  def fizzBuzz(x: Int)(multipleOfNumbers: Seq[(String, Int)]): String = {
    val multipleOf = (number: Int, divideBy: Int) => number % divideBy == 0
    val result = multipleOfNumbers.collect { case (output, d) if multipleOf(x, d) => output }
    if (result.nonEmpty) result.mkString
    else x.toString
  }

  def `fizzBuzz-1`(x: Int): String = (x % 3, x % 5) match {
    case (0, 0) => "FizzBuzz"
    case (0, _) => "Fizz"
    case (_, 0) => "Buzz"
    case _ => x.toString
  }

  def `fizzBuzz-2`(x: Int): String = {
    if (x % 15 == 0) "FizzBuzz"
    else if (x % 3 == 0) "Fizz"
    else if (x % 5 == 0) "Buzz"
    else x.toString
  }

  val multipleOfNumbers = Seq(("Fizz", 3), ("Buzz", 5), ("Buzz-11", 11), ("Fizz-21", 21))
  val numbers = 1 to 100
  val fizzBuzzFun = fizzBuzz(_: Int)(multipleOfNumbers)
  numbers.foreach(fizzBuzzFun andThen println)
  numbers.foreach(`fizzBuzz-1` _ andThen println)
}
