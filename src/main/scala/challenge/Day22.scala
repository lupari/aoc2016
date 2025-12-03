package challenge


import scala.io.Source

object Day22 {

  case class Node(y: Int, x: Int, used: Int, avail: Int) {
    def viablePair(other: Node): Boolean = used > 0 && other.avail >= used

    override def toString: String = if (used > 99) "#" else if (used == 0) "_" else "."
  }

  def run(): Int = {
    val input   = Source.fromResource("day22.txt").getLines.toList
    val pattern = """^.*-x(\d+)-y(\d+)\s+(\d+)T\s+(\d+)T\s+(\d+)T\s+(\d+)%$""".r
    val nodes = input.drop(2).map {
      case pattern(x, y, size, used, avail, load) => Node(y.toInt, x.toInt, used.toInt, avail.toInt)
    }

    val pairs = nodes.combinations(2).toList.flatMap(_.permutations.toList)
    pairs.count(p => p.head.viablePair(p.last))
  }

}
