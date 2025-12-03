package challenge


import scala.collection.mutable
import scala.io.Source

object Day12b {

  val registry: mutable.Map[Char, Int] =
    mutable.Map[Char, Int]('a' -> 0, 'b' -> 0, 'c' -> 0, 'd' -> 0)

  abstract class Instruction {
    def exec: Int
  }

  class Jump(cmd: String) extends Instruction {
    val a: AnyVal = {
      val operand = (cmd split ' ' slice (1, 2)).mkString
      if (operand.forall(_.isDigit)) operand.toInt else operand.head
    }
    val b: Int = cmd.split(' ').last.toInt

    override def exec: Int = a match {
      case i: Int  => if (i > 0) b else 1
      case c: Char => if (registry(c) > 0) b else 1
    }
  }

  class Copy(cmd: String) extends Instruction {
    val a: AnyVal = {
      val operand = (cmd split ' ' slice (1, 2)).mkString
      if (operand.forall(_.isDigit)) operand.toInt else operand.head
    }
    val b: Char = (cmd split ' ').last.head

    override def exec: Int = a match {
      case i: Int =>
        registry(b) = i
        1
      case c: Char =>
        registry(b) = registry(c)
        1
    }
  }

  class Inc(cmd: String) extends Instruction {
    val a: Char = cmd.last

    override def exec: Int = {
      registry(a) += 1
      1
    }
  }

  class Dec(cmd: String) extends Instruction {
    val a: Char = cmd.last

    override def exec: Int = {
      registry(a) -= 1
      1
    }
  }

  def parse(s: String): Instruction = s match {
    case `s` if s.startsWith("jnz") => new Jump(s)
    case `s` if s.startsWith("cpy") => new Copy(s)
    case `s` if s.startsWith("inc") => new Inc(s)
    case `s` if s.startsWith("dec") => new Dec(s)
  }

  def exec(xs: List[String]): Any = {
    var p = 0
    while (p < xs.length) {
      p += parse(xs(p)).exec
    }
  }

  def run(): Int = {
    def instructions = Source.fromResource("day12.txt").getLines.toList

    exec(instructions.map(_.trim))
    registry.keys.foreach(k => if (k != 'c') registry(k) = 0 else registry(k) = 1)
    exec(instructions.map(_.trim))
    registry('a')
  }

}
