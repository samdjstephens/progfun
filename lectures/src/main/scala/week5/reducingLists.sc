def mapFun[T, U](xs: List[T], f: T => U): List[U] =
  (xs foldRight List[U]())( f(_) :: _ )

def lengthFun[T](xs: List[T]): Int =
  (xs foldRight 0)( (x, acc) => acc + 1)



mapFun[Int, Int](List(1, 2, 3, 4), x => x * x)
lengthFun(List(1, 2, 3, 5))