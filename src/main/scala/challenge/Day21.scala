package challenge


import scala.annotation.tailrec
import scala.io.Source

object Day21 {

  private val pattRd = """rotate (right|left) (\d) step[s]*""".r
  private val pattSp = """swap position (\d) with position (\d)""".r
  private val pattRp = """rotate based on position of letter (\w)""".r
  private val pattSl = """swap letter (\w) with letter (\w)""".r
  private val pattRv = """reverse positions (\d) through (\d)""".r
  private val pattMv = """move position (\d) to position (\d)""".r

  abstract class Command {
    def exec(xs: String): String

    def revert(xs: String): String
  }

  trait RotateCommand {
    def rotate(n: Int, xs: List[Char]): List[Char] = n match {
      case i if i > 0 =>
        xs match {
          case h :: t => rotate(i - 1, t ::: List(h))
          case Nil    => rotate(i - 1, Nil)
        }
      case i if i < 0 =>
        xs.reverse match {
          case h :: t => rotate(i + 1, h :: t.reverse)
          case Nil    => rotate(i + 1, Nil)
        }
      case _ => xs
    }
  }

  case class Reverse(a: Int, b: Int) extends Command {
    override def exec(xs: String): String =
      (xs take a) + xs.slice(a, b + 1).reverse + (xs takeRight xs.length - b - 1)

    override def revert(xs: String): String = exec(xs)
  }

  case class PositionSwap(a: Int, b: Int) extends Command {
    override def exec(xs: String): String = xs.updated(a, xs(b)).updated(b, xs(a))

    override def revert(xs: String): String = exec(xs)
  }

  case class LetterSwap(a: Char, b: Char) extends Command {
    override def exec(xs: String): String = {
      @tailrec
      def inner(xs: List[Char], acc: String): String = xs match {
        case Nil              => acc
        case h :: t if h == a => inner(t, acc :+ b)
        case h :: t if h == b => inner(t, acc :+ a)
        case h :: t           => inner(t, acc :+ h)
      }

      inner(xs.toList, "")
    }

    override def revert(xs: String): String = exec(xs)
  }

  case class RotateDirection(dir: Char, by: Int) extends Command with RotateCommand {
    override def exec(xs: String): String = rotate(if (dir == 'R') -by else by, xs.toList).mkString

    override def revert(xs: String): String =
      rotate(if (dir == 'L') -by else by, xs.toList).mkString
  }

  case class RotatePosition(c: Char) extends Command with RotateCommand {
    override def exec(xs: String): String = {
      val i  = xs.indexOf(c)
      val by = i + (if (i >= 4) 2 else 1)
      rotate(-by, xs.toList).mkString
    }

    override def revert(xs: String): String = {
      xs.indexOf(c) match {
        case 0 => rotate(1, xs.toList).mkString
        case 1 => rotate(1, xs.toList).mkString
        case 2 => rotate(-2, xs.toList).mkString
        case 3 => rotate(2, xs.toList).mkString
        case 4 => rotate(-1, xs.toList).mkString
        case 5 => rotate(3, xs.toList).mkString
        case 6 => xs
        case 7 => rotate(-4, xs.toList).mkString
      }
    }
  }

  case class Move(a: Int, b: Int) extends Command {
    override def exec(xs: String): String = {
      val c      = List(xs(a))
      val (h, t) = (xs diff c) splitAt b
      h + c.mkString + t
    }

    override def revert(xs: String): String = {
      val c      = List(xs(b))
      val (h, t) = (xs diff c) splitAt a
      h + c.mkString + t
    }
  }

  def exec(s: String, cmd: List[String]): String = {

    def getCmd(x: String): Command = x match {
      case pattRv(a, b) => Reverse(a.toInt, b.toInt)
      case pattRp(a)    => RotatePosition(a.head)
      case pattRd(a, b) => RotateDirection(a.head.toUpper, b.toInt)
      case pattSp(a, b) => PositionSwap(a.toInt, b.toInt)
      case pattSl(a, b) => LetterSwap(a.head, b.head)
      case pattMv(a, b) => Move(a.toInt, b.toInt)
    }

    @tailrec
    def inner(xs: String, cmd: List[String]): String = cmd match {
      case Nil => xs
      case h :: t =>
        val command = getCmd(h)
        inner(command.exec(xs), t)
    }

    inner(s, cmd)
  }

  def run(): String = {
    val input = Source.fromResource("day21.txt").getLines.toList
    exec("abcdefgh", input)
  }

}
