package exceptions

import scala.util.control.Exception._

/**
 * Created by abdhesh on 12/18/15.
 */
object ExceptionExamples {

  val result = try {
    throw new Exception
  } catch {
    case e =>
  }
  ///////////////////////////////////////////
  val resultF = allCatch {
    throw new Exception
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

  result match {
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
