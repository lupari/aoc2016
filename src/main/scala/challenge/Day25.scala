package challenge


import scala.annotation.tailrec
import scala.collection.mutable
import scala.collection.mutable.ListBuffer
import scala.io.Source

object Day25 {

  val registry: mutable.Map[Char, Int] =
    mutable.Map[Char, Int]('a' -> 0, 'b' -> 0, 'c' -> 0, 'd' -> 0)
  val clock: ListBuffer[Int] = mutable.ListBuffer[Int]()

  abstract class Instruction {
    def exec: Int
  }

  case class Jump(a: String, b: String) extends Instruction {
    val _a: AnyVal = if (a.forall(_.isLetter)) a.head else a.toInt
    val _b: AnyVal = if (b.forall(_.isLetter)) b.head else b.toInt

    def this(cmd: Array[String]) = {
      this(cmd.head.mkString, cmd.last.mkString)
    }

    def this(cmd: String) = {
      this(cmd split ' ' drop 1)
    }

    override def toString: String = "jnz " + _a + " " + _b

    override def exec: Int = _a match {
      case i: Int =>
        if (i > 0) _b match {
          case j: Int  => j
          case j: Char => registry(j)
        } else 1
      case c: Char =>
        if (registry(c) > 0) _b match {
          case j: Int  => j
          case j: Char => registry(j)
        } else 1
    }
  }

  case class Copy(a: String, b: Char) extends Instruction {
    val _a: AnyVal = if (a.forall(_.isLetter)) a.head else a.toInt

    def this(cmd: String) = {
      this((cmd split ' ' slice (1, 2)).mkString, cmd.split(' ').last.head)
    }

    override def toString: String = "cpy " + _a + " " + b

    override def exec: Int = _a match {
      case i: Int =>
        if (registry.keySet.contains(b)) registry(b) = i
        1
      case c: Char =>
        if (registry.keySet.contains(b) && registry.keySet.contains(c)) registry(b) = registry(c)
        1
    }
  }

  case class Inc(a: Char) extends Instruction {
    def this(cmd: String) {
      this(cmd.last)
    }

    override def toString: String = "inc " + a

    override def exec: Int = {
      registry(a) += 1
      1
    }
  }

  case class Dec(a: Char) extends Instruction {
    def this(cmd: String) {
      this(cmd.last)
    }

    override def toString: String = "dec " + a

    override def exec: Int = {
      registry(a) -= 1
      1
    }
  }

  case class Out(a: Char) extends Instruction {
    def this(cmd: String) = this(cmd.last)

    override def toString: String = "out " + a

    override def exec: Int = {
      val i = registry(a)
      (clock.toList.reverse, i) match {
        case (Nil, 0)    => clock += 0; 1
        case (1 :: t, 0) => clock += 0; 1
        case (0 :: t, 1) => clock += 1; 1
        case _           => 0
      }
    }
  }

  def parse(xs: String): Instruction = xs match {
    case s if s.startsWith("jnz") => new Jump(s)
    case s if s.startsWith("cpy") => new Copy(s)
    case s if s.startsWith("inc") => new Inc(s)
    case s if s.startsWith("dec") => new Dec(s)
    case s if s.startsWith("out") => new Out(s)
  }

  def exec(a: Int, instructions: List[Instruction]): Boolean = {
    registry('a') = a
    registry('b') = 0
    registry('c') = 0
    registry('d') = 0
    clock.clear

    @tailrec
    def inner(p: Int): Boolean = p match {
      case i if i >= instructions.length => false
      case i =>
        if (clock.length > 100) true
        else {
          val incr = instructions(i).exec
          if (incr != 0) inner(p + incr) else false
        }
    }

    inner(0)
  }

  def run(): Any = {
    def input = Source.fromResource("day25.txt").getLines.toList

    val instructions = input.map(parse)
    Iterator
      .iterate((false, 1))(a => (exec(a._2, instructions), a._2 + 1))
      .dropWhile(!_._1)
      .next
      ._2 - 1
  }

}
