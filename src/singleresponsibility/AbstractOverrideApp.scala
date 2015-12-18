package singleresponsibility

/**
 * Created by abdhesh on 10/2/15.
 */

trait Foo {
  def foo()
}

trait M extends Foo {
  abstract override def foo() {
    println("M")
    super.foo()
  }
}

class FooImpl1 extends Foo {
  override def foo() {
    println("Impl")
  }
}

class FooImpl2 extends FooImpl1 with M

object AbstractOverrideApp extends App {
  new FooImpl2().foo()
}
