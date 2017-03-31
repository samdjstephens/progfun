import forcomp.Anagrams._


println("test")
wordOccurrences("hello")
sentenceOccurrences(List("hello", "world"))

dictionaryByOccurrences


val occs = List(('a', 2), ('b', 1), ('c', 1))

occs flatMap { case (char, count) => for (_ <- 1 to count) yield char }

def combIter(chars: List[Char]): List[List[Char]] = chars match {
  case Nil => List(List())
  case c :: rest =>
    val restCombs = combIter(rest)
    restCombs ::: (for (comb <- restCombs) yield c :: comb)
}

val lard = List(('a', 1), ('d', 1), ('l', 1), ('r', 1))
val r = List(('r', 1))


def subtractSingle(char: Char, count: Int, occurrences: Occurrences) = {
  val asMap = occurrences.toMap
  (asMap updated (char, asMap(char) - count) toList) sorted
}

subtractSingle('a', 1, occs)