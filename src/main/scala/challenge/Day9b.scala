package challenge

import base.Challenge

import scala.io.Source

object Day9b extends Challenge {

  def decompressedLength(xs: List[Char]): Long = {

    def getMarker(xs: List[Char]): (Int, Int, Int) = {
      val clause = xs.mkString.split(')').head
      val Array(a, b) = clause split 'x' map (_.toInt)
      (a, b, clause.length + 1)
    }

    def accumulator(xs: List[Char], acc: Long): Long = xs match {
      case Nil => acc
      case '(' :: t =>
        val marker = getMarker(t)
        val (content, remainder) = t drop marker._3 splitAt marker._1
        val scope: Long = accumulator(content, 0)
        accumulator(remainder, acc + marker._2 * scope)
      case _ :: t => accumulator(t, acc + 1)
    }

    accumulator(xs, 0)
  }

  override def run(): Any = {
    val input = Source.fromResource("day9.txt")
      .getLines.mkString.replaceAll("\\s", "").toList
    decompressedLength(input)
  }

}
