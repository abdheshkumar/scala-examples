package singleresponsibility

/**
 * Created by abdhesh on 10/2/15.
 */

abstract class Result {
  def userRepr: String = "wtv"
}

trait Abstract extends Result {
  abstract override def userRepr: String = "abstract" + super.userRepr
}

case class ValDefResult(name: String) extends Result {
  override def userRepr = name
}

object AbstravtResult extends App {
  val a = new ValDefResult("asd") with Abstract
  println(a.userRepr)
}

sealed trait A{}
