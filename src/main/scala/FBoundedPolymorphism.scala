object FBoundedPolymorphism extends App {
  /*
  trait Animal {
    def breed: List[Animal]
  }

  class Cat extends Animal {
    override def breed: List[Animal] = ???
  }

  class Dog extends Animal {
    override def breed: List[Animal] = ???
  }*/
  trait Animal[A <: Animal[A]] { self: A => //recursive type: F-Bounded Polymorphism
    def breed: List[Animal[A]]
  }

  class Cat extends Animal[Cat] {
    override def breed: List[Animal[Cat]] = ???
  }

  class Dog extends Animal[Dog] {
    override def breed: List[Animal[Dog]] = ???
  }
  /*//Will not compile
  class Crocodile extends Animal[Dog] {
    override def breed: List[Animal[Dog]] = ???
  }*/

  class Crocodile extends Animal[Crocodile] {
    override def breed: List[Animal[Crocodile]] = ???
  }
  trait Entity[E <: Entity[E]] //ORM
  class Person extends Comparable[Person] { //FBP
    override def compareTo(o: Person): Int = ???
  }
}
