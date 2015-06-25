package exceptions

import java.io.IOException
import javax.sound.sampled.{LineUnavailableException, UnsupportedAudioFileException}

/**
 * Created by abdhesh on 6/19/15.
 */
object CustomExceptions {

  case class Customer(age: Int)

  class Cigarettes

  case class UnderAgeException(message: String) extends Exception(message)

  def buyCigarettes(customer: Customer): Cigarettes =
    if (customer.age < 16)
      throw UnderAgeException(s"Customer must be older than 16 but was ${customer.age}")
    else new Cigarettes

  val youngCustomer = Customer(15)
  try {
    buyCigarettes(youngCustomer)
    "Yo, here are your cancer sticks! Happy smokin'!"
  } catch {
    case UnderAgeException(msg) => msg
  }

  @throws(classOf[IOException])
  @throws(classOf[LineUnavailableException])
  @throws(classOf[UnsupportedAudioFileException])
  def playSoundFileWithJavaAudio {
    // exception throwing code here ...
  }
}
