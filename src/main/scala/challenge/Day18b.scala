package challenge


import scala.annotation.tailrec

object Day18b {

  def nextRow(prev: String): String =
    ('.' +: prev :+ '.').sliding(3).map(c => if (c.head != c.last) '^' else '.').mkString

  def safeTiles(n: Int, init: String): Int = {
    @tailrec
    def countUp(row: String, acc: (Int, Int)): Int = acc match {
      case (x, y) if x == n => y
      case (x, y)           => countUp(nextRow(row), (x + 1, y + row.count(_ == '.')))
    }

    countUp(init, (0, 0))
  }

  def run(): Int = {
    val row1 =
      "^..^^.^^^..^^.^...^^^^^....^.^..^^^.^.^.^^...^.^.^.^.^^.....^.^^.^.^.^.^.^.^^..^^^^^...^.....^....^."
    safeTiles(400000, row1)
  }

}
