package challenge

import scala.io.Source

import base.Challenge

object Day3b extends Challenge {

  def isValid(t: Seq[Int]): Boolean = {
    val (a, b) = t.sorted(Ordering[Int].reverse).splitAt(1)
    a.head < b.foldLeft(0)(_+_)
  }

  override def run(): Any = {
    val input = Source.fromResource("day3.txt").getLines.toList
      .map (_.trim split " +" map (_.toInt))
    val triangles = input.transpose.map(c => c.grouped(3).toList).flatten
    triangles.count(isValid(_))
  }

}
