package challenge


import scala.collection.mutable
import scala.collection.mutable.ListBuffer
import scala.io.Source

object Day23 {

  val registry: mutable.Map[Char, Int] =
    mutable.Map[Char, Int]('a' -> 0, 'b' -> 0, 'c' -> 0, 'd' -> 0)
  val instructions: ListBuffer[Instruction] = mutable.ListBuffer[Instruction]()

  abstract class Instruction {
    def exec(args: AnyVal*): Int
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

    override def exec(args: AnyVal*): Int = _a match {
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

    override def exec(args: AnyVal*): Int = _a match {
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

    override def exec(args: AnyVal*): Int = {
      registry(a) += 1
      1
    }
  }

  case class Dec(a: Char) extends Instruction {
    def this(cmd: String) {
      this(cmd.last)
    }

    override def toString: String = "dec " + a

    override def exec(args: AnyVal*): Int = {
      registry(a) -= 1
      1
    }
  }

  case class Multi(a: Char, b: Char, c: Char) extends Instruction {
    def this(cmd: Array[String]) {
      this(cmd.head.head, cmd.slice(1, 2).head.head, cmd.last.head)
    }

    def this(cmd: String) {
      this(cmd split ' ' drop 1)
    }

    override def toString: String = "mlt " + a + " " + b + " " + c

    override def exec(args: AnyVal*): Int = {
      registry(c) = registry(a) * registry(b)
      1
    }
  }

  case class Toggle(a: Char) extends Instruction {
    def this(cmd: String) {
      this(cmd.last)
    }

    def toggleInstruction(x: Instruction): Instruction = x match {
      case i: Inc    => Dec(i.a)
      case i: Dec    => Inc(i.a)
      case i: Toggle => Inc(i.a)
      case i: Jump   => Copy(i.a, i.b.head)
      case i: Copy   => Jump(i.a, i.b.toString)
    }

    override def toString: String = "tgl " + a

    override def exec(args: AnyVal*): Int = args(0) match {
      case cp: Int =>
        val i = cp + registry(a)
        if (0 <= i && i < instructions.length) {
          instructions(i) = toggleInstruction(instructions(i))
        }
        1
    }
  }

  def parse(xs: String): Instruction = xs match {
    case s if s.startsWith("jnz") => new Jump(s)
    case s if s.startsWith("cpy") => new Copy(s)
    case s if s.startsWith("inc") => new Inc(s)
    case s if s.startsWith("dec") => new Dec(s)
    case s if s.startsWith("tgl") => new Toggle(s)
    case s if s.startsWith("mlt") => new Multi(s)
  }

  def exec(): Any = {
    var p = 0
    while (p < instructions.length) {
      p += instructions(p).exec(p)
    }
  }

  def run(): Int = {
    val input = Source.fromResource("day23.txt").getLines.toList
    instructions ++= input.map(parse)
    registry('a') = 7
    exec()
    registry('a')
  }

}
