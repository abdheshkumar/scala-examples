package monadic

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
object MonadicApp extends App {
  for {
    x <- Option(1)
    y <- Option("b")
    z <- List(3, 4)
  } {
    // Now we can use the x, y, z variables
    println(x, y, z)
    x // the last expression is *not* the output of the block in this case!
  }

  def listOfFuture: List[Future[Int]] = List(1, 2, 3).map(Future(_))
  def futureOfList: Future[List[Int]] = Future.traverse(listOfFuture)(x => x)
  def futureOfList01: Future[List[Int]] =
    Future.traverse(List(1, 2, 3))(Future(_))
  case class Foo(i: Int)
  // without the implicit
  println(Foo(40) + 2) // compilation-error (type mismatch)
  // defines how to turn a Foo into an Int
  implicit def fooToInt(foo: Foo): Int = foo.i
  // now the Foo is conv
  println(Foo(40) + 2) // 42

  trait Attempt[+A] {
    def flatMap[B](f: A => Attempt[B]): Attempt[B]
  }
  object Attempt {
    def apply[A](a: => A): Attempt[A] =
      try {
        Success(a)
      } catch {
        case ex: Throwable => Fail(ex)
      }
    case class Success[A](value: A) extends Attempt[A] {
      override def flatMap[B](f: A => Attempt[B]): Attempt[B] =
        try f(value)
        catch {
          case ex: Throwable => Fail(ex)
        }
    }
    case class Fail(ex: Throwable) extends Attempt[Nothing] {
      override def flatMap[B](f: Nothing => Attempt[B]): Attempt[B] = this
    }
    def fun(a: Int): Attempt[Int] = Attempt(a * 2)
    //left identity
    Attempt(1).flatMap(fun) == fun(1)
    Success(1).flatMap(fun) == fun(1)
    //right identity
    fun(1).flatMap(x => Attempt(x)) == fun(1)
    val e = new Exception("Failed")
    //associativity
  }

  //Lazy monad
  class Lazy[A](value: => A) {
    private lazy val internalVal = value
    def use: A = internalVal
    //def flatMap[B](f: A => Lazy[B]): Lazy[B] = f(value)
    def flatMap[B](f: (=> A) => Lazy[B]): Lazy[B] = f(internalVal)
  }
  object Lazy {
    def apply[A](value: => A): Lazy[A] = new Lazy(value)
  }

  val lazyInstance = Lazy {
    println("Today I don't feel like doing anything")
    50
  }

  //println(lazyInstance.use)
  val flatMapInstance = lazyInstance.flatMap(x => Lazy { 10 * x }) //It will println message which is not write
  //So avoiding println, we have to change signature of flatMa
  /*
  def flatMap[B](f: A => Lazy[B]): Lazy[B] = f(value) to below one
  def flatMap[B](f: (=>A) => Lazy[B]): Lazy[B] = f(value)
 */
  flatMapInstance.use
}
