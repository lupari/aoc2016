package challenge


object Day16 {

  def dragonCurve(n: Int, s: String): String = {
    def dc(s: String) = {
      s + '0' + s.reverse.replace('1', '2').replace('0', '1').replace('2', '0')
    }

    lazy val generator: Stream[String] = s #:: generator.map(dc)
    generator.find(_.length > n).get.take(n)
  }

  def checksum(s: String): String = {
    def compress(s: String): String = {
      val cs = new StringBuilder(s.length / 2 + 1)
      for {
        i <- s.indices by 2
      } yield {
        if (s(i) == s(i + 1)) cs.append('1') else cs.append('0')
      }
      cs.toString
    }

    lazy val generator: Stream[String] = s #:: generator.map(compress)
    generator.find(_.length % 2 == 1).head.mkString
  }

  def run(): String = {
    checksum(dragonCurve(272, "10011111011011001"))
  }

}
