def merge(xs: List[Int], ys: List[Int]): List[Int] =
  (xs, ys) match {
    case (List(), _) => ys
    case (_, List()) => xs
    case (x :: xs1, y :: ys1) =>
      if (x < y) x :: merge(xs1, ys)
      else y :: merge(xs, ys1)
  }


merge(List(1, 3, 5), List(2, 4, 6))
merge(List(), List(2, 4, 6))
merge(List(1, 3, 5), List())
merge(List(), List())