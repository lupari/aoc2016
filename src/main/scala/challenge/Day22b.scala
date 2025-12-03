package challenge


import scala.io.Source

object Day22b {

  case class Node(y: Int, x: Int, used: Int, avail: Int) {
    def viablePair(other: Node): Boolean = used > 0 && other.avail >= used

    override def toString: String = if (used > 99) "#" else if (used == 0) "_" else "."
  }

  def run(): String = {
    val input   = Source.fromResource("day22.txt").getLines.toList
    val pattern = """^.*-x(\d+)-y(\d+)\s+(\d+)T\s+(\d+)T\s+(\d+)T\s+(\d+)%$""".r
    val nodes = input.drop(2).map {
      case pattern(x, y, size, used, avail, load) => Node(y.toInt, x.toInt, used.toInt, avail.toInt)
    }
    val rows = nodes.groupBy(_.y)
    val grid = rows.keys.toList.indices.map(rows(_))
    // Display the damn grid. Lets solve this by hand from there.
    // Should find shortest path from '_' to top-right corner, then from there to top-left
    println(grid.map(_.mkString(" ")).mkString("\n"))
    "^ Check the grid and take it from there ^"
  }

}
