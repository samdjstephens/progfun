val x = new Rational(1, 2)
val y = new Rational(2, 3)
val z = new Rational(1, 5)

x.add(y)
y.sub(x)

x.sub(y).sub(z)

class Rational(x: Int, y: Int) {
  def numer: Int = x
  def denom: Int = y

  def add(that: Rational): Rational =
    new Rational(
      numer * that.denom + that.numer * denom,
      denom * that.denom
    )

  def sub(that: Rational): Rational = add(that.neg)

  def neg = new Rational(-numer, denom)

  override def toString: String = numer + "/" + denom
}