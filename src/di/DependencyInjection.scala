package di

/**
 * Created by abdhesh on 7/5/15.
 */

/*
 *To Provide a strategy to be used when needing to inject objects  into a dependent consumer
 */

trait Context

trait Config {
  load
  val text: String

  def load: Unit
}

trait Animal {

  def printName: Unit

  def speech(animal: Animal) = animal.printName
}

class Dog(name: String) extends Animal {
  override def printName: Unit = println(s"Dog:$name")
}


class Cat(name: String) extends Animal {
  override def printName: Unit = println(s"Cat:$name")
}


object A extends App {

  /*def speech(animal: Animal) = animal.printName

  speech(new Dog("Dog"))
  speech(new Cat("Cat"))*/

}

case class InMemoryConfig() extends Config {
  lazy val text = "Hello"

  def load = println("load:" + text)
}

trait MyContext extends Context {
  this: Config =>
  def welcome = this.text
}


object Env extends InMemoryConfig with MyContext

object DependencyInjection extends App {
  println(Env.text)
}
