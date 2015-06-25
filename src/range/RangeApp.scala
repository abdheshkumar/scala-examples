package range

/**
 * Created by abdhesh on 6/25/15.
 */
object RangeApp {
  //Creating a Range, List, or Array of Numbers
  val r = 1 to 10
  val r1 = 1 to 10 by 2
  val r2 = 1 to 10 by 3
  //toArray,toList,toSeq,toSet
  ///yield construct
  for (i <- 1 to 5) yield i * 2
}
