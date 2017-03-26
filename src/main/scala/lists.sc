trait List[+T] {
  def head: T
  def tail: List[T]
  def isEmpty: Boolean
  def prepend [U >: T](elem: U): List[U] = new Cons(elem, this)
}

object Nil extends List[Nothing] {
  def head = throw new java.util.NoSuchElementException("head of EmptyList")
  def tail = throw new java.util.NoSuchElementException("tail of EmptyList")
  def isEmpty = true
}

class Cons[T](val head: T, val tail: List[T]) extends List[T] {
  def isEmpty = false
}


object List {
  def apply = Nil
  def apply[T](a: T) = new Cons(a, Nil)
  def apply[T](a: T, b: T) = new Cons(a, new Cons(b, Nil))
}

trait IntSet {}
class Empty extends IntSet {}
class NonEmpty extends IntSet {}

def f(xs: List[NonEmpty], x: Empty) = xs.prepend(x)