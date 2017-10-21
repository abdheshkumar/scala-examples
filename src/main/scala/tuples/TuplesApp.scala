package tuples

/**
 * Created by abdhesh on 6/25/15.
 */
object TuplesApp {
  def getStockInfo = {
    // other code here ...
    ("NFLX", 100.00, 101.00) // this is a Tuple3
  }

  val (symbol, currentPrice, bidPrice) = getStockInfo

  val result = getStockInfo
  result._1
  result._2
}
