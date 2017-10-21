package options

/**
 * Created by abdhesh on 6/17/15.
 */
case class User(name: String)

object OptionApp extends App {
  val name = null

  def objectToString(obj: Object) = println(obj.toString)

  //objectToString(name) // this will through NullPointerException
  val absentGreeting: Option[String] = Option(null) //return None

  absentGreeting.getOrElse("Hello") //Use of getOrElse


}
