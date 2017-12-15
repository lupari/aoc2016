package challenge

import scala.io.Source

import base.Challenge

object Day3 extends Challenge {

  def isValid(t: Seq[Int]): Boolean = {
    val (a, b) = t.sorted(Ordering[Int].reverse).splitAt(1)
    a.head < b.foldLeft(0)(_+_)
  }

  override def run(): Any = {
    val triangles = Source.fromResource("day3.txt").getLines.toList
      .map (_.trim split " +" map (_.toInt))
    triangles.count(isValid(_))
  }

}
