package commons

/**
 * Created by abdhesh on 6/17/15.
 */
object PartialAppliedFunctionApp extends App {
  val table = (number: Int, count: Int) => number * count

  val rows = 1 to 10
  val columns = 1 to 10

  rows foreach {
    row =>
      columns foreach {
        column =>
          print(table(row, column) + "\t")
      }
      println()
  }
}
