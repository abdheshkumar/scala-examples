package inheritence

object InheritanceApp extends App {
  trait Writer[T] {
    def write(value: T): Unit
  }
  trait Closable {
    def close(status: Int): Int
  }
  trait GenericStream[T] {
    def foreach(f: T => Unit): Unit
  }

  def processStream[T](
    st: GenericStream[T] with Writer[T] with Closable
  ): Unit = {
    st.foreach(println)
    st.close(0)
  }

  //Diamond problem
  trait Animal {
    def name: String
  }
  trait Lion extends Animal {
    override def name: String = "lion"
  }
  trait Tiger extends Animal {
    override def name: String = "tiger"
  }
  class Mutant extends Lion with Tiger
  val m = new Mutant
  println(m.name) //tiger

  trait Cold {
    def print: Unit = println("cold")
  }
  trait Green extends Cold {
    override def print: Unit = {
      println("green")
      super.print
    }
  }
  trait Blue extends Cold {
    override def print: Unit = {
      println("blue")
      super.print
    }
  }
  class Red {
    def print = println("red")
  }
  class White extends Red with Green with Blue {
    override def print: Unit = {
      println("white")
      super.print
    }
  }
  val color = new White
  color.print

  //Path dependent problem

  /*
  trait Item[Key]
  trait IntItem extends Item[Int]
  trait StringItem extends Item[String]

  def get[ItemKey](key: ???): ItemKey
  get[Int](23) //ok
  get[String]("hello") //Ok
  get[Int]("hello") //should not be ok so how to prevent to compile this
  */
  trait ItemLikeKey{
    type Key
  }
  trait Item[K] extends ItemLikeKey{
    type Key = K
  }
  trait IntItem extends Item[Int]
  trait StringItem extends Item[String]
  def get[ItemType<:ItemLikeKey](key: ItemType#Key): ItemType = ???
  get[IntItem](23)
  get[StringItem]("hello")
  //get[IntItem]("hello") //Not compile

}
