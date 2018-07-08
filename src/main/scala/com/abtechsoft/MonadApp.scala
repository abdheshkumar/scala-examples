package com.abtechsoft

/**
  * Created by abdhesh on 18/06/17.
  */
object MonadApp {

  /** A for comprehension ready Monad */
  case class Focomo[A](value: A) {
    self =>
    /** satisfies monadic binding of a function f that returns B */
    def map[B](f: A => B): Focomo[B] = {
      println("map!")
      Focomo(f(value))
    }

    /** satisfies monadic binding of a function f that returns Focomo[B] */
    def flatMap[B](f: A => Focomo[B]): Focomo[B] = {
      println("flatMap!")
      f(value)
    }

    /** expect this to be called in a `Unit` for comprehension */
    def foreach[U](f: A => U): Unit = {
      println("foreach!")
      f(value)
    }

    /** for comprehension's `if` statements trigger this.
      * In a more useful monad, the result of the application
      * of a value to function f would determine the a special subclass
      * of the monad or other either-or behavior.
      */
    def filter(f: A => Boolean): Focomo[A] = {
      println("filter!")
      this
    }

    /** provides a delegate handler for calls to #withFilter */
    class WithFilter(p: A => Boolean) {
      println("with filter!")

      def map[B](f: A => B): Focomo[B] = self.filter(p).map(f)

      def flatMap[B](f: A => Focomo[B]): Focomo[B] = self.filter(p).flatMap(f)

      def foreach[U](f: A => U): Unit = self.filter(p).foreach(f)

      def withFilter(q: A => Boolean): WithFilter =
        new WithFilter(x => p(x) && q(x))
    }

    /** called with conditional statement in for comprehension */
    def withFilter(p: A => Boolean): WithFilter = new WithFilter(p)
  }

}
