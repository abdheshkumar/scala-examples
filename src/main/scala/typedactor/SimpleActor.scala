package typedactor

import akka.actor.typed.{ActorSystem, Behavior}
import akka.actor.typed.scaladsl.Behaviors

import scala.concurrent.{Future, Promise}

object SimpleActor {
  def createSimpleActor(): Behaviors.Receive[String] =
    Behaviors.receiveMessage[String] { message =>
      println(message)
      Behaviors.same
    }
  val rootActor = ActorSystem(createSimpleActor(), "simple-actor")

  val promiseResolver =
    ActorSystem(Behaviors.receiveMessage[(String, Promise[Int])] {
      case (message, promise) =>
        promise.success(message.length)
        Behaviors.same
    }, "promise-actor")

  def doAsyncNonBlockingComputation(s: String): Future[Int] = {
    val promise = Promise[Int]()
    promiseResolver ! (s, promise)
    promise.future
  }
  def main(args: Array[String]): Unit = {
    rootActor ! "Hello"
    val r = doAsyncNonBlockingComputation("Test")
  }
}
