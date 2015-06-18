package session2

/**
 * Created by abdhesh on 6/17/15.
 */
object OptionApp extends App {
  val name = null

  name.toString

  val absentGreeting: Option[String] = Option(null)
  //None
  val presentGreeting: Option[String] = Option("Hello!")

  case class User(
                   id: Int,
                   firstName: String,
                   lastName: String,
                   age: Int,
                   gender: Option[String])

  val users = Map(1 -> User(1, "John", "Doe", 32, Some("male")),
    2 -> User(2, "Johanna", "Doe", 30, None))

  val user2 = users.get(2)
  if (user2.isDefined) {
    println(user2.get.firstName)
  }

  println("User Name: " + user2.getOrElse("not specified"))


  val user = User(2, "Johanna", "Doe", 30, None)
  user.gender match {
    case Some(gender) => println("Gender: " + gender)
    case None => println("Gender: not specified")
  }

  val gender = user.gender match {
    case Some(gender) => gender
    case None => "not specified"
  }
  println("Gender: " + gender)

  //Options can be viewed as collections
  val userWithId2 = users.get(2)
  userWithId2.foreach(_user => println(user.firstName))


  val age = userWithId2.map(_.age)

  val usergender = userWithId2.map(_.gender)
  val usergender1 = userWithId2.flatMap(_.gender)
}
