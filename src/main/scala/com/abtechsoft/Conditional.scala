package com.abtechsoft

/**
  * Created by abdhesh on 6/10/15.
  */
object Conditional extends App {

  def conditional[A](x: A, p: A => Boolean, f: A => A): A = if (p(x)) f(x) else x

  def conditionalFn[A](x: A)(p: A => Boolean, f: A => A): A = if (p(x)) f(x) else x

  val a = conditional[String]("Hello,World", _.size > 4, _.reverse)
  //Advantage of type inference if you define function as currying function
  val result = conditionalFn("Hello,World")(_.size > 4, _.reverse)
  println(result)
}
