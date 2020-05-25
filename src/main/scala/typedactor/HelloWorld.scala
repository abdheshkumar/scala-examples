package typedactor

import akka.actor.typed.scaladsl.Behaviors
import akka.actor.typed.{ActorRef, ActorSystem, Behavior}

object HelloWorld {
  final case class Greet(whom: String, replyTo: ActorRef[Greeted])
  final case class Greeted(whom: String, from: ActorRef[Greet])
  final case class SayHello(name: String)
  val helloWorld: Behavior[Greet] = Behaviors.receive { (context, message) =>
    context.log.info("Hello {}!", message.whom)
    message.replyTo ! Greeted(message.whom, context.self)
    Behaviors.same
  }

  val helloWorldMain: Behavior[SayHello] =
    Behaviors.setup { context =>
      val greeter = context.spawn(helloWorld, "greeter")

      Behaviors.receiveMessage { message =>
        val replyTo = context.spawn(helloWorldBot(max = 3), message.name)
        greeter ! HelloWorld.Greet(message.name, replyTo)
        Behaviors.same
      }
    }

  def helloWorldBot(max: Int): Behavior[HelloWorld.Greeted] = {
    bot(0, max)
  }

  private def bot(greetingCounter: Int,
                  max: Int): Behavior[HelloWorld.Greeted] =
    Behaviors.receive { (context, message) =>
      val n = greetingCounter + 1
      context.log.info("Greeting {} for {}", n, message.whom)
      if (n == max) {
        Behaviors.stopped
      } else {
        message.from ! HelloWorld.Greet(message.whom, context.self)
        bot(n, max)
      }
    }

  def main(args: Array[String]): Unit = {
    val system: ActorSystem[SayHello] =
      ActorSystem(helloWorldMain, "hello")

    system ! SayHello("World")
    system ! SayHello("Akka")
  }
}
