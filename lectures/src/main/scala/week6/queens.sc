object queens {

  def isSafe(col: Int, queens: List[Int]): Boolean = {
    def isSafeAtOffsetI(queens: List[Int], offset: Int): Boolean = queens match {
      case Nil => true
      case colI :: rest =>
        if ((math.abs(col - colI) == offset) || (col == colI)) false
        else isSafeAtOffsetI(rest, offset + 1)
    }
    isSafeAtOffsetI(queens, 1)
  }

  def queens(n: Int) = {
    def placeQueens(k: Int): Set[List[Int]] = {
      if (k == 0) Set(List())
      else
        for {
          queens <- placeQueens(k - 1)
          col <- 0 until n
          if isSafe(col, queens)
        } yield col :: queens
    }
    placeQueens(n)
  }

  val solutions = for (i <- 1 to 20) yield (i, queens(i).size)
  solutions mkString "\n"
}