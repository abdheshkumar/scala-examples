package stringinterpolation

/**
 * Created by abdhesh on 6/11/15.
 */
object Main extends App {
  val name = "Jakson"
  val age = 33
  val weight = 200.00
  println(s"$name is $age years old, and weighs $weight pounds.")

  case class Student(name: String, score: Int)

  val hannah = Student("Hannah", 95)
  println(s"${hannah.name} has a score of ${hannah.score}")

  println(s"$hannah.name has a score of $hannah.score")
  //The f string interpolator (printf style formatting)
  println(f"$name is $age years old, and weighs $weight%.2f pounds.")
  println(f"$name is $age years old, and weighs $weight%.0f pounds.")

  //The raw interpolator
  s"foo\nbar"
  raw"foo\nbar"

  val tostring: String =
    "%s %s, age %d".format(name, hannah.name, age)
}
