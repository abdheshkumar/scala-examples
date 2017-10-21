package options

/**
 * Created by abdhesh on 6/17/15.
 */
case class User(name: String)

object OptionApp extends App {
  val name = null

  def objectToString(obj: Object) = println(obj.toString)

  //objectToString(name)
  val absentGreeting: Option[String] = Option(null)

  absentGreeting.getOrElse("Hello")
  //None


}
