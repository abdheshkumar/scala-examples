object FP6 extends App {

  trait RNG {
    def nextInt: (Int, RNG)
  }

  case class SimpleRNG(seed: Long) extends RNG {
    override def nextInt: (Int, RNG) = {
      val newSeed = (seed * 0x5DEECE66DL + 0xBL) & (0xFFFFFFFFFFFFL)
      val newRNG = SimpleRNG(newSeed)
      val n = (newSeed >>> 16).toInt
      (n, newRNG)
    }
  }

  println(new SimpleRNG(42).nextInt._1)
  println(new SimpleRNG(42).nextInt._1)
}
