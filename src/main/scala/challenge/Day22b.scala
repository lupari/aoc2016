package challenge

import scala.io.Source

import base.Challenge

object Day22b extends Challenge {

  case class Node(y: Int, x: Int, used: Int, avail: Int) {
    def viablePair(other: Node): Boolean = used > 0 && other.avail >= used
    override def toString: String = if (used > 99) "#" else if (used == 0) "_" else "."
  }

  override def run(): Any = {
    val input = Source.fromResource("22.txt").getLines.toList
    val pattern = """^.*-x(\d+)-y(\d+)\s+(\d+)T\s+(\d+)T\s+(\d+)T\s+(\d+)%$""".r
    val nodes = input.drop(2).map {
      case pattern(x, y, size, used, avail, load) => Node(y.toInt, x.toInt, used.toInt, avail.toInt)
    }
    val rows = nodes.groupBy(_.y)
    val grid = rows.keys.toList.indices.map(rows(_))
    // Display the damn grid. Lets solve this by hand from there.
    grid.map(_.mkString(" ")).mkString("\n")
  }

}
