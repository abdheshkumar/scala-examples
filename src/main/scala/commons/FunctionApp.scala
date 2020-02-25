package commons

/**
 * Created by abdhesh on 6/17/15.
 */
object FunctionApp {
  //0
  val javaVersion = () => sys.props("java.version")

  val anonfun0 = new Function0[String] {
    def apply(): String = sys.props("java.version")
  }
  assert(javaVersion() == anonfun0())

  //1
  val succ = (x: Int) => x + 1
  val anonfun1 = new Function1[Int, Int] {
    def apply(x: Int): Int = x + 1
  }
  assert(succ(0) == anonfun1(0))

  //2
  val max = (x: Int, y: Int) => if (x < y) y else x

  val anonfun2 = new ((Int, Int) => Int) {
    def apply(x: Int, y: Int): Int = if (x < y) y else x
  }
  assert(max(0, 1) == anonfun2(0, 1))
  //3
}
