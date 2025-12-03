package challenge


import scala.io.Source

object Day3 {

  def isValid(t: Seq[Int]): Boolean = {
    val (a, b) = t.sorted(Ordering[Int].reverse).splitAt(1)
    a.head < b.sum
  }

  def run(): Int = {
    val triangles = Source
      .fromResource("day3.txt")
      .getLines
      .toList
      .map(_.trim split " +" map (_.toInt))
    triangles.count(isValid(_))
  }

}
