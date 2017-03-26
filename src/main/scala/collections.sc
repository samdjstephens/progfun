val myList = 1 :: 4 :: 10 :: Nil


def isort(xs: List[Int]): List[Int] = xs match {
  case List() => List()
  case y :: ys => insert(y, isort(ys))
}


def insert(x: Int, xs: List[Int]): List[Int] = xs match {
  case List() => x :: xs
  case y :: ys => if (x <= y) x :: y :: ys else y :: insert(x, ys)
}

isort(1 :: 3 :: 2 :: 8 :: 0 :: 2 :: 6 :: Nil)