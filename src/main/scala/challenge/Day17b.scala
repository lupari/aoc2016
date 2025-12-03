package challenge

import java.security.MessageDigest


import scala.collection.mutable

object Day17b {

  def digest: MessageDigest = MessageDigest.getInstance("MD5")

  def dir: Map[Char, Int] = Map[Char, Int]('U' -> 0, 'D' -> 1, 'L' -> 2, 'R' -> 3)

  def md5(s: String): String = {
    digest.digest(s.getBytes).map("%02x".format(_)).mkString
  }

  case class Point(x: Int, y: Int) {
    def withinBoundaries: Boolean = x > -1 && x < 4 && y > -1 && y < 4

    def +(c: Char): Point = c match {
      case 'U' => Point(x, y - 1)
      case 'L' => Point(x - 1, y)
      case 'D' => Point(x, y + 1)
      case 'R' => Point(x + 1, y)
    }
  }

  def nextPoints(p: Point, dirs: List[Char], seed: String): Set[(Point, Char)] = {
    (dir.keys zip md5(seed + dirs.mkString) take 4)
      .filter(d => d._2 > 'a')
      .map(d => (p + d._1, d._1))
      .filter(_._1.withinBoundaries)
      .toSet
  }

  def bfs(start: Point, goal: Point, seed: String): List[List[Char]] = {
    val routes = mutable.ListBuffer[List[Char]]()
    val queue  = mutable.Queue[(Point, List[Point], List[Char])]((start, List(start), Nil))
    while (queue.nonEmpty) {
      val (point, path, dirs) = queue.dequeue
      val neighbors           = nextPoints(point, dirs, seed)
      for (neighbor <- neighbors) {
        val (nextPoint, dir) = neighbor
        if (nextPoint == goal) routes += dirs :+ dir
        else queue.enqueue((nextPoint, path :+ nextPoint, dirs :+ dir))
      }
    }
    routes.toList
  }

  def run(): Int = {
    val seed         = "lpvhkcbi"
    val (start, end) = (Point(0, 0), Point(3, 3))
    val paths        = bfs(start, end, seed)
    paths.maxBy(p => p.length).length
  }

}
