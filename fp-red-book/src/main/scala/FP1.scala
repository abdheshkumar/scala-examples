object FP1 extends App {
  def partial1[A, B, C](a: A, f: (A, B) => C): B => C =
    (b: B) => f(a, b)

  def curry[A, B, C](f: (A, B) => C): A => (B => C) =
    (a: A) => (b: B) => f(a, b) // (a) => (b) => f(a, b)

  def uncurry[A, B, C](f: A => B => C): (A, B) => C =
    (a: A, b: B) => f(a)(b) //  (a, b) => f(a)(b)

  def compose[A, B, C](f: B => C, g: A => B): A => C =
    (a: A) => f(g(a)) //(a) => f(g(a))

  def tail[A](l: List[A]): List[A] = l match {
    case Nil => List.empty[A]
    case _ :: tail => tail
  }

  def setHead[A](elem: A, l: List[A]): List[A] = l match {
    case Nil => List(elem)
    case _ :: tail => elem +: tail
  }

  def drop[A](l: List[A], n: Int): List[A] =
    if (n <= 0) l else l match {
      case Nil => Nil
      case _ :: tail => drop(tail, n - 1)
    }

  def dropWhile[A](l: List[A], f: A => Boolean): List[A] = l match {
    case head :: tail if f(head) => dropWhile(tail, f)
    case _ => l
  }

  def append[A](a1: List[A], a2: List[A]): List[A] =
    a1 match {
      case Nil => a2
      case l => l ++ a2
    }

  def dropWhile1[A](as: List[A])(f: A => Boolean): List[A] = as match {
    case h :: t if f(h) => dropWhile1(t)(f)
    case _ => as
  }

  def foldLeft[A, B](as: List[A], z: B)(f: (B, A) => B): B = as match {
    case Nil => z
    case h :: t => foldLeft(t, f(z, h))(f)
  }

  def sum(l: List[Int]): Int = foldLeft(l, 0)(_ + _) // `_ + _` is more concise notation for `(x,y) => x * y`
  def product(l: List[Int]): Int = foldLeft(l, 1)(_ * _)

  def reverse[A](l: List[A]): List[A] = foldLeft(l, List.empty[A])((r, c) => c +: r)

  println(reverse(List(1, 2, 3)))
}
