package functionalcollections

import scala.annotation.tailrec

//Sequences are callable through an integer index
//Sequences are partial functions. They are partially defined on the domain[0,...length-1]
/*Map  are "callable" through their keys. Here callable means calling an apply function
They are also partial functions too.
A map is defined on the domain of its keys.
 *
 */
abstract class MyStream[+A] {
  def isEmpty: Boolean
  def head: A
  def tail: MyStream[A]
  //prepend
  def #::[B >: A](elem: B): MyStream[B]
  //concat
  def ++[B >: A](anotherStream: => MyStream[B]): MyStream[B]

  def foreach(f: A => Unit): Unit
  def map[B](f: A => B): MyStream[B]
  def flatMap[B](f: A => MyStream[B]): MyStream[B]
  def filter(predicate: A => Boolean): MyStream[A]
  def take(n: Int): MyStream[A]
  def takeAsList(n: Int): List[A] = take(n).toList()

  @tailrec
  final def toList[B >: A](acc: List[B] = Nil): List[B] = {
    if (isEmpty) acc.reverse
    else tail.toList(head :: acc)
  }
}
object EmptyStream extends MyStream[Nothing] {
  override def isEmpty: Boolean = true

  override def head: Nothing = throw new NoSuchElementException

  override def tail: MyStream[Nothing] = throw new NoSuchElementException

  override def #::[B >: Nothing](elem: B): MyStream[B] =
    new Cons[B](elem, this)

  override def ++[B >: Nothing](anotherStream: => MyStream[B]): MyStream[B] =
    anotherStream

  override def foreach(f: Nothing => Unit): Unit = ()

  override def map[B](f: Nothing => B): MyStream[B] = this

  override def flatMap[B](f: Nothing => MyStream[B]): MyStream[B] = this

  override def filter(predicate: Nothing => Boolean): MyStream[Nothing] = this

  override def take(n: Int): MyStream[Nothing] = this

}
class Cons[+A](hd: A, tl: => MyStream[A]) extends MyStream[A] {
  override def isEmpty: Boolean = false
  override val head: A = hd
  override lazy val tail: MyStream[A] = tl
  override def #::[B >: A](elem: B): MyStream[B] =
    new Cons[B](elem, this)

  override def ++[B >: A](anotherStream: => MyStream[B]): MyStream[B] =
    new Cons[B](head, tail ++ anotherStream)

  override def foreach(f: A => Unit): Unit = {
    f(head)
    tail.foreach(f)
  }

  override def map[B](f: A => B): MyStream[B] = {
    new Cons[B](f(head), tail.map(f))
  }

  override def flatMap[B](f: A => MyStream[B]): MyStream[B] = {
    f(head) ++ tail.flatMap(f)
  }

  override def filter(predicate: A => Boolean): MyStream[A] = {
    if (predicate(head)) new Cons[A](head, tail.filter(predicate))
    else tail.filter(predicate)
  }

  override def take(n: Int): MyStream[A] =
    if (n <= 0) EmptyStream
    else if (n == 1) new Cons(head, EmptyStream)
    else new Cons(head, tail.take(n - 1))

}
object MyStream {
  def from[A](start: A)(generator: A => A): MyStream[A] =
    new Cons[A](start, MyStream.from(generator(start))(generator))
}

object StreamMain extends App {
  val natural = MyStream.from(1)(_ + 1)
  println(natural.head, natural.tail.head, natural.tail.tail.head)

  val startFrom0 = 0 #:: natural
  println(startFrom0.head)
  println(startFrom0.map(_ * 2).take(10).toList())
  println(
    startFrom0
      .take(5)
      .flatMap(x => new Cons(x, new Cons(x + 1, EmptyStream)))
      .toList()
  )
  //0,1,1,2,3,5
  def fibonacci(first: BigInt, second: BigInt): MyStream[BigInt] =
    new Cons(first, fibonacci(second, first + second))

  println(fibonacci(1, 1).take(10).toList())

  def eratosthenes(number: MyStream[Int]): MyStream[Int] =
    if (number.isEmpty) number
    else
      new Cons[Int](
        number.head,
        eratosthenes(number.tail.filter(_ % number.head != 0))
      )

  println(eratosthenes(MyStream.from(2)(_ + 1)).take(20).toList())
}
