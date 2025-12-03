package challenge


import scala.io.Source

object Day4b {

  def checksum(s: String): String = s.reverse.slice(1, 6).reverse

  def sectorId(s: String): Int = s.reverse.slice(7, 10).reverse.toInt

  def name(s: String): String = (s take s.length - 11) filterNot (_ == '-')

  def occurrences(s: String): List[Char] =
    s.groupBy(identity)
      .map(x => (x._1, x._2.length))
      .toList
      .sortWith((a, b) => if (a._2 == b._2) a._1 < b._1 else a._2 > b._2) map (_._1)

  def generateChecksum(s: String): String = {
    (occurrences(name(s)) take 5).mkString
  }

  def isValid(s: String): Boolean = checksum(s) == generateChecksum(s)

  def decrypt(name: String, sectorId: Int): String = {
    val alphabet = "abcdefghijklmnopqrstuvwxyz"

    def rotateLeft(seq: Seq[Char], i: Int): Seq[Char] = {
      val (first, last) = seq.splitAt(i % seq.size)
      last ++ first
    }

    name.map(c => rotateLeft(alphabet, sectorId)(alphabet.indexOf(c)))
  }

  def run(): Int = {
    val codes = Source.fromResource("day4.txt").getLines.toList
    val nr    = codes.find(c => decrypt(name(c), sectorId(c)) startsWith "northpole")
    sectorId(nr.get)
  }

}
