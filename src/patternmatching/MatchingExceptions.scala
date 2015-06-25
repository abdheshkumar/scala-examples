package patternmatching

import java.io.{FileNotFoundException, IOException}

/**
 * Created by abdhesh on 6/25/15.
 */
object MatchingExceptions {
  val s = "Foo"
  try {
    val i = s.toInt
  } catch {
    case e: Exception => e.printStackTrace
  }

  try {
    //openAndReadAFile(filename)
  } catch {
    case e: FileNotFoundException => println("Couldn't find that file.")
    case e: IOException => println("Had an IOException trying to read that file")
  }

  @throws(classOf[NumberFormatException])
  def toInt(s: String): Option[Int] =
    try {
      Some(s.toInt)
    } catch {
      case e: NumberFormatException => throw e
    }
}
