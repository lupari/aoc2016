package challenge

import java.security.MessageDigest


import scala.annotation.tailrec

object Day5 {

  def digest: MessageDigest = MessageDigest.getInstance("MD5")

  def pwdChar(s: String): Option[Char] = {
    val hash = digest.digest(s.getBytes)
    if (hash(0) == 0
        && hash(1) == 0
        && (hash(2) & 0xF0) == 0) Some(hash.map("%02x".format(_)).mkString.charAt(5))
    else None
  }

  def find(seed: String): String = {

    @tailrec
    def inner(n: Int, pwd: String): String = pwd match {
      case s if s.length == 8 => s
      case _ =>
        pwdChar(seed + n) match {
          case Some(c) => inner(n + 1, pwd + c)
          case _       => inner(n + 1, pwd)
        }
    }

    inner(0, "")
  }

  def run(): String = {
    find("cxdnnyjw")
  }

}
