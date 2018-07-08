package com.abtechsoft

/**
  * Created by abdhesh on 18/06/17.
  */
object Examples extends App {

  /*
  generic monad:
  --------------
  unit:     A => M[A]
  flatMap: (A => M[B]) => M[B]
  our monad:
  --------------
  unit:     User => Option[User]
  flatMap: (User => Option[User]) => Option[User]
   */


  case class User(name: String, child: Option[User] = None) {
    def unit(a: String) = User(a, Option.empty)

    def flatMap(fun: User => Option[User]) = fun(this)

    def map(fun: User => User): User = fun(this)
  }

  val data = User("A", child = Some(User("B", child = Some(User("C")))))

  val result = data.flatMap(_.child)
    .flatMap(_.child)

  println(result)

  val result1 = for {
    a <- data
    b <- Some(a)
  } yield b

  println(result1)

}
