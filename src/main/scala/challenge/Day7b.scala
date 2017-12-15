package challenge

import base.Challenge

import scala.io.Source

object Day7b extends Challenge {

  def hypernetParts(xs: String): List[List[Char]] = {
      val res = xs.split("\\[").toList filter
        (_.contains("]")) map (_.split("\\]") take 1)
      res.flatten map (_.toList)
    }

  def addrParts(xs: String): List[List[Char]] = {
    val res = xs.split("\\[").toList map (_.split("\\]") takeRight 1)
    res.flatten map (_.toList)
  }

  def listAbas(xs: List[Char]) = {
    for {
        i <- 0 to xs.length - 3
        t = xs.slice(i, i + 3)
        if (t(0) == t(2) && t(1) != t(0))
      }  yield t
  }

  def getBab(xs: List[Char]): String = xs match {
      case a :: b :: c :: d if (a == c && b != c) => List(b, a, b).mkString
      case _ => throw new NoSuchElementException
  }

  def isSsl (as: List[List[Char]], hs: List[List[Char]]) = {
    val abas = (as map listAbas).flatten
    abas.exists(aba => hs.exists(h => h.mkString.contains(getBab(aba))))
  }

  override def run(): Any = {
    val ips = Source.fromResource("day7.txt").getLines.toList
    val ipsWithSsl = ips filter (ip => isSsl(addrParts(ip), hypernetParts(ip)))
    ipsWithSsl.length
  }

}
