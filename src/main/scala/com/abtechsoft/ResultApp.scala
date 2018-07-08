package com.abtechsoft

/**
  * Created by abdhesh on 18/06/17.
  */
object ResultApp {

  sealed trait Result[E, A] {

    def change[B](f: A => B): Result[E, B] = this match {
      case Error(e) => Error(e)
      case Success(a) => Success(f(a))
    }

    def chain[B](f: A => Result[E, B]): Result[E, B] = this match {
      case Error(e) => Error(e)
      case Success(a) => f(a)
    }
  }

  final case class Error[E, A](error: E) extends Result[E, A]

  final case class Success[E, A](value: A) extends Result[E, A]

}
