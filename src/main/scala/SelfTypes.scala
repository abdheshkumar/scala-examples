object SelfTypes extends App {
//requiring a type to be mix in
  trait InstrumentList {
    def play(): Unit
  }
  trait Singer { self: InstrumentList => //whoever implements singer must implement InstrumentList
    def sing(): Unit
  }

  class LeadSinger extends Singer with InstrumentList {
    override def play(): Unit = ???
    override def sing(): Unit = ???
  }
  class Guitarist extends InstrumentList {
    override def play(): Unit = println("guitar solo")
  }

  val ericClapton = new Guitarist with Singer {
    override def sing(): Unit = ???
  }
  trait T
  trait S { self: T => //S requires a T
  }
  //CAKE PATTERN=> dependency injection

  //cyclic dependencies
  /*
  class X extends Y
  class Y extends X
   */
  trait X { self: Y =>
  }
  trait Y { self: X =>
  }

}
