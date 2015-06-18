package session8

import scala.collection.immutable.::

/**
 * Created by abdhesh on 6/11/15.
 */
object Main extends App {
  val roads = List("c1#c2#6", "c2#c3#12", "c2#c4#3", "c3#c5#22", "c3#c6#23"
    , "c4#c7#13", "c5#c8#16", "c6#c8#11", "c6#c9#9", "c7#c9#12", "c9#c10#15", "c8#c10#7"
  )
  val production = List("c2", "c5", "c7")
  val export = List("c4", "c10")

  val roadsMapping = roads.map {
    road =>
      road.split("#").toList match {
        case source :: destination :: unit :: _ =>
          (source -> destination) -> unit
      }

  }

  val matchedRecord = roadsMapping.filter {
    case (f, x) => production.find(ff => ff == f._1 || ff == f._2).isDefined
  }

  matchedRecord foreach {
    case (ff, fff) =>
      println(s"${ff._1}->${ff._2}=${fff}")
  }
}
