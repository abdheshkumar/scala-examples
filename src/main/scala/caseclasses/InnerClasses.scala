package caseclasses

/**
 * Created by abdhesh on 6/25/15.
 */
object InnerClasses {

  class PandorasBox {

    case class Thing(name: String)

    var things = new collection.mutable.ArrayBuffer[Thing]()
    things += Thing("Evil Thing #1")
    things += Thing("Evil Thing #2")

    def addThing(name: String) {
      things += new Thing(name)
    }
  }

  val p = new PandorasBox
  p.addThing("Evil Thing #3")
  p.addThing("Evil Thing #4")
  p.things.foreach(println)


  class OuterClass {

    class InnerClass {
      var x = 1
    }

  }

  // inner classes are bound to the object
  val oc1 = new OuterClass
  val oc2 = new OuterClass
  val ic1 = new oc1.InnerClass
  val ic2 = new oc2.InnerClass
  ic1.x = 10
  ic2.x = 20
  println(s"ic1.x = ${ic1.x}")
  println(s"ic2.x = ${ic2.x}")


  // class inside object
  println(new OuterObject.InnerClass1().x)
  // object inside class
  println(new OuterClass1().InnerObject.y)

  object OuterObject {

    class InnerClass1 {
      var x = 1
    }

  }

  class OuterClass1 {

    object InnerObject {
      val y = 2
    }

  }

}
