package partialapplied

object PartialFunctionApp extends App {
  val chatbot: PartialFunction[String, String] = {
    case "hello"   => "Hi, My name is Abdhesh!"
    case "goodbye" => "goodbye, have a good time!"
  }
  //It will read line by line from console
  scala.io.Source.stdin.getLines().foreach(line => println(chatbot(line)))
}
