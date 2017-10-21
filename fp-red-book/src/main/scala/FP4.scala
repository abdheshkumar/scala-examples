object FP4 {
  def Try[A](a: => A): Either[Exception, A] = try (Right(a))
  catch {
    case e: Exception => Left(e)
  }


  def map2[A, B, C, E](a: Either[E, A], b: Either[E, B])(f: (A, B) => C):
  Either[E, C] = for {a0 <- a; b0 <- b} yield f(a0, b0)

  def traverse[E, A, B](es: List[A])(f: A => Either[E, B]): Either[E, List[B]] = es match {
    case Nil => Right(Nil)
    case h :: t => map2(f(h), traverse(t)(f))(_ :: _)
  }

  def sequence[E, A](es: List[Either[E, A]]): Either[E, List[A]] = traverse(es)(identity)

  def traverse_1[E, A, B](es: List[A])(f: A => Either[E, B]): Either[E, List[B]] =
    es.foldRight[Either[E, List[B]]](Right(Nil))((a, b) => map2(f(a), b)(_ :: _))
}
