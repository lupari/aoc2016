package challenge


import scala.collection.mutable
import scala.io.Source

object Day24b {

  case class Delta(x: Int, y: Int)

  case class Point(x: Int, y: Int, wall: Boolean, value: Int) {
    def isOfInterest: Boolean = value > -1
  }

  def getNext(p: Point, d: Delta, points: Array[Array[Point]]): Point = {
    points(p.y + d.y)(p.x + d.x)
  }

  def aStar(start: Point, goal: Point, points: Array[Array[Point]])(
      costFn: (Point, Point) => Int): Map[Point, Int] = {
    val frontier = mutable.ListBuffer[(Point, Int)]((start, 0))
    val costs    = mutable.Map[Point, Int](start -> 0)
    while (frontier.nonEmpty) {
      val current = frontier.minBy(_._2)
      frontier -= current
      if (current._1 == goal) frontier.clear
      else {
        val newCost = costs(current._1) + 1
        val neighbors = List(Delta(0, -1), Delta(0, 1), Delta(1, 0), Delta(-1, 0))
          .map(getNext(current._1, _, points))
          .filter(p => !p.wall)
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

  def dist(k: (Point, Point), m: Map[(Point, Point), Int]): Int =
    // distance is symmetric so order of two points does not matter
    if (m.contains(k)) m(k) else (m(k._2, k._1))

  def pathLength(path: List[Point], distances: Map[(Point, Point), Int]): Int =
    path.sliding(2).foldLeft(0)((a, b) => a + dist((b.head, b.last), distances))

  def run(): Int = {
    val input = Source.fromResource("day24.txt").getLines.toList
    val points: Array[Array[Point]] = input.indices.toArray.map(
      y =>
        input(y).indices.toArray.map(x => {
          val p = input(y)(x)
          Point(x, y, p == '#', if (p.isDigit) p.asDigit else -1)
        })
    )
    val placesOfInterest = points.flatten.filter(p => p.isOfInterest)
    val pairs            = placesOfInterest.combinations(2).map(p => (p.head, p.last))

    def costFn = (a: Point, b: Point) => math.abs(a.x - b.x) + math.abs(a.y - b.y)

    val distances = pairs.map(p => p -> aStar(p._1, p._2, points)(costFn)(p._2)).toMap
    val start     = placesOfInterest.find(_.value == 0).get
    val paths     = (placesOfInterest.toList diff List(start)).permutations.map(l => start +: l).toList
    paths.map(p => p :+ start).map(pathLength(_, distances)).min
  }

}
