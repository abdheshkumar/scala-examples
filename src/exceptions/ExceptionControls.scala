package exceptions

import scala.util.{Failure, Success, Try}

/**
 * Created by abdhesh on 6/19/15.
 */
object ExceptionControls {

  def catchException = {
    try {
      1 / 0
    } catch {
      case ex: Exception => println("got an arthimatic excpetion")
    } finally {
      println("finally block executed")
    }
  }

  def handleExceptionTry = {
    Try {
      10 / 2
    } match {
      case Success(result) => result
      case Failure(excepton) => excepton
    }
  }

  def handleExceptionEither: Either[Int, Throwable] = {
    try {
      Left(10 / 2)
    } catch {
      case ex: Exception => Right(ex)
    }
  }

  import scala.util.control.Exception._

  val result1 = allCatch.opt(1.toInt)
  val result2 = allCatch.opt("aa".toInt)
  val result3 = allCatch.toTry("a".toInt)
  val result4 = allCatch.either("a".toInt)

  val exceptions = Seq(classOf[ArithmeticException], classOf[NullPointerException])

  catching(exceptions: _*).opt {
    1 / 0
  }

  catching(exceptions: _*).either {
    1 / 0
  }

  def catchSpecificExceptions[A](exceptions: Class[_]*)(body: => A) =
    catching(exceptions: _*).either(body).fold({ t: Throwable => t match {
      case ex if (exceptions contains ex.getClass) => println("Gotta Exception [" + ex + "]") //log exception
        Left(ex)
      case ex: Exception => println("Gotta Exception [" + ex + "]") //log exception
        Left(ex)
    }
    }, data => Right(data))

  catchSpecificExceptions(classOf[ArithmeticException]) {
    val list = List(1, 2, 3)
    (list map (_ + 1) sum) / 2
  }

  catchSpecificExceptions(classOf[ArithmeticException]) {
    val list = List(1, 2, 3)
    (list map (_ + 1) sum) / 0
  }


}
