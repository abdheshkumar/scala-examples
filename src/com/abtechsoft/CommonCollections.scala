
package com.abtechsoft

/**
 * Created by abdhesh on 6/11/15.
 */
/**
 * Create a list of the first 20 odd Long numbers. Can you create this with a
 * for-loop, with the filter operation, and with the map operation?
 * What’s the most efficient and expressive way to write this?
 */
object CommonCollections extends App {
  val forOdd = for (i <- 0L to 9L; j = i * 2 + 1) yield j

  val filterOdd = 0L to 20L filter (_ % 2 == 1)

  val mapOdd = 0L to 9L map (_ * 2 + 1)

  /**
   * Write a function titled "factors" that takes a number and returns a
   * list of its factors, other than 1 and the number itself.
   * For example, factors(15) should return List(3, 5)
   */

  def factors(x: Int): IndexedSeq[Int] = {
    2 to (x - 1) filter (x % _ == 0)
  }

  def factorsUseUntil(x: Int): IndexedSeq[Int] = {
    2 until x filter (x % _ == 0)
  }


  def uniqueFactors(list: Seq[Int]) = list flatMap factors

  uniqueFactors(List(9, 11, 13, 15))


  /**
   * Write a function, first[A](items: List[A], count: Int): List[A], that returns the first x number of items
   * in a given list.  For example, first(List(a,t,o), 2) should return List(a,t)
   */

  val chars = ('a' to 'f').toList

  def first[A](items: List[A], count: Int): List[A] = items take count

  def firstFor[A](items: List[A], count: Int): List[A] = {
    val l = for (i <- 0 until count) yield items(i)
    l.toList
  }

  firstA(List(1, 2, 3,4,6,7), 2)

  def firstA[A](items: List[A], count: Int): List[A] = {
    items.foldLeft[List[A]](Nil) { (a: List[A], i: A) =>
      println(":::::::::" + a.size)
      if (a.size >= count) a else i :: a
    }.reverse
  }

  /* def firstRecursion[A](items: List[A], count: Int): List[A] = {
     if (count > 0 && items.tail != Nil) items.head :: first(items.tail, count - 1)
     else Nil
   }*/

  /**
   * Write a function that takes a list of strings and returns the longest string in the list.
   * Can you avoid using mutable variables here?
   */
  def longest(names: List[String]): String = names.sortBy(0 - _.length).head

  def longestFold(names: List[String]): String =
    names.fold("")((a, i) => if (a.length < i.length) i else a)

  def longestReduce(names: List[String]): String =
    names.reduce((a, i) => if (a.length < i.length) i else a)

  def greatest[A](names: List[A], max: (A, A) => A): A =
    names reduce ((a, i) => max(a, i))

  greatest[String](List("aa", "a", "ssss"), (x, y) => if (x.length > y.length) x else y)


  /**
   * Write a function that takes a List[String] and returns a (List[String],List[String]), a tuple of string lists.
   * The first list should be items in the original list that are palindromes (written the same forwards and backwards, like "racecar"). The second list in the tuple should be all of the remaining items from the original list.
   * You can implement this easily with partition, but are there other operations you could use instead?
   */

  def splitPartition(list: List[String]) = list partition (s => s == s.reverse)

  val pallies = List("Hi", "otto", "yo", "racecar")

  def splitFold(list: List[String]) =
    list.foldLeft((List[String](), List[String]())) { (a, i) =>
      if (i == i.reverse) (i :: a._1, a._2) else (a._1, i :: a._2)
    }


}
