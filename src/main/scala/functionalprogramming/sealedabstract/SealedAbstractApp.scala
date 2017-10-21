package functionalprogramming.sealedabstract

/**
 * Created by abdhesh on 9/4/15.
 */
object SealedAbstractApp extends App {

  //sealed abstract case class Expression
  //sealed abstract case object Expression
  sealed abstract trait Expression

  case class Literal(x: Int) extends Expression

  case class Add(x: Expression, y: Expression) extends Expression

  case class Mult(x: Expression, y: Expression) extends Expression

  case class Sub(x: Expression, y: Expression) extends Expression

  val expr1 = Mult(Literal(6), Add(Literal(3), Literal(4)))
  val expr2 = Sub(Mult(Literal(21), Add(Literal(103), Literal(42))), Add(Literal(17), Mult(Literal(29), Literal(7))))
  val expr3 = Mult(Sub(Literal(6), Mult(Sub(Literal(5), Literal(3)), Literal(3))), Add(Literal(3), Mult(Literal(5), Literal(8))))

  val format: Expression => String =
    _ match {
      case Literal(x) => x.toString
      case Add(leftExpr, rightExpr) => s"( ${format(leftExpr)} + ${format(rightExpr)} )"
      case Mult(leftExpr, rightExpr) => s"( ${format(leftExpr)} * ${format(rightExpr)} )"
      case Sub(leftExpr, rightExpr) => s"( ${format(leftExpr)} - ${format(rightExpr)} )"
    }
  println(format(expr1)) // ( 6 * ( 3 + 4 ) )
  println(format(expr2)) // ( ( 21 * ( 103 + 42 ) ) - ( 17 + ( 29 * 7 ) ) )
  println(format(expr3))
}
