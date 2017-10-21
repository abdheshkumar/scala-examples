package filter_with_flatMap

/**
 * Created by abdhesh on 6/11/15.
 */
object Main extends App {
  def strings(list: List[Any]) = list flatMap {
    case st: String => Some(st)
    case _ => None
  }

  val list = strings("hi" :: 1 :: "world" :: 4 :: Nil)
  val filteredList = "hi" :: 1 :: "world" :: 4 :: Nil filter {
    _.isInstanceOf[String]
  }

  val collectList = "hi" :: 1 :: "world" :: 4 :: Nil collect { case s: String => s }
}
