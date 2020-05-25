package iterations

object TinyUrl extends App {
  val setOfChars = ('a' to 'z').mkString + ('A' to 'Z').mkString + (0 to 9).mkString

  def base62Encode(n: Long): String = {
    def go(r: Long, result: String): String = {
      r
      if (r <= 0) result
      else go(r / 2, setOfChars((r % 2).toInt).toString + result)
    }
    go(n, "")
  }

  import java.math.BigInteger

  def getBase10From62(longNumber: Long) = {
    val number = longNumber + ""
    var value = BigInteger.ZERO
    for (c <- number.toCharArray) {
      value = value.multiply(BigInteger.valueOf(62))
      if ('0' <= c && c <= '9') value = value.add(BigInteger.valueOf(c - '0'))
      if ('a' <= c && c <= 'z') value = value.add(BigInteger.valueOf(c - 'a' + 10))
      if ('A' <= c && c <= 'Z') value = value.add(BigInteger.valueOf(c - 'A' + 36))
    }
    value.toString
  }
  println(base62Encode(8)) //100
}
