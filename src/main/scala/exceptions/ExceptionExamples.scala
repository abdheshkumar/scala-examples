package exceptions

import scala.util.control.Exception._

/**
  * Created by abdhesh on 12/18/15.
  */
object ExceptionExamples extends App {

  val tryCatch = try {
    //Code here that might raise an exception
    throw new Exception
  } catch {
    case ex: Exception =>
    //Code here for handle an exception
  }

  val tryMultipleCatch = try {
    //Code here that might raise an exception
    throw new Exception
  } catch {
    case ae: ArithmeticException =>
    //Code here for handle an exception
    case ex: Exception =>
    //Code here for handle an exception
  }

  val tryMultipleCatchFinally = try {
    //Code here that might raise an exception
    throw new Exception
  } catch {
    case ae: ArithmeticException =>
    //Code here for handle an ArithmeticException
    case ex: Exception =>
    //Code here for handle an Exception
  } finally {
    println(":::::")
    //Code here, will always be execute whether an exception is thrown or not
  }

  val tryCatchWithValue: Int = try {
    //Code here that might raise an exception
    "NonNumericValue".toInt
  } catch {
    case ne: NumberFormatException => 0
  }

  import scala.util.{Failure, Success, Try}

  val withTry = Try("1".toInt) // Success(1)
  withTry match {
    case Success(value) => println(value)
    case Failure(ex) =>
      //Code here for handle an exception
      println(ex)
  }

  val tryWithRecover = Try("Non-Numeric-Value".toInt) match {
    case Success(value) => value
    case Failure(ex) => 0
  }

  val tryWithRecoverF = Try("Non-Numeric-Value".toInt).recover {
    //Here you pattern match on type of an exception
    case ne: NumberFormatException => 0
    case ex: Exception => 0
  }

  def recoverWith(first: String, second: String): Try[Int] = {
    //The code of recoverWith function will execute when `Try(first.toInt)` raise an exception
    Try(first.toInt).recoverWith {
      case ne: NumberFormatException => Try(second.toInt)
    }
  }

  //Try's map,flatMap,fold etc
  def inc(n: Int): Int = n + 1

  val try1 = Try("abc".toInt)
  val tResult = try1.map(f => inc(f)) // The function `inc` will execute when `Try("abc".toInt)` doesn't raise an exception


  val withTryWithFailure = Try("abc".toInt) // Failure(Exception in thread "main" java.lang.NumberFormatException: For input string: "")

  val withTryBody = Try {
    //Code here might raise an exception
  }

  def compute(number: Int, divideBY: Int): Int = number / divideBY

  val t1 = Try("123".toInt).map(n => compute(n, 2)) //Success(61)
  val t2 = Try("123".toInt).map(n => compute(n, 0)) //Failure(java.lang.ArithmeticException: / by zero)
  println(t2)

  def computeWithTry(value: String): Try[Int] = Try(value.toInt)

  val r1: Try[Int] = computeWithTry("123")
  r1.fold(
    ex => println(ex),
    value => println(compute(value, 2))
  )

  computeWithTry("123").fold(
    ex => println(s"Exception--${ex}"),
    value => println(compute(value, 0))
  ) // Exception--java.lang.ArithmeticException: / by zero

  computeWithTry("abc").fold(
    ex => println(ex),
    value => println(compute(value, 2))
  )

  computeWithTry("123").map(n => compute(n, 2)) //Success(61)
  computeWithTry("123").map(n => compute(n, 0)) //Failure(java.lang.ArithmeticException: / by zero)
  computeWithTry("abc").map(n => compute(n, 2)) //Failure(java.lang.NumberFormatException: For input string: "abc")
  //Good thing with this approach, you can use for-comprehension,map,flatMap,fold etc. functions

  ///////////////////////////////////////////
  val resultF = allCatch {
    "".toInt
  }
  //////////////////////////////////////////////
  allCatch withApply {
    (t: Throwable) => println("catched")
  } apply {
    println("foo")
    throw new Exception
  }

  val catchingEx = allCatch withApply { t => println("catched") }
  catchingEx {
    println("foo")
    throw new Exception
  }

  val result1: String = allCatch withApply { t => "bar" } apply {
    "foo"
  }
  // "foo"
  val result2: String = allCatch withApply { t => "bar" } apply {
    throw new Exception
  } // "bar"

  val result3: Option[String] = allCatch withApply { e => None } apply {
    Some("foo")
  }
  // Some("foo")
  val result4: Option[String] = allCatch withApply { e => None } apply {
    throw new Exception
  } // None

  val result5: Option[String] = allCatch opt {
    "foo"
  }
  // Some("foo")
  val result6: Option[String] = allCatch opt {
    throw new Exception
  } // None

  val result7: Either[Throwable, String] = allCatch either {
    "foo"
  }
  // Right(foo)
  val result8: Either[Throwable, String] = allCatch either {
    throw new Exception
  } // Left(java.lang.Exception)

