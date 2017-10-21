package options

/**
 * Created by abdhesh on 7/22/15.
 */
object OptionFolding {


  def objectToString(obj: Object) = println(obj.toString)

  val presentGreeting: Option[String] = Option("Hello!, World")

  if (presentGreeting.isDefined) {
    objectToString(presentGreeting.get)
  }

  presentGreeting.foreach(f => objectToString(f))

  presentGreeting.map(f => objectToString(f))

  presentGreeting.filterNot(_.isEmpty).map(f => objectToString(f))

  val matchedStr = presentGreeting.find(f => f == "Hello")

  val matchedStr1 = presentGreeting.find(_ == "Hello")

  val isExists = presentGreeting.exists(_ == "Hello")

  val isExists1 = presentGreeting.exists(_ == "Hello")

  presentGreeting.foreach(f => objectToString(f))


  val users = Map(1 -> User("John"), 2 -> User("Johanna"))

  val user2 = users.get(2)
  if (user2.isDefined) {
    println(user2.get.name)
  }

  println("User Name: " + user2.getOrElse("not specified"))

  println("User Name: " + users.getOrElse(1, "not specified"))


  val userIds = List(1, 2, 3, 4)

  val matchedUserMap = userIds.map(userId => users.get(userId))

  matchedUserMap.foreach {
    matchedUser =>
      if (matchedUser.isDefined) println("User Name:" + matchedUser.get)
      else println("Not found User")
  }

  matchedUserMap.foreach {
    matchedUser =>
      matchedUser.fold {
        //Execute list of statement here
        println("Not found User")
      } {
        user => println("User Name:" + user)
      }

  }

  matchedUserMap.foreach {
    matchedUser =>
      val user = matchedUser.fold("Not found User") {
        user =>
          //here you can execute statements
          "User Name:" + user
      }

      println(user)
  }

  val matchedUserFlatmap = userIds.flatMap(userId => users.get(userId))

  matchedUserFlatmap.foreach {
    matchedUser =>
      println("User Name:" + matchedUser)
  }
}


