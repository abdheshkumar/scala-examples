package caseclasses

/**
 * Created by abdhesh on 6/25/15.
 */
object CompanionObject {

  case class Person(var name: String, var age: Int)

  object Person {
    def apply() = new Person("<no name>", 0)

    def apply(name: String) = new Person(name, 0)
  }

  val a = Person()
  // corresponds to apply()
  val b = Person("Pam")
  // corresponds to apply(name: String)
  val c = Person("William Shatner", 82)
  println(a)
  println(b)
  println(c)
  // verify the setter methods work
  a.name = "Leonard Nimoy"
  a.age = 82
  println(a)


  class Brain private {
    override def toString = "This is the brain."
  }

  object Brain {
    val brain = new Brain

    def getInstance = brain
  }
  val brain = Brain.getInstance
  println(brain)
  Brain.brain

}
