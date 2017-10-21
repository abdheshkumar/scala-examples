package controls

import scala.annotation.tailrec

/**
 * Created by abdhesh on 6/25/15.
 */
object CustomControl {
  var i = 0
  whilst(i < 5) {
    println(i)
    i += 1
  }

  /*  def whilst(testCondition: => Boolean)(codeBlock: => Unit) {
      while (testCondition) {
        codeBlock
      }
    }*/

  @tailrec
  def whilst(testCondition: => Boolean)(codeBlock: => Unit) {
    if (testCondition) {
      codeBlock
      whilst(testCondition)(codeBlock)
    }
  }
}
