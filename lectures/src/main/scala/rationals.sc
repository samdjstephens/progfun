val x = new Rational(1, 2)
val y = new Rational(2, 3)
val z = new Rational(1, 5)
val anInt = new Rational(2)
val err = new Rational(2, 0)

x.add(y)
y.sub(x)

x.sub(y).sub(z)

x.max(y)

class Rational(x: Int, y: Int) {
  require(y != 0, "Denominator cannot be zero")

  def this(x: Int) = this(x, 1)

  private def gcd(a: Int, b: Int): Int = if (b == 0) a else gcd(b, a % b)
  private val g = gcd(x, y)
  def numer: Int = x / g
  def denom: Int = y / g

  def less(that: Rational): Boolean = numer * that.denom < that.numer * denom

  def max(that: Rational): Rational = if (this.less(that)) that else this

  def add(that: Rational): Rational =
    new Rational(
      numer * that.denom + that.numer * denom,
      denom * that.denom
    )

  def sub(that: Rational): Rational = add(that.neg)

  def neg = new Rational(-numer, denom)

  override def toString: String =
    if (denom == 1) numer.toString
    else numer + "/" + denom
}