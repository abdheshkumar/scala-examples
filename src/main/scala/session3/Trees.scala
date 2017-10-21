package session3

/**
 * Created by abdhesh on 6/15/15.
 */

sealed trait Tree[+A]

case class Leaf[A](value: A) extends Tree[A]

case class Branch[A](left: Tree[A], right: Tree[A]) extends Tree[A]

class Trees {
  case object Nill extends Tree[Nothing]

}
