package challenge


import scala.io.Source

object Day6 {

  def run(): String = {
    val input = Source.fromResource("day6.txt").getLines.toList
    (input.transpose map (x => x.groupBy(identity).mapValues(_.size).maxBy(_._2)._1)).mkString
  }

}
