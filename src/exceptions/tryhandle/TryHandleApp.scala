package exceptions.tryhandle

import scala.util.Try

/**
 * Created by abdhesh on 7/22/15.
 */
object TryHandleApp extends App {

  val resultRecover = Try("abc".toInt).recover {
    case e: NumberFormatException => 0
  }


  val resultRecoverWith = Try("abc".toInt).recoverWith {
    case e: NumberFormatException => Try(0)
  }


  println(resultRecover)
}
