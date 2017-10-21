package lazyevaluation

/**
 * Created by abdhesh on 7/5/15.
 */

object Evaluation {
  def eagerEval(x: Int) = {
    println("eager")
    x
  }

  def lazyEval(x: => Int) = {
    println("lazy")
    x
  }
}

object EvaluationApp extends App {

  import Evaluation._

  def answer = {
    println("answer")
    30
  }

  eagerEval(answer + 2)
  println("-------------")
  lazyEval(answer + 2)
}
