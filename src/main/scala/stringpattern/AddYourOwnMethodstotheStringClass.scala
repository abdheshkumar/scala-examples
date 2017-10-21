package stringpattern

/**
 * Created by abdhesh on 6/24/15.
 */

object StringUtils {

  implicit class StringImprovements(val s: String) {
    def increment = s.map(c => (c + 1).toChar)
  }

}

object AddYourOwnMethodstotheStringClass {

  import StringUtils._

  val result = "HAL".increment
}
