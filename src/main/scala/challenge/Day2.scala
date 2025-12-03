package challenge

import scala.annotation.tailrec
import scala.io.Source

object Day2 {

  def nextDigit(d: Int, dir: Char): Int = dir match {
    case 'D' => if (d + 3 > 9) d else d + 3
    case 'R' => if (d % 3 == 0) d else d + 1
    case 'U' => if (d - 3 < 1) d else d - 3
    case 'L' => if ((d + 2) % 3 == 0) d else d - 1
  }

  def getDigit(xs: List[Char], d: Int)(f: (Int, Char) => Int): Int = {

    @tailrec
    def enterDigit(s: List[Char], digit: Int): Int = s match {
      case Nil => digit
      case h :: t =>
        enterDigit(t, f(digit, h))
    }

    enterDigit(xs, d)
  }

  def run(): Int = {
    val source   = Source.fromResource("day2.txt").getLines.toList
    val passages = source map (l => l.toList)
    val sequence = passages.scanLeft(5)((a, b) => getDigit(b, a)(nextDigit))
    (sequence drop 1).mkString.toInt
  }

}
