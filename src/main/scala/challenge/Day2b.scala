package challenge


import scala.annotation.tailrec
import scala.io.Source

object Day2b {

  def nextDigit(d: Int, dir: Char): Int = dir match {
    case 'D' =>
      d match {
        case 5 | 9 | 10 | 12 | 13  => d
        case 2 | 3 | 4 | 6 | 7 | 8 => d + 4
        case 1 | 11                => d + 2
      }
    case 'U' =>
      d match {
        case 1 | 2 | 4 | 5 | 9        => d
        case 6 | 7 | 8 | 10 | 11 | 12 => d - 4
        case 3 | 13                   => d - 2
      }
    case 'R' =>
      d match {
        case 1 | 4 | 9 | 12 | 13 => d
        case _                   => d + 1
      }
    case 'L' =>
      d match {
        case 1 | 2 | 5 | 10 | 13 => d
        case _                   => d - 1
      }
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

  def run(): String = {
    val source   = Source.fromResource("day2.txt").getLines.toList
    val passages = source map (l => l.toList)
    val sequence = passages.scanLeft(5)((a, b) => getDigit(b, a)(nextDigit))
    (sequence drop 1).map("%x".format(_)).mkString
  }

}
