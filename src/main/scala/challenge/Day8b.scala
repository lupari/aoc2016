package challenge


import scala.collection.immutable
import scala.io.Source

object Day8b {

  val display: Array[Array[Int]] = Array.ofDim[Int](6, 50)

  def drawRect(cmd: String): immutable.IndexedSeq[Unit] = {
    val Array(w, h) = (cmd split " " takeRight 1).mkString.split("x") map (_.toInt)
    for {
      x <- 0 until w
      y <- 0 until h
    } yield display(y)(x) = 1
  }

  def rotate(cmd: String): Unit = {
    val Array(n) = cmd.split(" ") takeRight 1 map (_.toInt)
    val Array(z) = cmd.split(" ").slice(2, 3) map (a => a drop 2) map (_.toInt)
    val vertical = cmd.contains("column")
    val dim      = if (vertical) 6 else 50
    for (i <- 1 to n) {
      val container = Array.ofDim[Int](dim)
      for (j <- 0 until dim) {
        val next = if (j < dim - 1) j + 1 else 0
        container(next) = if (vertical) display(j)(z) else display(z)(j)
      }
      for (j <- 0 until dim) {
        if (vertical) display(j)(z) = container(j)
        else display(z)(j) = container(j)
      }
    }
  }

  def runCommand(cmd: String): Any = cmd match {
    case x if x.startsWith("rect")   => drawRect(x)
    case x if x.startsWith("rotate") => rotate(x)
  }

  def run(): String = {
    val input = Source.fromResource("day8.txt").getLines.toList
    for (cmd <- input) yield runCommand(cmd)

    println(
      display.map(_.mkString).mkString("\n")
        map {
          case '0' => ' '
          case '1' => '*'
          case _   => '\n'
        })

    "^ Should spell 'EFEYKFRFIJ' :) ^"

  }

}
