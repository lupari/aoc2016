package challenge

import scala.io.Source

import base.Challenge

object Day20b extends Challenge {

  case class Range(val from: Long, val to: Long)

  def merge(rs: List[Range], sep: List[Range] = Nil): List[Range] = rs match {
    case x :: y :: t =>
      if (y.from > x.to + 1) merge(y :: t, x :: sep)
      else merge(Range(x.from, x.to max y.to) :: t, sep)
    case _ => (rs ::: sep).reverse
  }

  def whitelisted(rs: List[Range], acc: Long = 0): Long = rs match {
    case _ :: Nil => acc
    case h :: x :: t => whitelisted(x :: t, acc + x.from - h.to - 1)
    case _ => acc
  }

  override def run(): Any = {
    val input = Source.fromResource("day20.txt").getLines.toList
    val ranges = input.map(_.split('-')).map(i => Range(i.head.toLong, i.last.toLong))
    val merged = merge(ranges.sortBy(_.from))
    whitelisted(merged)
  }

}