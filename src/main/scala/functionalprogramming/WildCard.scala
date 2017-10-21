package functionalprogramming

/**
 * Created by abdhesh on 6/25/15.
 */
object WildCard {
  val x = List.range(1, 10)
  val evens1 = x.filter((i: Int) => i % 2 == 0)
  val evens2 = x.filter(i => i % 2 == 0)
  val evens3 = x.filter(_ % 2 == 0)

  // => symbol as a transformer


}
