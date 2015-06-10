package com.abtechsoft

/**
 * Created by abdhesh on 6/10/15.
 */
object Conditional {

  def conditional[A](x: A, p: A => Boolean, f: A => A): A = if (p(x)) f(x) else x

  val a = conditional[String]("Hello,World", _.size > 4, _.reverse)

  for (i <- 1 to 100) {
    val a1 = conditional[Int](i, _ % 3 == 0, x => {
      print("type");
      0
    })
    val a2 = conditional[Int](i, _ % 5 == 0, x => {
      print("safe");
      0
    })
    if (a1 > 0 && a2 > 0) print(i)
    println("")
  }
}
