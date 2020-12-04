package challenge

import base.Challenge

import scala.io.Source

object Day6b extends Challenge {

  override def run(): Any = {
    val input = Source.fromResource("day6.txt").getLines.toList
    (input.transpose map (x => x.groupBy(identity).mapValues(_.size).minBy(_._2)._1)).mkString
  }

}
