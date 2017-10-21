package com.abtechsoft

/**
 * Created by abdhesh on 6/10/15.
 */
object Maximum extends App {

  val max = (x: Int, y: Int) => if (x > y) x else y

  val min = (x: Int, y: Int) => if (x < y) x else y

  def pickOne(t: (Int, Int, Int), cmp: (Int, Int) => Int): Int =
    cmp(t._1, cmp(t._2, t._3))

  println(s"Pick Max One=${pickOne((14, 7, 9), max)}")
  println(s"Pick Max One=${pickOne((14, 7, 9), min)}")

}
