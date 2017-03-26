trait Expr {
  def eval: Int = this match {
    case Sum(e1, e2) => e1.eval + e2.eval
    case Product(e1, e2) => e1.eval * e2.eval
    case Number(n) => n
  }
  def show: String = this match {
    case Product(Sum(e1, e2), e3) => s"(${Sum(e1, e2).show}) * ${e3.show}"
    case Sum(e1, e2) => s"${e1.show} + ${e2.show}"
    case Product(e1, e2) => s"${e1.show} * ${e2.show}"
    case Var(s) => s
    case Number(n) => n.toString
  }
}

case class Sum(e1: Expr, e2: Expr) extends Expr
case class Product(e1: Expr, e2: Expr) extends Expr
case class Var(s: String) extends Expr
case class Number(n: Int) extends Expr


val x = Sum(Number(1), Sum(Number(1), Number(2)))
x.eval
x.show


Sum(Product(Number(2), Var("x")), Var("y")).show
Product(Sum(Number(2), Var("x")), Var("y")).show