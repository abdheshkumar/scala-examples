package parameters

/**
 * Created by abdhesh on 6/25/15.
 */
object VariableArgument {
  def printAll(strings: String*) = {
    strings.foreach(println)
  }

  // these all work
  printAll()
  printAll("foo")
  printAll("foo", "bar")
  printAll("foo", "bar", "baz")

  //Use _* to adapt a sequence
  // a sequence of strings
  val fruits = List("apple", "banana", "cherry")
  // pass the sequence to the varargs field
  printAll(fruits: _*)
}
