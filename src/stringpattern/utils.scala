package stringpattern

/**
 * Created by abdhesh on 6/24/15.
 */
package object utils {

  implicit class StringImprovements(val s: String) {
    def increment = s.map(c => (c + 1).toChar)
  }

}

class StringImprovements(val s: String) {
  def increment: String = s.map(c => (c + 1).toChar)

  def decrement: String = s.map(c => (c − 1).toChar)

  def hideAll: String = s.replaceAll(".", "*")
}

object StringImprovementsApp {
  //Absence of explicit return type for implicit functions can make compilation slower
  implicit def stringToString(s: String): StringImprovements = new StringImprovements(s)

  "HAL".increment
}