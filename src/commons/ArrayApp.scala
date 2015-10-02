package commons

/**
 * Created by abdhesh on 6/17/15.
 */
object ArrayApp extends App {
  val numbers = Array(1, 2, 3, 4)
  val first = numbers(0) // read the first element
  numbers(3) = 100 // replace the 4th array element with 100
  val biggerNumbers = numbers.map(_ * 2) // multiply all numbers by two
}
