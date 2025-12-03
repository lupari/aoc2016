package challenge


import scala.io.Source

object Day6b {

  def run(): String = {
    val input = Source.fromResource("day6.txt").getLines.toList
    (input.transpose map (x => x.groupBy(identity).mapValues(_.size).minBy(_._2)._1)).mkString
  }

}
