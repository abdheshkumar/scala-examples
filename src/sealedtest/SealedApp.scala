package sealedtest

/**
 * Created by abdhesh on 10/2/15.
 */
object SealedApp extends App {

}

sealed abstract class LogMessage

case class StringMessage(message: String) extends LogMessage

case class ExceptionMessage(exception: Throwable) extends LogMessage

case class BothMessage(message: String, exception: Throwable) extends LogMessage

class Logger {
  def debug(l: LogMessage) = log(10, l)

  def info(l: LogMessage) = log(5, l)

  def error(l: LogMessage) = log(1, l)

  def log(level: Int, l: LogMessage): Unit = l match {
    case StringMessage(msg) => println(msg)
    case ExceptionMessage(exception: Error) => exception.printStackTrace()
    case ExceptionMessage(ex) => println(ex.toString)
  }
}