package patternmatching

/**
 * Created by abdhesh on 6/25/15.
 */
object VariableBindingApp {
  def matchType(x: Any): String = x match {
    case x@List(1, _*) => s"$x" // works; prints the list
    //case Some(_) => "got a Some" // works, but can't access the Some
    //case Some(x) => s"$x" // works, returns "foo"
    case x@Some(_) => s"$x" // works, returns "Some(foo)"
    case p@Person(first, "Doe") => s"$p" // works, returns "Person(John,Doe)"
  }

  println(matchType(List(1, 2, 3))) // prints "List(1, 2, 3)"
  println(matchType(Some("foo"))) // prints "Some(foo)"
  println(matchType(Person("John", "Doe"))) // prints "Person(John,Doe)"
}
