package challenge

import base.Challenge

import scala.annotation.tailrec
import scala.io.Source

object Day20 extends Challenge {

  case class Range(from: Long, to: Long)

  @tailrec
  def merge(rs: List[Range], sep: List[Range] = Nil): List[Range] = rs match {
    case x :: y :: t =>
      if (y.from > x.to + 1) merge(y :: t, x :: sep)
      else merge(Range(x.from, x.to max y.to) :: t, sep)
    case _ => (rs ::: sep).reverse
  }

  override def run(): Any = {
    val input  = Source.fromResource("day20.txt").getLines.toList
    val ranges = input.map(i => i.split('-')).map(i => Range(i.head.toLong, i.last.toLong))
    val merged = merge(ranges.sortBy(_.from))
    merged.head.to + 1
  }

}
