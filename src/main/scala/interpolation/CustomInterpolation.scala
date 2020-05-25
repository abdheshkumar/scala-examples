package interpolation

object CustomInterpolation {
  case class Person(name: String, age: Int)
  val s = "Test"

  implicit class PersonSyntax(sc: StringContext) {
    def person(args: Any*): Person = {
      val parts = sc.parts //the things between the args
      val totalString = sc.s(args: _*).split(",")
      Person(totalString(0), totalString(1).toInt)
    }
  }

  def main(args: Array[String]): Unit = {
    val p = person"Bob,20"
    println(p)
    val bob = "Bob"
    val age = "20"
    val p1 = person"$bob,$age"
    println(p1)

  }
}
