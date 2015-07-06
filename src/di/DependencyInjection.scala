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
