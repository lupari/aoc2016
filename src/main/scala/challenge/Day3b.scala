package challenge

import base.Challenge

import scala.io.Source

object Day3b extends Challenge {

  def isValid(t: Seq[Int]): Boolean = {
    val (a, b) = t.sorted(Ordering[Int].reverse).splitAt(1)
    a.head < b.sum
  }

  override def run(): Any = {
    val input = Source
      .fromResource("day3.txt")
      .getLines
      .toList
      .map(_.trim split " +" map (_.toInt))
    val triangles = input.transpose.flatMap(_.grouped(3).toList)
    triangles.count(isValid(_))
  }

}
