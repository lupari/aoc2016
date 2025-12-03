package challenge


import scala.annotation.tailrec
import scala.io.Source

object Day20b {

  case class Range(from: Long, to: Long)

  @tailrec
  def merge(rs: List[Range], sep: List[Range] = Nil): List[Range] = rs match {
    case x :: y :: t =>
      if (y.from > x.to + 1) merge(y :: t, x :: sep)
      else merge(Range(x.from, x.to max y.to) :: t, sep)
    case _ => (rs ::: sep).reverse
  }

  @tailrec
  def whitelisted(rs: List[Range], acc: Long = 0): Long = rs match {
    case _ :: Nil    => acc
    case h :: x :: t => whitelisted(x :: t, acc + x.from - h.to - 1)
    case _           => acc
  }

  def run(): Long = {
    val input  = Source.fromResource("day20.txt").getLines.toList
    val ranges = input.map(_.split('-')).map(i => Range(i.head.toLong, i.last.toLong))
    val merged = merge(ranges.sortBy(_.from))
    whitelisted(merged)
  }

}
