package session1

/**
 * Created by abdhesh on 6/17/15.
 */
object PatternMatchingandCaseClasses extends App {

  case class Person(name: String, age: Int, valid: Boolean)

  val p = Person("David", 45, true)
  val name = p.name
  val m = new Person("Martin", 44, true)
  //By default, the properties are read-only, and the case class is immutable.

  //p.name = "Martin odersky"

  def older(p: Person): Option[String] = p match {
    case Person(name, age, valid) if age > 35 => Some(name)
    case _ => None
  }

  //Nested Pattern Matching in Case Classes
  case class MarriedPerson(name: String,
                           age: Int,
                           valid: Boolean,
                           spouse: Person)

  val sally = MarriedPerson("Sally", 24, true, p)

  //Let’s create a method that returns the name of someone who is older or has a spouse who is older:
  def mOlder(p: Person): Option[String] = p match {
    case Person(name, age, true) if age > 35 => Some(name)
    case _ => None
  }

}
