package challenge

import base.Challenge

import scala.io.Source

object Day7 extends Challenge {

  def hypernetParts(xs: String): List[List[Char]] = {
    val res = xs.split("\\[").toList filter
      (_.contains("]")) map (_.split("\\]") take 1)
    res.flatten map (_.toList)
  }

  def addrParts(xs: String): List[List[Char]] = {
    val res = xs.split("\\[").toList map (_.split("\\]") takeRight 1)
    res.flatten map (_.toList)
  }

  def hasTls(xs: List[Char]): Boolean = xs match {
    case a :: b :: c :: d :: t =>
      if (a == d && b == c && a != b) true else hasTls(b :: c :: d :: t)
    case _ => false
  }

  override def run(): Any = {
    val ips = Source.fromResource("day7.txt").getLines.toList
    val ipsWithTls = ips filter (ip => addrParts(ip).exists(hasTls) && !hypernetParts(ip).exists(hasTls))
    ipsWithTls.length
  }

}
