package challenge


import scala.io.Source

object Day7b {

  def hypernetParts(xs: String): List[List[Char]] = {
    val res = xs.split("\\[").toList filter
      (_.contains("]")) map (_.split("\\]") take 1)
    res.flatten map (_.toList)
  }

  def addrParts(xs: String): List[List[Char]] = {
    val res = xs.split("\\[").toList map (_.split("\\]") takeRight 1)
    res.flatten map (_.toList)
  }

  def listAbas(xs: List[Char]): Seq[List[Char]] = {
    for {
      i <- 0 to xs.length - 3
      t = xs.slice(i, i + 3)
      if t.head == t(2) && t(1) != t.head
    } yield t
  }

  def getBab(xs: List[Char]): String = xs match {
    case a :: b :: c :: d if a == c && b != c => List(b, a, b).mkString
    case _                                    => throw new NoSuchElementException
  }

  def isSsl(as: List[List[Char]], hs: List[List[Char]]): Boolean = {
    val abas = as flatMap listAbas
    abas.exists(aba => hs.exists(h => h.mkString.contains(getBab(aba))))
  }

  def run(): Int = {
    val ips        = Source.fromResource("day7.txt").getLines.toList
    val ipsWithSsl = ips filter (ip => isSsl(addrParts(ip), hypernetParts(ip)))
    ipsWithSsl.length
  }

}
