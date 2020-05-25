package infix

object ExtractorInfixNotation extends App {
  case class Or[A, B](a: A, b: B)
  case class Test(a: String, b: Int)
  //infix
  val or = Or("Test", 34)
  println(or)
  or match {
    case name Or age =>
      println(name, age)
  }
  val t = Test("Test", 34)
  t match {
    case name Test age =>
      println(name, age)
  }
  sealed trait MyList[+A] {
    def head: A
    def tail: MyList[A]
  }

  object MyList {
    def unapplySeq[A](arg: MyList[A]): Option[Seq[A]] = {
      if (arg == Empty) Some(Seq.empty)
      else unapplySeq(arg.tail).map(arg.head +: _)
    }

    case object Empty extends MyList[Nothing] {
      def head: Nothing = throw new Exception("Empty list")
      def tail: MyList[Nothing] = this
    }
    //+A we can also use
    case class Cons[A](head: A, tail: MyList[A]) extends MyList[A]
  }
  import MyList._
  val myList = Cons(1, Cons(2, Cons(3, Empty)))
  myList match {
    case MyList(1, 2, _*) =>
      println("starting from 1")
    case _ => println("None case")
  }

  //custom return type for unapply. unapply function doesn't need to return Option
  //you can also return anything that has get and isEmpty function
  //https://riptutorial.com/scala/example/3434/unapply---custom-extractors
  abstract class Wrapper[T] {
    def isEmpty: Boolean
    def get: T
  }

  object PersonWrapper {
    case class Person(name: String, age: Int)
    def unapply(arg: Person): Wrapper[String] = new Wrapper[String] {
      override def isEmpty: Boolean = false

      override def get: String = arg.name
    }
  }
  val person = PersonWrapper.Person("Bob", 23)
  person match {
    case PersonWrapper(name) =>
      println(s"This person's name is $name")
    case _ =>
      println("Unknown name")
  }

  //  If a case class has exactly two values, its extractor can be used in infix notation.
  case class Pair(a: String, b: String)
  val p: Pair = Pair("hello", "world")
  val x Pair y = p
  val Pair(a, b) = p
}
