package challenge

import scala.annotation.tailrec
import scala.io.Source

object Day1b {

  def getRoute(xs: List[(Char, Int)]): List[(Int, Int)] = {

    def nextDir(dir: Char, turn: Char): Char = dir match {
      case 'E' => if (turn == 'R') 'S' else 'N'
      case 'N' => if (turn == 'R') 'E' else 'W'
      case 'W' => if (turn == 'R') 'N' else 'S'
      case 'S' => if (turn == 'R') 'W' else 'E'
    }

    def updateCoords(dir: Char, n: Int, c: (Int, Int)): List[(Int, Int)] = dir match {
      case 'E' => (1 to n).map(i => (c._1 + i, c._2)).toList
      case 'N' => (1 to n).map(i => (c._1, c._2 + i)).toList
      case 'W' => (1 to n).map(i => (c._1 - i, c._2)).toList
      case 'S' => (1 to n).map(i => (c._1, c._2 - i)).toList
    }

    @tailrec
    def drive(xs: List[(Char, Int)], dir: Char, acc: List[(Int, Int)]): List[(Int, Int)] =
      xs match {
        case Nil => acc
        case h :: t =>
          val newDir    = nextDir(dir, h._1)
          val newCoords = updateCoords(newDir, h._2, acc.last)
          drive(t, newDir, acc ::: newCoords)
      }

    drive(xs, 'N', List((0, 0)))
  }

  @tailrec
  def findRevisit(xs: List[(Int, Int)], acc: List[(Int, Int)]): (Int, Int) = xs match {
    case h :: t => if (acc.count(_ == h) == 1) h else findRevisit(t, acc :+ h)
    case Nil    => throw new NoSuchElementException
  }

  def run(): Int = {
    val lines          = Source.fromResource("day1.txt").mkString
    val blocks         = lines.trim.split(", ") map (b => (b.head, b.drop(1).toInt))
    val route          = getRoute(blocks.toList)
    val revisitedFirst = findRevisit(route, Nil)
    math.abs(revisitedFirst._1) + math.abs(revisitedFirst._2)
  }

}
