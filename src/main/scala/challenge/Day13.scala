package challenge


import scala.collection.mutable

object Day13 {

  case class Delta(x: Int, y: Int)

  case class Point(x: Int, y: Int) {
    def withinBoundaries: Boolean = x > -1 && y > -1

    def +(delta: Delta): Point = Point(x + delta.x, y + delta.y)

    def isWall: Boolean = {
      val bin = (x * x + 3 * x + 2 * x * y + y + y * y + 1362).toBinaryString
      bin.count(_ == '1') % 2 == 1
    }
  }

  def aStar(start: Point, goal: Point)(costFn: (Point, Point) => Int): Map[Point, Int] = {
    val frontier = mutable.ListBuffer[(Point, Int)]((start, 0))
    val costs    = mutable.Map[Point, Int](start -> 0)
    while (frontier.nonEmpty) {
      val current = frontier.minBy(_._2)
      frontier -= current
      if (current._1 == goal) frontier.clear
      else {
        val newCost = costs(current._1) + 1
        val neighbors = List(Delta(0, -1), Delta(0, 1), Delta(1, 0), Delta(-1, 0))
          .map(current._1 + _)
          .filter(p => p.withinBoundaries && !p.isWall)
        for (next <- neighbors) {
          if (!costs.contains(next) || newCost < costs(next)) {
            costs(next) = newCost
            val priority = newCost + costFn(goal, next)
            frontier += ((next, priority))
          }
        }
      }
    }
    costs.toMap
  }

  def run(): Int = {
    val (start, end) = (Point(1, 1), Point(31, 39))
    val results      = aStar(start, end)((a, b) => math.abs(a.x - b.x) + math.abs(a.y - b.y))
    results(end)
  }

}
