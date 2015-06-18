package session1

/**
 * Created by abdhesh on 6/11/15.
 */
object Main extends App {
  //The strength of expression-oriented

  val test = if (3 > 2) true else false

  //Referential Transparency
  val x = 20
  val y: Int = 30
  val z = x + y

  //A pure function is referentially transparent and has no side effects.

  def processMessage(message: String): Unit = println(message)

  //Function Literal/Anonymous Function

  val add = (x: Int, y: Int) => x + y
  val result = add(x, y)

  val areaOfRectangle: (Int, Int) => Int = (width: Int, height: Int) => {
    width * height
  }

  val areaOfRectangleA = new Function2[Int, Int, Int] {
    def apply(a: Int, b: Int): Int = {
      a + b
    }
  }

  val areaOfRectangleOmitParenthesis: (Int, Int) => Int = (width: Int, height: Int) => width * height

  areaOfRectangle(5, 3)
  areaOfRectangle.apply(5, 3)

  /*trait Function2[-T1, -T2, +R] extends AnyRef {
    abstract def apply(v1: T1, v2: T2): R
  }*/

  /*
  For every function that you define in Scala, the compiler comes up with an instance of the appropriate
  Function Trait, where the type parameters are parameterized with the given types of the arguments and the
  return type of the function.
   */

  /*
  (Int, Int) =>Int
  This is same as illustrated here:
  Function2[Int,Int,Int]
  So we could have also defined our add function this way:
*/
  val areaOfRectangleFunction2: Function2[Int, Int, Int] = (width: Int, height: Int) => {
    width * height
  }

}
