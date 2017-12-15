package challenge

import base.Challenge

object Day16b extends Challenge {

  def dragonCurve(n: Int, s: String): String = {
    def widen(x: String): String = {
      val sb = new StringBuilder(s.length * 2 + 1).append(x).append('0')
      for {
        i <- x.indices
      } yield {
        if (x(i) == '0') sb.append('1') else sb.append('0')
      }
      sb.toString
    }
    var dc = s
    while (dc.length <= n) dc = widen(dc)
    dc take n
  }

  def checksum(s: String): String = {
    def narrow(s: String): String = {
      val cs = new StringBuilder(s.length / 2 + 1)
      for {
        i <- s.indices by 2
      } yield {
        if (s(i) == s(i + 1)) cs.append('1') else cs.append('0')
      }
      cs.toString
    }
    var cs = s
    while (cs.length % 2 == 0) cs = narrow(cs)
    cs
  }

  override def run(): Any = {
    checksum(dragonCurve(35651584, "10001001100000001"))
  }

}
