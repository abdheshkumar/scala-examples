package lazyevaluation

/**
 * Created by abdhesh on 7/5/15.
 */


object ExpensiveOperation {
  val answer = {
    println("answer")
    20
  }

  def byNameParameter(x: => Int) = {
    val _x = x
    0 to 3 foreach {
      f => _x
    }
  }
}

object ExpensiveOperationApp extends App {

  //byNameParameter(answer)
  ExpensiveOperation.byNameParameter {

    println("answer")
    20
  }

}
