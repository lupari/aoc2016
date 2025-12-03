package challenge

import java.security.MessageDigest

import scala.annotation.tailrec
import scala.collection.mutable
import scala.util.matching.Regex

object Day14b {

  def match3: Regex = "(.)\\1{2,}".r
  def match5: Regex = "(.)\\1{4,}".r

  def md5(s: String): String = {
    val digest = MessageDigest.getInstance("MD5")
    val hexChars = Array('0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f')
    
    def hash(h: String): String = {
      digest.reset()
      val bytes = digest.digest(h.getBytes)
      val sb = new StringBuilder(bytes.length * 2)
      var i = 0
      while (i < bytes.length) {
        val b = bytes(i) & 0xff
        sb.append(hexChars(b >>> 4))
        sb.append(hexChars(b & 0x0f))
        i += 1
      }
      sb.toString
    }

    Iterator.iterate(s)(hash).drop(2017).next
  }

  def findKeys(salt: String): List[Int] = {

    val triplets = mutable.Map[Char, List[Int]]().withDefaultValue(Nil)

    @tailrec
    def inner(id: Int, acc: List[Int]): List[Int] = acc match {
      case x if x.length >= 64 => acc
      case x =>
        val hash = md5(salt + id)
        match5.findFirstIn(hash) match {
          case Some(q) =>
            val matches = triplets(q.head).filter(_ + 1000 > id)
            triplets(q.head) = List(id)
            inner(id + 1, x ::: matches)
          case _ =>
            match3.findFirstIn(hash) match {
              case Some(t) =>
                triplets(t.head) = triplets(t.head) :+ id
                inner(id + 1, x)
              case _ =>
                inner(id + 1, x)
            }
        }
    }

    inner(0, Nil).sorted
  }

  def run(): Int = {
    (findKeys("yjdafjpo") take 64).last
  }

}
