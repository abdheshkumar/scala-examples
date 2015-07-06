package lazyevaluation

/**
 * Created by abdhesh on 7/5/15.
 */

object MyAssert {
  var assertionEnabled = true

  def basicAssert(predicate: Boolean) =
    if (assertionEnabled && !predicate) throw new AssertionError


  def byNameAssert(predicate: => Boolean) =
    if (assertionEnabled && !predicate) throw new AssertionError
}

object MyAssertApp extends App {

  import MyAssert._

  /*Test to determine if 6<3 return true/false will only occur if the assertionEnabled property
    is true. However, the expression 6<3 will always be evaluated whether the assertionEnabled
    property is true or false.
    */
  basicAssert(6 < 3)

  byNameAssert(6 < 3)
}
