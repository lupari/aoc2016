package challenge


import scala.io.Source

object Day3b {

  def isValid(t: Seq[Int]): Boolean = {
    val (a, b) = t.sorted(Ordering[Int].reverse).splitAt(1)
    a.head < b.sum
  }

  def run(): Int = {
    val input = Source
      .fromResource("day3.txt")
      .getLines
      .toList
      .map(_.trim split " +" map (_.toInt))
    val triangles = input.transpose.flatMap(_.grouped(3).toList)
    triangles.count(isValid(_))
  }

}