  result8 match {
    case Left(t) => println("What's wrong?") // t: Throwable
    case Right(result) => println(result) // result: String
  }

  ///////////////////////////////////////////////////////

  val result9 = try {
    throw new Exception
  } catch {
    case e =>
  } finally {
    println("finally")
  }
  val result10 = allCatch withApply { e => } andFinally {
    println("finally")
  } apply {
    "ok"
  }

  val exHandler = (t: Throwable) => "ng"
  val finallyExec = () => println("finally")
  val result11 = allCatch withApply exHandler andFinally finallyExec() apply {
    "ok"
  }

  ////////////////////////////////////////
  val result12 = try {
    throw new Exception
  } catch {
    case e =>
  }

  ignoring(classOf[Throwable]) {
    throw new Exception
  }
  //////////////////////////////////////////////////////
  val result13 = try {
    throw new IllegalArgumentException
  } catch {
    case e: IllegalArgumentException => "IAE"
    case e => throw e
  } // IAE


  val catchingEx1: Catch[String] = catching(classOf[IllegalArgumentException]) withApply { t => "IAE" }
  val result14 = catchingEx1 {
    "ok"
  }
  // ok
  val result15 = catchingEx1 {
    throw new IllegalArgumentException
  }
  // "IAE"
  val result16 = catchingEx1 {
    throw new Exception
  } // java.lang.Exception


  val result17 = try {
    throw new IllegalArgumentException
  } catch {
    case e: IllegalArgumentException => "something wrong"
    case e: IllegalStateException => "something wrong"
    case e => throw e
  } // IAE


  val exHandler1 = (t: Throwable) => "something wrong"
  val catchingEx2 = catching(classOf[IllegalArgumentException], classOf[IllegalStateException]) withApply exHandler1
  val result18 = catchingEx2 {
    throw new IllegalArgumentException
  }
  // "something wrong"
  val result19 = catchingEx2 {
    throw new IllegalStateException
  } // "something wrong"

  /////////////////////////////////////////////////////
  val result120 = try {
    throw new IllegalArgumentException
  } catch {
    case e: IllegalArgumentException => "IAE"
    case e: IllegalStateException => "ISE"
    case e => throw e
  } // IAE

  val catchingIAE = catching(classOf[IllegalArgumentException]) withApply { t => "IAE" }
  val catchingISE = catching(classOf[IllegalStateException]) withApply { t => "ISE" }
  val catchingEx3 = catchingIAE or catchingISE
  catchingEx3 {
    throw new IllegalArgumentException
  } // "IAE"
  catchingEx3 {
    throw new IllegalStateException
  } // "ISE"
  catchingEx3 {
    throw new Exception
  } // java.lang.Exception

  //catching(classOf[InterruptedException]) withApply { t => } { throw new InterruptedException }
  catchingPromiscuously(classOf[InterruptedException]) withApply { t => } apply {
    throw new InterruptedException
  }

  ///////////////////////////////////////////////////////////
  val result21 = try {
    throw new IllegalArgumentException
  } catch {
    case e: IllegalArgumentException => "IAE"
    case e: IllegalStateException => "ISE"
    case e => throw e
  } // IAE

  val handlingIAE = handling(classOf[IllegalArgumentException]) by { t => "IAE" }
  val handlingISE = handling(classOf[IllegalStateException]) by { t => "ISE" }
  val handlingEx = handlingIAE or handlingISE
  val result22 = handlingEx {
    throw new IllegalArgumentException
  }
  // IAE
  val result23 = handlingEx {
    throw new IllegalStateException
  } // ISE

  //////////////////////////////////////////////////////////////////
  val iaeFailing = failing(classOf[IllegalArgumentException])
  val result24 = iaeFailing {
    Some("foo")
  }
  // Some("foo")
  val result25 = iaeFailing {
    throw new IllegalArgumentException
  } // None
  //////////////////////////////////////////////////////////////

  val iaeFailAsValue = failAsValue(classOf[IllegalArgumentException])("bar")
  val result26 = iaeFailAsValue {
    "foo"
  }
  // "foo"
  val result27 = iaeFailAsValue {
    throw new IllegalArgumentException
  } // "bar"

  /////////////////////////////////////////////////////////////////////////////
  try {
    println("foo")
  } finally {
    println("finally")
  }

  val withFinally = ultimately {
    println("finally")
  }
  withFinally {
    println("foo")
  }

  ultimately {
    println("finally")
  } {
    println("foo")
  }
  ///////////////////////////////////////////////////////
  unwrapping(classOf[RuntimeException]) {
    throw new RuntimeException(new IllegalArgumentException)
  } // java.lang.IllegalArgumentException

  val catchingEx4 = unwrapping(classOf[RuntimeException]) withApply {
    e => e.printStackTrace // java.lang.RuntimeException
  }
  catchingEx4 {
    throw new RuntimeException(new IllegalArgumentException)
  }
}
