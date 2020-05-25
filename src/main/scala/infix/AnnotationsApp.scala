package infix

object AnnotationsApp extends App {
  class Division @throws[ArithmeticException]( /*no annotation parameters*/ ) protected (
    num: Int,
    denom: Int
  ) {
    private[this] val wrongValue = num / denom

    /** Integer number
      * @param num Value */
    protected[Division] def this(num: Int) {
      this(num, 1)
    }
  }
  object Division {
    def apply(num: Int) = new Division(num)
    def apply(num: Int, denom: Int) = new Division(num, denom)
  }

  case class Division01 @throws[ArithmeticException]("denom is 0")(num: Int,
                                                                   denom: Int) {
    private[this] val wrongValue = num / denom
  }

  // Create Annotation `Mammal`
  class Mammal(indigenous:String) extends scala.annotation.StaticAnnotation
  // Annotate class Platypus as a `Mammal`
  @Mammal(indigenous = "North America")
  class Platypus{}
  //Annotations can then be interrogated using the reflection API.


}
