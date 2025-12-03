package challenge

import java.security.MessageDigest

import scala.annotation.tailrec

object Day5b {

  def digest: MessageDigest = MessageDigest.getInstance("MD5")

  def pwdChar(s: String): Option[(Char, Char)] = {
    val hash = digest.digest(s.getBytes)
    if (hash(0) == 0 && hash(1) == 0 && (hash(2) & 0xF0) == 0) {
      val hashed = hash.map("%02x".format(_)).mkString
      Some((hashed.charAt(5), hashed.charAt(6)))
    } else None
  }

  def find(seed: String): String = {
    @tailrec
    def inner(n: Int, m: Map[Int, Char]): String = m match {
      case s if s.size == 8 => (0 to 7).map(s(_)).mkString
      case _ =>
        pwdChar(seed + n) match {
          case Some(pair) =>
            val key = pair._1.asDigit
            if (!m.keySet.contains(key) && key < 8) inner(n + 1, m + (key -> pair._2))
            else inner(n + 1, m)
          case _ => inner(n + 1, m)
        }
    }

    inner(0, Map[Int, Char]())
  }

  def run(): String = {
    find("cxdnnyjw")
  }

}
