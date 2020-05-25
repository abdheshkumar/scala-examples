object StructuralTypeApp extends App {
  //structural types
  type JavaCloseable = java.io.Closeable
  class HipsterCloseable {
    def close(): Unit = println("hipster closeable")
    def closeSilently(): Unit = println("hipster silently closeable")
  }

  //def closeQuietly(closeable: HipsterCloseable OR JavaCloseable): Unit
  type UnifiedCloseable = {
    def close(): Unit
  } // STRUCTURAL TYPE - Allow working with unrelated type that has common structure

  def closeQuietly(closeable: UnifiedCloseable): Unit = closeable.close()
  closeQuietly(new JavaCloseable {
    override def close(): Unit = println("JavaCloseable...")
  })
  closeQuietly(new HipsterCloseable)

  //TYPE REFINEMENT- Refine existing type(e.g JavaCloseable) with structural type
  type AdvanceCloseable = JavaCloseable {
    def closeSilently(): Unit
  } //Enrich type from JavaCloseable

  class AdvanceJavaCloseable extends JavaCloseable {
    override def close(): Unit = println("java's close")
    def closeSilently(): Unit = {
      close()
      println("java closes silently")
    }
  }
  def closeShh(advanceJavaCloseable: AdvanceJavaCloseable) =
    advanceJavaCloseable.closeSilently()
  closeShh(new AdvanceJavaCloseable)
  //closeShh(new HipsterCloseable) // will not compile because HipsterCloseable doesn't originated from JavaCloseable even though it has all the methods
//Using structural types as standalone type
  def altClose(closeable: { def close(): Unit }) = closeable.close()

  //type-checking => Duck typing
  type SoundMaker = {
    def makeSound(): Unit
  }
  class Dog {
    def makeSound() = println("bark!")
  }
  class Cat {
    def makeSound() = println("vroom!")
  }
  val dog: SoundMaker = new Dog
  val cat: SoundMaker = new Cat

  //CAVEAT-structural and refinement type are based on reflection

  trait `CBL(Cons based list)`[+T] {
    def head: T
    def tail: `CBL(Cons based list)`[T]
  }
  class Human {
    def head: Brain = new Brain
  }
  class Brain {
    override def toString: String = "BRAINZ!"
  }

  def f[T](somethingWithAHead: { def head: T }) =
    println(somethingWithAHead.head)
  /*
  f is compatible with a CBL and Human? YES.
   */
  case object CBLNIl extends `CBL(Cons based list)`[Nothing] {
    override def head: Nothing = ???
    override def tail: `CBL(Cons based list)`[Nothing] = ???
  }

  case class CBCOns[T](override val head: T,
                       override val tail: `CBL(Cons based list)`[T])
      extends `CBL(Cons based list)`[T]
  f(CBCOns(12, CBLNIl))
  f(new Human)

  object HeadEqualizer {
    type Headable[T] = { def head: T }
    def ===[T](a: Headable[T], b: Headable[T]): Boolean = a.head == b.head
    /*
    is compatible with a CBL and Human? Yes
     */
    val brainzList = CBCOns(new Brain, CBLNIl)
    val stringsList = CBCOns("Brainz", CBLNIl)
    HeadEqualizer.===(brainzList, new Human) //Problem
    HeadEqualizer.===(new Human, stringsList) // It is not right and not type safe
  }

}
