package commons

/**
 * Created by abdhesh on 6/17/15.
 */
object TailCall extends App {

  def factorial(number: Int): Int = {
    if (number == 1) 1
    else number * factorial(number - 1)
  }

  @annotation.tailrec
  def factorial(accumulator: Int, number: Int): Int = {
    if (number == 1)
      return accumulator
    factorial(number * accumulator, number - 1)
  }

}