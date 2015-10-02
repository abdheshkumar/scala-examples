package lazyevaluation

/**
 * Created by abdhesh on 7/22/15.
 */
object LazyVal {
  //lazy will not be executed until the first time the value is accessed.
  def main(arga: Array[String]) = {
    val normalVal = {
      println("---->>>   Initializing normal val    <<<----")
      "This is the normal val"
    }

    lazy val lazyVal = {
      println("---->>>   Initializing lazy val    <<<----")
      "This is the lazy val"
    }

    println("\n\nno references have been made yet\n\n")

    println("Accessing normal val : ")
    println(normalVal)
    println("Accessing lazy val : ")
    println("\\n\\nAccessing lazy val a second time, there should be no initialization now:")
    println(lazyVal)
  }

}
