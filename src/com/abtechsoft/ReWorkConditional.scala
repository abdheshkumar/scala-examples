package com.abtechsoft

/**
 * Created by abdhesh on 6/10/15.
 */
class ReWorkConditional {
  def conditional[A](x: A, p: A => Boolean, f: A => String): String =
    if (p(x)) f(x) else ""

  for (i <- 1 to 100) {
    val a1 = conditional[Int](i, _ % 3 == 0, _ => "type")
    val a2 = conditional[Int](i, _ % 5 == 0, _ => "safe")
    val a3 = conditional[Int](i, _ % 3 > 0 && i % 5 > 0, x => s"$x")
    println(a1 + a2 + a3)
  }

  def typeSafely(i: Int): String = {
    val a1 = conditional[Int](i, _ % 3 == 0, _ => "type")
    val a2 = conditional[Int](i, _ % 5 == 0, _ => "safe")
    val a3 = conditional[Int](i, _ % 3 > 0 && i % 5 > 0, x => s"$x")
    a1 + a2 + a3
  }

  val sequence = 1 to 100 map typeSafely

  println(sequence.mkString("\n"))
}
