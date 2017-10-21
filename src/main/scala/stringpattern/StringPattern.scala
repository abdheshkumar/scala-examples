package stringpattern

import scala.util.matching.Regex

/**
 * Created by abdhesh on 6/24/15.
 */
object StringPattern {
//Finding Patterns in Strings
val numPattern = "[0-9]+".r
  val address = "123 Main Street Suite 101"
  val match1 = numPattern.findFirstIn(address)
  val matches = numPattern.findAllIn(address)
  matches.foreach(println)
  val matches1 = numPattern.findAllIn(address).toArray
  val numPattern1 = new Regex("[0-9]+")
  val address1= "123 Main Street Suite 101"
  val match11 = numPattern.findFirstIn(address)

  //Replacing Patterns in Strings
}
